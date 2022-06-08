package Server;
import java.io.*;

import algorithms.mazeGenerators.*;

//לשנות לMY
import IO.SimpleCompressorOutputStream;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    public ServerStrategyGenerateMaze() {

    }

    @Override
        public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
            try {
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

                int[] mazeSize = (int[])fromClient.readObject();
                Configurations conf = Configurations.getInstance();
                String mazeGen = conf.getval("mazeGeneratingAlgorithm");
                AMazeGenerator mazeGenerator;
                if (mazeGen.equals("MyMazeGenerator")) // generates based on the configuration file
                    mazeGenerator = new MyMazeGenerator();
                else if (mazeGen.equals("SimpleMazeGenerator"))
                    mazeGenerator = new SimpleMazeGenerator();
                else
                    mazeGenerator= new EmptyMazeGenerator();
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

