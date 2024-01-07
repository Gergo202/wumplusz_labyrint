package org.example;

import java.util.Scanner;

/**
 * Menu class handle the game's menu.
 */
public class Menu {
    /** The size of the map. */
    private static final int DEFAULT_SIZE = 10;

    /** Get the DEFAULT_SIZE. */
    public static int getDefaultSize() {
        return DEFAULT_SIZE;
    }

    /** Contain the selected map. */
    private char[][] selectedMap =
            new char[DEFAULT_SIZE][DEFAULT_SIZE];

    /** Get the selectedMap. */
    public char[][] getSelectedMap() {
        return selectedMap;
    }

    /** Handle the menu. */
    public void showMenu() {
        Highscore highscore = new Highscore();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input username: ");
        String username = scanner.nextLine();
        highscore.usernameToDatabase(username);

        boolean exit = false;
        MapCreator mapCreator = new MapCreator();
        MapOpen mapOpen = new MapOpen();
        Game game = new Game();

        while (!exit) {
            System.out.println("1. Map creator");
            System.out.println("2. Saved maps");
            System.out.println("3. Play");
            System.out.println("4. Quit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Loading map creator");
                    mapCreator.startMapCreator();
                    break;
                case "2":
                    System.out.println("Loading saved maps.");
                    selectedMap = mapOpen.mapOpen();
                    break;
                case "3":
                    if (selectedMap != null) {
                        System.out.println("Starting game...");
                        game.playGame(selectedMap);
                    } else {
                        System.out.println(
                                "Please chose map first"
                                        + " in the Saved maps menu.");
                    }
                    break;
                case "4":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        System.out.println("Goodbye, " + username + "!");
    }
}
