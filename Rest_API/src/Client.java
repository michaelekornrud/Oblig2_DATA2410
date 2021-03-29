import java.io.IOException;

public class Client {
    public static void main(String [] args) throws IOException {
        int port = 4343;
        String ip = "localhost";
        String name = args[0];
        new RunClient().run(ip, port, name);
    }
}
