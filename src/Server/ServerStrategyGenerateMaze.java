package Server;
import java.io.*;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;

//לשנות לMY
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.MyMazeGenerator;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    public ServerStrategyGenerateMaze() {

    }

    @Override
        public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
            try {
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

                int[] mazeSize = (int[])fromClient.readObject();
                AMazeGenerator mazeGenerator = new MyMazeGenerator();
                Maze maze = mazeGenerator.generate(mazeSize[0],mazeSize[1]);
                ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
                OutputStream out = new SimpleCompressorOutputStream(outPutStream );
            try{
                out.write(maze.toByteArray());
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();}


                toClient.writeObject(outPutStream.toByteArray());
                toClient.flush();
                fromClient.close();
                toClient.close();




            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

