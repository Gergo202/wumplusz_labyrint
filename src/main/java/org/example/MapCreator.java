package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** The class handle map making. */
public class MapCreator {

    /** The size of the map. */
    private static final int DEFAULT_MAPSIZE = 10;

    /** Get the DEAULT_MAPSIZE. */

    public static int getDefaultMapsize() {
        return DEFAULT_MAPSIZE;
    }

    /** The number of wumpus allowed. */
    private static final int DEFAULT_WUMPUS = 2;

    /** Get the DEFAULT_WUMPUS. */

    public static int getDefaultWumpus() {
        return DEFAULT_WUMPUS;
    }

    /** The number of gold allowed. */
    private static final int DEFAULT_GOLD = 2;

    /** Get the DEFAULT_GOLD. */

    public static int getDefaultGold() {
        return DEFAULT_GOLD;
    }

    /**
     * Handle the map creation.
     */
    public void startMapCreator() {
        Scanner scanner = new Scanner(System.in);

        int sizeOfMap = DEFAULT_MAPSIZE;
        int wumpusCount = DEFAULT_WUMPUS;
        int goldCount = DEFAULT_GOLD;

        char[][] map = initMap(sizeOfMap);

        int heroX = 1;
        int heroY = 1;
        char heroDirection = 'E';

        while (true) {
            drawMap(map, sizeOfMap, heroX, heroY, heroDirection);

            System.out.println("Chose an option:");
            System.out.println("1. Add element");
            System.out.println("2. Remove element");
            System.out.println("3. Finish");

            String choice = scanner.nextLine();

            if (choice == "1") {
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
                if (placeY != 1 && placeX != 1
                        && placeX < sizeOfMap && placeY < sizeOfMap) {
                    switch (elemChoice) {
                        case '1':
                            addElement(map, placeX, placeY, 'W');
                            break;
                        case'2':
                            addElement(map, placeX, placeY, 'P');
                            break;
                        case '3':
                            if (wumpusCount > 0) {
                                addElement(map, placeX, placeY, 'M');
                                wumpusCount--;
                            } else {
                                System.out.println(
                                        "No more wumpus you can add.");
                            }
                            break;
                        case '4':
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
            } else if (choice == "2") {
                System.out.println("Where: X=");
                int placeX = scanner.nextInt();
                System.out.println("Y=");
                int placeY = scanner.nextInt();
                removeElement(map, placeX, placeY);
            } else if (choice == "3") {
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

    /**
     * Save the map to a text file.
     *
     * @param map Contain the map.
     *
     * @param filename The name of the text file.
     */
    public static void saveMapToFile(
            final char[][] map, final String filename) {
        String directory = "saved_maps/";
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            if (directoryFile.mkdirs()) {
                System.out.println(
                        "Directory didn't exist, creating one");
            } else {
                System.out.println(
                        "Directory didn't exist, failed to create it");
                return;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(directory + filename))) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    char element = map[i][j];
                    char symbol = ' ';
                    if (i == 1 && j == 1) {
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

    /**
     * Create the map.
     *
     * @param mapSize Give the map size.
     *
     * @return Created default map.
     */
    public static char[][] initMap(final int mapSize) {
        char[][] map = new char[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (i == 0 || j == 0 || i == mapSize - 1
                        || j == mapSize - 1) {
                    map[i][j] = 'W';
                } else {
                    map[i][j] = ' ';
                }
            }
        }
        return map;
    }

    /**
     * Method do the map draw.
     *
     * @param map Contain the map.
     *
     * @param size Size of the map.
     *
     * @param heroX Hero X coordinate.
     *
     * @param heroY Hero Y coordinate.
     *
     * @param heroDirection Where the hero is looking.
     */
    public static void drawMap(
            final char[][] map, final int size,
            final int heroX, final int heroY,
            final char heroDirection) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == heroY && j == heroX) {
                    System.out.print("H" + heroDirection);
                } else {
                    System.out.print(getElementSymbol(map[i][j]) + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Change the symbols.
     *
     * @param element Contain the map's element.
     *
     * @return New symbols.
     */
    public static char getElementSymbol(
            final char element) {
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

    /**
     * Place elements the given coordination.
     *
     * @param map Map.
     *
     * @param placeX X coordinate to place.
     *
     * @param placeY Y coordinate to place.
     *
     * @param elem What elements to put.
     */
    public static void addElement(
            final char[][] map, final int placeX,
            final int placeY, final char elem) {
        if (placeX >= 1 && placeX <= map.length - 2
                && placeY >= 1 && placeY <= map[0].length - 2) {
            if (map[placeY][placeX] == ' ') {
                map[placeY][placeX] = elem;
            } else if (map[placeY][placeX] == elem) {
                System.out.println("Same element");
            } else if (placeX == 1 && placeY == 1) {
                System.out.println(
                        "There is the hero, place somewhere else");
            } else {
                map[placeY][placeX] = elem;
            }
        }
    }

    /**
     * Remove the given element.
     *
     * @param map Map.
     *
     * @param placeX Given X coordinate.
     *
     * @param placeY Given Y coordinate.
     */
    public static void removeElement(
            final char[][] map, final int placeX,
            final int placeY) {
        if (map[placeX][placeY] != ' ') {
            map[placeX][placeY] = ' ';
        } else if (placeX == 1 && placeY == 1) {
            System.out.println("Can't delete it, it is the hero.");
        } else {
            System.out.println("Nothing to delete.");
        }
    }
}
