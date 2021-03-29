import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler extends Thread {
    String ip;
    int port;
    String name;
    Socket server;
    PrintWriter outputStream;
    BufferedReader serverMsg;
    BufferedReader clientMSg;


    public ServerHandler(String ip_address, int port_number, String client_name, Socket server_socket) throws IOException {
        this.ip = ip_address;
        this.port = port_number;
        this.name = client_name;
        this.server = server_socket;
        this.outputStream = new PrintWriter(server.getOutputStream(), true);
        this.serverMsg = new BufferedReader(new InputStreamReader(server.getInputStream()));
        this.clientMSg = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {

        try {
            String sMsg, cMsg;
            while (!(sMsg = serverMsg.readLine()).contains("Exit")) {
                System.out.println(sMsg);

                //Read msg from client-terminal
                cMsg = clientMSg.readLine();
                //Send the msg to server
                outputStream.println(name + ": " + cMsg);
            }
            System.out.println("The server threw me out....");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
