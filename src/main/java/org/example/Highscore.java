package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Highscore {
    private int score = 40000;
    private boolean steps = false;
    private int arrowsLeft;
    private boolean gold = false;
    private int countSteps = 0;
    private String usernameSave;
    Menu menu = new Menu();
    public void arrowsCount(int arrowsLeft) {
        score = score + (arrowsLeft * 1000);
    }
    public void stepsCount(boolean steps) {
        if(steps) {
            score -= 100;
            countSteps++;
        }
    }

    public void getGold(boolean gold){
        if (gold){
            score =+ 500;
        }
    }
    public void usernameToDatabase(String username){
        usernameSave = username;
    }
    public void writeScore(){
        System.out.println("Your score: " + score);
        System.out.println("Your steps: " + countSteps);
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test","wumpusGergo","asdasd")) {
            String insertLastPlayer = "INSERT INTO highscores(users, scores) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertLastPlayer);
            preparedStatement.setString(1, usernameSave);
            preparedStatement.setInt(2, score);

            String listAllScoreQuery = "SELECT * FROM SCORES";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(listAllScoreQuery);

            List<WriteScore> writeScores = new ArrayList<>();

            while(resultSet.next()){
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
        };
    }


}