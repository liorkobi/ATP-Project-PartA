package algorithms.mazeGenerators;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Maze {
    int row;
    int col;
    int[][] maze;
    Position start;
    Position exit;

    /**
     * constractor arguments
     *
     * @param r - assign to rows
     * @param c - assign to columns
     *          assign maze filed to int array with size of [row][col]
     *          initialize start and exit positions with null
     */
    public Maze(int r, int c) {
        if (r < 0 || c < 0) {
            r = 0;
            c = 0;
        }
        row = r;
        col = c;
        maze = new int[row][col];
        start = null;
        exit = null;

    }

    /**
     * Constractor - build maze from byte array
     *
     * @param b - byte array contains all the maze's data
     */
    public Maze(byte[] b) {
        int i = 0;
        while (b[i] != 0) {
            row += (b[i] & 0xFF);
            i++;
        }
        while (b[i+1] != 0) {
            col += (b[i+1] & 0xFF);
            i++;
        }
        i++;

        int startR = 0;
        //what about 255??
        if (b[i+1] > -1) {
            startR = (b[i+1] & 0xFF);
            i+=2;
        } else {
            while (b[i+1] != 0) {
                startR += (b[i+1] & 0xFF);
                i++;
            }
            i++;

        }
        int startC = 0;
        if (b[i+1] > -1) {
            startC = (b[i+1] & 0xFF);
            i+=2;

        } else {
            while (b[i+1] != 0) {
                startC += (b[i+1] & 0xFF);
                i++;
            }  i++;

        }
        this.start = new Position(startR, startC);
        int exitR = 0;
        if (b[i+1] > -1) {
            exitR = (b[i+1] & 0xFF);
            i+=2;
        } else {
            while (b[i+1] != 0) {
                exitR += (b[i+1] & 0xFF);
                i++;
            }  i++;

        }
        int exitC = 0;
        if (b[i+1] > -1) {
            exitC = (b[i+1] & 0xFF);
            i+=2;

        } else {
            while (b[i+1] != 0) {
                exitC += (b[i+1] & 0xFF);
                i++;
            }
            i++;


        }
        exit = new Position(exitR, exitC);
        i++;
        maze=new int[row][col];
        for (int k = 0; k < row; k++) {
            for (int h = 0; h < col; h++) {
                maze[k][h] = (b[i]&0xFF);
                i++;
            }
        }
    }



        public void setmaze ( int[][] Nmaze){
            maze = Nmaze;
        }

        public Position getStartPosition () {
            if (start == null && row > 0 && col > 0) {
                start = chooseRandom();
            }
            return start;
        }

        /**
         * check that maze doesn't have goal position yet
         * raffle one as long as is different from the maze start position
         *
         * @return goal position
         */
        public Position getGoalPosition () {
            if (exit == null && row > 0 && col > 0) {
                boolean bool = true;
                Position exitP = chooseRandom();
                //goal point cant be WALL ot equal to start point.
                while (bool) {
                    if (maze[exitP.rowidx][exitP.colidx] == 0 && !(exitP.equals(start))) {
                        bool = false;
                    } else {
                        exitP = chooseRandom();
                    }
                }
                exit = exitP;
            }
            return exit;
        }

        public void print () {
            System.out.print("{");
            for (int r = 0; r < row; r++) {
                System.out.print("{ ");
                for (int c = 0; c < col; c++) {
                    if (r == start.getRowIndex() && c == start.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (r == exit.getRowIndex() && c == exit.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(maze[r][c] + " ");
                    }
                }
                if (r + 1 == row) {
                    System.out.print("}");
                } else {
                    System.out.println("},");
                }

            }
            System.out.println("}");
        }

        /**
         * frame - randomize number between 1 and 4 , these represent the maze boundaries
         *
         * @return the chosen position
         */
        private Position chooseRandom () {
            Random R = new Random();
            int frame = R.nextInt(4);
            Position randomP = new Position(R.nextInt(row), R.nextInt(col));
            //first row - any column
            if (frame == 0) {
                randomP.setRowidx(0);
                randomP.setColidx(R.nextInt(col));
            }
            //last row - any column
            else if (frame == 1) {
                randomP.setRowidx(row - 1);
                randomP.setColidx(R.nextInt(col));
            }
            //first column - any row
            else if (frame == 2) {
                randomP.setRowidx(R.nextInt(row));
                randomP.setColidx(0);
            }
            //last column - any row
            else {
                randomP.setRowidx(R.nextInt(row));
                randomP.setColidx(col - 1);
            }
            return randomP;
        }


        public int getRow () {
            return row;
        }

        public int getCol () {
            return col;
        }

        public int getval ( int i, int j){
            if (i < row && i >= 0 && j < col && j >= 0)
                return maze[i][j];
            else {
                return -1;
            }
        }
    private int rowSize () {
        int R = row / 255;
        if (R == 0) R = 2;
        else if (R % 255 != 0) {
            R += 2;
        }
        return R;
    }
    private int colSize () {
        int C = col / 255;
        if (C == 0) C = 2;
        else if (C % 255 != 0) {
            C += 2;
        }
        return C;
    }
    private int startRowSize () {
        int SRowIdx = start.rowidx / 255;
        if (SRowIdx == 0) SRowIdx = 2;
        else if (SRowIdx % 255 != 0) {
            SRowIdx += 2;
        }
        return SRowIdx;
    }

    private int startColSize () {
        int SColIdx = start.colidx / 255;
        if (SColIdx == 0) SColIdx = 2;
        else if (start.colidx % 255 != 0) {
            SColIdx += 2;
        } else if (start.colidx % 255 == 0) {
            SColIdx++;
        }
        return SColIdx;
    }

    private int exitRowSize () {

        int ERowIdx = exit.rowidx / 255;
        if (ERowIdx == 0) ERowIdx = 2;
        else if (ERowIdx % 255 != 0) {
            ERowIdx += 2;
        }
        return ERowIdx;
    }

    private int exitColSizE () {
        int EColIdx = exit.colidx / 255;
        if (EColIdx == 0) EColIdx = 2;
        else if (EColIdx % 255 != 0) {
            EColIdx += 2;
        }
        return EColIdx;
    }


    public byte[] toByteArray () {
//check needed size for each parameter in the byte array
        int R = rowSize();
        int C = colSize();
        int SColIdx = startColSize();
        int SRowIdx = startRowSize();
        int EColIdx = exitColSizE();
        int ERowIdx = exitRowSize();

        int size = R + C + SColIdx + SRowIdx + EColIdx + ERowIdx + (row * col)+1;

        byte[] Barr = new byte[size];
        //insert num of rows
        int temp1 = row;
        int i;
        for (i = 0; i < R; i++) {
            if (temp1 < 255) {
                Barr[i] = (byte) temp1;
                temp1 -= temp1;
            } else {
                Barr[i] = (byte) 255;
                temp1 -= 255;
            }
        }
        //insert num of cols
        int temp2 = col;
        for (i = i; i < C + R; i++) {
            if (temp2 < 255) {
                Barr[i] = (byte) temp2;
                temp2 -= temp2;
            } else {
                Barr[i] = (byte) 255;
                temp2 -= 255;
            }
        }
        //insert maze start row index
        int temp3 = start.rowidx;
        for (i = i; i < SRowIdx + R + C; i++) {
            if (temp3 < 255) {
                Barr[i] = (byte) temp3;
                temp3 -= temp3;
            } else {
                Barr[i] = (byte) 255;
                temp3 -= 255;
            }
        }
        //insert maze start col index
        int temp4 = start.colidx;
        for (i = i; i < SColIdx + SRowIdx + R + C; i++) {
            if (temp4 < 255) {
                Barr[i] = (byte) temp4;
                temp4 -= temp4;
            } else {
                Barr[i] = (byte) 255;
                temp4 -= 255;
            }
        }
        //insert maze exit row index
        int temp5 = exit.rowidx;
        for (i = i; i < ERowIdx + SColIdx + SRowIdx + R + C; i++) {
            if (temp5 < 255) {
                Barr[i] = (byte) temp5;
                temp5 -= temp5;
            } else {
                Barr[i] = (byte) 255;
                temp5 -= 255;
            }
        }
        //insert maze exit row index
        int temp = exit.colidx;
        for (i = i; i < EColIdx + ERowIdx + SColIdx + SRowIdx + R + C; i++) {
            if (temp < 255) {
                Barr[i] = (byte) temp;
                temp -= temp;
            } else {
                Barr[i] = (byte) 255;
                temp -= 255;
            }
        }
        //indicats the start of the maze representation
        Barr[Barr.length-1]=(byte) i;
        for (int j = 0; j < row; j++) {
            for (int k = 0; k < col; k++) {
                Barr[i] = (byte) maze[j][k];
                i++;
            }
        }
//            for (int g = 0; g < size; g++)
//                System.out.println(Barr[g]);
//        System.out.println("from maze to byte arr");
//
//        System.out.println(Arrays.toString(Barr));

        return Barr;
    }
    }
