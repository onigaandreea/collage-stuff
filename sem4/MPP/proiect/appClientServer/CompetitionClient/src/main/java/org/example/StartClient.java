package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import org.example.gui.Controller;
import org.example.gui.LoginController;
import org.example.service.ICompetitionServices;

import java.io.IOException;
import java.util.Properties;


public class StartClient extends Application {
    private Stage primaryStage;
    private static int defaultChatPort=55555;
    private static String defaultServer="localhost";
    public void start(Stage primaryStage) throws Exception {
        System.out.println("In start");
        Properties clientProps=new Properties();
        try {
            clientProps.load(StartClient.class.getResourceAsStream("/competitionclient.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find competitionclient.properties "+e);
            return;
        }
        String serverIP=clientProps.getProperty("competition.server.host",defaultServer);
        int serverPort=defaultChatPort;
        try{
            serverPort=Integer.parseInt(clientProps.getProperty("competition.server.port"));
        }catch(NumberFormatException ex){
            System.err.println("Wrong port number "+ex.getMessage());
            System.out.println("Using default port: "+defaultChatPort);
        }
        System.out.println("Using server IP "+serverIP);
        System.out.println("Using server port "+serverPort);
        ICompetitionServices server=new CompetitionServiceProxy(serverIP, serverPort);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("loginView.fxml"));
        Parent root=loader.load();

        LoginController ctrl = loader.getController();
        ctrl.setLoginServer(server);

        FXMLLoader cloader = new FXMLLoader(getClass().getClassLoader().getResource("mainView.fxml"));
        Parent croot=cloader.load();

        Controller chatCtrl = cloader.getController();
        chatCtrl.setServer(server);

        ctrl.setMainCtrl(chatCtrl);
        ctrl.setParent(croot);

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}
