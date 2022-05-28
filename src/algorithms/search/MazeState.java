package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.ArrayList;


public class MazeState extends AState {

    Position p;

    /**
     * Maze state is state of the maze
     * @param r - row
     * @param c - column
     *          create new positin with these parameters
     *          initialize the state color as white
     */
    public MazeState(int r, int c) {
        Color = color.white;
        this.p = new Position(r,c);
    }

    public String toString() {
        return p.toString();
    }

    @Override
    public boolean equals(Object P) {

        // If the object is compared with itself then return true
        if (P == this) {
            return true;
        }
        if (!(P instanceof MazeState)) {
            return false;
        }
        MazeState M = (MazeState) P;

        return this.p.equals(M.p);
    }

}
