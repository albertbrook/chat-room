package com.albertbrook.utils;

import java.sql.*;
import java.util.ArrayList;

public class MyConnection {
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    private static Connection getConnection() {
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.233.129:3306/chat_room?useSSL=false&user=root&password=123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet query(String sql, ArrayList<Object> arrayList) {
        connection = getConnection();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < arrayList.size(); i++)
                preparedStatement.setObject(i + 1, arrayList.get(i));
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int update(String sql, ArrayList<Object> arrayList) {
        connection = getConnection();
        int result = -1;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < arrayList.size(); i++)
                preparedStatement.setObject(i + 1, arrayList.get(i));
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void close() {
        try {
            if (connection != null)
                connection.close();
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
