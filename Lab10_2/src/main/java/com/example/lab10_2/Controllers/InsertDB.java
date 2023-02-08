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

public class InsertDB {

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
    @FXML
    void initialize() {

        ObservableList<String> listMaker = FXCollections.observableArrayList(DBConnector.getMaker());
        comboBoxMaker.setItems(listMaker);

        ObservableList<String> listGenre = FXCollections.observableArrayList(DBConnector.getGenre());
        comboBoxGenre.setItems(listGenre);

    }


    public void onClickOk(){
        if(comboBoxMaker.getValue() == null || textFieldReleaseDate.getText() == null ||
                textFieldRecordName.getText() == null || comboBoxGenre.getValue() == null) {
            Stage stage = (Stage) buttonOk.getScene().getWindow();
            stage.close();
            return;
        }

        Record newRecord = new Record(1, comboBoxMaker.getValue(),textFieldReleaseDate.getText(), textFieldRecordName.getText(), comboBoxGenre.getValue());
        DBConnector.insertNewRecord(newRecord);

        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
    }
}