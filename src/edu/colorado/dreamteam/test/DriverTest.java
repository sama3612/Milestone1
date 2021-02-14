package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DriverTest {
    private Driver driver;

    @Before
    public void setup() {
        driver = new Driver();
    }

    @Test
    public void playersAreInitialized() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(("Sai" + System.getProperty("line.separator") + "Andrew" + System.getProperty("line.separator")).getBytes());
        System.setIn(in);

        driver.initializeGame();
        assertThat(driver.getPlayerNames(), is("Sai, Andrew"));

        // reset System.in to its original
        System.setIn(sysInBackup);
    }

    @Test
    public void gameSwitchesTurns() {
        //Start game and see if each player gets a turn and if the turns switch properly
    }

    @Test
    public void checkIfGameIsOver() {
        //Start a game and see if it ends if a player loses
    }

    @Test
    public void playerIsAlive() {
        //create player with map, attack remaining ship, check if isAlive is set to false
    }
}
