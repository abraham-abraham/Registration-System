package com.soen387.registrationsystem.gateway;

import java.sql.*;
public class DBConnection {
    static final String DB_URL = "jdbc:mysql://localhost/assignment1";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection getSQLConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        conn.setAutoCommit(false);
        return conn;
    }
}
