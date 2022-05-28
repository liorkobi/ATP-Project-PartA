package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;

public class Maze3DState extends AState {

    Position3D p;


    public Maze3DState(int d,int r, int c) {
        Color = color.white;
        this.p = new Position3D(d,r,c);
    }

    //AState's status for the searching algorithms
//    public color getColor() {
//        return Color;
//    }
//
//    public void setColor(color color) {
//        Color = color;
//    }


    public String toString() {
        return p.toString();
    }

    @Override
    public boolean equals(Object P) {

        // If the object is compared with itself then return true
        if (P == this) {
            return true;
        }
        if (!(P instanceof Maze3DState)) {
            return false;
        }
        Maze3DState M = (Maze3DState) P;

        return this.p.equals(M.p);
    }


}


