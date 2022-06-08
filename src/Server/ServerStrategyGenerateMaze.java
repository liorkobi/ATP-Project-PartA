package Server;
import java.io.*;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

//לשנות לMY
import IO.SimpleCompressorOutputStream;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    public ServerStrategyGenerateMaze() {

    }

    /**
     * this strategy generate a maze with the size mention in 'fromClient'
     * with the generator algorithm specified in the configuration file
     * compress the maze and send it to compress
     * @param inFromClient
     * @param outToClient
     */
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
                OutputStream out = new MyCompressorOutputStream(outPutStream );
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

