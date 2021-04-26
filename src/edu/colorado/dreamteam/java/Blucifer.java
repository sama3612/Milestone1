package edu.colorado.dreamteam.java;

/**
 * Chlid class of Ship, built for a Trojan Horse type of ship
 */
public class Blucifer extends Ship {
    /**
     * Constructor for Blucifer class
     * @param name
     * @param size
     * @param coordinates
     * @param submerged
     */
    public Blucifer(String name, int size, Coordinate[] coordinates, boolean submerged) {
        super(name,size,coordinates,submerged);
    }

    /**
     * Overrides Ship class method of getting attacked below (mainly used for submarine class)
     * @param row
     * @param col
     * @return
     */
    @Override
    public Boolean getAttackedBelow(int row, int col) {
        return false;
    }
}
