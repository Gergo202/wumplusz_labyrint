package org.example;

public class Player {
    Arrows arrows = new Arrows();
    private int heroX;
    private int heroY;
    private char look;
    private String input;
    private int newHeroX;
    private int newHeroY;

    public int getHeroX() {
        return heroX;
    }

    public int getHeroY() {
        return heroY;
    }

    public char getLook() { return look; }

    public String getInput() { return input; }

    public void setHeroX(int heroX) {
        this.heroX = heroX;
    }

    public void setHeroY(int heroY) {
        this.heroY = heroY;
    }

    public void setLook(char look) {
        this.look = look;
    }

    public void setInput(String input) {
        this.input = input;
    }
    public char lookChange(char look, String input) {
        switch (input) {
            case "a" -> {
                return switch (look) {
                    case '→' -> '↑';
                    case '↑' -> '←';
                    case '←' -> '↓';
                    case '↓' -> '→';
                    default -> look;
                };
            }
            case "d" -> {
                return switch (look) {
                    case '→' -> '↓';
                    case '↓' -> '←';
                    case '←' -> '↑';
                    case '↑' -> '→';
                    default -> look;
                };
            }
            default -> {
                return look;
            }
        }
    }

    public char move(char look, String input, char[][] mapContent, int heroX, int heroY) {
        newHeroX = heroX;
        newHeroY = heroY;
        switch (input) {
            case "w" -> {
                if (look == '→') {
                    newHeroY++;
                } else if (look == '↓') {
                    newHeroX++;
                } else if (look == '←') {
                    newHeroY--;
                } else {
                    newHeroX--;
                }
            }
            case "s" -> {
                if (look == '→') {
                    newHeroY--;
                } else if (look == '↓') {
                    newHeroX--;
                } else if (look == '←') {
                    newHeroY++;
                } else {
                    newHeroX++;
                }
            }
        }

        if (isValidPosition(newHeroX, newHeroY, mapContent)) {
            mapContent[newHeroX][newHeroY] = look;
            heroX = newHeroX;
            heroY = newHeroY;
        }

        Highscore highscore = new Highscore();
        highscore.stepsCount(true);

        return mapContent[heroX][heroY];
    }

    public boolean isValidPosition(int placeX, int placeY, char[][] mapContent) {
        if (mapContent[placeX][placeY] == 'O') {
            arrows.loseArrow();
        }

        return mapContent[placeX][placeY] == '_' || mapContent[placeX][placeY] == '*';
    }
}
