package com.example.lab10_2.Controllers;

import com.example.lab10_2.Connector.DBConnector;
import com.example.lab10_2.Model.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateDB {

    @FXML
    private ComboBox<String> comboBoxGenre;

    @FXML
    private ComboBox<String> comboBoxMaker;

    @FXML
    private TextField textFieldRecordName;

    @FXML
    private TextField textFieldReleaseDate;

    @FXML
    private Button buttonOk;

    public static int idTEXT;
    public static String makerNameTEXT;
    public static String releaseDateTEXT;
    public static String recordNameTEXT;
    public static String genteNameTEXT;

    public static void setData(int id,  String makerName, String dateRelease, String recordName, String genreName){
        idTEXT = id;
        makerNameTEXT = makerName;
        releaseDateTEXT = dateRelease;
        recordNameTEXT = recordName;
        genteNameTEXT = genreName;
    }

    @FXML
    void initialize() {

        ObservableList<String> listMaker = FXCollections.observableArrayList(DBConnector.getMaker());
        comboBoxMaker.setItems(listMaker);
        comboBoxMaker.setValue(makerNameTEXT);

        textFieldReleaseDate.setText(releaseDateTEXT);

        textFieldRecordName.setText(recordNameTEXT);

        ObservableList<String> listGenre = FXCollections.observableArrayList(DBConnector.getGenre());
        comboBoxGenre.setItems(listGenre);
        comboBoxGenre.setValue(genteNameTEXT);

    }
    public void onClickOk(){
        if(comboBoxMaker.getValue() == null || textFieldReleaseDate.getText() == null ||
                textFieldRecordName.getText() == null || comboBoxGenre.getValue() == null) {
            Stage stage = (Stage) buttonOk.getScene().getWindow();
            stage.close();
            return;
        }

        Record updateRecord = new Record(idTEXT, comboBoxMaker.getValue(),textFieldReleaseDate.getText(), textFieldRecordName.getText(), comboBoxGenre.getValue());
        DBConnector.updateRecord(updateRecord);

        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();

    }
}
