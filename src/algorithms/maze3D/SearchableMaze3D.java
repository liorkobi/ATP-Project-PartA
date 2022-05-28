package algorithms.maze3D;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import java.util.ArrayList;


/**
 * adapt maze to a searchable object
 */
public class SearchableMaze3D implements ISearchable,Cloneable {
    Maze3D m;
    Maze3DState[][][] V3D;
    Maze3DState Start;
    Maze3DState Goal;

    /**
     * Constractor
     * @param maze - to adapt
     *             V3D - 3D array of MazeStates.
     */
    public SearchableMaze3D(Maze3D maze) {
        V3D = new Maze3DState[maze.getDepth()][maze.getRow()][maze.getCol()];
        Start=null;
        Goal=null;
        m=maze;
        buildVertexList(m);



    }
    //copy constructor
    public SearchableMaze3D(SearchableMaze3D S){
        Start=S.Start;
        Goal=S.Goal;
        m=S.m;
        V3D=new Maze3DState[m.getDepth()][m.getRow()][m.getCol()];;
        buildVertexList(m);
    }
    private void buildVertexList(Maze3D maze){
        for(int k=0;k<maze.getDepth();k++){
            for(int i=0;i<maze.getRow();i++){
            for(int j=0;j<maze.getCol();j++){
                if(maze.getval(k,i,j)==0){
                    Maze3DState v=new Maze3DState(k,i,j);
                    V3D[k][i][j]=v;
                }
            }
        }
        Start=V3D[maze.getStartPosition().getDepthIndex()][maze.getStartPosition().getRowIndex()][maze.getStartPosition().getColumnIndex()];
        Goal=V3D[maze.getGoalPosition().getDepthIndex()][maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()];

    }}

    public AState getStart() {
        return Start;
    }

    public AState getGoal() {
        return Goal;
    }

    /**
     * the following "Check..." methods verify that the nodes are within the maze boundaries
     */
    //up
    private boolean checkUp(Maze3DState v, Maze3D m) {
        if(v==null){return false;}
        return v.p.getRowIndex() - 1 >= 0 && m.getval(v.p.getDepthIndex(),v.p.getRowIndex() - 1, v.p.getColumnIndex()) == 0;
    }

    //down
    private boolean checkDown(Maze3DState v, Maze3D m){
        if(v==null){return false;}
        return v.p.getRowIndex() + 1 < m.getRow() && m.getval(v.p.getDepthIndex(),v.p.getRowIndex() + 1, v.p.getColumnIndex()) == 0;
    }
    private boolean checkIn(Maze3DState v, Maze3D m){
        if(v==null){return false;}
        return v.p.getDepthIndex() +1 < m.getDepth() && m.getval(v.p.getDepthIndex()+1,v.p.getRowIndex() , v.p.getColumnIndex()) == 0;
    }
    public boolean checkOut(Maze3DState v, Maze3D m){
        if(v==null){return false;}
        return v.p.getDepthIndex() -1 >= 0 && m.getval(v.p.getDepthIndex()-1,v.p.getRowIndex() , v.p.getColumnIndex()) == 0;
    }

    //right
    private boolean checkRight(Maze3DState v, Maze3D m) {
        if(v==null){return false;}
        return v.p.getColumnIndex() + 1 < m.getCol() && m.getval(v.p.getDepthIndex(),v.p.getRowIndex(), v.p.getColumnIndex() + 1) == 0;
    }

    //left
    private boolean checkLeft(Maze3DState v, Maze3D m) {
        if(v==null){return false;}
        return v.p.getColumnIndex() - 1 >= 0 && m.getval(v.p.getDepthIndex(),v.p.getRowIndex(), v.p.getColumnIndex() - 1) == 0;
    }

    private ArrayList<AState> buildAdjacentList(Maze3DState v) {
        ArrayList<AState> Adj = new ArrayList<AState>();
        //up
        if (checkUp(v, m)) {
            Adj.add(V3D[v.p.getDepthIndex()][v.p.getRowIndex() - 1][v.p.getColumnIndex()]);
        }
        //right
        if (checkRight(v, m)) {
            Adj.add(V3D[v.p.getDepthIndex()][v.p.getRowIndex()][v.p.getColumnIndex() + 1]);
        }
        //down
        if (checkDown(v, m)) {
            Adj.add(V3D[v.p.getDepthIndex()][v.p.getRowIndex() + 1][v.p.getColumnIndex()]);
        }

        //left
        if (checkLeft(v, m)) {
            Adj.add(V3D[v.p.getDepthIndex()][v.p.getRowIndex()][v.p.getColumnIndex() - 1]);
        }
        //Inside
        if (checkIn(v, m)) {
            Adj.add(V3D[v.p.getDepthIndex()+1][v.p.getRowIndex()][v.p.getColumnIndex()]);
        }
        //Outside
        if (checkOut(v, m)) {
            Adj.add(V3D[v.p.getDepthIndex()-1][v.p.getRowIndex()][v.p.getColumnIndex()]);
        }

        return Adj;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        return buildAdjacentList((Maze3DState) state);}

    @Override
    public SearchableMaze3D clone() {
        SearchableMaze3D clone = new SearchableMaze3D(this);
        return clone;
    }
}

