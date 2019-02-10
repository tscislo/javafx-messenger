package socketmvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socketmvc.controller.ChatController;

public class Main extends Application {

    private int width = 400;
    private int height = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./view/Chat.fxml"));
        AnchorPane pane = fxmlLoader.load(); // To oznacza, że będziemy podpinać kontoler do AnchorPane
        primaryStage.setTitle("Klient");
        Scene scene = new Scene(pane, width, height);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        primaryStage.setMaxWidth(width);
        primaryStage.setMaxHeight(height);
        ChatController chatController = fxmlLoader.getController();
        chatController.setPrimaryStage(primaryStage);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.out.print("setOnCloseRequest");
            if (Alert.confirmation("Czy zamknąć?")) {
                event.consume();
            }
        });
        chatController.socketClient.setUserName(Alert.showInputDialog());
        chatController.socketClient.startThread();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
