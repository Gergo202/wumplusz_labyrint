package org.example;

import java.util.Scanner;

public class Menu {
    private char[][] selectedMap = new char[10][10];
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

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Loading map creator");
                    mapCreator.startMapCreator();
                    break;
                case 2:
                    System.out.println("Loading saved maps.");
                    selectedMap = mapOpen.mapOpen();
                    break;
                case 3:
                    if (selectedMap != null) {
                        System.out.println("Starting game...");
                        game.playGame(selectedMap);
                    } else {
                        System.out.println("Please chose map first in the Saved maps menu.");
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        System.out.println("Goodbye, " + username + "!");
    }
}
