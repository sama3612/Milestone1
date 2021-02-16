package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
public class MapTest {

    //Test if the map intializes, check if it displays the 10 by 10 grid.
    @Test
    public void checkMapExist(){ //getMaps
        Map map = new Map();
        map.getMaps();
    }
    //Test if it set ships on map at given location
    @Test
    public void checkSetMap(){
        Map map = new Map();
        map.setMaps(new Coordinate(1,2));
        assertThat(map.hasShips(),is(true));
    }
    //Add a test that checks for ships cant be placed diagonally
    @Test
    public void checkDiagonal(){
        Map map = new Map();
        map.setMaps(new Coordinate{new Coordinate(1,2)});
    }
    //Check if next to eachother.
    //Choose if vertical or Horizontal, and size of ship, and where the head of the coordinate will be.
    //More Test
    //Check if goes outside of the map.
    //Check if it overlaps
    @Test
    public void checkifAttackMiss(){//checks if the gettattacked returns a miss
        Map map = new Map();
        assertThat(map.getAttacked(new Coordinate(1, 1)),is(false));
    }
    //Test if Map updates when hit
    @Test
    public void checkifHit(){//checks if gettattacked return a Hit
        Map map = new Map();
        map.setMaps(new Coordinate(1,2));
        assertThat(map.getAttacked(new Coordinate(1, 2)), is(true));
    }
    @Test
    public void checkifAlreadyHit(){//checks if gettattacked return a Hit
        Map map = new Map();
        map.setMaps(new Coordinate(1,2));
        map.getAttacked(new Coordinate(1, 2));//even tho this prints out
        assertThat(map.getAttacked(new Coordinate(1, 2)), is(false));
    }
    //Test if player already missed that coordinate
    @Test
    public void checkifAlreadyMissed(){
        Map map = new Map();
        map.getAttacked(new Coordinate(1, 1));
        assertThat(map.getAttacked(new Coordinate(1, 1)),is(false));
//        map.getAttacked(new Coordinate(1, 1));//checks if its a miss again

    }



    //Test if there is any ships left in the grid.
    @Test
    public void checkifShipsleft(){ //Checks if there are any ships left
        Map map = new Map();
        map.setMaps(new Coordinate(1,2));
        assertThat(map.hasShips(),is(true));
    }

}