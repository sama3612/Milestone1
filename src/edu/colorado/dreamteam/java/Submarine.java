package edu.colorado.dreamteam.java;

/**
 * Child of Ship class that represents a Submarine
 */
public class Submarine extends Ship {
    /**
     * Constructor for Submarine class
     * @param name
     * @param size
     * @param coordinates
     * @param submerged
     */
    public Submarine(String name, int size, Coordinate[] coordinates, boolean submerged) {
        super(name,size,coordinates,submerged);
    }
}