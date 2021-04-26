package edu.colorado.dreamteam.java;

/**
 * Base class for all Ship objects, parent to Minesweeper, Battleship, Destroyer, Submarine, and Blucifer
 */
public abstract class Ship {
    protected String name;
    protected int health;
    protected Coordinate[] coordinates;
    protected Coordinate captainQuart;
    protected int armor;
    protected boolean submerged;
    protected boolean fullHeath;
    protected Coordinate[] oldStatus;
    private Map maps;

    /**
     * Constructor for ship that has the coordinates that the ship occupies
     * @param name
     * @param size
     * @param coordinates
     * @param submerged
     */
    public Ship(String name, int size, Coordinate[] coordinates, boolean submerged) {

        this.name = name;
        this.health = size;
//        maps=new Map();
        this.coordinates = coordinates;
        this.oldStatus = new Coordinate[coordinates.length];
        if(size > 1){
//            System.out.println("ISsue in Ship");
            captainQuart = coordinates[coordinates.length-2];
        }
        this.submerged = submerged;
        fullHeath = true;
        //by convention, must initialize ship coordinates with the stub as last coordinate

        if(size > 2) {
            armor = 1;
        }
        else {
            armor = 0;
        }
//        System.out.println("ISsue in Ship");

    }

    /**
     * Set the captains quarters for this ship
     */
    public void setCaptainQuart() {
        captainQuart = coordinates[coordinates.length-2];
        if (submerged) {
            captainQuart.setBelowSurfaceStatus(Coordinate.Status.CAPTAINQ);
        }
        else {
            captainQuart.setStatus(Coordinate.Status.CAPTAINQ);
        }
        if(health > 2) {
            armor = 1;
        }
        else {
            armor = 0;
        }
    }

    /**
     * Functions that attacks this ship and returns false if it was not a valid hit
     * @param row
     * @param col
     * @return
     */
    public Boolean getAttacked(int row, int col) {     //method to handle a ship being shot at
        Coordinate attack = new Coordinate(row, col);

        if(captainQuart.equals(attack)) {
            if(armor != 0){
                armor = 0;
                captainQuart.setStatus(Coordinate.Status.FAKEEMPTY);
                return false;
            }
            else{
                for(Coordinate c : coordinates) {
                    c.setStatus(Coordinate.Status.HIT);

                }
                health = 0;
                fullHeath = false;
                return true;
            }
        }
        else {
            for (Coordinate c : coordinates) {
                if (c.equals(attack) && c.getStatus() == Coordinate.Status.SHIP) {
                    c.setStatus(Coordinate.Status.HIT);
                    health =health - 1;
                    fullHeath = false;
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Attacks a ship below the surface (mainly used for Submarine class)
     * @param row
     * @param col
     * @return
     */
    public Boolean getAttackedBelow(int row, int col){
        boolean returnValue = false;
        if(submerged) {
            Coordinate attack = new Coordinate(row, col);
            if(captainQuart.equals(attack)) {
                if(armor != 0){
                    armor = 0;
                    captainQuart.setBelowSurfaceStatus(Coordinate.Status.FAKEEMPTY);
                    returnValue = false;
                }
                else{
                    for(Coordinate c : coordinates) {
                        c.setBelowSurfaceStatus(Coordinate.Status.HIT);
                    }
                    health = 0;
                    returnValue = true;
                }
            }
            else {
                for (Coordinate c : coordinates) {
                    if (c.equals(attack) && c.getBelowSurfaceStatus() == Coordinate.Status.SHIP) {
                        c.setBelowSurfaceStatus(Coordinate.Status.HIT);
                        System.out.println("HERE GETTIN");
                        health =health - 1;
                        returnValue = true;
                    }
                }
                returnValue = false;
            }
        }
        return returnValue;
    }

    /**
     * Checks if the ship is sunk yet
     * @return
     */
    public Boolean isSunk() {
        return health <= 0;
    }

    /**
     * Gets the name of this ship
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the health of the ship (number of coordinates not attacked)
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * Getter for the coordinates of this ship
     * @return
     */
    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    /**
     * Moves the ship to the new location specified by the coord parameter
     * @param coord
     * @return
     */
    public boolean moveCoordinates(Coordinate[] coord){
        this.coordinates=coord;
        return true;

    }

    /**
     * Check if this ship overlaps with another ship based on the coordinates of both ships
     * @param ship
     * @return
     */
    public boolean overlaps(Ship ship) {
        for(Coordinate c1 : coordinates) {
            for(Coordinate c2 : ship.getCoordinates()) {
                if(c1 == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if the ship has full health
     * @return
     */
    public boolean hasFullHealth() {
        return fullHeath;
    }

    /**
     * Make a ship invisible even when a sonar pulse is used
     */
    public void makeInvisible() {
        System.arraycopy(coordinates,0,oldStatus,0,coordinates.length);
        for (Coordinate c : coordinates) {
            c.setStatus(Coordinate.Status.FAKEEMPTY);
        }
    }
}
