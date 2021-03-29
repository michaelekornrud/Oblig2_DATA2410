import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    PrintWriter out;
    Socket socket;
    BufferedReader clientReader;
    BufferedReader serverReader;
    RunServer server;

    public ClientHandler(Socket socket_connected, RunServer runServer) throws IOException {
        this.socket = socket_connected;
        this.server = runServer;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.serverReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        int counter = 0;

        while (true) {

            try {
                String clientMsg;
                while (counter < server.allClients.size()) {
                    clientMsg = clientReader.readLine();
                    if (clientMsg != null) {
                        System.out.println(clientMsg);
                    } else {
                        System.out.println("Closing server...");
                        System.exit(0);
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
