package algorithms.maze3D;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator{

   public Maze3D generate(int d, int r, int c){

        Maze3D maze = new Maze3D(d,r,c);
        maze.setmaze3D(all1maze3D(d,r,c));

        //select random start point.
        //Position3D start = maze.getStartPosition();

        //frontiers list , candidates to be selected as the next cell in the maze.
//        List<Position3D> Flist= new ArrayList<>();
//        Flist.add(start);

       ArrayList<Position3D> N = new ArrayList<>();
       maze.maze[0][0][0] = 0;

       // For each existing cell in the grid, randomly carve a passage either north or west
            for (int x = 0; x < maze.getDepth(); x+=2) {
                for (int y = 0; y < maze.getRow(); y+=2) {
                    for (int z = 0; z < maze.getCol(); z+=2) {

                        // Get available neighbours (nort + west)
//                        if (x > 0) Flist.add(new Position3D(x - 1,y,z));
//                        if (y > 0) Flist.add(new Position3D(x ,y-1,z));
//                        if (z > 0) Flist.add(new Position3D(x ,y,z-1));



                        if (x > 1 && maze.maze[x-2][y][z] == 0) N.add(new Position3D(x - 2,y,z));
                        if (y > 1  && maze.maze[x][y-2][z] == 0) N.add(new Position3D(x ,y-2,z));
                        if (z > 1  && maze.maze[x][y][z-2] == 0) N.add(new Position3D(x ,y,z-2));

                        // No possible connection
                        if (N.isEmpty())
                            continue;

                        // Randomly connect whether south or east
                        Random R = new Random();
                        int randi = R.nextInt(N.size());
//                        maze.maze[N.get(randi).getDepthIndex()][N.get(randi).getRowIndex()][N.get(randi).getColumnIndex()]=0;
                        Fill(x,y,z,N.get(randi).getDepthIndex(),N.get(randi).getRowIndex(),N.get(randi).getColumnIndex(),maze);
//                        N.remove(randi);
                        N = new ArrayList<>();
                    }

                }}
        return maze;
    }


    private void Fill(int d,int r,int c,int D,int R,int C, Maze3D maze){
       if (d>D){
           maze.maze[d][r][c] = 0;
           maze.maze[d-1][r][c] = 0;
           maze.maze[d-2][r][c] = 0;
       }
       else if(r>R){
           maze.maze[d][r][c] = 0;
           maze.maze[d][r-1][c] = 0;
           maze.maze[d][r-2][c] = 0;
       }
       else if(c>C){
           maze.maze[d][r][c] = 0;
           maze.maze[d][r][c-1] = 0;
           maze.maze[d][r][c-2] = 0;
       }
    }


//    if(d<=0|| r<=0 || c<=0){
//        return null;
//    }
//
//    //initiate new maze and sel all it's cells with 1 as walls.
//       Maze3D maze = new Maze3D(d,r,c);
//        maze.setmaze3D(all1maze3D(d,r,c));
//
//        //select random start point.
//        Position3D start = maze.getStartPosition();
//
//    Position3D curr;
//    int count; //num of visited neighbors for current cell.
//
//    //frontiers list , candidates to be selected as the next cell in the maze.
//    List<Position3D> Flist= new ArrayList<>();
//       Flist.add(start);
//
//        while (!Flist.isEmpty()){
//        Random R = new Random( );
//        curr=Flist.remove(R.nextInt(Flist.size()));
//        count=countVisitedN(maze,curr);
//
//        if(count<=1){
//            maze.maze[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()]=0;
//
//            //down
//            addValidFrontier(curr.getDepthIndex(),curr.getRowIndex()+1, curr.getColumnIndex(), maze,Flist);
//            //up
//            addValidFrontier(curr.getDepthIndex(),curr.getRowIndex()-1, curr.getColumnIndex(), maze,Flist);
//            //left
//            addValidFrontier(curr.getDepthIndex(),curr.getRowIndex(), curr.getColumnIndex()-1, maze,Flist);
//            //right
//            addValidFrontier(curr.getDepthIndex(),curr.getRowIndex(), curr.getColumnIndex()+1, maze,Flist);
//            //inside
//            addValidFrontier(curr.getDepthIndex()+1,curr.getRowIndex(), curr.getColumnIndex(), maze,Flist);
//            //outside
//            addValidFrontier(curr.getDepthIndex()-1,curr.getRowIndex(), curr.getColumnIndex(), maze,Flist);
//        }
//
//    }
//        maze.getGoalPosition();
//        return maze;
//}

    //return the count of visited neighbors for a cell.
    private int countVisitedN(Maze3D m, Position3D curr){
        int Ncount=0;
        //down
        if(curr.getRowIndex()+1<m.row && m.maze[curr.getDepthIndex()][curr.getRowIndex()+1][curr.getColumnIndex()]==0)
            Ncount++;
        //up
        if(curr.getRowIndex()-1>=0 && m.maze[curr.getDepthIndex()][curr.getRowIndex()-1][curr.getColumnIndex()]==0)
            Ncount++;
        //right
        if(curr.getColumnIndex()+1<m.col && m.maze[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()+1]==0)
            Ncount++;
        //left
        if(curr.getColumnIndex()-1>=0 && m.maze[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()-1]==0)
            Ncount++;
        //inside
        if(curr.getDepthIndex()+1<m.depth && m.maze[curr.getDepthIndex()+1][curr.getRowIndex()][curr.getColumnIndex()]==0)
            Ncount++;
        //outside
        if(curr.getDepthIndex()-1>=0 && m.maze[curr.getDepthIndex()-1][curr.getRowIndex()][curr.getColumnIndex()]==0)
            Ncount++;



        return Ncount;}


                private void addValidFrontier(int d, int r, int c, Maze3D m, List< Position3D > frontiers) {
                    if (d >= 0 && d < m.getDepth() && c >= 0 && c < m.col && r >= 0 && r < m.row && m.maze[d][r][c] == 1) {
                        frontiers.add(new Position3D(d, r, c));
                    }
                }


    private int[][][] all1maze3D(int d,int r,int c) {
        int[][][] Nmaze = new int[d][r][c];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    Nmaze[i][j][k] = 1;
            }
        }}
        return Nmaze;}
}
