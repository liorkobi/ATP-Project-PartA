package algorithms.maze3D;

/**
 * declaration of the necessary methods for 3D maze generator
 */
public interface IMaze3DGenerator {
    Maze3D generate(int depth,int row, int col);
    long measureAlgorithmTimeMillis(int depth,int r,int c);
}
