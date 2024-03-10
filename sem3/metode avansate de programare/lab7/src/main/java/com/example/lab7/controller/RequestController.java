package com.example.lab7.controller;

import com.example.lab7.domain.FriendRequest;
import com.example.lab7.domain.User;
import com.example.lab7.service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RequestController {
    public Service service;

    public User user;
    ObservableList<FriendRequest> requesters = FXCollections.observableArrayList();

    @FXML
    TableView<FriendRequest> requestsTable;
    @FXML
    TableColumn<FriendRequest,String> tableColumnFirstName;
    @FXML
    TableColumn<FriendRequest,String> tableColumnLastName;
    @FXML
    TableColumn<FriendRequest, String> tableColumnDate;
    @FXML
    TableColumn<FriendRequest, String> tableColumnStatus;
    public void setService(Service service) {
        this.service = service;
        initModel();
    }
    public void setUser(User user){
        this.user = user;
    }
    @FXML
    public void initialize() {
        tableColumnFirstName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<FriendRequest,String>, ObservableValue<String>>(){
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<FriendRequest, String> p){
                        FriendRequest req = p.getValue();

                        User user1 = null;
                        long userId = req.getIdUser1();
                        for(User u: service.getAll()){
                            if(u.getId() == userId) {
                                user1 = u;
                            }
                        }
                        if(user1 == null){
                            return new SimpleStringProperty("");
                        }
                        return new SimpleStringProperty(user1.getFirstName());
                    }
                }
        );
        tableColumnLastName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<FriendRequest,String>, ObservableValue<String>>(){
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<FriendRequest, String> p){
                        FriendRequest req = p.getValue();

                        User user2 = null;
                        long userId = req.getIdUser1();
                        for(User u: service.getAll()){
                            if(u.getId() == userId) {
                                user2 = u;
                            }
                        }
                        if(user2 == null){
                            return new SimpleStringProperty("");
                        }
                        return new SimpleStringProperty(user2.getLastName());
                    }
                }
        );
        tableColumnDate.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<FriendRequest,String>, ObservableValue<String>>(){
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<FriendRequest, String> p){
                        FriendRequest req = p.getValue();
                        LocalDateTime friendsForm = req.getSendRequest();
                        String date = friendsForm.toString();
                        return new SimpleStringProperty(date);
                    }
                }
        );
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<FriendRequest, String>("status"));
        requestsTable.setItems(requesters);
        requestsTable.getColumns().clear();
        requestsTable.getColumns().addAll(tableColumnFirstName, tableColumnLastName, tableColumnDate, tableColumnStatus);
    }

    private void initModel() {
        Iterable<FriendRequest> requests_iterable = service.searchRequestsForUser(user);
        List<FriendRequest> requests = StreamSupport.stream(requests_iterable.spliterator(), false)
                .collect(Collectors.toList());
        requesters.setAll(requests);
    }

    public void handleAcceptRequest() {
        FriendRequest request =  requestsTable.getSelectionModel().getSelectedItem();

        service.changeRequestStatus(request.getId(),"accepted");
        service.addFriendship(user.getId(), request.getIdUser1());
        initialize();
        //requesters.remove(request);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Request accepted!");
        alert.show();
    }

    public void handleDeclineRequest() {
        FriendRequest request =  requestsTable.getSelectionModel().getSelectedItem();

        service.changeRequestStatus(request.getId(),"declined");
        initialize();
        //requesters.remove(request);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Request declined!");
        alert.show();
    }
}
