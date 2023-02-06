package com.example.lab10_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.*;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");
    }


}