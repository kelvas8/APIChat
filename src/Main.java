import client.Client;

import java.io.*;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args)  {
        Files data = new Files();

        ArrayList<String> contacts = data.read("contacts.json");
        new Thread (new Server()).start();
        Thread client = new Thread (new Client(contacts));
        client.run();


    }



}

class Files {

    public ArrayList<String>  read(String input) {
            String separator = File.separator;
            String path = "src"+separator+"data"+separator+input;
            FileReader file = null;
        try {
            file = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(file);
        String str;
        ArrayList<String> list = new ArrayList<String>();

        try {
            while ((str = buffer.readLine()) != null) {
                list.add(str);
            }
            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}