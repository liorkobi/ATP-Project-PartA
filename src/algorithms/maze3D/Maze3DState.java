package algorithms.maze3D;
import algorithms.search.AState;


public class Maze3DState extends AState {

    Position3D p;


    /**
     * Maze state is state of the maze
     * @param d - depth
     * @param r - row
     * @param c - column
     *          create new positin with these parameters
     *          initialize the state color as white
     */
    public Maze3DState(int d,int r, int c) {
        Color = color.white;
        this.p = new Position3D(d,r,c);
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
        if (!(P instanceof Maze3DState)) {
            return false;
        }
        Maze3DState M = (Maze3DState) P;

        return this.p.equals(M.p);
    }


}


