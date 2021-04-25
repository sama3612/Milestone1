package edu.colorado.dreamteam.java;

public class Player {
    private String name;
    private Map map;
//    private int Score;//each player will have their own map that contains their ships and the shot history

    public Player(String name, Map map) {
        this.name = name;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public boolean getAttacked(int row, int col, String weapon) {
        return map.getAttacked(row, col, new Weapon(weapon));
    }
//    public int getScore(){
//        return Score;
//    }
//    public void setScore(int Score){
//        this.Score=Score
//    }


    public boolean placeShips(int row, int col, int m, char v, boolean submerged, String name) { return map.placeShips(row,col,m,v, submerged, name); }

    public boolean isAlive() { return map.hasShips(); }

    public void displayFullMap() { map.getMaps(); }

    public void displayEnemyMap() {map.getEnemyMaps();}

    public void checkPoints() {map.getPoints();}
}

//testing to see if my intellj is broken :-)