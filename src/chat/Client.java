package chat;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;


public class Client implements Runnable {
     private Socket connection;
     private ObjectOutputStream output;
     private ObjectInputStream input;
     public ArrayList<String> contacts;
     public String user = "127.0.0.1";
     private Controller mc;


    public Client(ArrayList<String> contacts) {
        this.contacts = contacts;
    }
    @Override
    public void run() {
        System.out.println("client start");
        try {
            while (true) {
                try {
                connection = new Socket(InetAddress.getByName(user), 7896);
                } catch (ConnectException e) {
                    e.printStackTrace();
                }
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                  //  System.out.println((String) input.readObject());
                Controller mc = null;
                System.out.println(mc.i);
                //sendDate(Controller.obj);
                if(!Controller.obj.equals("{}")) { System.out.println(0); sendDate(mc.obj); }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } //catch (ClassNotFoundException e) {
           // e.printStackTrace();
       // }

    }

    public  void sendDate(Object obj) {
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
