package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.Map;
import edu.colorado.dreamteam.java.Weapon;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MapTest {
    @Test
    public void checkMapExist() {
        Map map = new Map();
        map.getMaps();
    }

    @Test
    public void checkSetMap() {
        Map map = new Map();
        map.placeShips(1, 2, 3, 'V', false, "destroyer");
        assertThat(map.hasShips(), is(true));
    }
    @Test
    public void setWrongShip(){
        Map map = new Map();
        map.placeShips(1, 2, 3, 'V', false, "desroyer");
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void checkLengthSetMap() { //edge case: choosing an invalid length
        Map map = new Map();
        map.placeShips(11, 2, 30, 'V', false, "mistake");//invalid value for m
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void checkDiagonal() {
        Map map = new Map();
        map.placeShips(1, 2, 2, 'V', false, "minesweeper");
    }

    @Test
    public void checkIfAttackMiss() {
        Map map = new Map();
        assertThat(map.getAttacked(1, 1, new Weapon("mine")), is(false));
    }

    @Test
    public void checkIfHit() {
        Map map = new Map();
        map.placeShips(1, 1, 2, 'V', false, "minesweeper");
        map.getAttacked(1, 1, new Weapon("mine"));
        map.getMaps();
    }

    @Test
    public void checkIfAlreadyHit() {
        Map map = new Map();
        map.placeShips(1, 2, 2, 'V', false, "minesweeper");
        map.getAttacked(1, 2, new Weapon("mine"));//even tho this prints out
        assertThat(map.getAttacked(1, 2, new Weapon("mine")), is(false));
    }

    @Test
    public void checkIfAlreadyMissed() {
        Map map = new Map();
        map.getAttacked(1, 1, new Weapon("mine"));
        assertThat(map.getAttacked(1, 1, new Weapon("mine")), is(false));
    }

    @Test
    public void checkIfShipsLeft() {
        Map map = new Map();
        map.placeShips(1, 2, 3, 'V', false, "destroyer");
        assertThat(map.hasShips(), is(true));
    }

    @Test
    public void sonarPulseWasUsed() {
        Map map = new Map();
        map.getAttacked(1, 2, new Weapon("sonar_pulse"));
        map.getAttacked(1, 2, new Weapon("space_laser"));
        map.placeShips(1, 2, 3, 'V', false, "destroyer");
        map.placeShips(5, 5, 2, 'V', false, "minesweeper");
        map.placeShips(6, 6, 2, 'H', false, "minesweeper");
        map.placeShips(1, 2, 4, 'H', true, "submarine");
        map.getAttacked(6, 6, new Weapon("mine"));
        map.getAttacked(6, 7, new Weapon("mine"));
        map.getAttacked(9, 7, new Weapon("mine"));
        assertThat(map.getAttacked(1, 2, new Weapon("sonar_pulse")), is(true));
    }

    @Test
    public void canOnlyUseTwo() {
        Map map = new Map();
        map.placeShips(1, 2, 3, 'V', false, "destroyer");
        map.placeShips(5, 5, 2, 'V', false, "minesweeper");
        map.placeShips(6, 6, 2, 'H', false, "minesweeper");
        map.getAttacked(6, 6, new Weapon("mine"));
        map.getAttacked(6, 7, new Weapon("mine"));
        map.getAttacked(9, 7, new Weapon("mine"));
        System.out.println("Using first pulse");
        assertThat(map.getAttacked(1, 2, new Weapon("sonar_pulse")), is(true));
        System.out.println("Using second pulse");
        assertThat(map.getAttacked(4, 7, new Weapon("sonar_pulse")), is(true));
        System.out.println("Using third pulse");
        assertThat(map.getAttacked(9, 8, new Weapon("sonar_pulse")), is(false));
    }

    @Test
    public void printMultipleShipsAndAttacks() {
        Map map = new Map();
        map.placeShips(1, 2, 3, 'V', false, "destroyer");
        map.placeShips(5, 5, 2, 'V', false, "minesweeper");
        map.placeShips(6, 6, 2, 'H', false, "minesweeper");
        map.getAttacked(6, 6, new Weapon("mine"));
        map.getAttacked(6, 7, new Weapon("mine"));
        map.getAttacked(9, 7, new Weapon("mine"));
        map.getMaps();
    }

    @Test
    public void attackCaptainQTwiceBattleship() {
        Map map = new Map();
        map.placeShips(0, 0, 4, 'H', false, "battleship");
        map.getAttacked(0, 2, new Weapon("mine"));
        map.getMaps();
        map.getAttacked(0, 2, new Weapon("mine"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void attackCaptainQTwiceDestroyer() {
        Map map = new Map();
        map.placeShips(1, 4, 3, 'H', false, "destroyer");
        map.getAttacked(1, 5, new Weapon("mine"));
        map.getMaps();
        map.getAttacked(1, 5, new Weapon("mine"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void attackCaptainQTwiceMinesweeper() {
        Map map = new Map();
        map.placeShips(1, 4, 2, 'H', false, "minesweeper");
        map.getAttacked(1, 4, new Weapon("mine"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void placeIllegalSub() {
        Map map = new Map();
        map.placeShips(0, 1, 4, 'H', true, "submarine");
        map.placeShips(5, 9, 4, 'V', true, "submarine");
        assertThat(map.hasShips(), is(false));
        map.placeShips(2, 2, 2, 'H', false, "minesweeper");
        assertThat(map.placeShips(2, 2, 4, 'H', false, "submarine"), is(false));
    }

    @Test
    public void subFailAttack() {
        Map map = new Map();
        map.placeShips(2, 2, 4, 'V', true, "submarine");
        map.getAttacked(2, 2, new Weapon("mine"));
        map.getMaps();
    }

    @Test
    public void subSurfaceAttack() {
        Map map = new Map();
        map.placeShips(2, 2, 4, 'V', false, "submarine");
        map.getAttacked(5, 2, new Weapon("mine"));
        map.getAttacked(4, 2, new Weapon("mine"));
        map.getMaps();
        map.getAttacked(5, 2, new Weapon("mine"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void moveTest() {
        Map map = new Map();
        map.placeShips(1, 4, 2, 'H', false, "minesweeper");
        map.getMaps();
        map.moveShips('N');
        map.getMaps();
    }
    @Test
    public void moveOutOfRange() {
        Map map = new Map();
        map.placeShips(0, 4, 2, 'H', false, "minesweeper");
        map.getMaps();
        map.moveShips('N');
        map.getMaps();
    }
    @Test
    public void moveAgain() {
        Map map = new Map();
        map.placeShips(2, 4, 2, 'H', false, "minesweeper");
        map.getMaps();
        map.moveShips('E');
        map.getMaps();
        map.moveShips('W');
        map.getMaps();
        map.moveShips('E');
        map.getMaps();
    }
    @Test
    public void undoMove() {
        Map map = new Map();
        map.placeShips(2, 4, 2, 'H', false, "minesweeper");
        map.getMaps();
        map.moveShips('N');
        map.getMaps();
        map.Undo();
        map.getMaps();
    }
    @Test
    public void redoMove(){
        Map map = new Map();
        map.placeShips(1, 4, 2, 'H', false, "minesweeper");
        map.getMaps();
        map.moveShips('N');
        map.getMaps();
        map.Undo();
        map.getMaps();
        map.Redo();
        map.getMaps();
    }

    @Test
    public void spaceLaser() {
        Map map = new Map();
        map.placeShips(2, 2, 4, 'V', true, "submarine");
        map.placeShips(1, 4, 2, 'H', false, "minesweeper");
        map.getAttacked(1, 4, new Weapon("mine"));
        map.getAttacked(5, 2, new Weapon("space_laser"));
        map.getAttacked(5, 2, new Weapon("space_laser"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void spaceLaserDoubleHit() {
        Map map = new Map();
        map.placeShips(2, 2, 4, 'V', true, "submarine");
        map.placeShips(1, 4, 2, 'H', false, "minesweeper");
        map.placeShips(3, 2, 4, 'V', false, "battleship");
        map.getAttacked(1, 4, new Weapon("mine"));
        map.getAttacked(1, 5, new Weapon("mine"));
        map.getAttacked(5, 2, new Weapon("space_laser"));
        map.getAttacked(5, 2, new Weapon("space_laser"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void spaceLaserSubmarineHit() {
        Map map = new Map();
        map.placeShips(2, 2, 4, 'V', true, "submarine");
        map.placeShips(2, 9, 4, 'V', true, "submarine");
        map.placeShips(1, 4, 2, 'H', false, "minesweeper");
        map.getAttacked(1, 4, new Weapon("mine"));
        map.getAttacked(1, 5, new Weapon("mine"));
        map.getAttacked(2, 2, new Weapon("space_laser"));
        map.getAttacked(3, 2, new Weapon("space_laser"));
        map.getAttacked(5, 2, new Weapon("space_laser"));
        map.getAttacked(5, 2, new Weapon("space_laser"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void invisibilityTest() {
        Map map = new Map();
        map.placeShips(2, 2, 4, 'V', true, "submarine");
        map.placeShips(7, 4, 2, 'H', false, "minesweeper");
        map.placeShips(1, 1, 4, 'H', false, "battleship");
        map.placeShips(5, 5, 3, 'H', false, "destroyer");
        map.getMaps();
        map.getAttacked(7, 4, new Weapon("mine"));
        map.getAttacked(7, 5, new Weapon("mine"));
        map.sonarPulse(2,2);
        map.makeInvisible("battleship");
        map.sonarPulse(2,2);
    }

    @Test
    public void disableTest() {
        Map map = new Map();
        map.placeShips(2, 2, 4, 'V', true, "submarine");
        map.placeShips(7, 4, 2, 'H', false, "minesweeper");
        map.placeShips(1, 1, 4, 'H', false, "battleship");
        map.getMaps();
        map.getAttacked(5, 2, new Weapon("stopper"));
        map.moveShips('N');
        map.makeInvisible("minesweeper");
        map.getMaps();
    }
    @Test
    public void createBlucifer() {
        Map map = new Map();
        map.placeShips(2, 4, 1, 'V', false, "blucifer");
        map.getMaps();
    }
    @Test
    public void attackBlucifer(){
        Map map = new Map();
        map.placeShips(2, 2, 4, 'V', true, "submarine");
        map.placeShips(7, 4, 2, 'H', false, "minesweeper");
        map.placeShips(1, 1, 4, 'H', false, "battleship");
        map.placeShips(2, 4, 1, 'V', false, "blucifer");
        map.getMaps();
        map.getAttacked(2,4,new Weapon("mine"));
        map.getMaps();
    }
    @Test
    public void checkNumberOfPoints(){
        Map map = new Map();
        map.placeShips(7, 4, 2, 'H', false, "minesweeper");
        map.placeShips(1, 1, 4, 'H', false, "battleship");
        map.placeShips(2, 4, 1, 'V', false, "blucifer");
        map.getMaps();
        map.getAttacked(7,4,new Weapon("mine"));
        map.getPoints();
        map.getAttacked(1,1,new Weapon("mine"));
        map.getPoints();
        map.getAttacked(2,4,new Weapon("mine"));
        map.getPoints();
    }
    @Test
    public void submergedDestroyer() {
        Map map = new Map();
        map.placeShips(2, 2, 4, 'V', true, "destroyer");
        map.placeShips(1, 4, 2, 'H', false, "minesweeper");
        map.getMaps();
        map.getAttacked(1, 4, new Weapon("mine"));
        map.getAttacked(1, 5, new Weapon("mine"));
        assertThat(map.hasShips(), is(true));
        map.getAttacked(4, 2, new Weapon("space_laser"));
        map.getAttacked(4, 2, new Weapon("space_laser"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void submergedMinesweeper() {
        Map map = new Map();
        map.placeShips(2, 2, 2, 'V', true, "minesweeper");
        map.placeShips(1, 4, 2, 'H', false, "minesweeper");
        map.getMaps();
        map.getAttacked(1, 4, new Weapon("mine"));
        map.getAttacked(1, 5, new Weapon("mine"));
        assertThat(map.hasShips(), is(true));
        map.getAttacked(2, 2, new Weapon("space_laser"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

    @Test
    public void submergedBattleship() {
        Map map = new Map();
        map.placeShips(3, 5, 4, 'V', true, "battleship");
        map.placeShips(1, 4, 2, 'H', false, "minesweeper");
        map.getMaps();
        map.getAttacked(1, 4, new Weapon("mine"));
        map.getAttacked(1, 5, new Weapon("mine"));
        assertThat(map.hasShips(), is(true));
        map.getAttacked(5, 5, new Weapon("space_laser"));
        map.getAttacked(5, 5, new Weapon("space_laser"));
        map.getMaps();
        assertThat(map.hasShips(), is(false));
    }

}

