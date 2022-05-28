package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable,Cloneable{

    Maze m;
    MazeState[][] V2D;
    MazeState Start;
    MazeState Goal;


    public SearchableMaze(Maze maze) {
        Start=null;
        Goal=null;
        if(maze.getCol()>0 && maze.getRow()>0){
             m=maze;
            V2D = new MazeState[maze.getRow()][maze.getCol()];
            buildVertexList(m);}
        else {
            m=null;
            V2D = null;}



    }
public SearchableMaze(SearchableMaze S){
        Start=S.Start;
        Goal=S.Goal;
        m=S.m;
        V2D = new MazeState[m.getRow()][m.getCol()];
        buildVertexList(m);


}
    private void buildVertexList(Maze maze){
        for(int i=0;i<maze.getRow();i++){
            for(int j=0;j<maze.getCol();j++){
                if(maze.getval(i,j)==0){
                    MazeState v=new MazeState(i,j);
                    V2D[i][j]=v;
                }
            }
        }
        Start=V2D[(maze.getStartPosition().getRowIndex())][maze.getStartPosition().getColumnIndex()];
        Goal=V2D[(maze.getGoalPosition().getRowIndex())][maze.getGoalPosition().getColumnIndex()];

    }

    public AState getStart() {
        return Start;
    }

    public AState getGoal() {
        return Goal;
    }



    //up
    private boolean checkUp(MazeState v, Maze m) {
        if(v==null){return false;}
        return v.p.getRowIndex() - 1 >= 0 && m.getval(v.p.getRowIndex() - 1, v.p.getColumnIndex()) == 0;
    }

    //down
    private boolean checkDown(MazeState v, Maze m){
            if(v==null){return false;}
            return v.p.getRowIndex() + 1 < m.getRow() && m.getval(v.p.getRowIndex() + 1, v.p.getColumnIndex()) == 0;
        }
    private boolean checkUR(MazeState v, Maze m){
        if(v==null){return false;}
        return v.p.getColumnIndex() +1 < m.getCol() && v.p.getRowIndex() - 1 >= 0 && m.getval(v.p.getRowIndex() - 1, v.p.getColumnIndex()+1) == 0;
    }
    public boolean checkDR(MazeState v, Maze m){
        if(v==null){return false;}
        return v.p.getColumnIndex() +1 < m.getCol() && v.p.getRowIndex() + 1 < m.getRow() && m.getval(v.p.getRowIndex() + 1, v.p.getColumnIndex()+1) == 0;
    }
    private boolean checkDL(MazeState v, Maze m){
        if(v==null){return false;}
        return v.p.getColumnIndex() -1 >=0 && v.p.getRowIndex() + 1 < m.getRow() && m.getval(v.p.getRowIndex() + 1, v.p.getColumnIndex()-1) == 0;
    }

    private boolean checkUL(MazeState v, Maze m){
        if(v==null){return false;}
        return v.p.getColumnIndex() -1 >=0 && v.p.getRowIndex() - 1>=0 && m.getval(v.p.getRowIndex() - 1, v.p.getColumnIndex()-1) == 0;
    }


        //right
        private boolean checkRight(MazeState v, Maze m) {
        if(v==null){return false;}
            return v.p.getColumnIndex() + 1 < m.getCol() && m.getval(v.p.getRowIndex(), v.p.getColumnIndex() + 1) == 0;
        }

        //left
        private boolean checkLeft(MazeState v, Maze m) {
            if(v==null){return false;}
            return v.p.getColumnIndex() - 1 >= 0 && m.getval(v.p.getRowIndex(), v.p.getColumnIndex() - 1) == 0;
        }

    private ArrayList<AState> buildAdjacentList(MazeState v) {
        ArrayList<AState> Adj = new ArrayList<AState>();
        //up
        if (checkUp(v, m)) {
            Adj.add(V2D[v.p.getRowIndex() - 1][v.p.getColumnIndex()]);
            V2D[v.p.getRowIndex() - 1][v.p.getColumnIndex()].setCost(10+v.getCost());

        }
        //UR diagonal
        if (checkRight(v, m) || checkUp(v, m)) {
            if (checkUR(v,m)) {
                Adj.add(V2D[v.p.getRowIndex() - 1][v.p.getColumnIndex() + 1]);
                V2D[v.p.getRowIndex() - 1][v.p.getColumnIndex() + 1].setCost(15+v.getCost());
            }
        }

        //right
        if (checkRight(v, m)) {
            Adj.add(V2D[v.p.getRowIndex()][v.p.getColumnIndex() + 1]);
            V2D[v.p.getRowIndex()][v.p.getColumnIndex()+1].setCost(10+v.getCost());

        }
        //DR diagonal
        if (checkRight(v, m) || checkDown(v, m)) {
            if (checkDR(v,m)) {
                Adj.add(V2D[v.p.getRowIndex() + 1][v.p.getColumnIndex() + 1]);
                V2D[v.p.getRowIndex() + 1][v.p.getColumnIndex() + 1].setCost(15+v.getCost());
            }
        }

        //down
        if (checkDown(v, m)) {
            Adj.add(V2D[v.p.getRowIndex() + 1][v.p.getColumnIndex()]);
            V2D[v.p.getRowIndex() +1][v.p.getColumnIndex()].setCost(10+v.getCost());

        }
        //DL diagonal
        if (checkDown(v, m) || checkLeft(v, m)) {
            if (checkDL(v,m)) {
                Adj.add(V2D[v.p.getRowIndex() + 1][v.p.getColumnIndex() - 1]);
                V2D[v.p.getRowIndex() + 1][v.p.getColumnIndex() - 1].setCost(15+v.getCost());
            }
        }
        //left
        if (checkLeft(v, m)) {
            Adj.add(V2D[v.p.getRowIndex()][v.p.getColumnIndex() - 1]);
            V2D[v.p.getRowIndex()][v.p.getColumnIndex()-1].setCost(10+v.getCost());

        }
        //UL diagonal
        if (checkLeft(v, m) || (checkUp(v, m))){
            if (checkUL(v,m)) {
                Adj.add(V2D[v.p.getRowIndex() - 1][v.p.getColumnIndex() - 1]);
                V2D[v.p.getRowIndex() - 1][v.p.getColumnIndex() - 1].setCost(15+v.getCost());
            }}


    return Adj;
}
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        return buildAdjacentList((MazeState) state);}

    @Override
    public SearchableMaze clone() {
            SearchableMaze clone = new SearchableMaze(this);
            return clone;
    }
}
