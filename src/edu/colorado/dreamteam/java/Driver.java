package edu.colorado.dreamteam.java;

public class Driver {
    private Player player1;
    private Player player2;

    public Driver() {
    }

    public void initializeGame() {
        player1 = UI.initPlayer();
        player2 = UI.initPlayer();
    }
    
    public void startGame() {
        System.out.println("Game is going to be created now");
    }

    public String getPlayerNames() {
        return player1.getName() + ", " + player2.getName();
    }
}
