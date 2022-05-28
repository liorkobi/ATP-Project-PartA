package algorithms.mazeGenerators;
/**
 * declaration of the necessary methods for 2D maze generator
 */
public interface IMazeGenerator {
    Maze generate(int r,int c);
    long measureAlgorithmTimeMillis(int r,int c);
}
