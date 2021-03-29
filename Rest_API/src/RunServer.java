import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RunServer {

    public ArrayList<ClientHandler> allClients = new ArrayList<>();

    public void run(int port) throws IOException {
        //Creates a server that is connected to port
        ServerSocket server = new ServerSocket(port);
        //Create a thread to handle the server-broadcasting
        BroadcastThread broadcastThread = new BroadcastThread(this);
        broadcastThread.start();


        try {
            while (true) {
                System.out.println("Listening for connections...");
                //Accepts a new client/connection
                Socket client = server.accept();
                //allClients.add(client);
                System.out.println("New client with port: "+client.getPort()+" joined server..");
                //Start a new thread to handle the client
                ClientHandler handler = new ClientHandler(client, this);
                //Add client to list of clients
                allClients.add(handler);
                System.out.println("Clientlist: "+allClients);
                //Start thread
                handler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void broadcast(String msg){
        for (ClientHandler client: allClients){
            client.out.println(msg);
            client.out.flush();
        }
    }
}
