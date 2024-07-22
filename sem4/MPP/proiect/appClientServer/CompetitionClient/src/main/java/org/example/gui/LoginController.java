package org.example.gui;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.model.User;
import org.example.service.CompetitionException;
import org.example.service.ICompetitionServices;


import java.io.IOException;

public class LoginController {
    private ICompetitionServices server;
    private Controller mainCtrl;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField loginPasswordPasswordField;
    @FXML
    Button loginButton;
    @FXML
    Label loginErrorLabel;

    Parent mainCompetitionParent;

    public LoginController() {
        System.out.println("Login Controller called!");
    }

    public void setLoginServer(ICompetitionServices server) {
        this.server = server;
    }

    public void setParent(Parent p){
        mainCompetitionParent = p;
    }

    public void setMainCtrl(Controller mainCtrl){
        this.mainCtrl = mainCtrl;
    }
    @FXML
    public void loginButtonClicked(ActionEvent e) throws IOException, CompetitionException {

        try{
            String email = usernameTextField.getText();
            String password = loginPasswordPasswordField.getText();

            usernameTextField.clear();
            loginPasswordPasswordField.clear();
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
                User newUser = new User(email, password);
                User logged = server.login(newUser, mainCtrl);

                showNewWindow(logged);
            }
        }catch (CompetitionException er) {
            MessageAlert.showErrorMessage(null, er.getMessage());
        }
    }

    public void showNewWindow(User user) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(mainCompetitionParent);
        stage.setScene(scene);

        mainCtrl.initModel();
        mainCtrl.setUser(user);

        stage.setTitle("Participants window");
        stage.setWidth(800);
        stage.setHeight(650);
        stage.show();

        Stage thisStage = (Stage) loginButton.getScene().getWindow();
        thisStage.hide();
    }
}
