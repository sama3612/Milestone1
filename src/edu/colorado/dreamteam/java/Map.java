package edu.colorado.dreamteam.java;
// TODO: initialize map (maybe a constructor too). Add necessary private variables to keep track of state of map for each player
// TODO: implement hasShips. Check if the map has any ships left
// TODO: implement getAttacked. takes in an attack Coordinate and attacks the ship at that location. returns whether it was a successful attack
// TODO: check if the spot was already attacked (if it was, "tuff luck, better luck next time" and its the other persons turn)
// TODO: add other methods that may be useful (how many ships left,

public class Map {
    private int board[][];//Create a 2d array
    private Ship ships[];
    private int numShips;

    public Map(){
        board = new int[10][10];
        ships = new Ship[3];
        numShips = 0;
        for( int i=0; i < 10; i++) { //Populate map with 0s then update when person inputs value, might need 2 of these
            for (int j = 0; j < 10; j++) {
                board[i][j] = 0;
            }
        }
    }

    public boolean hasShips() {
        return numShips != 0;
    }
    //Return true and update map if the coordinates has a ship there if not return false.
    public boolean getAttacked(Coordinate attack) {
        for(int i = 0; i < numShips; i++) {
            if(ships[i].getAttacked(attack) && ships[i].isSunk()) numShips--;
        }

        if(board[attack.getX()][attack.getY()] == 1) {//check if there is a ship there.
            board[attack.getX()][attack.getY()] = 2;//if so update map.//we can set2 for attacked ship
//                System.out.println("ITS A HIT");
            return true;//which means a hit.
        }
        else if(board[attack.getX()][attack.getY()] == 2){
            System.out.println("Already Hit!");
            return false;
        }
        else if(board[attack.getX()][attack.getY()] == 3){
            //Print out that they already miss
            System.out.println("Already Missed!");
            return false;
        }
        else{
            board[attack.getX()][attack.getY()] = 3;
//                System.out.println("Missed!");
            return false;//which is a miss.
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
                    coors[i] = new Coordinate(loc.getX(), loc.getY() + i);
                }
                Ship ship = new Ship("ship"+numShips, m, coors);
                for(int i = 0; i < numShips; i++) {
                    if(ships[i].overlaps(ship)) return false;
                }
                ships[numShips++] = ship;
                for(int i=0; i<m; i++){
                    board[loc.getX()+i][loc.getY()] = 1;
                }
                return true;
            }
        }
        else if(v == 'H'){
            if(loc.getX() + m < 9) {
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
                    board[loc.getX()][loc.getY()+i] = 1;
                }
                return true;
            }
        }
        return false;
//
    }
    //Return the map array!
    public void getMaps(){
        for( int i=0; i < 10; i++) { //Populate map with 0s then update when person inputs value, might need 2 of these
            for (int j = 0; j < 10; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }
}
