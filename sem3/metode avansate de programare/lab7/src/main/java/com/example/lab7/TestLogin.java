package com.example.lab7;

import com.example.lab7.controller.LoginController;
import com.example.lab7.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TestLogin extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginView.fxml"));
        AnchorPane root = loader.load();

        LoginController ctrl = loader.getController();
        ctrl.setLoginService(new Service());

        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
