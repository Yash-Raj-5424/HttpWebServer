package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);

        while(true){
            try{
                System.out.println("Server is listening on port " + port);
                Socket socket = serverSocket.accept();
                System.out.println("Connection accepted from " + socket.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                toClient.println("Hello from a SingleThreaded Server!");
                toClient.close();
                fromClient.close();
                socket.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        try{
            Server server = new Server();
            server.run();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
