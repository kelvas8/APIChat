package chat.server.handlers;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;

public class Handler implements IHandler {

    private JSONObject jsondata;
    private Object data;
    private String type;
    private String separator = File.separator;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String IPAddress;
    private Object obj;


    public Handler(Socket connection) throws IOException, ClassNotFoundException  {
        output = new ObjectOutputStream(connection.getOutputStream());
        input = new ObjectInputStream(connection.getInputStream());
        IPAddress = String.valueOf(connection.getInetAddress());
        this.data = input.readObject();
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
            sentData(message);
        }
    }

    @Override
    public void setDataContacts() {
        if(type.equals("dataContact")) {
            JSONObject obj_publicKey = (JSONObject) jsondata.get("data");
                obj_publicKey.put("IP", IPAddress);
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

    @Override
    public synchronized void sentData(Object obj) {

        this.obj = obj;
        try {
            output.flush();
            output.writeObject(this.obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
