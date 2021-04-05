package edu.colorado.dreamteam.java;
public abstract class Ship {
    protected String name;
    protected int health;
    protected Coordinate[] coordinates;
    protected Coordinate captainQuart;
    protected int armor;
    protected boolean submerged;
    protected boolean fullHeath;
    protected Coordinate[] oldStatus;

    public Ship(String name, int size, Coordinate[] coordinates, boolean submerged) {
        this.name = name;
        this.health = size;
        this.coordinates = coordinates;
        this.oldStatus = new Coordinate[coordinates.length];
        captainQuart = coordinates[coordinates.length-2];
        this.submerged = submerged;
        fullHeath = true;
        //by convention, must initialize ship coordinates with the stub as last coordinate

        if(size > 2) {
            armor = 1;
        }
        else {
            armor = 0;
        }
    }

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
                    System.out.println("HERE GETTIN");
                    health =health - 1;
                    fullHeath = false;
                    return true;
                }
            }
            return false;
        }
    }

    public abstract Boolean getAttackedBelow(int row, int col);

    public Boolean isSunk() {
        return health <= 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
//    public boolean getSubmerdge(){
//        return submerged;
//    }
    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public boolean moveCoordinates(Coordinate[] coord){
        this.coordinates=coord;
        return true;

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

    public boolean hasFullHealth() {
        return fullHeath;
    }

    public void makeInvisible() {
        System.arraycopy(coordinates,0,oldStatus,0,coordinates.length);
        for (Coordinate c : coordinates) {
            c.setStatus(Coordinate.Status.FAKEEMPTY);
        }
    }
}
