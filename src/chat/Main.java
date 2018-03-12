package chat;
import chat.client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {


    public static void main(String[] args)  {
        Files data = new Files();

        ArrayList<String> contacts = data.read("contacts.json");
        new Thread (new Server()).start();
        Thread client = new Thread (new Client(contacts));
        client.run();
        launch();

    }


    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("APIC");
        stage.setScene(new Scene(root, 600, 550));
        stage.show();
    }

}

class Files {

    public ArrayList<String>  read(String input) {
            String separator = File.separator;
            String path = "src"+separator+"chat"+separator+"data"+separator+input;
            FileReader file = null;
        try {
            file = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(file);
        String str;
        ArrayList<String> list = new ArrayList<String>();

        try {
            while ((str = buffer.readLine()) != null) {
                list.add(str);
            }
            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}