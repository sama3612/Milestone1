package edu.colorado.dreamteam.java;

public class Coordinate {
    /**
     * Enum representing different types of Coordinate statuses
     */
    enum Status {
        SHIP,
        CAPTAINQ,
        HIT,
        MISS,
        EMPTY,
        BLU,
        FAKEEMPTY //secondary status for a hit captain's quarters with armor that displays as miss but acts differently
    }

    private int x;  /*x-coordinate*/
    private int y;  /*y-coordinate*/
    private Status status;
    private Status belowSurfaceStatus;
    private String attackType;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = Status.EMPTY;
        this.belowSurfaceStatus = Status.EMPTY;
    }

    /**
     * Getter for x coordinate value
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for y coordinate value
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for Status enum object
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Setter for Enum Status object
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Overrided equals method that is used to compare different coordinate values
     * @param c
     * @return
     */
    public boolean equals(Coordinate c) {
        return getX() == c.getX() && getY() == c.getY();
    }

    /**
     * Getter for below surface Status enum
     * @return
     */
    public Status getBelowSurfaceStatus() {
        return belowSurfaceStatus;
    }

    /**
     * Setter for below surface status enum object
     * @param belowSurfaceStatus
     */
    public void setBelowSurfaceStatus(Status belowSurfaceStatus) {
        this.belowSurfaceStatus = belowSurfaceStatus;
    }

    /**
     * Returns the type of attack that was used on the coordinate
     * @return
     */
    public String getAttackType() {
        return attackType;
    }

    /**
     * Setter for the type of attack that was used on the coordinate
     * @param attackType
     */
    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    /**
     * Converts the status of the enum status object to a String number that is used when displaying the maps to the user
     * @return
     */
    @Override
    public String toString() {
        switch (this.status) {
            case EMPTY:
                return "0";
            case BLU:
            case SHIP:
            case CAPTAINQ:
                return "1";
            case HIT:
                return "2";
            case FAKEEMPTY:
            default: //MISS
                return "3";
        }
    }
}
