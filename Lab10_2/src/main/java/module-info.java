module com.example.lab10_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lab10_2 to javafx.fxml;
    exports com.example.lab10_2;
}