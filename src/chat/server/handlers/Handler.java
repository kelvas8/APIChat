package chat.server.handlers;

import chat.server.Server;
import org.json.simple.JSONObject;

import java.io.*;

public class Handler implements IHandler {

    private JSONObject jsondata;
    private Object data;
    private String type;
    private Server server = new Server();
    static private String separator = File.separator;

    public Handler(Object data) {
        this.data = data;

    }

    @Override
    public void run() {
        this.jsondata = (JSONObject) this.data;
        this.type  = String.valueOf(jsondata.get("type"));
        setData();
        setDataContacts();
        setMessages();
    }

    @Override
    public void setMessages() {
        if(type.equals("messages")) {
            String message = String.valueOf(jsondata.get("message"));
            server.sentData(message);
        }
    }

    @Override
    public void setDataContacts() {
        if(type.equals("dataContact")) {
            JSONObject obj_publicKey = (JSONObject) jsondata.get("data");
                obj_publicKey.put("IP", server.getIPAddress());
            File path = new File ("src"+separator+"chat"+separator+"data"+separator+"contacts.json");
            try  {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
                writer.println(obj_publicKey);
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setData() {
        return;
    }


}
