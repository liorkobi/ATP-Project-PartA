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



    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.stop=false;
        this.threadPool = Executors.newFixedThreadPool(3);

    }

    public void start() {
        new Thread(() -> {
            this.startInner();
        }).start();
    }
    public void startInner(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);
        //    LOG.info("Starting server at port = " + port);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());

                    //      LOG.info("Client accepted: " + clientSocket.toString());

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
           // LOG.error("IOException", e);
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
           // LOG.info("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e){
            //LOG.error("IOException", e);
        }
    }

    public void stop(){
     //   LOG.info("Stopping server...");
        stop = true;
    }
}