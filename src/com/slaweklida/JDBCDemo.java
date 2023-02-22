package com.slaweklida;

import java.sql.*;
import java.util.ArrayList;

public class JDBCDemo {
    private static String url;
    private static String username;
    private static String password;

    public JDBCDemo() {
//        url = "jdbc:mysql://localhost:3306/jdbcdemo";
//        username = "root";
//        password = "";
        url = "jdbc:mysql://s129.cyber-folks.pl:3306/abcslawek_jdbc";
        username = "abcslawek_abcslawek";
        password = "Chess6767@";
    }

    public static String[][] highscore() {
        ArrayList<String[]> dataList = new ArrayList<>();
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nick, wins FROM players ORDER BY wins DESC;");
            int i = 1;
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + ": " + resultSet.getString(2) + ": " + resultSet.getInt(3));
                String[] data = {Integer.toString(i), resultSet.getString(2), Integer.toString(resultSet.getInt(3))};
                dataList.add(data);
                i++;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            GUI.highscoreWindow.setNoConnectionText();
        }

        String[][] data = new String[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }
        return data;
    }

    public static void saveScore(String nickname) {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
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

    public static boolean isPlayerInDatabase(String nickname) {
        int result = 2;
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT EXISTS(SELECT 1 FROM players WHERE nick = '" + nickname + "')");

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
//            Class.forName("com.mysql.cj.jdbc.Driver");
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
