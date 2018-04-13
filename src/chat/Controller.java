package chat;

import chat.client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.json.simple.JSONObject;


public class Controller  {

    private  JSONObject obj = new JSONObject();

    @FXML
    private TextArea textMessage;
    @FXML
    private Pane messages = new Pane();
    @FXML
    private AnchorPane pane;
    @FXML
    public  void sendAction(ActionEvent event)  {
        obj.put("type", "messages");
        obj.put("message", textMessage.getText());
        Client client = new Client();
        client.sentData(obj);
        showMessages(textMessage.getText());
    }

    public void showMessages(String str) {
        //System.out.println(str);
        TextArea textArea = new TextArea(str);
        messages.getChildren().add(textArea);
    }
}
