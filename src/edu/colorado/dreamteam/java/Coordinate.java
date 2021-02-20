package edu.colorado.dreamteam.java;

public class Coordinate {
    private int x;  /*x-coordinate*/
    private int y;  /*y-coordinate*/
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
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

    public boolean equals(Coordinate c) {
        return getX() == c.getX() && getY() == c.getY();
    }
}
