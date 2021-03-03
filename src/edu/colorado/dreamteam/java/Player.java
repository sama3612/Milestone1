package edu.colorado.dreamteam.java;

public class Player {
    private String name;
    private Map map;        //each player will have their own map that contains their ships and the shot history

    public Player(String name, Map map) {
        this.name = name;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public boolean getAttacked(int row, int col, Weapon weapon) {
        return map.getAttacked(row, col, weapon);
    }

    public boolean isAlive() { return map.hasShips(); }
}
