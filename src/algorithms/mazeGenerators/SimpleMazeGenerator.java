package algorithms.mazeGenerators;

import java.util.Random;

/**
 * generates maze using Random to ruffle Walls and cells.
 */
public class SimpleMazeGenerator extends AmazeGenerator {
    @Override
    public Maze generate(int r , int c) {
        if(r<=0 || c<=0){
            r=3; c=3;
        }

        Maze maze=new Maze(r,c);

        Random R = new Random( );
        int[][] nmaze=new int[r][c];
        for(int i=0; i < r; i++){
            for(int j=0; j < c; j++){
                nmaze[i][j] = R.nextInt( 2 );
            }
        }
        maze.setmaze(nmaze);
        //select random start point.
        maze.getStartPosition();
        maze.getGoalPosition();
        createPath(maze);
        return maze;
    }


    private void createPath(Maze m){
        int d=m.start.rowidx-m.exit.rowidx;
        int f=m.start.colidx-m.exit.colidx;
        if(d<0){
            for (int i=m.start.rowidx;i<=m.exit.rowidx;i++){
                m.maze[i][m.start.colidx]=0;
            }
        }else{
            for (int i=m.exit.rowidx;i<=m.start.rowidx;i++){
                m.maze[i][m.exit.colidx]=0;
            }
        }
        if(f<0) {
            for (int i = m.start.colidx; i <= m.exit.colidx; i++) {
                m.maze[m.exit.rowidx][i] = 0;
            }
        }else{
            for (int i=m.exit.colidx;i<=m.start.colidx;i++){
                m.maze[m.start.rowidx][i]=0;
            }
        }
    }



}