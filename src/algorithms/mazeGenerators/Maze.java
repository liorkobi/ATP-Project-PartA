package algorithms.mazeGenerators;

import java.util.Arrays;
import java.util.Random;

public class Maze {
    int row;
    int col;
    int[][] maze;
    Position start;
    Position exit;

    public Maze(int r, int c) {
        if(r<0 || c<0){
            r=0;
            c=0;
        }
        row = r;
        col = c;
        maze = new int[row][col];
        start = null;
        exit = null;

    }

    public void setmaze(int[][] Nmaze) {
        maze = Nmaze;
    }

    public Position getStartPosition() {
        if (start == null && row > 0 && col > 0) {
            start = chooseRandom();
        }
        return start;
    }

    public Position getGoalPosition() {
        if (exit == null && row > 0 && col > 0) {
            boolean bool = true;
            Position exitP = chooseRandom();
            //goal point cant be WALL ot equal to start point.
            while (bool) {
                if (maze[exitP.rowidx][exitP.colidx] == 0 && !(exitP.equals(start))){
                    bool = false;}
                else {
                    exitP = chooseRandom();
                }
            }
            exit = exitP;
        }
        return exit;
    }

    public void print(){
        if(start!=null && exit!=null) {
            char[][] charArr = new char[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    charArr[i][j] = (char) ('0' + maze[i][j]);
                }
            }
            charArr[exit.getRowIndex()][exit.getColumnIndex()]='E';
            charArr[start.getRowIndex()][start.getColumnIndex()]='S';
            for (int i = 0; i < row; i++) {
                System.out.println(Arrays.toString(charArr[i]));}
        }
    }

   private Position chooseRandom() {
        Random R = new Random();
        int frame = R.nextInt(4);
        Position randomP = new Position(R.nextInt(row), R.nextInt(col));
        if (frame == 0) {
            randomP.setRowidx(0);
            randomP.setColidx(R.nextInt(col));
        } else if (frame == 1) {
            randomP.setRowidx(row-1);
            randomP.setColidx(R.nextInt(col));
        } else if (frame == 2) {
            randomP.setRowidx(R.nextInt(row));
            randomP.setColidx(0);
        } else {
            randomP.setRowidx(R.nextInt(row));
            randomP.setColidx(col-1);
        }
        return randomP;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getval(int i, int j) {
        if(i<row && i>=0 && j<col && j>=0)
        return maze[i][j];
        else {return -1;}
    }

    //delete before submission!!!


    public void setStart(Position start) {
        this.start = start;
    }

    public void setExit(Position exit) {
        this.exit = exit;
    }
}