package test;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.*;

public class GeneralCheckingFunctions {
    public static boolean check3DMaze(){
        boolean weChoseToDoTheMaze3DAssignment=true;
        return weChoseToDoTheMaze3DAssignment;
    }

    public static void main(String[] args) {
      //  testMazeGenerator(new EmptyMazeGenerator());
   //     testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator3D mazeGenerator) {
// prints the time it takes the algorithm to run
       System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(20,20,20)));
// generate another maze
        Maze3D maze = mazeGenerator.generate(20, 20,20);
// prints the maze


// get the maze entrance
        Position3D startPosition = maze.getStartPosition();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
       maze.print();

    }
}
