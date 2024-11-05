package com.nontage;

public class Database {
    public static SQLConnection connection;

    public static void connect(String url, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = new SQLConnection(url, user, password);
    }
}