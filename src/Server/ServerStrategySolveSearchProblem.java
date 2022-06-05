package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

 try {
        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

        Maze maze = (Maze)fromClient.readObject();
        ISearchingAlgorithm i = new BestFirstSearch();
        ISearchable Smaze=new SearchableMaze(maze);
        Solution solution = i.solve(Smaze);
     OutputStream outStream = new FileOutputStream("Solution.ser");
     ObjectOutputStream fileObjectOut = new ObjectOutputStream(outStream);

     try{
         fileObjectOut.writeObject(solution);
         fileObjectOut.close();
         outStream.close();
     }
     catch (Exception e) {
         e.printStackTrace();}

        toClient.writeObject(fileObjectOut);
        toClient.flush();
        fromClient.close();
        toClient.close();

 }
 catch (Exception e1) {
     e1.printStackTrace();}


        }
        }

