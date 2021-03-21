package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.Map;
import edu.colorado.dreamteam.java.Player;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerTest {
    @Test
    public void playerCreated1() {
        Player player = new Player("test", new Map());
        assertThat(player.getName(), is("test"));
    }

    //No ships, so not alive
    @Test
    public void playerIsAlive1() {
        Player player = new Player("test", new Map());
        player.placeShips(1, 2, 2, 'V', false, "minesweeper");
        assertThat(player.isAlive(), is(true));
    }

    //No ships, so not alive
    @Test
    public void playerIsAlive2() {
        Player player = new Player("test", new Map());
        assertThat(player.isAlive(), is(false));
    }

    //Had ships but lost ships, so dead
    @Test
    public void playerIsAlive3() {
        Player player = new Player("test", new Map());
        player.placeShips(1, 2, 2, 'V', false, "minesweeper");
        assertThat(player.isAlive(), is(true));
        player.getAttacked(1, 2, "mine");
        player.getAttacked(2, 2, "mine");
        assertThat(player.isAlive(), is(false));
    }

    //Player has multiple ships
    @Test
    public void playerIsAlive4() {
        Player player = new Player("test", new Map());
        player.placeShips(1, 2, 2, 'V', false, "minesweeper");
        player.placeShips(4, 7, 2, 'H', false, "minesweeper");
        assertThat(player.isAlive(), is(true));
        player.getAttacked(1, 2, "mine");
        player.getAttacked(2, 2, "mine");
        assertThat(player.isAlive(), is(false));
    }
}
