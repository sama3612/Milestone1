package edu.colorado.dreamteam.java;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {
    //Dream Team pair 1 was here
    // TODO: create appropriate getter and setter methods
    // TODO: Understand encapsulation
    // TODO: Understand what these todo comments mean
    //Team DreamTeam, pair 2 was here

    private String name;
    private int health;
    private Coordinate[] coordinates;

    public Ship(String name, int size, Coordinate[] coordinates) {
        this.name = name;
        this.health = size;
        this.coordinates = coordinates;
    }

    public Boolean getAttacked(Coordinate attack) {     //method to handle a ship being shot at
        for(Coordinate c : coordinates) {
            if(c.equals(attack)) {
                health -= 1;
                return true;
            }
        }
        return false;
    }

    public Boolean isSunk() {
        return health == 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public boolean overlaps(Ship ship) {            //method to check that a ship's coordinates does not overlap with other ships
        for(Coordinate c1 : coordinates) {
            for(Coordinate c2 : ship.getCoordinates()) {
                if(c1 == c2) {
                    return true;
                }
            }
        }
        return false;
    }
}
