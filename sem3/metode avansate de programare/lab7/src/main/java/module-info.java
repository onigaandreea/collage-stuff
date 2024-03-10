module com.example.lab7 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.lab7 to javafx.fxml;
    opens com.example.lab7.controller to javafx.fxml;

    exports com.example.lab7;
    exports com.example.lab7.domain;
    exports com.example.lab7.controller;
}