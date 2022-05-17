package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MazeState extends AState {

    Position p;

    ArrayList<AState> Adj;

    public MazeState(int r, int c) {
        Color = color.white;
        Adj=new ArrayList<>();
        this.p = new Position(r,c);
    }

    //AState's status for the searching algorithms
    public color getColor() {
        return Color;
    }

    public void setColor(color color) {
        Color = color;
    }

    //add an adjacent AState
    public void addAdj(AState v){
        Adj.add(v);
    }

    //check if v is adjacent of this
    public boolean isAdj(AState v){
        return Adj.contains(v);
    }

    public ArrayList<AState> getAdj() {
        return Adj;
    }
/*

    @Override
    public int compareTo(Object o) {
      //  if(o==null){return 1;}
        MazeState P=(MazeState) o;
       return Integer.compare(cost,P.cost);
    }


 */

    public String toString() {
        return p.toString();
    }
    @Override
    public boolean equals(Object P) {

        // If the object is compared with itself then return true
        if (P == this) {
            return true;
        }


        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(P instanceof MazeState)) {
            return false;
        }
        MazeState M = (MazeState) P;

        // Compare the data members and return accordingly
        return this.p.equals(M.p);
    }

}
