package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.*;
import org.junit.Before;
import org.junit.Test;

public class DriverTest {
    private Driver driver;

    @Before
    public void setup() {
        driver = new Driver();
    }

    @Test
    public void canStartGame() {
        driver.startGame();
    }
}
