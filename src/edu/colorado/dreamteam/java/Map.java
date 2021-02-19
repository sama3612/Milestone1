package edu.colorado.dreamteam.java;
// TODO: initialize map (maybe a constructor too). Add necessary private variables to keep track of state of map for each player
// TODO: implement hasShips. Check if the map has any ships left
// TODO: implement getAttacked. takes in an attack Coordinate and attacks the ship at that location. returns whether it was a successful attack
// TODO: check if the spot was already attacked (if it was, "tuff luck, better luck next time" and its the other persons turn)
// TODO: add other methods that may be useful (how many ships left,

public class Map<i> {
    private int Maps[][]=new int[10][10];//Create a 2d array
//    private char ShipType;
//    private char Position;
    public Map(){
        for( int i=0; i < 10; i++) { //Populate map with 0s then update when person inputs value, might need 2 of these
            for (int j = 0; j < 10; j++) {
                Maps[i][j] = 0;
            }
        }
    }
        public boolean hasShips() {
            for( int i=0; i < 10; i++) { //Populate map with 0 then update when person inputs value, might need 2 of these
                for (int j = 0; j < 10; j++) {
                    if(Maps[i][j] != 0){
                        return true;
                    }
                }
            }
            return false;
        }
        //Return true and update map if the coordinates has a ship there if not return false.
        public boolean getAttacked(Coordinate attack) {
            if(Maps[attack.getX()][attack.getY()] == 1) {//check if there is a ship there.
                Maps[attack.getX()][attack.getY()] = 2;//if so update map.//we can set2 for attacked ship
//                System.out.println("ITS A HIT");
                return true;//which means a hit.
            }
            else if(Maps[attack.getX()][attack.getY()] == 2){
                System.out.println("Already Hit!");
                return false;
            }
            else if(Maps[attack.getX()][attack.getY()] == 3){
                //Print out that they already miss
                System.out.println("Already Missed!");
                return false;
            }
            else{
                Maps[attack.getX()][attack.getY()] = 3;
//                System.out.println("Missed!");
                return false;//which is a miss.
            }
        }
        //Set ships on map on the given locations!
        public void setMaps(Coordinate ship, int m, char v) {//Go through coordinates and set those on the map =1
            //Takes in a coordinate and it sets those values to 1;
            //limit user, to down or sideways.
            //V for vertical, H for Horizontal.
            //M for Minesweeper=2 cells, D for Destroyer=3 cells, B for Battleship=4 cells.
            if(v=='V'){
                for(int i=0; i<m; i++){
                    Maps[ship.getX()+i][ship.getY()] = 1;
                }
            }
            else{
                for(int i=0; i<m; i++){
                    Maps[ship.getX()][ship.getY()+i] = 1;
                }
            }
//
        }
        //Return the map array!
        public void getMaps(){
            for( int i=0; i < 10; i++) { //Populate map with 0s then update when person inputs value, might need 2 of these
                for (int j = 0; j < 10; j++) {
                    System.out.printf("%2d ", Maps[i][j]);
                }
                System.out.println();
            }
        }
}
