package algorithms.mazeGenerators;

/**
 * generats maze in size 0.
 */
public class EmptyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int r , int c) {
        Maze maze=new Maze(0,0);
        return maze;
    }
}
