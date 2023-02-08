package com.example.lab10_2.Connector;

import com.example.lab10_2.Controllers.HelloController;
import com.example.lab10_2.Controllers.InfoController;
import com.example.lab10_2.Model.Record;
import java.sql.*;
import java.text.MessageFormat;
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

    public static List<String> getMaker(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            List<String> list = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM maker");

            while(rs.next()){
                list.add(rs.getString("name"));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<String> getGenre(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            List<String> list = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM genre");

            while(rs.next()){
                list.add(rs.getString("name"));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void insertNewRecord(Record newRecord){
        String sql = "INSERT INTO record ( id_maker, date_release, name, id_genre) VALUES ((select id from maker where name = ?),\n" +
                    "(?), (?), (select id from genre where name = ?))";
        try ( Connection con  = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newRecord.getMakerName());
            ps.setString(2, newRecord.getDateRelease());
            ps.setString(3,  newRecord.getRecordName() );
            ps.setString(4, newRecord.getGenreName());
            ps.executeUpdate();
            helloController.onClickRefresh();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static  void deleteRecord(int id){
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM record WHERE ID = " + String.valueOf(id);
            stmt.execute(sql);
            helloController.onClickRefresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateRecord(Record updateRecord){

        String sql =  "UPDATE record SET id_maker = (SELECT id FROM maker where name = ?), date_release = ?,  name = ?, id_genre = (SELECT id FROM genre where name = ?) " +
                "WHERE id = ?";
        try ( Connection con  = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, updateRecord.getMakerName());
            ps.setString(2, updateRecord.getDateRelease());
            ps.setString(3, updateRecord.getRecordName() );
            ps.setString(4, updateRecord.getGenreName());
            ps.setString(5, String.valueOf(updateRecord.getId()));
            ps.executeUpdate();
            helloController.onClickRefresh();
        } catch (SQLException e){
            e.printStackTrace();
        }
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
