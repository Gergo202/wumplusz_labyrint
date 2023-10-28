package org.example;

import java.util.Scanner;

public class Menu {
    private String selectedMap = null;
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input username: ");
        String username = scanner.nextLine();

        boolean exit = false;
        MapCreator mapCreator = new MapCreator();
        MapOpen mapOpen = new MapOpen();
        GameLauncher gameLauncher = new GameLauncher();

        while (!exit) {
            System.out.println("1. Map creator");
            System.out.println("2. Saved maps");
            System.out.println("3. Load");
            System.out.println("4. Play");
            System.out.println("5. Quit");

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
                    System.out.println("Load option didn't implemented.");
                    break;
                case 4:
                    if (selectedMap != null) {
                        System.out.println("Starting game...");
                        gameLauncher.startGame(selectedMap);
                    } else {
                        System.out.println("Please chose map first in the Saved maps menu.");
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        System.out.println("Goodbye, " + username + "!");
    }
}
