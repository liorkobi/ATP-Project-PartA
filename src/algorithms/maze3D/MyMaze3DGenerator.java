package algorithms.maze3D;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator{

   public Maze3D generate(int d, int r, int c){
       if(d<=0 ||r<=0 ||c<=0){d=3;c=3;r=3;}

        Maze3D maze = new Maze3D(d,r,c);
        maze.setmaze3D(all1maze3D(d,r,c));

        //frontiers list , candidates to be selected as the next cell in the maze.
       ArrayList<Position3D> N = new ArrayList<>();
       maze.setStart(new Position3D(0,0,0));
       maze.maze[0][0][0] = 0;

       // For each existing cell in the grid, randomly carve a passage either north or west
        for (int x = 0; x < maze.getDepth(); x+=2) {
            for (int y = 0; y < maze.getRow(); y+=2) {
                for (int z = 0; z < maze.getCol(); z+=2) {

                    if (x > 1 && maze.maze[x-2][y][z] == 0) N.add(new Position3D(x - 2,y,z));
                    if (y > 1  && maze.maze[x][y-2][z] == 0) N.add(new Position3D(x ,y-2,z));
                    if (z > 1  && maze.maze[x][y][z-2] == 0) N.add(new Position3D(x ,y,z-2));

                    // No possible connection
                    if (N.isEmpty())
                        continue;

                    // Randomly connect whether south or east
                    Random R = new Random();
                    int randi = R.nextInt(N.size());
                    Fill(x,y,z,N.get(randi).getDepthIndex(),N.get(randi).getRowIndex(),N.get(randi).getColumnIndex(),maze);
                    N = new ArrayList<>();
                }

            }}
        maze.getGoalPosition();
        createPath(maze);
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

    private int[][][] all1maze3D(int d,int r,int c) {
        int[][][] Nmaze = new int[d][r][c];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    Nmaze[i][j][k] = 1;
            }
        }}
        return Nmaze;}


    private void createPath(Maze3D m){
        int d=m.start.rowidx-m.exit.rowidx;
        int f=m.start.colidx-m.exit.colidx;
        int e=m.start.depidx-m.exit.depidx;

        if(d<0){
            for (int i=m.start.rowidx;i<=m.exit.rowidx;i++){
                m.maze[m.start.depidx][i][m.start.colidx]=0;
            }
        }else{
            for (int i=m.exit.rowidx;i<=m.start.rowidx;i++){
                m.maze[m.start.depidx][i][m.exit.colidx]=0;
            }
        }
        if(f<0) {
            for (int i = m.start.colidx; i <= m.exit.colidx; i++) {
                m.maze[m.start.depidx][m.exit.rowidx][i] = 0;
            }
        }else {
            for (int i = m.exit.colidx; i <= m.start.colidx; i++) {
                m.maze[m.start.depidx][m.start.rowidx][i] = 0;
            }
        }
        if(e<0) {
            for (int i = m.start.depidx; i <= m.exit.depidx; i++) {
                m.maze[i][m.exit.rowidx][m.exit.colidx] = 0;
            }
        }else {
            for (int i = m.exit.colidx; i <= m.start.colidx; i++) {
                m.maze[i][m.start.rowidx][m.start.colidx] = 0;
            }
        }
    }
}
