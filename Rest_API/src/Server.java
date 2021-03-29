import java.io.IOException;

public class Server {
    public static void main(String [] args) throws IOException {
        int port = 4343;
        new RunServer().run(port);
    }

}
