package test;
import algorithms.mazeGenerators.*;
public class RunMazeGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new EmptyMazeGenerator());
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
// prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(1000,1000)));
// generate another maze
        Maze maze = mazeGenerator.generate(5, 5);
// prints the maze
        /*maze.print();
        Maze maze2= new Maze(maze.toByteArray());
        maze2.print();*/
        /*for(int i=0; i<maze.toByteArray().length;i++){
            System.out.print(maze.toByteArray()[i]+",");
        }*/

// get the maze entrance
        Position startPosition = maze.getStartPosition();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}