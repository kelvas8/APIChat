package chat.client;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;


public class Client extends JFrame implements Runnable {
     private Socket connection;
     private ObjectOutputStream output;
     private ObjectInputStream input;
     public JTextArea textinp = new JTextArea(25, 30);
     public ArrayList<String> contacts;
     public String user = "127.0.0.1";
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
                textinp.append((String) input.readObject() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendDate(Object obj) {
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
