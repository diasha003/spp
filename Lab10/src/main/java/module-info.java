module com.example.lab10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab10 to javafx.fxml;
    exports com.example.lab10;
    exports com.example.lab10.Controllers;
    opens com.example.lab10.Controllers to javafx.fxml;
}