package chat;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.JSONObject;

public class Server implements Runnable {
    static private ServerSocket server;
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    public String separator = File.separator;
    public String IPAddress;

    @Override
    public void run() {
        System.out.println("Server start");
        try {
            server = new ServerSocket(7896, 340);
            while(true) {
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                IPAddress = String.valueOf(connection.getInetAddress());
                String type = null;
                JSONObject data = (JSONObject) input.readObject();
                type  = String.valueOf(data.get("type"));
                if(!type.equals("")) { new Handler(new Handler(data));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public class Handler implements Runnable {


        public Handler(JSONObject data) {
            String type  = String.valueOf(data.get("type"));
            //scenario
            if(type.equals("messages")) {
                String message = String.valueOf(data.get("message"));
                try {
                    output.writeObject("Your message: " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //scenario
            if(type.equals("dataContact")) {
                JSONObject obj_publicKey = (JSONObject) data.get("data");
                try {
                    obj_publicKey.put("IP", IPAddress);
                    output.writeObject("Your message: " + obj_publicKey);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File path = new File ("src"+separator+"chat"+separator+"data"+separator+"contacts.json");
                try  {
                    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
                    writer.println(obj_publicKey);
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //scenario
        }

        public Handler(Handler handler) {
        }

        @Override
        public void run() {

        }
    }
}
