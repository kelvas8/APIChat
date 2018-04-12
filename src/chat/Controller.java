package chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import org.json.simple.JSONObject;


public class Controller  {

    private  JSONObject obj = new JSONObject();

    @FXML
    private TextArea textMessage;
    @FXML
    private Pane messages;
    @FXML
    public  void sendAction(ActionEvent event)  {
        obj.put("type", "messages");
        obj.put("message", textMessage.getText());
        Client client = new Client();
        client.sentData(obj);


    }


}
