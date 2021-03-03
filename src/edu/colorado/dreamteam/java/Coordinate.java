package edu.colorado.dreamteam.java;

public class Coordinate {
    enum Status {
        SHIP,
        CAPTAINQ,
        HIT,
        MISS,
        EMPTY
    }

    private int x;  /*x-coordinate*/
    private int y;  /*y-coordinate*/
    private Status status;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = Status.EMPTY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean equals(Coordinate c) {
        return getX() == c.getX() && getY() == c.getY();
    }
}
