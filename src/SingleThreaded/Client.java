package SingleThreaded;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void run() throws UnknownHostException, Exception {

        int port = 8080;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);
        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer.println("Hello from a SingleThreaded Client!");
        String response = fromServer.readLine();
        System.out.println("Response from server: " + response);
        toServer.close();
        fromServer.close();
        socket.close();
    }

    public static void main(String[] args) {

        Client client = new Client();
        try {
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
