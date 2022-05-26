//package algorithms.maze3D;
//
//import algorithms.mazeGenerators.Maze;
//import algorithms.mazeGenerators.Position;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyMaze3DGenerator {
//
//    Maze generate(int depth, int row, int col){
//
//        Maze3D maze = new Maze3D(depth,row,col);
//        maze.setmaze3D(all1maze3D(d,r,c));
//
//        //select random start point.
//        Position3D start = maze.getStartPosition();
//
//        //frontiers list , candidates to be selected as the next cell in the maze.
//        List<Position3D> Flist= new ArrayList<>();
//        Flist.add(start);
//
//        // For each existing cell in the grid, randomly carve a passage either north or west
//            for (int x = 0; x < maze.getDepth(); ++x) {
//                for (int y = 0; y < maze.getRow(); ++y) {
//                    for (int z = 0; x < maze.getCol(); ++z) {
//
//                        // Get available neighbours (nort + west)
//                        if (x > 0) neighbours.push_back(maze[x - 1][y]);
//                        if (y > 0) neighbours.push_back(maze[x][y - 1]);
////                        if (y > 0) neighbours.push_back(maze[x][y][z-1]);
//
//                        // No possible connection
//                        if (neighbours.empty())
//                            continue;
//
//                        // Randomly connect whether south or east
//                        auto tossCoin = Random() % neighbours.size();
//                        mazeMatrix[x][y]->Connect(neighbours[tossCoin]);
//                    }
//
//                    return maze;
//                }}
//            }
//                private void addValidFrontier(int d, int r, int c, Maze3D m, List< Position3D > frontiers) {
//                    if (d >= 0 && d < m.getDepth() && c >= 0 && c < m.col && r >= 0 && r < m.row && m.maze[d][r][c] == 1) {
//                        frontiers.add(new Position3D(d, r, c));
//                    }
//                }
//
//
//    private int[][][] all1maze3D(int d,int r,int c) {
//        int[][][] Nmaze = new int[d][r][c];
//        for (int i = 0; i < d; i++) {
//            for (int j = 0; j < r; j++) {
//                for (int k = 0; k < c; k++) {
//                    Nmaze[i][j][k] = 1;
//            }
//        }}
//        return Nmaze;}
//}
