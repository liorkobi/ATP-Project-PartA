package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    /**
     * this strategy solve Maze from 'fromClient' with the searching algorithm specified in the config file
     * gives the maze a uniqe name and save it and its solution in a file for future use.
     * the strategy will never solve the same Maze twice ' it will use yhe solution from the file if exists.
     * @param inFromClient
     * @param outToClient
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();

            Maze maze = (Maze) fromClient.readObject();
            String fileP = System.getProperty("java.io.tmpdir") + maze.toString().hashCode() + "maze";
            File file = new File(fileP);


            if (!file.exists()) {
                ISearchable Smaze = new SearchableMaze(maze);
                Configurations conf = Configurations.getInstance();
                String search = conf.getval("mazeSearchingAlgorithm");
                ASearchingAlgorithm i;
                if (search.equals("Breadth")) // solves via the algorithm that is written in the configuration file
                    i = new BreadthFirstSearch();
                else if (search.equals("DFS"))
                     i = new DepthFirstSearch();
                else
                   i = new BestFirstSearch();
                Solution solution = i.solve(Smaze);
                toClient.writeObject(solution);
                toClient.flush();
                FileOutputStream outStream = new FileOutputStream(fileP);
                ObjectOutputStream fileObjectOut = new ObjectOutputStream(outStream);

                fileObjectOut.writeObject(solution);
                fileObjectOut.close();

            } else {
                FileInputStream fileN = new FileInputStream(fileP);
                ObjectInputStream mazeI = new ObjectInputStream(fileN);
                toClient.writeObject((Solution) mazeI.readObject());
                mazeI.close();}

                fromClient.close();
                toClient.close();

        }


 catch (Exception e1) {
     e1.printStackTrace();}


        }
        }
