package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** The Highscore class handle the scores. */
public class Highscore {
    /** DEFAULT_SCORE has the starting score. */
    private static final int DEFAULT_SCORE = 40000;

    /** Get DEFAULT_SCORE. */

    public static int getDefaultScore() {
        return DEFAULT_SCORE;
    }

    /** Score handle the changes of the starting score. */
    private int score = DEFAULT_SCORE;

    /** Get the score. */

    public int getScore() {
        return score;
    }

    /** Flag indicating whether the player take a step. */
    private boolean steps = false;

    /** Get the steps. */

    public boolean isSteps() {
        return steps;
    }

    /** Contain the remaining arrows. */
    private int arrowsLeft;

    /** Get the arrowsLeft. */

    public int getArrowsLeft() {
        return arrowsLeft;
    }

    /** Flag indicating whether the gold get picked up.*/
    private boolean gold = false;

    /** Get the gold. */

    public boolean isGold() {
        return gold;
    }

    /** Count the taken steps. */
    private int countSteps = 0;

    /** Get the countSteps. */

    public int getCountSteps() {
        return countSteps;
    }

    /** Saving the player's username. */
    private String usernameSave;

    /** Get the usernameSave. */

    public String getUsernameSave() {
        return usernameSave;
    }

    /** The method add score using the remaining arrows.
     *
     * @param remainingArrows Contain the remaining arrows.
     */
    public void arrowsCount(final int remainingArrows) {
        final int addThousand = 1000;
        score = score + (remainingArrows * addThousand);
    }

    /** The method remove score and add step.
     *
     * @param tookStep True if the player moved.
     */

    public void stepsCount(final boolean tookStep) {
        if (tookStep) {
            final int addHundred = 100;
            score -= addHundred;
            countSteps++;
        }
    }

    /** The method add score when the gold has been picked up.
     *
     * @param goldPickedUp True if the gold is picked up.
     */
    public void getGold(final boolean goldPickedUp) {
        if (goldPickedUp) {
            final int addFiveHundred = 500;
            score = score + addFiveHundred;
        }
    }

    /** Put the username into another char which
     * we use to save it to the database.
     *
     * @param username Contain the username the player gave.
     */
    public void usernameToDatabase(final String username) {
        usernameSave = username;
    }

    /** The method handle the h2 database. */
    public void writeScore() {
        System.out.println("Your score: " + score);
        System.out.println("Your steps: " + countSteps);
        try (Connection connection = DriverManager.getConnection(
                "jdbc:h2:~/test", "wumpusGergo", "asdasd")) {
            String insertLastPlayer =
                    "INSERT INTO highscores(users, scores) VALUES(?,?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertLastPlayer);
            preparedStatement.setString(1, usernameSave);
            preparedStatement.setInt(2, score);

            String listAllScoreQuery = "SELECT * FROM SCORES";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(listAllScoreQuery);

            List<WriteScore> writeScores = new ArrayList<>();

            while (resultSet.next()) {
                String users = resultSet.getString("users");
                int scores = resultSet.getInt("scores");
                WriteScore writeScore = new WriteScore(users, scores);
                writeScores.add(writeScore);
            }

            System.out.println(writeScores);

            preparedStatement.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Unexpected error happened!");
        }
    }
}
