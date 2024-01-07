package org.example;

/** Save and show the current scores. */
public class WriteScore {
    /** Contain the username. */
    private String names;

    /** Get the names. */
    public String getNames() {
        return names;
    }

    /** Contain the score. */
    private int scores;

    /** Get the scores. */
    public int getScores() {
        return scores;
    }

    /**
     * WriteScore's construction.
     *
     * @param userName Get the username.
     *
     * @param finalScore Get the score the player got.
     */
    public WriteScore(final String userName, final int finalScore) {
        this.names = userName;
        this.scores = finalScore;
    }

    /** Will write the name and the score. */
    @Override
    public String toString() {
        return "Username: '" + names + '\''
                + " Highscores: " + scores;
    }
}
