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
    private Coordinate captainQuart;
    private int armor;

    public Ship(String name, int size, Coordinate[] coordinates) {
        this.name = name;
        this.health = size;
        this.coordinates = coordinates;
        captainQuart = coordinates[coordinates.length-2];

        if(size > 2) {
            armor = 1;
        }
        else {
            armor = 0;
        }
    }

    public void setCaptainQuart() {
        captainQuart = coordinates[coordinates.length-2];
        captainQuart.setStatus(Coordinate.Status.CAPTAINQ);
        if(health > 2) {
            armor = 1;
        }
        else {
            armor = 0;
        }
    }

    public Boolean getAttacked(int row, int col) {     //method to handle a ship being shot at
        Coordinate attack = new Coordinate(row, col);
        if(captainQuart.equals(attack)) {
            if(armor != 0){
                armor = 0;
                captainQuart.setStatus(Coordinate.Status.MISS);
                return false;
            }
            else{
                for(Coordinate c : coordinates) {
                    if (c.equals(attack)) {
                        c.setStatus(Coordinate.Status.HIT);
                        break;
                    }
                }
                health = 0;
                return true;
            }
        }
        else {
            for (Coordinate c : coordinates) {
                if (c.equals(attack) && c.getStatus() == Coordinate.Status.SHIP) {
                    c.setStatus(Coordinate.Status.HIT);
                    health -= 1;
                    return true;
                }
            }
            return false;
        }
    }

    public Boolean isSunk() {
        return health <= 0;
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

    public Coordinate getCaptainQuart() {
        return captainQuart;
    }
}
