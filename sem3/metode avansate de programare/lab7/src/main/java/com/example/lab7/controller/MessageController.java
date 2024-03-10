package com.example.lab7.controller;

import com.example.lab7.domain.FriendRequest;
import com.example.lab7.domain.Message;
import com.example.lab7.domain.User;
import com.example.lab7.service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageController {
    public Service service;

    public User user1;
    public User user2;
    ObservableList<Message> messages = FXCollections.observableArrayList();

    @FXML
    Label nameLabel;
    @FXML
    TableView<Message> chatTableView;
    @FXML
    TableColumn<Message,String> chatTableColumn;
    @FXML
    TextField messageTextField;

    public void setService(Service service) {
        this.service = service;
        initModel();
    }
    public void setUser(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
    }
    @FXML
    public void initialize() {
        chatTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Message,String>, ObservableValue<String>>(){
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Message, String> p){
                        Message message = p.getValue();

                        User user1 = null;
                        User user2 = null;
                        long userId1 = message.getFrom();
                        long userId2 = message.getTo();
                        for(User u: service.getAll()){
                            if(u.getId() == userId1) {
                                user1 = u;
                            }
                            if(u.getId() == userId2) {
                                user2 = u;
                            }
                        }
                        if(user1 == null || user2 == null){
                            return new SimpleStringProperty("");
                        }
                        return new SimpleStringProperty(message.getText());
                    }
                }
        );
        chatTableView.setItems(messages);
        chatTableView.getColumns().clear();
        chatTableView.getColumns().addAll(chatTableColumn);
    }

    private void initModel() {
        String nameFriend = user2.getLastName() + " " + user2.getFirstName();
        nameLabel.setText(nameFriend);
        Iterable<Message> messageIterable = service.messagesForTwoUsers(user1,user2.getId());
        List<Message> messages1 = StreamSupport.stream(messageIterable.spliterator(), false)
                .collect(Collectors.toList());
        messages.setAll(messages1);
    }

    public void handleMessages() {
        String message = messageTextField.getText();
        service.addMessage(user1.getId(),user2.getId(),message);
        initModel();
        messageTextField.clear();
        //initialize();
    }
}
