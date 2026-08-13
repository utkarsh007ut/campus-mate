package com.utprogramming.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static Connection con;

    public static Connection createConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            /*String user = "root";
            String pass = ;
            String url = "jdbc:mysql://localhost:3306/student";*/

            String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
            String pass = System.getenv("DB_PASS");
            String url  = "jdbc:mysql://localhost:3306/student";

            con = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}