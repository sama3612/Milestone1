package edu.colorado.dreamteam.java;
import java.util.*;

/**
 * Map class that controls the board for each player
 */
public class Map {
    private Coordinate board[][];
    private Ship ships[];
    private Stack<Character> Undostack;
    private Stack<Character> Redostack;
    private int numShips;
    private boolean firstShipSunk;
    private int sonarPulsesLeft;
    private boolean disabled;
    private int points;
    private boolean used_once;

    /**
     * Map constructor that sets up the initial map with the default initial values
     */
    public Map(){
        Undostack = new Stack<Character>();
        Redostack = new Stack<Character>();
        board = new Coordinate[10][10];
        ships = new Ship[5];
        numShips = 0;
        points=0;
        sonarPulsesLeft = 2;
        firstShipSunk = false;
        used_once = false;
        for( int i=0; i < 10; i++) { //Populate map with 0s then update when player inputs value
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Coordinate(i,j);
            }
        }
    }

    /**
     * Returns the number of points that this Map has based on predefined points system
     * @return
     */
    public int getPoints(){
        return points;
    }

    /**
     * Setter for the number of points that the map system has
     * @param points
     */
    public void setPoints(int points){
        this.points=points;
    }

    /**
     * Returns true if there are any ships left alive on the map
     * @return
     */
    public boolean hasShips() {
        return numShips != 0;
    }

    /**
     * If a Stopper is used, disable movement for the entire fleet of ships
     * @return
     */
    public boolean stopper(){
        disabled = true;
        return true;
    }

    /**
     * Attack the specified coordinate with a sonar pulse
     * @param row
     * @param col
     * @return
     */
    public boolean sonarPulse(int row, int col){
        if(sonarPulsesLeft <= 0) {
            System.out.println("You are out of sonar pulses!");
            return false;
        }
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
        if(row-2 >= 0) {
            sonarBoard[row-2][col] = board[row-2][col].toString();
        }
        if(col+2 >= 0) {
            sonarBoard[row][col+2] = board[row][col+2].toString();
        }
        if(row+2 >= 0) {
            sonarBoard[row+2][col] = board[row+2][col].toString();
        }
        getSonarMaps(sonarBoard);
        sonarPulsesLeft--;
        return true;
    }

    /**
     * Attack the specified coordinate with a Space Laser
     * @param row
     * @param col
     * @param returnValue
     */
    public void spaceLaser(int row, int col, boolean returnValue) {
        if (board[row][col].getBelowSurfaceStatus() == Coordinate.Status.SHIP || board[row][col].getBelowSurfaceStatus() == Coordinate.Status.CAPTAINQ || board[row][col].getBelowSurfaceStatus() == Coordinate.Status.FAKEEMPTY) {
            for (int i = 0; i < numShips; i++) {
                if (ships[i].getAttackedBelow(row, col)) {
                    System.out.println("That was a Hit!");
                    if (ships[i].isSunk()) {
                        points += 5;
                        numShips--;
                    }
                    returnValue = true;
                }
            }
        }
    }

    /**
     * Attack a certain coordinate with a specified weapon
     * @param row
     * @param col
     * @param weapon
     * @return
     */
    public boolean getAttacked(int row, int col, Weapon weapon) {
        if (board[row][col].getStatus() == Coordinate.Status.BLU && weapon.getWeaponType() != "sonar_pulse") {
            System.out.println("You hit blucifer, your ship is about to get recked!!");
            points -= 10;
            return true;
        }
        if (weapon.getWeaponType() == "stopper") {
            return stopper();
        }
        else if (weapon.getWeaponType().equals("sonar_pulse")) {
            return sonarPulse(row, col);
        }
        else {
            boolean returnValue = false;
            if (weapon.getWeaponType().equals("space_laser")) {
                if (!firstShipSunk) {
                    System.out.println("You need to destroy a ship first!");
                    return false;
                } else {
                    spaceLaser(row, col, returnValue);
                }
            }
            if (board[row][col].getStatus() == Coordinate.Status.SHIP || board[row][col].getStatus() == Coordinate.Status.CAPTAINQ || board[row][col].getStatus() == Coordinate.Status.FAKEEMPTY) {

                for (int i = 0; i < numShips; i++) {
                    if (ships[i].getAttacked(row, col)) {
                        System.out.println("That was a Hit!");

                        if (ships[i].isSunk()) {
                            points += 5;
                            numShips--;
                            firstShipSunk = true;
                        }
                        returnValue = true;
                    }
                }
            } else if (board[row][col].getStatus() == Coordinate.Status.HIT) {
                System.out.println("This spot was attacked and Hit already!");
            } else if (board[row][col].getStatus() == Coordinate.Status.MISS) {
                System.out.println("This spot was attacked and Missed already!");
            } else {
                board[row][col].setStatus(Coordinate.Status.MISS);
                System.out.println("That was a Miss!");
            }
            return returnValue;
        }
    }

    /**
     * Place the specified ship on the coordinates that were given. If it doesn't work, return false
     * @param row
     * @param col
     * @param m
     * @param v
     * @param submerged
     * @param name
     * @return
     */
    public boolean placeShips(int row, int col, int m, char v, boolean submerged, String name){
        //Takes in a coordinate and it sets those values to 1
        //V for vertical, H for Horizontal.
        //M for Minesweeper= 2 cells, D for Destroyer=3 cells, B for Battleship= 4 cells
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
            if(row + m <= 10) {
                for(int i = 0; i < m; i++) {
                    coors[i] = board[row+i][col];
                }
            } else {

                return false;
            }
        } else {
            if(col + m <= 10) {
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

        Ship ship;

        switch (name) {
            case "minesweeper":
                ship = new Minesweeper(name, m, coors, submerged);
                break;
            case "destroyer":
                ship = new Destroyer(name, m, coors, submerged);
                break;
            case "battleship":
                ship = new Battleship(name, m, coors, submerged);
                break;
            case "submarine":
                ship = new Submarine(name, m, coors, submerged);
                break;
            case "blucifer":
                ship = new Blucifer(name, m, coors, submerged);
                break;
            default:
                System.out.println("Invalid type of ship!");
                return false;
        }
        if(!submerged){
            for(int i = 0; i < numShips; i++) {
                if(ships[i].overlaps(ship)) {
                    return false;
                }
            }
            for(Coordinate c : ship.getCoordinates()) {
                if(name=="blucifer"){
                    c.setStatus(Coordinate.Status.BLU);
                }
                else{
                    c.setStatus(Coordinate.Status.SHIP);
                }

            }
        }
        else {
            for(Coordinate c : ship.getCoordinates()) {
                c.setBelowSurfaceStatus(Coordinate.Status.SHIP);
            }
        }
        ships[numShips++] = ship;
        //Initialize coordinates as SHIP
        if(m!=1){
            ship.setCaptainQuart();
        }

        return true;
    }

    /**
     * Print the map for the user (mainly used for testing purposes)
     */
    public void getMaps(){
        System.out.printf("   ");
        for (int j = 0; j < 10; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();
        for( int i=0; i < 10; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < 10; j++) {
                if(board[i][j].getStatus() == Coordinate.Status.SHIP || board[i][j].getStatus() == Coordinate.Status.CAPTAINQ) {
                    System.out.print("\u001B[35m" + " " + board[i][j] + " " + "\u001B[0m");
                }
                else if(board[i][j].getBelowSurfaceStatus() == Coordinate.Status.SHIP || board[i][j].getBelowSurfaceStatus() == Coordinate.Status.CAPTAINQ) {
                    System.out.print("\u001B[33m" + " " + board[i][j] + " " + "\u001B[0m");
                }
                else if(board[i][j].getStatus() == Coordinate.Status.BLU) {
                    System.out.print("\u001B[34m" + " " + board[i][j] + " " + "\u001B[0m");
                }
                else if(board[i][j].getStatus() == Coordinate.Status.HIT){
                    System.out.print("\u001B[31m" + " " + board[i][j] + " " + "\u001B[0m");
                }
                else if(board[i][j].getStatus() == Coordinate.Status.MISS) {
                    System.out.print("\u001B[32m" + " " + board[i][j] + " " + "\u001B[0m");
                }

                else {
                    System.out.print(" " + board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * This is the map that is displayed to enemies, the locations of ships that have not yet been attacked are hidden
     */
    public void getEnemyMaps(){
        System.out.printf("   ");
        for (int j = 0; j < 10; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();
        for( int i=0; i < 10; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < 10; j++) {
                if(board[i][j].getStatus() == Coordinate.Status.HIT){
                    System.out.print("\u001B[32m" + " " + board[i][j] + " " + "\u001B[0m");
                }
                else if(board[i][j].getStatus() == Coordinate.Status.MISS) {
                    System.out.print("\u001B[31m" + " " + board[i][j] + " " + "\u001B[0m");
                }
                else {
                    System.out.print(" " + "0" + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Return the map that is returned when a sonar pulse is used on any of the coordinates on the board
     * @param board
     */
    public void getSonarMaps(String board[][]){
        System.out.printf("   ");
        for (int j = 0; j < 10; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();
        for( int i=0; i < 10; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < 10; j++) {
                System.out.printf("%2s ", board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Move ships in a specified direction -- the whole fleet moves in this direction
     * @param M
     * @return
     */
    public boolean moveShips(char M){
        if (disabled == true)  {
            System.out.println("You cannot move your ships right now because the enemy has disabled them with a stopper. ");
            return false;
        }
        else if(M=='N'){
            Undostack.push('S');
        }
        else if(M=='S'){
            Undostack.push('N');
        }
        else if(M=='E'){
            Undostack.push('W');
        }
        else if(M=='W'){
            Undostack.push('E');
        }

        for(int i = 0; i<numShips; i++){
            Coordinate coors[] = new Coordinate[ships[i].getHealth()]; //created an array for the updated coordinates
            int counter=0;
            for(Coordinate c : ships[i].getCoordinates()){
                if(M=='S'&& c.getX()<9){
                    board[c.getX()+1][c.getY()].setStatus(Coordinate.Status.SHIP);
                    board[c.getX()][c.getY()].setStatus(Coordinate.Status.EMPTY);
                    coors[counter++] = board[c.getX()+1][c.getY()];
                }
                else if(M=='N' && c.getX()>0){
                    board[c.getX()-1][c.getY()].setStatus(Coordinate.Status.SHIP);
                    board[c.getX()][c.getY()].setStatus(Coordinate.Status.EMPTY);
                    coors[counter++] = board[c.getX()-1][c.getY()];
                }
                else if(M=='W' && c.getY()>0){
                    board[c.getX()][c.getY()-1].setStatus(Coordinate.Status.SHIP);
                    if(counter==ships[i].getHealth()-1){//only set the last sell to empty
                        board[c.getX()][c.getY()].setStatus(Coordinate.Status.EMPTY);
                    }
                    coors[counter++] = board[c.getX()][c.getY()-1];
                }
                else if(M=='E' && c.getY()<9){
                    board[c.getX()][c.getY()+1].setStatus(Coordinate.Status.SHIP);
                    if(counter==0) {
                        board[c.getX()][c.getY()].setStatus(Coordinate.Status.EMPTY);
                    }
                    coors[counter++] = board[c.getX()][c.getY()+1];
                }
                else{
                    System.out.println("Can not move in that direction");
                    return false;
                }
            }
            ships[i].moveCoordinates(coors); //updates the new coordinates for the ships
            ships[i].setCaptainQuart();
        }
        return true;
    }

    /**
     * Undo a move that was just made by the player
     */
    public void Undo(){
        System.out.println("Undo Completed.");
        moveShips(Undostack.peek());
        Redostack.push(Undostack.peek());
        Undostack.pop();
    }

    /**
     * Redo a move that was made by the player
     */
    public void Redo(){
        System.out.println("Redo Completed.");
        moveShips(Redostack.peek());
        Redostack.pop();
    }

    /**
     * Make a certain ship invisible on sonar maps when specified by the user
     * @param name
     */
    public void makeInvisible(String name) {
        if (disabled == false) {
            for (Ship ship : ships) {
                if(used_once == false) {
                    ship.getAttackedBelow(-1, -1);
                    if (ship.name.equals(name)) {
                        if (ship.hasFullHealth()) {
                            ship.makeInvisible();
                            used_once = true;
                        }
                    }
                }
            }
        }
        else {
                System.out.println("Your ships have been disabled by enemy stopper, you cannot move them. ");
            }
        }
}

