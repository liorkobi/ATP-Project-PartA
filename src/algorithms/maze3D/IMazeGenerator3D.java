package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;

public interface IMazeGenerator3D {
    Maze generate(int depth,int row, int col);
    long measureAlgorithmTimeMillis(int depth,int r,int c);
}
