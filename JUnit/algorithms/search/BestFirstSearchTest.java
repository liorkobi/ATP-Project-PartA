package algorithms.search;
import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    private IMazeGenerator Empty = new EmptyMazeGenerator();
    private IMazeGenerator Simple = new SimpleMazeGenerator();
    private IMazeGenerator My = new MyMazeGenerator();
    // Empty maze
    private Maze emptySmallMaze = Empty.generate(0, 0);
    private Maze emptyBigMaze = Empty.generate(1000, 1000);
    private Maze emptyWrongMaze = Empty.generate(-10, -10);
    private SearchableMaze s_mSmallEmpty = new SearchableMaze(emptySmallMaze);
    private SearchableMaze s_mBigEmpty = new SearchableMaze(emptyBigMaze);
    private SearchableMaze s_mWrongEmpty = new SearchableMaze(emptyWrongMaze);

    // Simple maze
    private Maze simpleSmallMaze = Simple.generate(55, 11);
    private Maze simpleBigMaze = Simple.generate(1000, 750);
    private Maze simpleWrongMaze = Simple.generate(-10, -10);
    private SearchableMaze s_mSmallSimple = new SearchableMaze(simpleSmallMaze);
    private SearchableMaze s_mBigSimple = new SearchableMaze(simpleBigMaze);
    private SearchableMaze s_mWrongSimple = new SearchableMaze(simpleWrongMaze);

    // My maze
    private Maze MySmallMaze = My.generate(9, 61);
    private Maze MyBigMaze = My.generate(650, 1000);
    private Maze MyWrongMaze = My.generate(-10, -10);
    private SearchableMaze s_mSmallMy = new SearchableMaze(MySmallMaze);
    private SearchableMaze s_mBigMy = new SearchableMaze(MyBigMaze);
    private SearchableMaze s_mWrongMy = new SearchableMaze(MyWrongMaze);

    private BestFirstSearch bfs = new BestFirstSearch();


    @Test
    @SuppressWarnings("Duplicates")
    void solve() {
        Solution sol1;
        Solution sol2;

        // Empty maze
        sol1 = bfs.solve(s_mSmallEmpty);
        sol2 = bfs.solve(s_mBigEmpty);
        assertTrue(checkSol(sol1, emptySmallMaze));
        assertTrue(checkSol(sol2, emptyBigMaze));

        // Simple maze
        sol1 = bfs.solve(s_mSmallSimple);
        sol2 = bfs.solve(s_mBigSimple);
        assertTrue(checkSol(sol1, simpleSmallMaze));
        assertTrue(checkSol(sol2, simpleBigMaze));

        // My maze
        sol1 = bfs.solve(s_mSmallMy);
        sol2 = bfs.solve(s_mBigMy);
        assertTrue(checkSol(sol1, MySmallMaze));
        assertTrue(checkSol(sol2, MyBigMaze));

        // Wrong maze
        sol1 = bfs.solve(s_mWrongEmpty);
        assertTrue(checkSol(sol1, emptyWrongMaze));
        sol2 = bfs.solve(s_mWrongMy);
        assertTrue(checkSol(sol2, MyWrongMaze));
        sol1 = bfs.solve(s_mWrongSimple);
        assertTrue(checkSol(sol1, simpleWrongMaze));


    }

    private boolean checkSol(Solution sol,Maze maze) {
        if(sol==null&&maze.getStartPosition()==null){return true;}
        if(sol!=null&&maze.getStartPosition()==null){return false;}
        if(sol==null&&maze.getStartPosition()!=null){return false;}

        ArrayList<AState> solPath = sol.getSolutionPath();
        for (int i=0; i<solPath.size(); i++) {
            AState state = solPath.get(i);
            Position pos;
            MazeState M;
            if(state instanceof  MazeState){
                 M=(MazeState)state;
                pos = M.p;}
            else{
                return false;}

            if(i == 0 && !(pos.equals(maze.getStartPosition())))
                return false;

            if(maze.getval(M.p.getRowIndex(),M.p.getColumnIndex())==1)
                return false;
            if(i == solPath.size()-1 && !(pos.equals(maze.getGoalPosition())))
                return false;

    }
        return true;}}
