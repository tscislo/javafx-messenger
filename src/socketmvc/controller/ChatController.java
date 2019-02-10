package socketmvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import socketmvc.Alert;
import socketmvc.SocketClient;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    @FXML
    public TextField messageField;

    @FXML
    public WebView webView;

    public SocketClient socketClient;

    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void getUserName() {
        socketClient.setUserName(Alert.showInputDialog());
        socketClient.startThread();
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

    @FXML
    public void send() {
        this.socketClient.sendMsg(this.messageField.getText());
    }
}
