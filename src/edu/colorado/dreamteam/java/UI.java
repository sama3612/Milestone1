package edu.colorado.dreamteam.java;
import java.util.Scanner;

// UI class is used by both players, so keep all methods static
// TODO: implement getAttack that takes in user input
// TODO: implement displayMap that displays your own map first (first parameter) and then the opposing map as well (2nd parameter)
// TODO: initialize map for each player based on user input
public class UI {
    public static Player initPlayer() {
        Scanner sc = new Scanner(System.in);

        System.out.println("What's your name?");
        String name = sc.nextLine();
        System.out.println("Awesome, good luck " + name);

        //ADD CODE HERE TO INITIALIZE A MAP FOR PLAYER AND RETURN PLAYER WITH THEIR MAP

        return new Player(name, map);
    }

    public static Coordinate getAttack(Player player) {
        return null;
    }

    public static void displayMap(Player player2, Player player1) {
    }
}
