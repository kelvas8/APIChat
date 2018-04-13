package chat.server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import chat.server.handlers.Handler;

public class Server implements Runnable {
     private ServerSocket server;
     private Socket connection;

    @Override
    public void run() {
        runServer();
    }

    private  void runServer() {
        try {
            server = new ServerSocket(7896, 340);
            System.out.println("Server start");
            while(!server.isClosed()) {
                connection = server.accept();
              //System.out.println(connection.getInetAddress().getHostName() + " connected");
                new Thread( new Handler(connection)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
