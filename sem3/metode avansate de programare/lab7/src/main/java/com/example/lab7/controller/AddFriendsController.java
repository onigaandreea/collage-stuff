package com.example.lab7.controller;

import com.example.lab7.domain.User;
import com.example.lab7.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AddFriendsController {
    public Service service;

    public User user;

    public void setService(Service service) {
        this.service = service;
    }
    public void setUser(User user){
        this.user = user;
    }

    ObservableList<User> users = FXCollections.observableArrayList();
    @FXML
    TableView<User> tableView;
    @FXML
    TableColumn<User,String> tableColumnFirstName;
    @FXML
    TableColumn<User,String> tableColumnLastName;
    @FXML
    TableColumn<User, String> tableColumnEmail;

    @FXML
    TextField textFirstName;

    @FXML
    TextField textLastName;

    @FXML
    public void initialize() {
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        tableView.setItems(users);
    }

    private void initModel() {
        if (textFirstName.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("You need to search a friend!");
            alert.show();
        }
        else {
            Iterable<User> users_iterable = service.searchUserByName(user, textFirstName.getText());
            List<User> users_list = StreamSupport.stream(users_iterable.spliterator(), false)
                    .collect(Collectors.toList());
            users.setAll(users_list);
            initialize();
        }
    }

    public void handleSendRequest(ActionEvent actionEvent) {
        User requested_friend= tableView.getSelectionModel().getSelectedItem();
        service.addRequest(user.getId(), requested_friend.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Request send!");
        alert.show();
    }
    public void handleSearch(ActionEvent actionEvent) {
        initModel();
    }

}
