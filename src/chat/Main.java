package chat;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;


    @Override
    public void start(Stage stage) {
    this.stage = stage;
        try {
            pane = (AnchorPane) FXMLLoader.load(Main.class.getResource("chat.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("APIC");

        scene = new Scene(pane);
    stage.setScene(scene);
    stage.show();
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        MainThread main = new MainThread();
                        main.threads();
                        return null;
                    }
                };
            }
        };
        service.start();

    }

        @FXML
        public void sendAction() {
        System.out.println(1);
        }

    public static void main(String[] args) {
        launch(args);

    }
}
