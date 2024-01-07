package org.example;

import java.util.Scanner;

/** The Game class represents the main game logic. */

public class Game {
    /** Where the player is looking. */
    private char lookNow;

    /** Get the lookNow. */
    public char getLookNow() {
        return lookNow;
    }

    /** Where the player is in the X coordinate. */
    private int heroXNow;

    /** Get the heroXNow. */
    public int getHeroXNow() {
        return heroXNow;
    }

    /** Where the player is in the Y coordinate. */
    private int heroYNow;

    /** Get the heroYNow. */
    public int getHeroYNow() {
        return heroYNow;
    }

    /** Put the last X coordinate. */
    private int pastHeroX;

    /** Get the pastHeroX. */
    public int getPastHeroX() {
        return pastHeroX;
    }

    /** Put the last Y coordinate. */
    private int pastHeroY;

    /** Get the pastHeroY. */
    public int getPastHeroY() {
        return pastHeroY;
    }

    /** The flag indicate whether Wumpus is alive. */
    private boolean wumpusAlive;

    /**
     * Plays the game based on the provided map content.
     *
     * @param mapContent The 2D array representing the game map.
     */
    public void playGame(final char[][] mapContent) {
        Player player = new Player();
        Arrows arrows = new Arrows();
        Highscore highscore = new Highscore();
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (isWumpusAlive(wumpusAlive, mapContent) && !exit) {
            for (int i = 0; i < mapContent.length; i++) {
                for (int j = 0; j < mapContent[i].length; j++) {
                    if (mapContent[i][j] == '→' || mapContent[i][j] == '↓'
                            || mapContent[i][j] == '←'
                            || mapContent[i][j] == '↑') {
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
                case "w":
                case "s":
                    pastHeroX = heroXNow;
                    pastHeroY = heroYNow;
                    mapContent[heroXNow][heroYNow] =
                            player.move(lookNow, button, mapContent,
                                    heroXNow, heroYNow);

                    break;
                case "a":
                case "d":
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
                    break;
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

    /** Get the wumpusAlive. */

    public boolean isWumpusAlive() {
        return wumpusAlive;
    }

    /**
     * Checks if Wumpus is alive on the game map.
     *
     * @param isWumpusAlive Flag indicating whether the Wumpus is alive.
     *
     * @param mapContent The 2D array representing the game map.
     *
     * @return True if the Wumpus is alive, false otherwise.
     */

    public boolean isWumpusAlive(final boolean isWumpusAlive, final char[][] mapContent) {
        boolean newWumpusAlive = isWumpusAlive;
        for (int i = 0; i < mapContent.length; i++) {
            for (int j = 0; j < mapContent[i].length; j++) {
                if (mapContent[i][j] == 'M') {
                    newWumpusAlive = true;
                }
            }
        }
        return newWumpusAlive;
    }
}
