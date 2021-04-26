package edu.colorado.dreamteam.java;

/**
 * Child of Ship class that represents a Submarine
 */
public class Submarine extends Ship {
    /**
     * Constructor for Submarine class
     * @param name
     * @param size
     * @param coordinates
     * @param submerged
     */
    public Submarine(String name, int size, Coordinate[] coordinates, boolean submerged) {
        super(name,size,coordinates,submerged);
    }

//    @Override
//    public Boolean getAttackedBelow(int row, int col) {
//        return null;
//    }

//    @Override
//    public Boolean getAttackedBelow(int row, int col) {     //method to handle a ship being shot at
//        boolean returnValue = false;
//        if(submerged) {
//            Coordinate attack = new Coordinate(row, col);
//            if(captainQuart.equals(attack)) {
//                if(armor != 0){
//                    armor = 0;
//                    captainQuart.setBelowSurfaceStatus(Coordinate.Status.FAKEEMPTY);
//                    returnValue = false;
//                }
//                else{
//                    for(Coordinate c : coordinates) {
//                        c.setBelowSurfaceStatus(Coordinate.Status.HIT);
//                    }
//                    health = 0;
//                    returnValue = true;
//                }
//            }
//            else {
//                for (Coordinate c : coordinates) {
//                    if (c.equals(attack) && c.getBelowSurfaceStatus() == Coordinate.Status.SHIP) {
//                        c.setBelowSurfaceStatus(Coordinate.Status.HIT);
//                        System.out.println("HERE GETTIN");
//                        health =health - 1;
//                        returnValue = true;
//                    }
//                }
//                returnValue = false;
//            }
//        }
//        return returnValue;
//    }
}