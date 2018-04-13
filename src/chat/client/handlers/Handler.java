package chat.client.handlers;

import chat.Controller;
import org.json.simple.JSONObject;

public class Handler implements IHandler {

    private JSONObject jsondata;
    private Object data;
    private String type;

    public Handler(Object data) {
        this.data = data;

    }

    @Override
    public void sentData() {
        if(type.equals("messages")) {
            String data = String.valueOf(jsondata.get("message"));
            System.out.println(String.valueOf(jsondata.get("message")));
            //Controller cont = new Controller();
            //cont.showMessages(data);
        }
        }

    @Override
    public void run() {
        this.jsondata = (JSONObject) this.data;
        this.type  = String.valueOf(jsondata.get("type"));
        sentData();
    }
}
