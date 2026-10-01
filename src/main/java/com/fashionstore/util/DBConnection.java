package com.fashionstore.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = System.getenv("DB_URL") != null ? 
            System.getenv("DB_URL") : "jdbc:mysql://localhost:3306/fashion_store";
    private static final String USERNAME = System.getenv("DB_USER") != null ? 
            System.getenv("DB_USER") : "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null ? 
            System.getenv("DB_PASSWORD") : "your_mysql_password";

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
