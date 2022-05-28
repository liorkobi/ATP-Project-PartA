package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.Arrays;
import java.util.Random;

public class Maze3D {
        int row;
        int col;
        int depth;
        int[][][] maze;
        Position3D start;
        Position3D exit;


        public Maze3D(int d, int r, int c) {
            depth=d;
            row = r;
            col = c;
            maze = new int[depth][row][col];
            start = null;
            exit = null;

        }

        public void setmaze3D(int[][][] Nmaze) {
            maze = Nmaze;
        }

        public Position3D getStartPosition() {
            return start;
        }

        public Position3D getGoalPosition() {
            if (exit == null && depth>0 && row > 0 && col > 0) {
                boolean bool = true;
                Position3D exitP = chooseRandom();
                //goal point cant be WALL ot equal to start point.
                while (bool) {
                    if (maze[exitP.depidx][exitP.rowidx][exitP.colidx] == 0 && !(exitP.equals(start))){
                        bool = false;}
                    else {
                        exitP = chooseRandom();
                    }
                }
                exit = exitP;
            }
            return exit;
        }


    public void print() {
        System.out.print("{");
        for (int d = 0; d < depth; d++) {
            for (int r = 0; r < row; r++) {
                System.out.print("{ ");
                for (int c = 0; c < col; c++) {
                    if (d == start.getDepthIndex() && r == start.getRowIndex() && c == start.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (d == exit.getDepthIndex() && r == exit.getRowIndex() && c == exit.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(maze[d][r][c] + " ");
                    }
                }
                if (r == row - 1 && d!=depth-1) {
                    System.out.println("},");
                }
                else if (r == row - 1 && d == depth - 1) {
                    System.out.print("}");}
                else {
                    System.out.println("}");
                }
            }
        }
            System.out.println("}");}



       private Position3D chooseRandom() {
            Random R = new Random();
            int frame = R.nextInt(6);
            Position3D randomP = new Position3D(R.nextInt(row), R.nextInt(col),R.nextInt(depth));
            //פאה עליונה
            if (frame == 0) {
                randomP.setRowidx(0);
                randomP.setColidx(R.nextInt(col));
                randomP.setDepidx(R.nextInt(depth));
                //פאה תחתונה
            } else if (frame == 1) {
                randomP.setRowidx(row-1);
                randomP.setColidx(R.nextInt(col));
                randomP.setDepidx(R.nextInt(depth));
                //פאה קדמית
            } else if (frame == 2) {
                randomP.setDepidx(0);
                randomP.setColidx(R.nextInt(col));
                randomP.setRowidx(R.nextInt(row));

                //פאה אחורית
            } else if (frame == 3){
                randomP.setDepidx(depth-1);
                randomP.setRowidx(R.nextInt(row));
                randomP.setColidx(R.nextInt(col));}
           //פאה ימנית
            else if (frame == 4){
                randomP.setDepidx(R.nextInt(depth));
                randomP.setRowidx(R.nextInt(row));
                randomP.setColidx(col-1);
                //פאה שמאלית
            }else{
                randomP.setDepidx(R.nextInt(depth));
                randomP.setRowidx(R.nextInt(row));
                randomP.setColidx(0);
            }
            return randomP;
        }


        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

    public int getDepth() {
        return depth;
    }

    public int getval(int i, int j,int k) {
            return maze[i][j][k];
        }


    public void setStart(Position3D start) {
        this.start = start;
    }
    public int[][][] getMap(){return maze;}
}

