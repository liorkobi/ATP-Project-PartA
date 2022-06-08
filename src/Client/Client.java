//package Client;
//
//import Client.IClientStrategy;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.Socket;
//
//public class Client {
//    private InetAddress serverIP;
//    private int serverPort;
//    private IClientStrategy strategy;
//
//    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
//        this.serverIP = serverIP;
//        this.serverPort = serverPort;
//        this.strategy = strategy;
//    }
//
////    public void communicateWithServer(){
////        try(Socket serverSocket = new Socket(serverIP, serverPort)){
////            System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
////            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
//    public void communicateWithServer() {
//        try {
//            Socket serverSocket = new Socket(this.serverIP, this.serverPort);
//
//            try {
//                this.strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
//            } catch (Throwable var5) {
//                try {
//                    serverSocket.close();
//                } catch (Throwable var4) {
//                    var5.addSuppressed(var4);
//                }
//
//                throw var5;
//            }
//
//            serverSocket.close();
//        } catch (IOException var6) {
//            var6.printStackTrace();
//        }
//
//    }
//}
package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements IClientStrategy {

    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) { }

    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    /**
     *
     * @param serverIP - ip of the target server
     * @param serverPort - port of the target server
     * @param strategy - what do i want the server to do?
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = strategy;
    }

    /**
     * start communication with the server
     * send to the client strategy the necessary parameters
     */
    public void communicateWithServer(){
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
            //  System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
            clientStrategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}