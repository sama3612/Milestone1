package edu.colorado.dreamteam.java;
import java.util.Scanner;

public class UI {
    public static Player initPlayer() {
        Scanner sc = new Scanner(System.in);

        System.out.println("What's your name?");
        String name = sc.nextLine();
        System.out.println("Awesome, good luck " + name);

        return new Player(name);
    }
}
