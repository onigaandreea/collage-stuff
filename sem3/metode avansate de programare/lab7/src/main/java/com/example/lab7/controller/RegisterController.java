package com.example.lab7.controller;

import com.example.lab7.domain.User;
import com.example.lab7.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    private Service service;
    @FXML
    TextField firstNameTField;
    @FXML
    TextField lastNameTField;
    @FXML
    TextField emailTField;
    @FXML
    PasswordField passwordPasswordField;
    @FXML
    Button registerButton;
    @FXML
    Label registerErrorLabel;

    public void setService(Service service) {
        this.service = service;
    }

    public void registerButtonClicked(ActionEvent actionEvent) throws IOException
    {
        String firstName = firstNameTField.getText();
        String lastName = lastNameTField.getText();
        String email = emailTField.getText();
        String password = passwordPasswordField.getText();

        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty())
            registerErrorLabel.setText("Please complete all the fields before you click the button!");

        if(!firstName.isEmpty() || !lastName.isEmpty() || !email.isEmpty() || !password.isEmpty())
        {
            User savedUser = new User();
            try{
                savedUser = service.addUser(firstName, lastName, email, password);
            }catch (RuntimeException e){
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Data is not valid!");
                alert.show();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User succesfully added!");
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setContentText("Could not add user!");
            if(savedUser != null){
                alert.show();
                Stage thisStage = (Stage) registerButton.getScene().getWindow();
                thisStage.close();
            } else{
                alert2.show();
            }
        }
    }
}
