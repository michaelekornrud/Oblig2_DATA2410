package oblig2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientHandler implements Runnable{

    //Deeclaring different variables
    private final Socket client;
    private final BufferedReader in;
    private final PrintWriter out;
    private final ArrayList<ClientHandler> clients;

    //Regular constructor
    public ClientHandler (Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
        this.client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

    }

    @Override
    public void run () {
        try {
            while (true){
                String request = in.readLine();
                int firstSpace = request.indexOf(" ");
                if (firstSpace  != -1){
                    broadcast(request.substring(firstSpace + 1));
                }

                if (request == null) {
                    out.println("Skriv noe ordentlig din penis");
                }

            }
        }
        catch (IOException e){
            System.err.println("IOexeption in client handler...");
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

        //If the while-loop breaks, the readers will close

        finally {
            out.close();
            try {
               in.close();
            }
            catch (IOException e){
                System.out.println("Could not close socket-in");
                e.printStackTrace();
            }
        }
    }

    //Broadcastinig to the server
    public void broadcast (String msg){
        for (ClientHandler aClient : clients){
            aClient.out.println(msg);
            aClient.out.flush();
        }
    }
}
