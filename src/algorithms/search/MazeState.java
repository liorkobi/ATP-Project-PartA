package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.ArrayList;


public class MazeState extends AState {

    Position p;


    public MazeState(int r, int c) {
        Color = color.white;
        this.p = new Position(r,c);
    }

    //AState's status for the searching algorithms
    public color getColor() {
        return Color;
    }

    public void setColor(color color) {
        Color = color;
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
