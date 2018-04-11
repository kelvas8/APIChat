package chat;

import javafx.fxml.FXML;
import org.json.simple.JSONObject;

public class Controller {
public static JSONObject obj = new JSONObject();
public static int i = 1;

    @FXML
    public  void sendAction() {
        i++;
        obj.put("type", "messages");
        obj.put("message", "try");
        Client client = new Client();
        client.sentData(obj);
            }

}
