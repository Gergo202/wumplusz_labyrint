package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MapOpen {
    public char[][] mapOpen(){
        Scanner scanner = new Scanner(System.in);
        File savedMapsDirectory = new File("saved_maps");
        File[] mapFiles = savedMapsDirectory.listFiles();

        if (mapFiles == null || mapFiles.length == 0) {
            System.out.println("There is no maps saved.");
            return null;
        }

        System.out.println("Choose a map to load.");
        for (int i = 0; i < mapFiles.length; i++) {
            System.out.println((i + 1) + ". " + mapFiles[i].getName());
        }

        System.out.println("0. quit");
        int choice = scanner.nextInt();

        if (choice == 0) {
            return null;
        }

        if (choice >= 1 && choice <= mapFiles.length) {
            return readMapFile(mapFiles[choice - 1]);
        } else {
            System.out.println("Invalid choice.");
        }
        return null;
    }
     private char[][] readMapFile(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            StringBuilder mapContent = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                mapContent.append(fileScanner.nextLine()).append("\n");
            }

            String[] lines = mapContent.toString().split("\n");
            char[][] mapArray = new char[lines.length][lines[0].length()];
            for (int i = 0; i < lines.length; i++) {
                mapArray[i] = lines[i].toCharArray();
            }

            return mapArray;
        } catch (IOException e) {
            System.out.println("Error with the reading: " + e.getMessage());
            return null;
        }
     }
}
