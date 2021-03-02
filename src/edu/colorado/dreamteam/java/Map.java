package edu.colorado.dreamteam.java;

//TODO: add constructor for map so we can test the placing of ships
//TODO: same coordinate can get attacked multiple times possibly
//TODO: figure out better way to update the map and store all of the information
public class Map {
    private int board[][];//Create a 2d array
    private Ship ships[];
    private int startShips; //The number of ships present at the start of the game
    private int numShips;
    private int sonarPulsesLeft;

    public Map(){
        board = new int[10][10];
        ships = new Ship[3];
        numShips = 0;
        startShips = 0;
        sonarPulsesLeft = 2;
        for( int i=0; i < 10; i++) { //Populate map with 0s then update when person inputs value, might need 2 of these
            for (int j = 0; j < 10; j++) {
                board[i][j] = 0;
            }
        }
    }

    public boolean hasShips() {
        return numShips != 0;
    }

    public boolean getAttacked(Coordinate attack, Weapon weapon) {
        if(weapon.isSonarPulse()) {
            //Check if there are sonar pulses left
            if(sonarPulsesLeft <= 0) {
                System.out.println("You are out of sonar pulses!");
                return false;
            }
            //Check if at least one ship has been destroyed yet
            if(startShips == numShips) {
                System.out.println("You need to destroy a ship first!");
                return false;
            }

            int x = attack.getX();
            int y = attack.getY();
            int sonarBoard[][] = new int[10][10];
            for( int i=0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    sonarBoard[i][j] = 0;
                }
            }
            for(int i = x-1; i <= x+1; i++) {
                for(int j = y-1; j <= y+1; j++) {
                    if(i >= 0 && i < 10 && j >= 0 && j < 10) {
                        if(board[i][j] != 0) sonarBoard[i][j] = 1;
                    }
                }
            }
            if(y-2 >= 0) { //This is the top most point of the diamond sonar pulse
                if(board[x][y-2] != 0) sonarBoard[x][y-2] = 1;
            }
            if(x-2 >= 0) { //This is the left most point of the diamond sonar pulse
                sonarBoard[x-2][y] = board[x-2][y];
                if(board[x][y-2] != 0) sonarBoard[x][y-2] = 1;
            }
            if(y+2 >= 0) { //This is the bottom most point of the diamond sonar pulse
                sonarBoard[x][y+2] = board[x][y+2];
                if(board[x][y-2] != 0) sonarBoard[x][y-2] = 1;
            }
            if(x+2 >= 0) { //This is the right most point of the diamond sonar pulse
                sonarBoard[x+2][y] = board[x+2][y];
                if(board[x][y-2] != 0) sonarBoard[x][y-2] = 1;
            }
            getMaps(sonarBoard);
            sonarPulsesLeft--;
            return true;
        } else {
            for(int i = 0; i < numShips; i++) {
                if(ships[i].getAttacked(attack) && ships[i].isSunk()) numShips--;
            }

            if(board[attack.getX()][attack.getY()] == 1) {//check if there is a ship there.
                board[attack.getX()][attack.getY()] = 2;//if so update map.//we can set2 for attacked ship
//                System.out.println("ITS A HIT");
                return true;//which means a hit.
            }
            else if(board[attack.getX()][attack.getY()] == 2){
//                System.out.println("Already Hit!");
                return false;
            }
            else if(board[attack.getX()][attack.getY()] == 3){
                //Print out that they already miss
//                System.out.println("Already Missed!");
                return false;
            }
            else{
                board[attack.getX()][attack.getY()] = 3;
//                System.out.println("Missed!");
                return false;//which is a miss.
            }
        }
    }
    //Set ships on map on the given locations!
    public boolean placeShips(Coordinate loc, int m, char v) {//Go through coordinates and set those on the map =1
        //Takes in a coordinate and it sets those values to 1;
        //limit user, to down or sideways.
        //V for vertical, H for Horizontal.
        //M for Minesweeper=2 cells, D for Destroyer=3 cells, B for Battleship=4 cells.

        if(v=='V'){
            if(loc.getY() + m < 9) {
                Coordinate coors[] = new Coordinate[m];
                for(int i = 0; i < m; i++) {
                    coors[i] = new Coordinate(loc.getX() + i, loc.getY());
                }
                Ship ship = new Ship("ship"+numShips, m, coors);
                for(int i = 0; i < numShips; i++) {
                    if(ships[i].overlaps(ship)) return false;
                }
                ships[numShips++] = ship;
                for(int i=0; i<m; i++){
                    board[loc.getX()+i][loc.getY()] = 1;
                }
                startShips++;
                return true;
            }
        }
        else {
            if(loc.getX() + m < 9) {
                Coordinate coors[] = new Coordinate[m];
                for(int i = 0; i < m; i++) {
                    coors[i] = new Coordinate(loc.getX(), loc.getY()+i);
                }
                Ship ship = new Ship("ship"+numShips, m, coors);
                for(int i = 0; i < numShips; i++) {
                    if(ships[i].overlaps(ship)) return false;
                }
                ships[numShips++] = ship;
                for(int i=0; i<m; i++) {
                    board[loc.getX()][loc.getY() + i] = 1;
                }

                startShips++;
                return true;
            }
        }
        return false;
    }
    private void updateMap() {
        int board1[][] = new int[10][10];
        for(int i=0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board1[i][j] = 0;
            }
        }

        for(Ship ship : ships) {
            for(Coordinate c : ship.getCoordinates()) {
                if(c.wasUsed()) {
                    board1[c.getX()][c.getY()] = 1;
                }
            }
        }
    }

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
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }
    //Return a specific array!
    public void getMaps(int board[][]){
        System.out.printf("   ");
        for (int j = 0; j < 10; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();
        for( int i=0; i < 10; i++) { //Populate map with 0s then update when person inputs value, might need 2 of these
            System.out.printf("%2d ", i);
            for (int j = 0; j < 10; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }
}
