package edu.colorado.dreamteam.java;

import java.util.Objects;

public class Coordinate {
    private char x;
    private int y;
    public Coordinate(char x, int y) {
        this.x = x;
        this.y = y;
    }

    public char getX() {
        return x;
    }

    public void setX(char x) {
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
