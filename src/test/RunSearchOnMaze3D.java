package test;

import algorithms.maze3D.*;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(30,31, 31);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);

        solveProblem(searchableMaze, new BreadthFirstSearch());
         solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());

        // prints the maze
        maze.print();


// get the maze entrance
        Position3D startPosition = maze.getStartPosition();

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
