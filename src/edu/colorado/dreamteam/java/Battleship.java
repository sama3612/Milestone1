package edu.colorado.dreamteam.java;

public class Battleship extends Ship {
    public Battleship(String name, int size, Coordinate[] coordinates, boolean submerged) {
        super(name,size,coordinates,submerged);
    }
    @Override
    public Boolean getAttackedBelow(int row, int col) {
        return false;
    }
}
