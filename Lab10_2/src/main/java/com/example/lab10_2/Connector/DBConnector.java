package com.example.lab10_2.Connector;

import com.example.lab10_2.Controllers.HelloController;
import com.example.lab10_2.Controllers.InfoController;
import com.example.lab10_2.Model.Record;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {

    private static final String url = "jdbc:mysql://localhost:3306/musicshop";
    private static final String user = "root";
    private static final String password = "";

    public static HelloController helloController;

    public DBConnector(HelloController helloContr){
        helloController = helloContr;
    }

    public List<Record> connection() throws ClassNotFoundException, SQLException {

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            List<Record> list = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT record.id, maker.name as maker, date_release, record.name, genre.name as genre FROM record"+
                    " INNER JOIN maker ON (record.id_maker=maker.id)"+
                    " INNER JOIN genre ON (record.id_genre=genre.id)"+
                    "ORDER BY genre");

            while(rs.next()){
                list.add(new Record(rs.getInt("id"), rs.getString("maker"), rs.getString("date_release"),
                        rs.getString("name"), rs.getString("genre")) );

            }
            return list;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static void getAllInfo(int id) throws SQLException{

        Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement();
        String sql = "SELECT record.id, maker.name as maker, date_release, record.name, genre.name as genre FROM record"+
                " INNER JOIN maker ON (record.id_maker=maker.id)"+
                " INNER JOIN genre ON (record.id_genre=genre.id)"+
                "WHERE record.id = " + String.valueOf(id);
        ResultSet rs = stmt.executeQuery(sql);


        while(rs.next()){
            InfoController.getData(rs.getInt("record.id"),
                    rs.getString("maker"),
                    rs.getString("date_release"),
                    rs.getString("record.name"),
                    rs.getString("genre"));

        }
    }

}
