package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int r,int c);
    long measureAlgorithmTimeMillis(int r,int c);
}
