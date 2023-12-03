package org.example;

import java.util.Scanner;

public class Game {
    private char lookNow;
    private int heroXNow;
    private int heroYNow;
    private int pastHeroX;
    private int pastHeroY;
    private boolean wumpusAlive;
    public void playGame(char[][] mapContent){
        Player player = new Player();
        Arrows arrows = new Arrows();
        Highscore highscore = new Highscore();
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);


        while (isWumpusAlive(wumpusAlive, mapContent) && !exit){
            for (int i = 0; i < mapContent.length; i++){
                for (int j = 0; j < mapContent[i].length; j++){
                    if (mapContent[i][j] == '→' || mapContent[i][j] == '↓'
                            || mapContent[i][j] == '←' || mapContent[i][j] == '↑') {
                        heroXNow = i;
                        heroYNow = j;
                        lookNow = mapContent[i][j];
                        break;
                    }
                }
            }

            for (int i = 0; i < mapContent.length; i++) {
                for (int j = 0; j < mapContent[i].length; j++) {
                    System.out.print(mapContent[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println("Arrows: " + arrows.countArrows());
            System.out.println("1. Quit");

            if (arrows.countArrows() == 0) {
                System.out.println("Out of arrows!");
                break;
            }

            String button = scanner.nextLine();

            switch (button) {
                case "w", "s":
                    pastHeroX = heroXNow;
                    pastHeroY = heroYNow;
                    mapContent[heroXNow][heroYNow] = player.move(lookNow, button, mapContent, heroXNow, heroYNow);

                    break;
                case "a", "d":
                    lookNow = player.lookChange(lookNow, button);
                    mapContent[heroXNow][heroYNow] = lookNow;
                    break;
                case "f":
                    arrows.setShootArrow(true);
                    if (lookNow == '→') {
                        for (int i = heroYNow; i < mapContent.length; i++) {
                            if (mapContent[heroXNow][i] == 'M') {
                                mapContent[heroXNow][i] = '_';
                                break;
                            } else if (mapContent[heroXNow][i] == '+') {
                                break;
                            }
                        }
                    } else if (lookNow == '↓') {
                        for (int i = heroXNow; i < mapContent.length; i++) {
                            if (mapContent[i][heroYNow] == 'M') {
                                mapContent[i][heroYNow] = '_';
                                break;
                            } else if (mapContent[i][heroYNow] == '+') {
                                break;
                            }
                        }
                    } else if (lookNow == '←') {
                        for (int i = heroYNow; i > 0; i--) {
                            if (mapContent[heroXNow][i] == 'M') {
                                mapContent[heroXNow][i] = '_';
                                break;
                            } else if (mapContent[heroXNow][i] == '+') {
                                break;
                            }
                        }
                    } else {
                        for (int i = heroXNow; i > 0; i--) {
                            if (mapContent[i][heroYNow] == 'M') {
                                mapContent[i][heroYNow] = '_';
                                break;
                            } else if (mapContent[i][heroYNow] == '+') {
                                break;
                            }
                        }
                    }
                case "1":
                    System.out.println("Quiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

            wumpusAlive = isWumpusAlive(wumpusAlive, mapContent);

        }
        if (!wumpusAlive) {
            System.out.println("Wumpus has been defeated!");
            arrows.score();
            highscore.writeScore();
        }
    }
    public boolean isWumpusAlive(boolean wumpusAlive, char mapContent[][]){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (mapContent[i][j] == 'M'){
                    wumpusAlive = true;
                }
            }
        }
        return wumpusAlive;
    }
}
