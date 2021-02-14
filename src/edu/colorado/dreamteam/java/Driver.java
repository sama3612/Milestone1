package edu.colorado.dreamteam.java;

// (DONE) TODO: mechanism for switching turns
// (DONE) TODO: loop to keep game going
// (DONE) TODO: figure out if game is over and display winner

public class Driver {
    private Player player1;
    private Player player2;
    private boolean player1Turn;
    private boolean gameOver;

    public Driver() {
        player1Turn = true;
        gameOver = false;
    }

    public void initializeGame() {
        player1 = UI.initPlayer();
        player2 = UI.initPlayer();
    }
    
    public void startGame() {
        while(!gameOver) {
            if(player1Turn) {
                UI.displayMap(player1,player2);
                player2.getAttacked(UI.getAttack(player1));
            } else {
                UI.displayMap(player2,player1);
                player1.getAttacked(UI.getAttack(player2));
            }
            player1Turn = !player1Turn;
            checkIfGameOver();
        }

        if(player1.isAlive()) {
            System.out.println("Congrats " + player1.getName() + ", you won!!!");
        } else {
            System.out.println("Congrats " + player2.getName() + ", you won!!!");
        }
    }

    private void checkIfGameOver() {
        gameOver = player1.isAlive() && player2.isAlive();
    }

    public String getPlayerNames() {
        return player1.getName() + ", " + player2.getName();
    }
}
