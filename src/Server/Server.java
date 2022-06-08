//package Server;
//import Server.IServerStrategy;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.SocketTimeoutException;
//
//    public class Server {
//        private int port;
//        private int listeningIntervalMS;
//        private IServerStrategy strategy;
//        private boolean stop;
//
//        public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
//            this.port = port;
//            this.listeningIntervalMS = listeningIntervalMS;
//            this.strategy = strategy;
//        }
//
//        public void start(){
//            try {
//                ServerSocket serverSocket = new ServerSocket(port);
//                serverSocket.setSoTimeout(listeningIntervalMS);
//                System.out.println("Starting server at port = " + port);
//
//                while (!stop) {
//                    try {
//                        Socket clientSocket = serverSocket.accept();
//                        System.out.println("Client accepted: " + clientSocket.toString());
//
//                        try {
//                            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
//                            clientSocket.close();
//                        } catch (IOException e){
//                            e.printStackTrace();
//                        }
//                    } catch (SocketTimeoutException e){
//                        System.out.println("Socket timeout");
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public void stop(){
//            stop = true;
//        }
//    }
//
//
package Server;

import Server.IServerStrategy;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;


    /**
     * Constractor
     * @param port - the port clients can communicate with tis server
     * @param listeningIntervalMS - the time the server is open for requests
     * @param strategy - what is my service for the client.
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        Configurations conf = Configurations.getInstance();
        String s = conf.getval("threadPoolSize");
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.stop=false;
        this.threadPool = Executors.newFixedThreadPool(Integer.parseInt(s));

    }

    /**
     * start a thread that waits for the client's acceptance
     */
    public void start() {
        new Thread(() -> {
            this.startInner();
        }).start();
    }

    /**
     * ask if any client is interested in me , if exists handle it
     */
    public void startInner(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());

                    // This thread will handle the new Client
                    Thread t = new Thread(() -> {
                        handleClient(clientSocket);
                    });
                    threadPool.execute(t);

                } catch (SocketTimeoutException e){
                 System.out.println("Socket timeout");
                }
            }            threadPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * apply the appropriate server strategy (match the client request from the client socket).
     * @param clientSocket
     */
    private void handleClient(Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e1){
            e1.printStackTrace();

        }
    }

    public void stop(){
        stop = true;
    }
}
