package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MapCreator {
    public void startMapCreator() {
        Scanner scanner = new Scanner(System.in);

        int N = 10;
        int wumpusCount = 2;
        int goldCount = 1;

        String[][] map = initMap(N);

        int heroX = 1;
        int heroY = 1;
        char heroDirection = 'E';

        while (true) {
            drawMap(map, N, heroX, heroY, heroDirection);

            System.out.println("Chose an option:");
            System.out.println("1. Add element");
            System.out.println("2. Remove element");
            System.out.println("3. Finish");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Chose an element:");
                System.out.println("1. WALL");
                System.out.println("2. PIT");
                System.out.println("3. WUMPUS");
                System.out.println("4. GOLD");

                int elemChoice = scanner.nextInt();
                System.out.println("Place: X=");
                int placeX = scanner.nextInt();
                System.out.println("Y=");
                int placeY = scanner.nextInt();
                if(placeY != 1 && placeX != 1) {
                    switch (elemChoice) {
                        case 1:
                            addElement(map, placeX, placeY, "WALL");
                            break;
                        case 2:
                            addElement(map, placeX, placeY, "PIT");
                            break;
                        case 3:
                            if (wumpusCount > 0) {
                                addElement(map, placeX, placeY, "WUMPUS");
                                wumpusCount--;
                            } else {
                                System.out.println("No more wumpus you can add.");
                            }
                            break;
                        case 4:
                            if (goldCount > 0) {
                                addElement(map, placeX, placeY, "GOLD");
                                goldCount--;
                            } else {
                                System.out.println("No more gold you can add.");
                            }
                            break;
                        default:
                            System.out.println("Wrong element.");
                    }
                } else {
                    System.out.println("Can't place there, there's the hero.");
                }
            } else if (choice == 2) {
                System.out.println("Where: X=");
                int placeX = scanner.nextInt();
                System.out.println("Y=");
                int placeY = scanner.nextInt();
                removeElement(map, placeX, placeY);
            } else if (choice == 3) {
                System.out.println("Enter the map name: ");
                String filename = scanner.next();
                saveMapToFile(map, filename);
                break;
            } else {
                System.out.println("Wrong option.");
            }
        }

        System.out.println("Quiting map creator.");
    }

    public static void saveMapToFile(String[][] map, String filename) {
        String directory = "saved_maps/";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + filename))) {
            for (int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[i].length; j++) {
                    String element = map[i][j];
                    String symbol = "";
                    if (i ==1 && j == 1){
                        symbol = "→";
                    }
                    switch (element) {
                        case "_":
                            symbol = "_";
                            break;
                        case "W":
                            symbol = "+";
                            break;
                        case "P":
                            symbol = "O";
                            break;
                        case "M":
                            symbol = "M";
                            break;
                        case "G":
                            symbol = "*";
                            break;
                    }
                    writer.write(symbol);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while saving.");
        }
    }

    public static String[][] initMap(int N) {
        String[][] map = new String[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || j == 0 || i == N - 1|| j == N - 1) {
                    map[i][j] = "WALL";
                } else {
                    map[i][j] = "EMPTY";
                }
            }
        }
        return map;
    }

    public static void drawMap(String[][] map,int N, int heroX, int heroY, char heroDirection) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (i == heroY && j == heroX) {
                    System.out.print("H" + heroDirection);
                } else {
                    System.out.print(getElementSymbol(map[i][j]) + " ");
                }
            }
            System.out.println();
        }
    }

    public static String getElementSymbol(String element) {
        switch (element) {
            case "WALL":
                return "W";
            case "PIT":
                return "P";
            case "WUMPUS":
                return "M";
            case "GOLD":
                return "G";
            default:
                return "_";
        }
    }

    public static void addElement(String[][] map, int placeX, int placeY, String elem) {
        if (placeX >= 1 && placeX <= map.length - 2 && placeY >= 1 && placeY <= map[0].length - 2) {
            if(map[placeY][placeX] == "EMPTY") {
                map[placeY][placeX] = elem;
            } else if (map[placeY][placeX].equals(elem)) {
                System.out.println("Same element");
            } else if (placeX == 1 && placeY == 1) {
                System.out.println("There is the hero, place somewhere else");
            } else {
                map[placeY][placeX] = elem;
            }
        }
    }

    public static void removeElement(String[][] map, int placeX, int placeY) {
        if(map[placeX][placeY] != "EMPTY"){
            map[placeX][placeY] = "EMPTY";
        } else if(placeX == 1 && placeY == 1) {
            System.out.println("Can't delete it, it is the hero.");
        } else {
            System.out.println("Nothing to delete.");
        }
    }
}
