package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.Map;
import edu.colorado.dreamteam.java.Weapon;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
        map.placeShips(1,2,3,'V', false, "destroyer");//change the second parameter to length
        //V for vertical
        //H for Horizontal
        assertThat(map.hasShips(),is(true));
    }

    @Test
    public void checkLengthSetMap(){ //edge case: choosing an invalid length
        Map map = new Map();
        map.placeShips(11,2,30,'V', false, "mistake");//invalid value for m
        assertThat(map.hasShips(),is(false));
    }

    //Add a test that checks for ships cant be placed diagonally
    @Test
    public void checkDiagonal(){
        Map map = new Map();
        map.placeShips(1,2, 2, 'V', false, "minesweeper");
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
        map.placeShips(1,1, 2, 'V', false, "minesweeper");
        map.getAttacked(1,1, new Weapon("mine"));
        map.getMaps();
    }
    @Test
    public void checkifAlreadyHit(){//checks if gettattacked return a Hit
        Map map = new Map();
        map.placeShips(1,2, 2, 'V', false, "minesweeper");
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
        map.placeShips(1,2, 3, 'V', false, "destroyer");
        assertThat(map.hasShips(),is(true));
    }

    @Test //test if weapon can be used to attack
    public void sonarPulseWasUsed() {
        Map map = new Map();
        map.placeShips(1,2, 3, 'V', false, "destroyer");
        map.placeShips(5,5, 2, 'V', false, "minesweeper");
        map.placeShips(6,6, 2, 'H', false, "minesweeper");
        map.placeShips(1,2, 4, 'H', true, "submarine");
        map.getAttacked(6,6, new Weapon("mine"));
        map.getAttacked(6,7, new Weapon("mine"));
        map.getAttacked(9,7, new Weapon("mine"));
        assertThat(map.getAttacked(1,2, new Weapon("sonar_pulse")),is(true));
    }

    @Test
    public void canOnlyUseTwo() {
        Map map = new Map();
        map.placeShips(1,2, 3, 'V', false, "destroyer");
        map.placeShips(5,5, 2, 'V', false, "minesweeper");
        map.placeShips(6,6, 2, 'H', false, "minesweeper");
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
        map.placeShips(1,2, 3, 'V', false, "destroyer");
        map.placeShips(5,5, 2, 'V', false, "minesweeper");
        map.placeShips(6,6, 2, 'H', false, "minesweeper");
        map.getAttacked(6,6, new Weapon("mine"));
        map.getAttacked(6,7, new Weapon("mine"));
        map.getAttacked(9,7, new Weapon("mine"));
        map.getMaps();
    }

    //TODO: add assertion
    @Test
    public void attackCaptainQTwice() { //testing battleship
        Map map = new Map();
        map.placeShips(0,0,4,'H', false, "battleship");
        map.getAttacked(0,2, new Weapon("mine"));
        map.getMaps();
        map.getAttacked(0,2, new Weapon("mine"));
        map.getMaps();
        assertThat(map.hasShips(),is(false));
    }

    @Test
    public void attackCaptainQTwiceDestroyer() { //testing destroyer
        Map map = new Map();
        map.placeShips(1,4,3,'H', false, "destroyer");
        map.getAttacked(1,5, new Weapon("mine"));
        map.getMaps();
        map.getAttacked(1,5, new Weapon("mine"));
        map.getMaps();
        assertThat(map.hasShips(),is(false));
    }

    @Test
    public void attackCaptainQTwiceMinesweeper() { //testing minesweeper
        Map map = new Map();
        map.placeShips(1,4,2,'H', false, "minesweeper");
        map.getAttacked(1,4, new Weapon("mine"));
        map.getMaps();
        assertThat(map.hasShips(),is(false));
    }

    @Test
    public void placeIllegalSub() {
        Map map = new Map();
        map.placeShips(0,1,4,'H',true,"submarine");
        map.placeShips(5,9,4,'V',true,"submarine");
        assertThat(map.hasShips(), is(false));
        map.placeShips(2,2,2,'H',false,"minesweeper");
        assertThat(map.placeShips(2,2,4,'H',false,"submarine"),is(false));
    }

    @Test
    public void subFailAttack() {
        Map map = new Map();
        map.placeShips(2,2,4,'V',true,"submarine");
        map.getAttacked(2,2, new Weapon("mine"));
        map.getMaps();
    }

    @Test
    public void subSurfaceAttack() {
        Map map = new Map();
        map.placeShips(2,2,4,'V',false,"submarine");
        map.getAttacked(5,2, new Weapon("mine"));
        map.getAttacked(4,2, new Weapon("mine"));
        map.getMaps();
        map.getAttacked(5,2, new Weapon("mine"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }
    @Test
    public void MoveTest(){
        Map map = new Map();
        map.placeShips(1,4,2,'H', false, "minesweeper");
        map.getMaps();
        map.moveShips('N');
        map.getMaps();
    }
    @Test
    public void Moveoutofrange(){
        Map map = new Map();
        map.placeShips(0,4,2,'H', false, "minesweeper");
        map.getMaps();// assert that this returns false.
        map.moveShips('N');
        map.getMaps();

    }
    @Test
    public void Moveagain(){
        Map map = new Map();
        map.placeShips(1,4,2,'H', false, "minesweeper");
        map.getMaps();
        map.moveShips('N');
        map.getMaps();
        map.moveShips('E');
        map.getMaps();
    }
    @Test
    public void UndoMove(){
        Map map = new Map();
        map.placeShips(1,4,2,'H', false, "minesweeper");
        map.getMaps();
        map.moveShips('N');
//        Undo();
    }


    //TODO: write tests for space laser
    @Test
    public void spaceLaser() {
        Map map = new Map();
        map.placeShips(2,2,4,'V',true,"submarine");
        map.placeShips(1,4,2,'H', false, "minesweeper");
        map.getAttacked(1,4, new Weapon("mine"));
        map.getAttacked(5,2, new Weapon("space_laser"));
        map.getAttacked(5,2, new Weapon("space_laser"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void spaceLaserDoubleHit() {
        Map map = new Map();
        map.placeShips(2,2,4,'V',true,"submarine");
        map.placeShips(1,4,2,'H', false, "minesweeper");
        map.placeShips(3,2,4,'V', false,"battleship");
        map.getAttacked(1,4, new Weapon("mine"));
        map.getAttacked(5,2, new Weapon("space_laser"));
        map.getAttacked(5,2, new Weapon("space_laser"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    //TODO need to redo and check get maps and clarify what is displayed and what isn't
}