import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable {

    //Declaring variables
    private final Socket server;
    private final BufferedReader in;

    //General constructor
    public ServerConnection (Socket s) throws IOException {
        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true){
                //Printing the input to the server so the bots can read it
                String serverResponse = in.readLine();
                if (serverResponse == null) break;
                System.out.println(serverResponse);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //Closing if while-loop above breaks
        finally {
            try {
                in.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
