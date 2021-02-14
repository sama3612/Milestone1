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
}
