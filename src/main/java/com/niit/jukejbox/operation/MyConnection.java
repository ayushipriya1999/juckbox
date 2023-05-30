package com.niit.jukejbox.operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeBox1","root","ayushi@000");
        return connection;

    }
}
/*
DriverManager.getconnection():-driverManager .getconnection this wil connect to data base
 and my java program and its return connection reference

 Connection connection:- connection is an interface in java sql that will hold the the information of database
 */