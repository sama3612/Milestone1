package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ShipTest {
    @Test
    public void shipCreated() {
        Ship ship = new Ship("destroyer",3, new Coordinate[]{new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(3, 1)});
        assertThat(ship.getName(), is("destroyer"));
        assertThat(ship.getHealth(), is(3));
        assertThat(ship.getCoordinates()[0].getX(), is(1));
        assertThat(ship.getCoordinates()[0].getY(), is(1));
    }

    @Test
    public void shipWasNotHit() {
        Ship ship = new Ship("destroyer",3, new Coordinate[]{new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(3, 1)});
        assertThat(ship.getHealth(),is(3));
        ship.getAttacked(1,5);
        assertThat(ship.getHealth(),is(3));
    }

    @Test
    public void shipWasHit() {
        Ship ship = new Ship("destroyer",3, new Coordinate[]{new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(3, 1)});
        assertThat(ship.getHealth(),is(3));
        ship.getAttacked(3,1);
        assertThat(ship.getHealth(),is(2));
    }

    @Test
    public void shipIsNotSunk() {
        Ship ship = new Ship("destroyer",3, new Coordinate[]{new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(3, 1)});
        assertThat(ship.isSunk(), is(false));
        ship.getAttacked(1,5);
        assertThat(ship.isSunk(), is(false));
    }

    @Test
    public void shipIsSunk() {
        Ship ship = new Ship("destroyer",3, new Coordinate[]{new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(3, 1)});
        assertThat(ship.isSunk(), is(false));
        ship.getAttacked(1,1);
        assertThat(ship.isSunk(), is(false));
        ship.getAttacked(1,1);
        assertThat(ship.isSunk(), is(false));
        ship.getAttacked(1,1);
        assertThat(ship.isSunk(), is(true));
    }
}

