module com.example.lab8_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab8_2 to javafx.fxml;
    exports com.example.lab8_2;
}