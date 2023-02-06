package com.example.lab10_2.Connector;

import java.sql.*;

public class DBConnector {
    private static final String url = "jdbc:mysql://localhost:3306/musicshop";
    private static final String user = "root";
    private static final String password = "";



    public void  connection() {

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }



    }

}
