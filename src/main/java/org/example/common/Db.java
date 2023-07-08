package org.example.common;

import java.sql.*;

public class Db {

    public static final String URL = "jdbc:sqlite:C:/Users/STEALTH/DataGripProjects/M1/identifier.sqlite";

    public static final String CLASS = "org.sqlite.JDBC";




    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL);
    }
}

