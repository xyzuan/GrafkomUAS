module com.example.grafkom {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.grafkom.kel3 to javafx.fxml;
    exports com.grafkom.kel3;
}