package com.example.lab10_2.Controllers;

import com.example.lab10_2.Connector.DBConnector;
import com.example.lab10_2.Model.Record;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class HelloController {

    @FXML
    private Button Add;

    @FXML
    private Button Delete;

    @FXML
    private Button Info;

    @FXML
    private Button Refresh;

    @FXML
    private Button Update;

    @FXML
    private TableColumn<Record, String> genreColumn;

    @FXML
    private TableColumn<Record, Integer> idColumn;

    @FXML
    private TableColumn<Record, String> nameColumn ;

    @FXML
    private TableView<Record> records;

    private final DBConnector bdController;

    public  static int idNew;
    public  static String makerNameNew;
    public  static String dateReleaseNew;
    public  static String recordNameNew;
    public  static String genreNameNew;

    private ObservableList<Record> listView = FXCollections.observableArrayList(); //список объектов

    public HelloController() {
        this.bdController = new DBConnector(this);
    }

    @FXML
    void initialize(){

        try {
            listView = FXCollections.observableArrayList(bdController.connection()); //получение списка данных из БД
        } catch (SQLException | ClassNotFoundException exception){
            exception.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<Record, Integer>("id")); //привязка к свойствам модели
        nameColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("recordName"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("genreName"));

        records.setItems(listView);


        TableView.TableViewSelectionModel<Record> selectionModel = records.getSelectionModel(); //выбранная запись в таблице
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Record>(){

            public void changed(ObservableValue<? extends Record> observableValue, Record oldVal, Record newVal){
                if(newVal != null) {
                    idNew = newVal.getId();
                    makerNameNew = newVal.getMakerName();
                    recordNameNew = newVal.getRecordName();
                    dateReleaseNew = newVal.getDateRelease();
                    genreNameNew = newVal.getGenreName();
                }
            }
        });
    }


    public void onClickDelete(){

            if (idNew < 1) {
                infoBox("Select the record to delete, please!", "Warning", null);
                return;
            }
            DBConnector.deleteRecord(idNew);

    }

    public  void onClickAdd(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/lab10_2/add.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            Stage stage = new Stage();
            stage.setTitle("Add new record");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void onClickAllInfo(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            if (idNew < 1) return;
            DBConnector.getAllInfo(idNew);
            fxmlLoader.setLocation(getClass().getResource("/com/example/lab10_2/info.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            Stage stage = new Stage();
            stage.setTitle("All info");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void onClickUpdate(){
        if (idNew < 1) {
            infoBox("Select the record to update, please!", "Warning", null);
            return;
        }

        try {
            UpdateDB.setData(idNew, makerNameNew, dateReleaseNew, recordNameNew, genreNameNew);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/lab10_2/update.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            Stage stage = new Stage();
            stage.setTitle("Update this record");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void onClickRefresh(){
        listView.clear();
        try {
            listView = FXCollections.observableArrayList(bdController.connection()); //получение списка данных из БД
        } catch (SQLException | ClassNotFoundException exception){
            exception.printStackTrace();
        };
        records.setItems(listView);
    }


    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}