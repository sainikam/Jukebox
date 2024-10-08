package com.jap.jukebox.utility;


import java.sql.*;

public class ConnectDatabase {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver is loaded");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Sai@1004");
        System.out.println("Connection established");
        return con;

    }
}
