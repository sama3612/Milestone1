package edu.colorado.dreamteam.java;

//TODO: add constructor for map so we can test the placing of ships
//TODO: same coordinate can get attacked multiple times possibly
//TODO: figure out better way to update the map and store all of the information
public class Map {
//    private int board[][];//Create a 2d array
    private Coordinate board[][];
    private Ship ships[];
    private int numShips;
    private boolean firstShipSunk;
    private int sonarPulsesLeft;

    public Map(){
        board = new Coordinate[10][10];
        ships = new Ship[4];
        numShips = 0;
        sonarPulsesLeft = 2;
        firstShipSunk = false;
        for( int i=0; i < 10; i++) { //Populate map with 0s then update when person inputs value, might need 2 of these
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Coordinate(i,j);
            }
        }
    }

    public boolean hasShips() {
        return numShips != 0;
    }

//TODO: add check for if the ship gets sunk and update numShips accordingly
    public boolean getAttacked(int row, int col, Weapon weapon) {
        if(weapon.getWeaponType() == "sonar_pulse") {
            //Check if there are sonar pulses left
            if(sonarPulsesLeft <= 0) {
                System.out.println("You are out of sonar pulses!");
                return false;
            }
            //Check if at least one ship has been destroyed yet
            if(!firstShipSunk) {
                System.out.println("You need to destroy a ship first!");
                return false;
            }

            String sonarBoard[][] = new String[10][10];
            for( int i=0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    sonarBoard[i][j] = "0";
                }
            }
            for(int i = row-1; i <= row+1; i++) {
                for(int j = col-1; j <= col+1; j++) {
                    if(i >= 0 && i < 10 && j >= 0 && j < 10) {
                        sonarBoard[i][j] = board[i][j].toString();
                    }
                }
            }
            if(col-2 >= 0) { //This is the top most point of the diamond sonar pulse
                sonarBoard[row][col-2] = board[row][col-2].toString();
            }
            if(row-2 >= 0) { //This is the top most point of the diamond sonar pulse
                sonarBoard[row-2][col] = board[row-2][col].toString();
            }
            if(col+2 >= 0) { //This is the top most point of the diamond sonar pulse
                sonarBoard[row][col+2] = board[row][col+2].toString();
            }
            if(row+2 >= 0) { //This is the top most point of the diamond sonar pulse
                sonarBoard[row+2][col] = board[row+2][col].toString();
            }
            getSonarMaps(sonarBoard);
            sonarPulsesLeft--;
            return true;
        }
        else {
            boolean returnValue = false;
            if(weapon.getWeaponType() == "space_laser") {
                if (!firstShipSunk) {
                    System.out.println("You need to destroy a ship first!");
                    return false;
                }
                else {
                    if(board[row][col].getBelowSurfaceStatus() == Coordinate.Status.SHIP || board[row][col].getBelowSurfaceStatus() == Coordinate.Status.CAPTAINQ || board[row][col].getBelowSurfaceStatus() == Coordinate.Status.FAKEEMPTY) {
                        for(int i = 0; i < numShips; i++) {
                            if(ships[i].getAttacked(row,col)) {
                                if(ships[i].isSunk()){
                                    numShips--;
                                    firstShipSunk = true;
                                }
                                returnValue = true;
                            }
                        }
                    }
                }
            }
            if(board[row][col].getStatus() == Coordinate.Status.SHIP || board[row][col].getStatus() == Coordinate.Status.CAPTAINQ || board[row][col].getStatus() == Coordinate.Status.FAKEEMPTY) {
                for(int i = 0; i < numShips; i++) {
                    if(ships[i].getAttacked(row,col)) {
                        if(ships[i].isSunk()){
                            numShips--;
                            firstShipSunk = true;
                        }
                        returnValue = true;
                    }
                }
            } else if (returnValue == true) {
                return true;
            } else if(board[row][col].getStatus() == Coordinate.Status.HIT) {
                    System.out.println("This spot was attacked and Hit already!");
            } else if(board[row][col].getStatus() == Coordinate.Status.MISS){
                System.out.println("This spot was attacked and Missed already!");
            } else {
                board[row][col].setStatus(Coordinate.Status.MISS);
                System.out.println("That was a Miss!");
            }
            return false;
        }
    }
    //Set ships on map on the given locations!
    public boolean placeShips(int row, int col, int m, char v, boolean submerged, String name) { //Go through coordinates and set those on the map =1
        //Takes in a coordinate and it sets those values to 1;
        //limit user, to down or sideways.
        //V for vertical, H for Horizontal.
        //M for Minesweeper=2 cells, D for Destroyer=3 cells, B for Battleship=4 cells
        assert(row >= 0);
        assert(col >= 0);
        Coordinate coors[];
        if(name == "submarine") {
            coors = new Coordinate[m+1];
        }
        else {
            coors = new Coordinate[m];
        }
        if(v=='V'){
            if(row + m < 9) {
                for(int i = 0; i < m; i++) {
                    coors[i] = board[row+i][col];
                }
            } else {

                return false;
            }
        } else {
            if(col + m < 9) {
                for(int i = 0; i < m; i++) {
                    coors[i] = board[row][col+i];
                }
            } else {
                return false;
            }
        }
        if(name == "submarine") {
            if(v=='V') {
                if(col + 1 < 9){
                    coors[m]=board[row+2][col+1];
                }
                else {
                    return false;
                }
            }
            else {
                if(row - 1 >= 0){
                    coors[m]=board[row-1][col+2];
                }
                else {
                    return false;
                }
            }
        }


        Ship ship = new Ship(name, m, coors, submerged);
        if(!submerged){
            for(int i = 0; i < numShips; i++) {
                if(ships[i].overlaps(ship)) return false;
            }
            for(Coordinate c : ship.getCoordinates()) {
                c.setStatus(Coordinate.Status.SHIP);
            }
        }
        else {
            for(Coordinate c : ship.getCoordinates()) {
                c.setBelowSurfaceStatus(Coordinate.Status.SHIP);
            }
        }
        ships[numShips++] = ship;
        //Initialize coordinates as SHIP
        ship.setCaptainQuart();
        return true;
    }
//TODO: Update this getMaps() with the new definition of board using enumerations
    //Return the map array!
    public void getMaps(){
        System.out.printf("   ");
        for (int j = 0; j < 10; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();
        for( int i=0; i < 10; i++) { //Populate map with 0s then update when person inputs value, might need 2 of these
            System.out.printf("%2d ", i);
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }
    //Return a specific array!
    public void getSonarMaps(String board[][]){
        System.out.printf("   ");
        for (int j = 0; j < 10; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();
        for( int i=0; i < 10; i++) { //Populate map with 0s then update when person inputs value, might need 2 of these
            System.out.printf("%2d ", i);
            for (int j = 0; j < 10; j++) {
                System.out.printf("%2s ", board[i][j]);
            }
            System.out.println();
        }
    }
}
