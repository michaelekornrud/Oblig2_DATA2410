import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BroadcastThread extends Thread{
    RunServer server;
    BufferedReader serverReader;

    public BroadcastThread(RunServer serverRunner){
        this.server = serverRunner;
        this.serverReader = new BufferedReader(new InputStreamReader(System.in));

    }

    @Override
    public void run(){

        try {
            String serverMsg;
            while (true) {
                serverMsg = serverReader.readLine();
                if (serverMsg != null) {
                    System.out.println("Echo: " + serverMsg);
                    server.broadcast("Server: " + serverMsg);
                    //out.println("Server: "+serverMsg);
                } else {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        }catch (IOException e ){
            e.printStackTrace();
        }

    }
}
