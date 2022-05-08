package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AmazeGenerator{
    @Override
    public Maze generate(int r , int c) {
        Maze maze=new Maze(0,0);
        return maze;
    }
}
