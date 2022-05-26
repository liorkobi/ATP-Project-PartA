package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(8, 8);
//        int[][] n;
//           n= new int[][]{{0, 0, 0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 1, 1, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 1}, {1, 0, 0, 1, 0, 1, 0, 1}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 0, 1, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 0}};
//           maze.setExit(new Position(7,7));
//           maze.setStart(new Position(0,3));
//        maze.setmaze(n);
        SearchableMaze searchableMaze = new SearchableMaze(maze);

     solveProblem(searchableMaze, new BreadthFirstSearch());
      solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());

        // prints the maze
         maze.print();


// get the maze entrance
        Position startPosition = maze.getStartPosition();

// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"

// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
         for (int i = 0; i < solutionPath.size(); i++) {
           System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }

}