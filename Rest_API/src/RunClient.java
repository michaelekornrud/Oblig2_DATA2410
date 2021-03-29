import java.io.IOException;
import java.net.Socket;

public class RunClient {

    public void run(String ip,int port, String name) throws IOException {
        Socket server = new Socket(ip, port);
        System.out.println(name+" is now connected to the server with port "+server.getPort());

        ServerHandler serverHandler = new ServerHandler(ip, port, name, server);
        serverHandler.start();

    }

}
