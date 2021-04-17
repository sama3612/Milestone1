package edu.colorado.dreamteam.java;

public class Destroyer extends Ship {
    public Destroyer(String name, int size, Coordinate[] coordinates, boolean submerged) {
        super(name,size,coordinates,submerged);
    }

   // @Override
//    public Boolean getAttackedBelow(int row, int col) {
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