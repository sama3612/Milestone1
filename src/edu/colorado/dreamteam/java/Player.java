package edu.colorado.dreamteam.java;

public class Player {
    private String name;
    private Map map;

    public Player(String name, Map map) {
        this.name = name;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public boolean getAttacked(Coordinate attack) {
        return map.getAttacked(attack);
    }

    public boolean isAlive() {
        return map.hasShips();
    }
}
