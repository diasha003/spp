package com.example.lab8_2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class HelloController {

    int number;
    MyThread obi;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button pause;

    @FXML
    private Button resume;

    @FXML
    private Button start;

    @FXML
    private Button stop;

    @FXML
    private Label currentAmount;
    @FXML
    private TextField outputNumber;

    @FXML
    private TextField sum;

    @FXML
    void initialize() {
        sum.setDisable(true);

        start.setOnAction(actionEvent -> {
            if (outputNumber.getText().isEmpty()) {
                infoBox("Enter the number, please!", "Warning", null);
            } else {
                pause.setDisable(false);
                number = Integer.parseInt(outputNumber.getText());
                obi = new MyThread("My thread", number, this);
                start.setDisable(true);
            }

        });

        pause.setOnAction(actionEvent -> {
            try {
                pause.setDisable(true);
                resume.setDisable(false);
                obi.mysuspend();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        resume.setOnAction(actionEvent -> {
            try {
                pause.setDisable(false);
                resume.setDisable(true);
                obi.myresume();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        stop.setOnAction(actionEvent -> {

            start.setDisable(false);
            pause.setDisable(true);
            resume.setDisable(true);
            obi.mystop();
        });


    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }


    public void changeSum(double value) {

        System.out.println(value);
        String currentVal = sum.getText();

        if (!currentVal.equals(Double.toString(value))) {
            //oldVal = currentVal;
            sum.setText(Double.toString(value));

        }
    }

}
