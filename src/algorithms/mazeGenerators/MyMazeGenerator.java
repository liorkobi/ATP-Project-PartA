package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {
    /**
     *
     * @param r - row
     * @param c - column
     * @return maze with walls and cells
     * generate the maze using prim algorithm.
     */
   public Maze generate(int r,int c){
       if(r<=0 || c<=0){
           return new EmptyMazeGenerator().generate(r,c);
       }

        //initiate new maze and sel all it's cells with 1 as walls.
        Maze maze = new Maze(r, c);
        maze.setmaze(all1maze(r,c));

        //select random start point.
       Position start = maze.getStartPosition();

       Position curr;
       int count; //num of visited neighbors for current cell.

       //frontiers list , candidates to be selected as the next cell in the maze.
       List<Position> Flist= new ArrayList<>();
       Flist.add(start);

        while (!Flist.isEmpty()){
            Random R = new Random( );
            curr=Flist.remove(R.nextInt(Flist.size()));
            count=countVisitedN(maze,curr);

            if(count<=1){
                maze.maze[curr.getRowIndex()][curr.getColumnIndex()]=0;

                //down
                addValidFrontier(curr.getRowIndex()+1, curr.getColumnIndex(), maze,Flist);
                //up
                addValidFrontier(curr.getRowIndex()-1, curr.getColumnIndex(), maze,Flist);
                //left
                addValidFrontier(curr.getRowIndex(), curr.getColumnIndex()-1, maze,Flist);
                //right
                addValidFrontier(curr.getRowIndex(), curr.getColumnIndex()+1, maze,Flist);
            }

        }
        maze.getGoalPosition();
        return maze;
   }

   private int[][] all1maze(int r,int c) {
        int[][] Nmaze = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Nmaze[i][j] = 1;
            }
        }
    return Nmaze;}




    //return the count of visited neighbors for a cell.
   private int countVisitedN(Maze m, Position curr){
       int Ncount=0;
       //down
       if(curr.getRowIndex()+1<m.row && m.maze[curr.getRowIndex()+1][curr.getColumnIndex()]==0)
           Ncount++;
       //up
       if(curr.getRowIndex()-1>=0 && m.maze[curr.getRowIndex()-1][curr.getColumnIndex()]==0)
           Ncount++;
       //right
       if(curr.getColumnIndex()+1<m.col && m.maze[curr.getRowIndex()][curr.getColumnIndex()+1]==0)
           Ncount++;
       //left
       if(curr.getColumnIndex()-1>=0 && m.maze[curr.getRowIndex()][curr.getColumnIndex()-1]==0)
           Ncount++;
       return Ncount;}

    private void addValidFrontier(int r, int c, Maze m,List<Position> frontiers){
        if(c>=0 && c<m.col && r>=0 && r<m.row && m.maze[r][c]==1){
            frontiers.add(new Position(r,c));
        }

    }



}