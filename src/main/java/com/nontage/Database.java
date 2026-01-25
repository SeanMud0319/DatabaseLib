package com.nontage;

import java.sql.SQLException;

public class Database {
    public static SQLConnection connection;
    private static boolean connected = false;

    public static void connect(String url, String user, String password) {
        disconnect();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = new SQLConnection(url, user, password);
        connected = true;
    }

    public static void connectSQLite(String url) {
        disconnect();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = new SQLConnection(url);
        connected = true;
    }

    public static boolean isConnected() {
        try {
            return connected && connection != null && !connection.connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void disconnect() {
        if (connection != null && connected) {
            try {
                if (!connection.connection.isClosed()) {
                    connection.connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
                connected = false;
            }
        }
    }
}
