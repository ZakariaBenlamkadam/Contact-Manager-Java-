package com.example.mycontactmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public DatabaseConnection() {
    }

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //consider to create a database named contacts 
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/contacts ", "root", "kkuunn123");
            return connect;
        } catch (Exception var1) {
            var1.printStackTrace();
            return null;
        }
    }
}
