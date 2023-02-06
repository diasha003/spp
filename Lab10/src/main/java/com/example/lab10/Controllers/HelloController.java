package com.example.lab10.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableColumn<Record, String> idColumn;

    @FXML
    private TableColumn<Record, String> nameColumn;

    @FXML
    private TableView<Record> records;

    public  ObservableList<Record>  listView = FXCollections.observableArrayList(); //список объектов



}