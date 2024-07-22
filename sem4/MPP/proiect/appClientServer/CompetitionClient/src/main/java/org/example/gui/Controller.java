package org.example.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.model.*;
import org.example.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller implements ICompetitionObserver {
    private ICompetitionServices server;
    private User user;

    ObservableList<TaskDTO> tasks = FXCollections.observableArrayList();
    ObservableList<Child> children = FXCollections.observableArrayList();
    @FXML
    Button signOutButton;
    @FXML
    TableView<TaskDTO> tasksTableView;
    @FXML
    TableColumn<TaskDTO, String> descriptionColumn;
    @FXML
    TableColumn<TaskDTO, Integer> minAgeColumn;
    @FXML
    TableColumn<TaskDTO, Integer> maxAgeColumn;
    @FXML
    TableColumn<TaskDTO, Integer> noParticipantsColumn;

    @FXML
    TableView<Child> participantsTableView;
    @FXML
    TableColumn<Child,String> nameTableColumn;
    @FXML
    TableColumn<Child,Integer> ageTableColumn;
    @FXML
    TextField nameTxt;
    @FXML
    TextField ageTxt;
    @FXML
    Button addButton;

    public void setServer(ICompetitionServices server) throws CompetitionException
    {
        this.server = server;
        initialize();
        initialize2();
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void initialize() {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<TaskDTO, String>("description"));
        minAgeColumn.setCellValueFactory(new PropertyValueFactory<TaskDTO, Integer>("ageCatStart"));
        maxAgeColumn.setCellValueFactory(new PropertyValueFactory<TaskDTO, Integer>("ageCatEnd"));
        noParticipantsColumn.setCellValueFactory(new PropertyValueFactory<TaskDTO, Integer>("noChildren"));
        tasksTableView.setItems(tasks);
    }

    private void initialize2(){

        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Child,String>("name"));
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<Child,Integer>("age"));
        participantsTableView.setItems(children);
    }

    public void initModel() {
        try{
            TaskDTO[] listTasks = server.findAllTasks();
            List<TaskDTO> all = new ArrayList<>(Arrays.asList(listTasks));
            tasks.setAll(all);
        }catch (CompetitionException e){
            e.printStackTrace();
        }
    }

    public void handleSearch() {
        try{
            TaskDTO task = (TaskDTO) tasksTableView.getSelectionModel().getSelectedItem();
            Task tsk = new Task(task.getDescription(), task.getAgeCatStart(), task.getAgeCatEnd());
            tsk.setId(task.getId());
            Child[] children1 = server.findByTask(tsk);
            children.setAll(children1);
        }catch (CompetitionException e){
            e.printStackTrace();
        }
    }

    public void handleAddParticipation() throws IOException {
        String name = nameTxt.getText();
        int age = Integer.parseInt(ageTxt.getText());
        TaskDTO tsk = (TaskDTO) tasksTableView.getSelectionModel().getSelectedItem();
        Task task = new Task(tsk.getDescription(), tsk.getAgeCatStart(), tsk.getAgeCatEnd());
        task.setId(tsk.getId());
        Child child = new Child(name, age);
        try {
            server.addChild(child);
            Child addedChild = server.findLastAdded();
            Participation participation = new Participation(addedChild, task);
            server.addParticipation(participation);
            server.updateEvent();
        }catch (CompetitionException e){
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.toString());
            alert.show();
        }
        nameTxt.clear();
        ageTxt.clear();
    }

    public void signOut() {
        try {
            server.logout(user, this);
        } catch (CompetitionException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
        Stage thisStage = (Stage) signOutButton.getScene().getWindow();
        thisStage.close();
    }

    @Override
    public void updateEvents() throws CompetitionException {
        Platform.runLater(() -> {initModel(); handleSearch();});
    }
}
