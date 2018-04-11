package chat;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;


public class Client  {
     private static Socket connection;
     private static ObjectOutputStream output;
     private static ObjectInputStream input;
     private static ArrayList<String> contacts;
     private static String user = "127.0.0.1";
     private Object obj;


    public Client() {
        // this constructor probably will be needing
    }

    public Client(ArrayList<String> contacts) {
        this.contacts = contacts;
    }

    public static void runClient() {
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
                System.out.println((String) input.readObject() + "this working");
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



}
