package org.example;

public class Player {
    private int heroX;
    private int heroY;
    private char look;
    private int arrows = 5;
    private char input;

    public int getHeroX() {
        return heroX;
    }

    public int getHeroY() {
        return heroY;
    }

    public int getArrows() {
        return arrows;
    }

    public char getLook() { return look; }

    public char getInput() { return input; }

    public char lookChange(char look, char input) {
        switch (input) {
            case 'a':
                switch(look) {
                    case '→':
                        return '↑';
                    case '↑':
                        return '←';
                    case '←':
                        return '↓';
                    case '↓':
                        return '→';
                    default:
                        return look;
                }
            case 'b':
                switch (look) {
                    case '→':
                        return '↓';
                    case '↓':
                        return '←';
                    case '←':
                        return '↑';
                    case '↑':
                        return '→';
                    default:
                        return look;
                }
            default:
                 return look;
        }
    }
}
