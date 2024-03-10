package com.example.lab7.controller;

import com.example.lab7.HelloApplication;
import com.example.lab7.domain.User;
import com.example.lab7.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Controller {
    public Service service;

    ObservableList<User> friends = FXCollections.observableArrayList();
    public User user;
    @FXML
    Button sighOutButton;
    @FXML
    TableView friendsTableView;
    @FXML
    TableColumn firstNameColumn;
    @FXML
    TableColumn lastNameColumn;
    @FXML
    TableColumn emailColumn;
    @FXML
    Button friendRequestButton;
    @FXML
    Button addFriendButton;
    @FXML
    Button deleteFriend;
    @FXML
    Button friendRequestButton2;
    @FXML
    Button messageButton;
    @FXML
    Button messagesButton;

    public void setService(Service service,User user)
    {
        this.service = service;
        this.user = user;
        initModel();
    }

    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        friendsTableView.setItems(friends);
    }

    private void initModel() {
        Iterable<User> users_iterable = service.searchFriends(user);
        List<User> users = StreamSupport.stream(users_iterable.spliterator(), false)
                .collect(Collectors.toList());
        friends.setAll(users);
    }

    public void signOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("loginView.fxml"));
        Scene scene  = new Scene(loader.load(), 400, 500);

        LoginController loginCtrl = loader.getController();
        loginCtrl.setLoginService(service);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage thisStage = (Stage) sighOutButton.getScene().getWindow();
        thisStage.close();
    }

    public void handleDeleteFriend(ActionEvent actionEvent) {
        User user_friend = new User();
        user_friend = (User) friendsTableView.getSelectionModel().getSelectedItem();
        if (user_friend != null) {
            service.removeFriendship(user_friend.getId(), this.user.getId());
            friends.remove(user_friend);
        }
        initModel();
    }

    public void handleAddFriend(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("addFriendsView.fxml"));
        Scene scene  = new Scene(loader.load(), 480, 500);

        AddFriendsController addCtrl = loader.getController();
        addCtrl.setService(service);
        addCtrl.setUser(user);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        initModel();
    }

    public void handleRequests(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("requestView.fxml"));
        Scene scene  = new Scene(loader.load(), 400, 450);

        RequestController requestCtrl = loader.getController();
        requestCtrl.setUser(user);
        requestCtrl.setService(service);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        initModel();
    }

    public void handleMyRequests(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("requestByMeView.fxml"));
        Scene scene  = new Scene(loader.load(), 450, 500);

        MyRequestsController requestCtrl = loader.getController();
        requestCtrl.setUser(user);
        requestCtrl.setService(service);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        initModel();
    }

    public void handleMessages(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("messageView.fxml"));
        Scene scene  = new Scene(loader.load(), 500, 500);

        MessageController msgCtrl = loader.getController();
        User user1 = (User) friendsTableView.getSelectionModel().getSelectedItem();
        msgCtrl.setUser(user,user1);
        msgCtrl.setService(service);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
