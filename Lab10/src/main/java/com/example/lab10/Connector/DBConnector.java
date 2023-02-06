package com.example.lab10.Connector;

public class DBConnector {
    private static final String url = "jdbc:mysql://localhost:3306/musicshop";
    private static final String user = "root";
    private static final String password = "";


    public void  connection(){

       /* con = DriverManager.getConnection(url, user, password);


        System.out.println("\nSQL query: SELECT * FROM record");
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT record.id, maker.name as maker, date_release, record.name, genre.name as genre FROM record"+
                " INNER JOIN maker ON (record.id_maker=maker.id)"+
                " INNER JOIN genre ON (record.id_genre=genre.id)"+
                "ORDER BY date_release");

    }*/

}
