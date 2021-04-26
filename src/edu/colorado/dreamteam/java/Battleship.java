package edu.colorado.dreamteam.java;

/**
 * Child class of Ship, built for a Battleship
 */
public class Battleship extends Ship {
    /**
     * Battleship constructor
     * @param name
     * @param size
     * @param coordinates
     * @param submerged
     */
    public Battleship(String name, int size, Coordinate[] coordinates, boolean submerged) {
        super(name,size,coordinates,submerged);
    }
}
