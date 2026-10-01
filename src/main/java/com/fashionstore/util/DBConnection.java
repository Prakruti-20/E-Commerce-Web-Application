package com.fashionstore.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/fashion_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "prakruti@2094";

    private static Connection connection = null;

    public static Connection getConnection() {
    	System.out.println("DB CONNECTED SUCCESSFULLY");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return connection;
    }
    }
