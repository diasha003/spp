package com.example.lab10_2.Controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
public class InfoController {

    @FXML
    private Text genreName;

    @FXML
    private Text makerName;

    @FXML
    private Text recordName;

    @FXML
    private Text releaseDate;
    @FXML
    private Text id;
    public  static int idTEXT;
    public  static String makerNameTEXT;
    public  static String dateReleaseTEXT;
    public  static String recordNameTEXT;
    public  static String genreNameTEXT;

    public static void getData(int id,  String makerName, String dateRelease, String recordName, String genreName){
        idTEXT = id;
        makerNameTEXT = makerName;
        dateReleaseTEXT = dateRelease;
        recordNameTEXT = recordName;
        genreNameTEXT = genreName;
    }

    @FXML
    void initialize() {
        makerName.setText(makerNameTEXT);
        releaseDate.setText(dateReleaseTEXT);
        recordName.setText(recordNameTEXT);
        genreName.setText(genreNameTEXT);
        id.setText(String.valueOf(idTEXT));
    }
}
