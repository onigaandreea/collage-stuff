package com.example.lab7.controller;

import com.example.lab7.HelloApplication;
import com.example.lab7.domain.User;
import com.example.lab7.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Service service;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField loginPasswordPasswordField;
    @FXML
    Button loginButton;
    @FXML
    Hyperlink registerHyperlink;
    @FXML
    Label loginErrorLabel;

    public void setLoginService(Service service) {
        this.service = service;
    }

    @FXML
    public void loginButtonClicked(ActionEvent e) throws IOException {
        String email = usernameTextField.getText();
        String password = loginPasswordPasswordField.getText();

        if(password.isBlank())
        {
            if(email.isBlank())
                loginErrorLabel.setText("Please complete both of the fields!");
            else loginErrorLabel.setText("Please enter the password!");
        }
        else if(email.isBlank())
        {
            loginErrorLabel.setText("Please enter your email!");
        }
        else
        {
            User foundUser = service.searchUserByEmail(email);

            if(foundUser == null) {
                loginErrorLabel.setText("Invalid username, please try again, or register if you are a new user.");
            }
            else if (foundUser.getPassword().equals(password)) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("userView.fxml"));
                Scene scene = new Scene(loader.load(), 600, 500);

                Controller userCtrl = loader.getController();
                userCtrl.setService(service, foundUser);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Hello, " + foundUser.getFirstName() + " " + foundUser.getLastName() + "!");
                stage.show();

                Stage thisStage = (Stage) loginButton.getScene().getWindow();
                thisStage.hide();
            } else {
                loginErrorLabel.setText("Your password is incorrect!");
            }
        }

        usernameTextField.clear();
        loginPasswordPasswordField.clear();
    }

    public void hereHyperlinkClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("registerView.fxml"));
        Scene scene = new Scene(loader.load(), 400, 500);

        RegisterController registerCtrl = loader.getController();
        registerCtrl.setService(service);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
