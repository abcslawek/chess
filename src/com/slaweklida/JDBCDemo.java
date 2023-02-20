package com.slaweklida;

import java.sql.*;

public class JDBCDemo {
    private static String url;
    private static String username;
    private static String password;

    public JDBCDemo() {
        url = "jdbc:mysql://localhost:3306/jdbcdemo";
        username = "root";
        password = "";
    }

    public static void showHighscore() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nick, wins FROM players ORDER BY wins DESC;");
            int i = 1;
            while (resultSet.next()) {
                System.out.println(i + ". " + resultSet.getString(1) + ": " + resultSet.getInt(2));
                if(i==1) HighscoreWindow.first.setText(i+". " + resultSet.getString(1) + ": " + resultSet.getInt(2));
                if(i==2) HighscoreWindow.second.setText(i+". " + resultSet.getString(1) + ": " + resultSet.getInt(2));
                if(i==3) HighscoreWindow.third.setText(i+". " + resultSet.getString(1) + ": " + resultSet.getInt(2));
                i++;
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void saveScore(String nickname) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE players SET wins = wins + 1 WHERE nick = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nickname);
            preparedStatement.execute();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean isPlayerInDatabase(String nickname){
        int result = 2;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT EXISTS(SELECT 1 FROM players WHERE nick = '" + nickname +"')");

            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            System.out.println("result to: " + result);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result == 1;
    }

    public static void addPlayer(String nickname) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = " INSERT INTO players (nick, wins) VALUES (?, 0)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nickname);
            preparedStatement.execute();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
