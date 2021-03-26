package oblig2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    //Dercalring port
    private static final  int PORT = 8080; //Could  be 9090, 8010 etc.

    //A list of things to do (if needed)
    public static String [] toDO = {"Hei jesus", "penis"};

    //An arraylist to keep track of connections
    private static final ArrayList<ClientHandler> clients = new ArrayList<>();

    //There can only be 4 (or specified number) clients connected at the same time
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        //Creating a serversocket
        ServerSocket listener = new ServerSocket(PORT);

        //While the socket is open and listening for connections
        while (true){
            //Accepting connections
            System.out.println("[SERVER] Waiting for a connection");
            Socket client = listener.accept();

            //Retrieving hostname and client-port
            String hName = client.getInetAddress().getHostName();
            byte[] hostName = client.getInetAddress().getAddress();
            int clientPort = client.getPort();

            //Printing names and portnumbers:
            System.out.println("[SERVER] " + hName + " " + hostName + " " + clientPort + " connected to server");


            //Welcomes new connections  to the server
            PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
            pw.println("Welcome to the server!");

            //Using clientHandler to handle different connections
            ClientHandler clientHandler = new ClientHandler(client, clients);
            clients.add(clientHandler);
            pool.execute(clientHandler);
        }

    }
}
