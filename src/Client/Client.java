package Client;

import Client.IClientStrategy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

//    public void communicateWithServer(){
//        try(Socket serverSocket = new Socket(serverIP, serverPort)){
//            System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
//            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void communicateWithServer() {
        try {
            Socket serverSocket = new Socket(this.serverIP, this.serverPort);

            try {
                this.strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
            } catch (Throwable var5) {
                try {
                    serverSocket.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            serverSocket.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }
}
