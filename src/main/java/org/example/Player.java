package org.example;

/** Handle the movement of the player. */
public class Player {
    /** Contain the hero's X location. */
    private int heroX;

    /** Contain the hero's Y location. */
    private int heroY;

    /** Get the heroX location. */
    public int getHeroX() {
        return heroX;
    }

    /** Get the heroY location. */
    public int getHeroY() {
        return heroY;
    }

    /** Use the key's input. */
    private String input;

    /** Get the input. */
    public String getInput() {
        return input;
    }

    /** New player's X location. */
    private int currentX;

    /** Get the currentX. */
    public int getCurrentX() {
        return currentX;
    }

    /** New player's Y location. */
    private int currentY;

    /** Get the currentY. */
    public int getCurrentY() {
        return currentY;
    }

    /**
     * Manages the player changing facing.
     *
     * @param facing Where the player is looking now.
     *
     * @param keyInput Key input.
     *
     * @return New look.
     */
    public char lookChange(final char facing, final String keyInput) {
        switch (keyInput) {
            case "a":
                switch (facing) {
                    case '→':
                        return '↑';
                    case '↑':
                        return '←';
                    case '←':
                        return '↓';
                    case '↓':
                        return '→';
                    default:
                        return facing;
                }
            case "d":
                switch (facing) {
                    case '→':
                        return '↓';
                    case '↓':
                        return '←';
                    case '←':
                        return '↑';
                    case '↑':
                        return '→';
                    default:
                        return facing;
                }
            default:
                return facing;
        }
    }

    /**
     * Manages the movement.
     *
     * @param facing Where the player is facing.
     *
     * @param keyInput Key input.
     *
     * @param mapContent What the map has.
     *
     * @param locationX Hero's X location.
     *
     * @param locationY Hero's Y location.
     *
     * @return New coordination of the player.
     */
    public char move(final char facing, final String keyInput,
                     final char[][] mapContent,
                     final int locationX, final int locationY) {
        currentX = locationX;
        currentY = locationY;
        switch (keyInput) {
            case "w":
                if (facing == '→') {
                    currentY++;
                } else if (facing == '↓') {
                    currentX++;
                } else if (facing == '←') {
                    currentY--;
                } else {
                    currentX--;
                }
                break;
            case "s":
                if (facing == '→') {
                    currentY--;
                } else if (facing == '↓') {
                    currentX--;
                } else if (facing == '←') {
                    currentY++;
                } else {
                    currentX++;
                }
                break;
            default:
                break;
            }


        if (isValidPosition(currentX, currentY, mapContent)) {
            mapContent[currentX][currentY] = facing;
            heroX = currentX;
            heroY = currentY;
        }

        Highscore highscore = new Highscore();
        highscore.stepsCount(true);

        return mapContent[heroX][heroY];
    }

    /**
     * Search if the player can move into the new place.
     *
     * @param placeX Next X coordination.
     *
     * @param placeY Next Y Coordination.
     *
     * @param mapContent Hold the map's content.
     *
     * @return New location's content.
     */
    public boolean isValidPosition(
            final int placeX, final int placeY,
            final char[][] mapContent) {
        Arrows arrows = new Arrows();
        if (mapContent[placeX][placeY] == 'O') {
            arrows.loseArrow();
        }

        return mapContent[placeX][placeY] == '_'
                || mapContent[placeX][placeY] == '*';
    }
}
