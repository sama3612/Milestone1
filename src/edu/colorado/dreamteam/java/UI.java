package edu.colorado.dreamteam.java;
import java.util.Scanner;

/**
 * Class for the UI
 */
public class UI {
    /**
     * Iniitalize the player with a name
     * @param sc
     * @return
     */
    public static Player initPlayer(Scanner sc) {
        System.out.println("What's your name?");
        String name = sc.nextLine();
        System.out.println("Awesome, good luck " + name + "!\n\n");

        System.out.println("Now, let's get your map setup!");
        return new Player(name, setupMap(sc, name));
    }

    /**
     * Setup the map for the player by setting up each ship on the board
     * @param sc
     * @param name
     * @return
     */
    private static Map setupMap(Scanner sc, String name) {
        Map map = new Map();
        setupShip(sc,2,"minesweeper",map);
        setupShip(sc,3,"destroyer", map);
        setupShip(sc,4,"battleship",map);
        setupShip(sc,4,"submarine",map);
        setupShip(sc,1,"blucifer",map);
        System.out.println("Great!! Your map is setup now " + name + ", here's what it looks like.");
        map.getMaps();
        System.out.println("\n\n\n");
        return map;
    }

    /**
     * Setup the ship based on the user input, keep going until the ship is constructed properly
     * @param sc
     * @param length
     * @param shipType
     * @param map
     */
    private static void setupShip(Scanner sc, int length, String shipType, Map map) {
        System.out.println("Here's your map so far:");
        map.getMaps();
        System.out.println("\n\nWhere do you want your " + shipType + "? (Enter coordinates as (row,col) -- don't include the parenthesis");
        String coors = sc.nextLine();
        int row = Integer.parseInt(coors.substring(0,coors.indexOf(',')));
        int col = Integer.parseInt(coors.substring(coors.indexOf(',') + 1));
        System.out.println("What orientation do you want the ship in? (H or V)");
        char orientation = sc.nextLine().charAt(0);
        boolean submerged = false;
        if(shipType.equals("submarine")) {
            System.out.println("Do you want this ship submerged? (yes or no)");
            submerged = sc.nextLine().equals("yes");
        }
        if(map.placeShips(row, col, length, orientation, submerged, shipType)) {
            System.out.println("Nice, your " + shipType + " has been setup!");
        } else {
            System.out.println("That was not valid, let's try that again...\n");
            setupShip(sc, length, shipType, map);
        }
    }

    /**
     * Get the attack that the user wants to use against the other player
     * @param sc
     * @param player
     * @param player2
     * @return
     */
    public static Coordinate getAttack(Scanner sc, Player player, Player player2) {
        System.out.println("Here is the enemy- " + player.getName() + "'s - map so far. Your hits are shown in green and misses are shown in red. ");
        player.displayEnemyMap();
        System.out.println("Where do you want to attack " + player.getName() + "? (Enter coordinates as (row,col) -- don't include the parenthesis");
        String coors = sc.nextLine();
        int row = Integer.parseInt(coors.substring(0,coors.indexOf(',')));
        int col = Integer.parseInt(coors.substring(coors.indexOf(',') + 1));
        System.out.println("What kind of attack do you want to use? (mine, sonar_pulse, space_laser)");
        String attack = sc.nextLine();
        Coordinate coordinate = new Coordinate(row,col);
        coordinate.setAttackType(attack);
        return coordinate;
    }


    /**
     * Simple function to congratulate the player that wins the game
     * @param player1
     */
    public static void congratulatePlayer(Player player1) {
        System.out.println("Congrats " + player1.getName() + ", you won!");
    }
}
