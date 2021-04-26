package edu.colorado.dreamteam.java;

/**
 * Child class of Ship that represents a Minesweeper ship
 */
public class Minesweeper extends Ship {
    /**
     * Constructor for a Minesweeper
     * @param name
     * @param size
     * @param coordinates
     * @param submerged
     */
    public Minesweeper(String name, int size, Coordinate[] coordinates, boolean submerged) {
        super(name,size,coordinates,submerged);
    }
}

