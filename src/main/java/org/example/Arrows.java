package org.example;

/** Represents a set of arrows with associated functionality. */
public class Arrows {
    /** DEFAULT_ARROWS is the default arrows before change. */
    private static final int DEFAULT_ARROWS = 5;

    /** Get the DEFAULT_ARROWS. */
    public static int getDefaultArrows() {
        return DEFAULT_ARROWS;
    }

    /** Integer is using the DEFAULT_ARROWS to count the remaining arrows. */
    private int arrows = DEFAULT_ARROWS;

    /** Get the arrows. */
    public int getArrows() {
        return arrows;
    }

    /** Flag indicating whether the arrow is being shot. */
    private boolean shootArrow = false;

    /** Get the shootArrow. */
    public boolean isShootArrow() {
        return shootArrow;
    }

    /** Flag indicating whether the player has fallen into the pit. */
    private boolean fallPit = false;

    /** Get the fallPit. */
    public boolean isFallPit() {
        return fallPit;
    }

    /**
     *Sets the flag indicating whether the arrow is being shot.
     *
     *@param newShootArrow True if the arrow is being shot, false otherwise.
     */
    public void setShootArrow(final boolean newShootArrow) {
        this.shootArrow = newShootArrow;
        if (newShootArrow && arrows > 0) {
            loseArrow();
        }
    }

    /**
     *Decreases the number of arrows by one.
     */
    public void loseArrow() {
        if (arrows > 0) {
            arrows--;
        }
    }

    /**
    *Gets the current count of arrows.
     *
    *@return the number of arrows available.
     */
    public int countArrows() {
        return arrows;
    }

    /**
    *The Highscore use the remaining arrows to turn them into scores.
     */
    public void score() {
        Highscore highscore = new Highscore();
        highscore.arrowsCount(arrows);
    }
}
