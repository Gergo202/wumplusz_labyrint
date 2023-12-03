package org.example;

public class Arrows {
    private int arrows = 5;
    private boolean shootArrow = false;
    private boolean fallPit = false;
    public int getArrows() {
        return arrows;
    }
    public void setShootArrow(boolean shootArrow) {
        this.shootArrow = shootArrow;
        if (shootArrow && arrows > 0){
            loseArrow();
        }
    }
    public void loseArrow(){
        if (arrows > 0)
            arrows--;
    }
    public int countArrows() {
        return arrows;
    }
    public void score(){
        Highscore highscore = new Highscore();
        highscore.arrowsCount(arrows);
    }
}
