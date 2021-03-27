import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    //Declaring IP and Port
    private static final String SERVER_IP = "127.0.0.1"; // Localhost
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        //Creating a socket
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        //Setting up a connectioin between client and server
        ServerConnection serverConnection = new ServerConnection(socket);

        //Getting keyboard input and writing it to the sever
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        //Starting connection
        new Thread(serverConnection).start();

        while (true) {
            System.out.println("> ");
            String command = br.readLine();

            //If the input fomr keyboard contains "say", the server will print the input to all connections
            if (command.contains("say")){
                pw.println(command);
            }

            //A clean command to force a shutdown
            else if (command.equals("sys-shutdown") || command.equals("force shutdown")){
                break;
            }

            //Printing input to the server and then things happen
            else {
                pw.println(command);
            }
        }
        //If the while-loop breaks, the system shuts down
        socket.close();
        System.exit(0); //Denne er ikke nødvendigvis nødvendig å ha her.
    }
}
