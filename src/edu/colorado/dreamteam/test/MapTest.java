package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class MapTest {
    //Test if the map intializes, check if it displays the 10 by 10 grid.
    @Test
    public void checkMapExist(){ //getMaps
        Map map = new Map();
        map.getMaps();
    }
    //Test if it set ships on map at given location
    @Test
    public void checkSetMap(){ //Choose if vertical or Horizontal, and size of ship, and where the head of the coordinate will be.
        Map map = new Map();
        map.placeShips(1,2,3,'V');//change the second parameter to length
        //V for vertical
        //H for Horizontal
        assertThat(map.hasShips(),is(true));
    }

    @Test
    public void checkLengthSetMap(){ //edge case: choosing an invalid length
        Map map = new Map();
        map.placeShips(11,2,30,'V');//invalid value for m
        assertThat(map.hasShips(),is(false));
    }

    //Add a test that checks for ships cant be placed diagonally
    @Test
    public void checkDiagonal(){
        Map map = new Map();
        map.placeShips(1,2, 2, 'V' );
    }

    //More Test
    //Check if goes outside of the map.
    //Check if it overlaps this test is for
    @Test
    public void checkifAttackMiss(){//checks if the gettattacked returns a miss
        Map map = new Map();
        assertThat(map.getAttacked(1,1, new Weapon("mine")),is(false));
    }
    //Test if Map updates when hit
    @Test
    public void checkifHit(){//checks if gettattacked return a Hit
        Map map = new Map();
        map.placeShips(1,1, 2, 'V');
        assertThat(map.getAttacked(1,1, new Weapon("mine")), is(true));
    }
    @Test
    public void checkifAlreadyHit(){//checks if gettattacked return a Hit
        Map map = new Map();
        map.placeShips(1,2, 2, 'V');
        map.getAttacked(1,2, new Weapon("mine"));//even tho this prints out
        assertThat(map.getAttacked(1,2, new Weapon("mine")), is(false));
    }
    //Test if player already missed that coordinate
    @Test
    public void checkifAlreadyMissed(){
        Map map = new Map();
        map.getAttacked(1,1, new Weapon("mine"));
        assertThat(map.getAttacked(1,1, new Weapon("mine")),is(false));
//        map.getAttacked(new Coordinate(1, 1));//checks if its a miss again

    }

    //Test if there is any ships left in the grid.
    @Test
    public void checkifShipsleft(){ //Checks if there are any ships left
        Map map = new Map();
        map.placeShips(1,2, 3, 'V');
        assertThat(map.hasShips(),is(true));
    }

    @Test //test if weapon can be used to attack
    public void sonarPulseWasUsed() {
        Map map = new Map();
        map.placeShips(1,2, 3, 'V');
        map.placeShips(5,5, 2, 'V');
        map.placeShips(6,6, 2, 'H');
        map.getAttacked(6,6, new Weapon("mine"));
        map.getAttacked(6,7, new Weapon("mine"));
        map.getAttacked(9,7, new Weapon("mine"));
        assertThat(map.getAttacked(1,2, new Weapon("sonar_pulse")),is(true));
    }

    @Test
    public void canOnlyUseTwo() {
        Map map = new Map();
        map.placeShips(1,2, 3, 'V');
        map.placeShips(5,5, 2, 'V');
        map.placeShips(6,6, 2, 'H');
        map.getAttacked(6,6, new Weapon("mine"));
        map.getAttacked(6,7, new Weapon("mine"));
        map.getAttacked(9,7, new Weapon("mine"));
        System.out.println("Using first pulse");
        assertThat(map.getAttacked(1,2, new Weapon("sonar_pulse")),is(true));
        System.out.println("Using second pulse");
        assertThat(map.getAttacked(4,7, new Weapon("sonar_pulse")),is(true));
        System.out.println("Using third pulse");
        assertThat(map.getAttacked(9,8, new Weapon("sonar_pulse")),is(false));
    }

    @Test
    public void printMultipleShipsAndAttacks() {
        Map map = new Map();
        map.placeShips(1,2, 3, 'V');
        map.placeShips(5,5, 2, 'V');
        map.placeShips(6,6, 2, 'H');
        map.getAttacked(6,6, new Weapon("mine"));
        map.getAttacked(6,7, new Weapon("mine"));
        map.getAttacked(9,7, new Weapon("mine"));
        map.getMaps();
    }
}
