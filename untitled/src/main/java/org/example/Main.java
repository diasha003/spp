package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/musicshop";
    private static final String user = "root";
    private static final String password = "";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private  static int count = 1;

    public static void main (String args[]) {

        List<String> RecordId = new ArrayList<>();
        List<String> MakerName = new ArrayList<>();
        List<String> DateRelease = new ArrayList<>();
        List<String> RecordName = new ArrayList<>();
        List<String> GenreName = new ArrayList<>();


        try {

            con = DriverManager.getConnection(url, user, password);


            System.out.println("\nSQL query: SELECT * FROM record");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT record.id, maker.name as maker, date_release, record.name, genre.name as genre FROM record"+
                            " INNER JOIN maker ON (record.id_maker=maker.id)"+
                            " INNER JOIN genre ON (record.id_genre=genre.id)"+
                            "ORDER BY date_release");

            while (rs.next()) {
                RecordId.add(rs.getString("id"));
                MakerName.add(rs.getString("maker"));
                DateRelease.add(rs.getString("date_release"));
                RecordName.add(rs.getString("name"));
                GenreName.add(rs.getString("genre"));
            }

            String leftAlignFormat = "|  %-4s |  %-12s |  %-12s |  %-40s |  %-13s |%n";

            System.out.format("+----+------------------+---------------+-------------------------------------------+----------------+%n");
            System.out.format("|  ID  |    MakerName   |  DateRelease  |                 RecordName                |    GenreName   |%n");
            System.out.format("+------+----------------+---------------+------------------------------------------------------------+%n");
            for (int i = 0; i < RecordId.size(); i++) {
                System.out.format(leftAlignFormat, RecordId.get(i), MakerName.get(i), DateRelease.get(i), RecordName.get(i), GenreName.get(i));
            }
            System.out.format("+----+------------------+---------------+-------------------------------------------+----------------+%n");


            System.out.println("\nSQL query: INSERT INTO genre (`name`) VALUES ('TEST')");
            /*String addTestGenre = "INSERT INTO genre(`name`) VALUES ('TEST')";
            stmt.execute(addTestGenre);*/

            String readingTestGenre = "SELECT * FROM genre";
            dataTableGenreOutput(readingTestGenre);


            System.out.println("\nSQL query: DELETE FROM genre WHERE Name='Pop'");
            /*String deleteTestGenre = "DELETE FROM genre WHERE Name='Pop'";
            stmt.execute(deleteTestGenre);*/
            dataTableGenreOutput(readingTestGenre);

            System.out.println("\nSQL query: UPDATE groups SET GroupName='NORD' WHERE GroupName='TEST'");
            String updatingTestGente = "UPDATE genre SET name= \'TEST UPDATE\' WHERE name = \'TEST\'";
            stmt.execute((updatingTestGente));
            dataTableGenreOutput(readingTestGenre);


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { }
            try { stmt.close(); } catch(SQLException se) {  }
            try { rs.close(); } catch(SQLException se) {  }
        }
    }

    public static void dataTableGenreOutput (String query) throws SQLException{
        ResultSet rsGenre = stmt.executeQuery(query);
        System.out.println("Table: genre");
        while (rsGenre.next()){
            System.out.println(count+"." + "  " + rsGenre.getString("name"));
            count++;
        }
        count = 1;
    }
}