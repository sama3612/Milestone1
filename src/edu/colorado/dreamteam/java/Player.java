package edu.colorado.dreamteam.java;

/**
 * Class that represents a player, contains map as well as name
 */
public class Player {
    private String name;
    private Map map;
    //each player will have their own map that contains their ships and the shot history

    /**
     * Constructor for Player that takes a name and map object
     * @param name
     * @param map
     */
    public Player(String name, Map map) {
        this.name = name;
        this.map = map;
    }

    /**
     * Getter for the name of the player
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Attacks a certain coordinate with a weapon that is specified
     * @param row
     * @param col
     * @param weapon
     * @return
     */
    public boolean getAttacked(int row, int col, String weapon) {
        return map.getAttacked(row, col, new Weapon(weapon));
    }

    /**
     * Places ships on certain coordinates and orientation specified by the user
     * @param row
     * @param col
     * @param m
     * @param v
     * @param submerged
     * @param name
     * @return
     */
    public boolean placeShips(int row, int col, int m, char v, boolean submerged, String name) { return map.placeShips(row,col,m,v, submerged, name); }

    /**
     * Checks if the player is still in the game by checking if they have any ships that are still alive
     * @return
     */
    public boolean isAlive() { return map.hasShips(); }

    /**
     * Displayes the full map for this player
     */
    public void displayFullMap() { map.getMaps(); }

    /**
     * Displays a map that is censored, used to show the enemy only the spots that they already attacked
     */
    public void displayEnemyMap() {map.getEnemyMaps();}

    /**
     * Get the number of points that this player has accumulated so far
     * @return
     */
    public int checkPoints() {return map.getPoints();}
}