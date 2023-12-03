package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MapCreator {
    public void startMapCreator() {
        Scanner scanner = new Scanner(System.in);

        int N = 10;
        int wumpusCount = 2;
        int goldCount = 1;

        char[][] map = initMap(N);

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
                if(placeY != 1 && placeX != 1 && placeX < 10 && placeY < 10) {
                    switch (elemChoice) {
                        case 1:
                            addElement(map, placeX, placeY, 'W');
                            break;
                        case 2:
                            addElement(map, placeX, placeY, 'P');
                            break;
                        case 3:
                            if (wumpusCount > 0) {
                                addElement(map, placeX, placeY, 'M');
                                wumpusCount--;
                            } else {
                                System.out.println("No more wumpus you can add.");
                            }
                            break;
                        case 4:
                            if (goldCount > 0) {
                                addElement(map, placeX, placeY, 'G');
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
                saveMapToFile(map, filename + ".txt");
                break;
            } else {
                System.out.println("Wrong option.");
            }
        }

        System.out.println("Quiting map creator.");
    }

    public static void saveMapToFile(char[][] map, String filename) {
        String directory = "saved_maps/";
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            if (directoryFile.mkdirs()) {
                System.out.println("Directory didn't exist, creating one");
            } else {
                System.out.println("Directory didn't exist, failed to create it");
                return;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + filename))) {
            for (int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[i].length; j++) {
                    char element = map[i][j];
                    char symbol = ' ';
                    if (i ==1 && j == 1){
                        symbol = '→';
                    }  else {
                        symbol = getElementSymbol(element);
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

    public static char[][] initMap(int N) {
        char[][] map = new char[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || j == 0 || i == N - 1|| j == N - 1) {
                    map[i][j] = 'W';
                } else {
                    map[i][j] = ' ';
                }
            }
        }
        return map;
    }

    public static void drawMap(char[][] map,int N, int heroX, int heroY, char heroDirection) {
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

    public static char getElementSymbol(char element) {
        switch (element) {
            case 'W':
                return '+';
            case 'P':
                return 'O';
            case 'M':
                return 'M';
            case 'G':
                return '*';
            default:
                return '_';
        }
    }

    public static void addElement(char[][] map, int placeX, int placeY, char elem) {
        if (placeX >= 1 && placeX <= map.length - 2 && placeY >= 1 && placeY <= map[0].length - 2) {
            if(map[placeY][placeX] == ' ') {
                map[placeY][placeX] = elem;
            } else if (map[placeY][placeX] == elem) {
                System.out.println("Same element");
            } else if (placeX == 1 && placeY == 1) {
                System.out.println("There is the hero, place somewhere else");
            } else {
                map[placeY][placeX] = elem;
            }
        }
    }

    public static void removeElement(char[][] map, int placeX, int placeY) {
        if(map[placeX][placeY] != ' '){
            map[placeX][placeY] = ' ';
        } else if(placeX == 1 && placeY == 1) {
            System.out.println("Can't delete it, it is the hero.");
        } else {
            System.out.println("Nothing to delete.");
        }
    }
}
