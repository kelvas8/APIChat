package chat.server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import chat.server.handlers.Handler;
import org.json.simple.JSONObject;

public class Server implements Runnable {
    static private ServerSocket server;
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    static private String IPAddress;
    private Object obj;

    @Override
    public void run() {
        runServer();
    }

    private static void runServer() {
        try {
            System.out.println("Server start");
            server = new ServerSocket(7896, 340);
            while(true) {
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                IPAddress = String.valueOf(connection.getInetAddress());
                String type;
                JSONObject data = (JSONObject) input.readObject();
                type  = String.valueOf(data.get("type"));
                if(!type.equals(null)) {
                    new Thread( new Handler(input.readObject())).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sentData(Object obj) {

        this.obj = obj;
        try {
            output.flush();
            output.writeObject(this.obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getIPAddress() {
        return IPAddress;
    }


}
