package com.example.lab10_2.Controllers;

import com.example.lab10_2.Connector.DBConnector;
import com.example.lab10_2.Model.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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



    }

   /* private void initData() {
        listView.add(new Record(1, "Alex", "qwerty", "alex@mail.com", "dsf"));
        listView.add(new Record(2, "Bob", "dsfsdfw", "bob@mail.com","dsf"));
        listView.add(new Record(3, "Jeck", "dsfdsfwe", "Jeck@mail.com", "dsf"));
        listView.add(new Record(4, "Mike", "iueern", "mike@mail.com", "dsf"));
        listView.add(new Record(5, "colin", "woeirn", "colin@mail.com", "dsf"));
    }*/







}