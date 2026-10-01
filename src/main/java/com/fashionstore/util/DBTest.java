
package com.fashionstore.util;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("Connection test passed");
        } else {
            System.out.println("Connection test failed");
        }
    }
}