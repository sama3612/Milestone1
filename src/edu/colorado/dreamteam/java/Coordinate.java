package edu.colorado.dreamteam.java;

public class Coordinate {
    enum Status {
        SHIP,
        CAPTAINQ,
        HIT,
        MISS,
        EMPTY,
        FAKEEMPTY //secondary status for a hit captain's quarters with armor that displays as miss but acts differently
    }

    private int x;  /*x-coordinate*/
    private int y;  /*y-coordinate*/
    private Status status;
    private Status belowSurfaceStatus;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = Status.EMPTY;
        this.belowSurfaceStatus = Status.EMPTY;
    }

    public int getX() {
        return x;
    }

//    public void setX(int x) {
//        this.x = x;
//    }

    public int getY() {
        return y;
    }

//    public void setY(int y) {
//        this.y = y;
//    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean equals(Coordinate c) {
        return getX() == c.getX() && getY() == c.getY();
    }

    public Status getBelowSurfaceStatus() {
        return belowSurfaceStatus;
    }

    public void setBelowSurfaceStatus(Status belowSurfaceStatus) {
        this.belowSurfaceStatus = belowSurfaceStatus;
    }

    @Override
    public String toString() {
        switch (this.status) {
            case EMPTY:
                return "0";
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
