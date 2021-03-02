package edu.colorado.dreamteam.java;

public class Coordinate {
    private int x;  /*x-coordinate*/
    private int y;  /*y-coordinate*/
    private boolean used;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.used = false;
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

    public void getUsed() {
        this.used = true;
    }

    public boolean wasUsed() {
        return used;
    }

    public boolean equals(Coordinate c) {
        return getX() == c.getX() && getY() == c.getY();
    }
}
