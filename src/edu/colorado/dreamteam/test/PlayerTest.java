package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

//TODO: Have to create some good tests for Player, we haven't done that yet
//TODO: Need to test if we can sink a ship
//TODO: Need to test if game can be ended by sinking all of the ships
public class PlayerTest {
    @Test
    public void playerCreated() {
        Player player = new Player("test", new Map());
    }

    @Test
    public void playerCreated1() {
        Player player = new Player("test", new Map());
        assertThat(player.getName(), is("test"));
    }

    @Test
    public void playerIsAlive() {
        //create player with map, attack remaining ship, check if isAlive is set to false
    }

    @Test
    public void playerGotAttacked() {
        //create player with map, attack fleet, check if map and fields are updated properly
    }
}
