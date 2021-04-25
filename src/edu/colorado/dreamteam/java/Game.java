package edu.colorado.dreamteam.java;

import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;

    public Game() {
        playGame();
    }

    public void playGame() {
        Scanner sc = new Scanner(System.in);

        //Initialize players and their maps
        player1 = UI.initPlayer(sc);
        player2 = UI.initPlayer(sc);

        //Start game and keep going until someone wins
        boolean player1Turn = true;
        Coordinate coors;
        int row;
        int col;
        String attackType;

        while(player1.isAlive() && player2.isAlive()) {
            if(player1Turn) {
                player2.displayFullMap();
                coors = UI.getAttack(sc, player1, player2);
                row = coors.getX();
                col = coors.getY();
                attackType = coors.getAttackType();
                player2.getAttacked(row,col,attackType);
            } else {
                player1.displayFullMap();
                coors = UI.getAttack(sc, player2, player1);
                row = coors.getX();
                col = coors.getY();
                attackType = coors.getAttackType();
                player1.getAttacked(row,col,attackType);
            }
            player1Turn = !player1Turn;
        }

        if(player1.isAlive()) {
            UI.congratulatePlayer(player1);
        } else {
            UI.congratulatePlayer(player2);
        }
    }
}
