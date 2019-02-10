package socketmvc.controller;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import socketmvc.Alert;
import socketmvc.SocketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    public SocketClient socketClient;

    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.socketClient = new SocketClient("localhost", 9001);
            this.socketClient.openSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
