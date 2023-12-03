package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WriteScore {
    private String names;
    private int scores;

    public WriteScore(String names, int scores) {
        this.names = names;
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Username: '" + names + '\'' +
                " Highscores: " + scores;
    }
}
