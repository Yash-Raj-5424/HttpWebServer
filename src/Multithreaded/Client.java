package Multithreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable(){
        return new Runnable(){
            private int port = 8080;
            @Override
            public void run(){
                try{
                    InetAddress address = InetAddress.getByName("localhost");
                    Socket socket = new Socket(address, port);
                    try(
                        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    ){
                        toServer.println("Hello From a client! " + socket.getLocalSocketAddress());
                        String line = fromServer.readLine();
                        System.out.println("Response from server: " + line);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i < 100; i++) {
            try{
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
