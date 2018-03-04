package net.geekscore.algo.backtrack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a maze, NxN matrix. A rat has to find a path from source to des­ti­na­tion.
 * maze[0][0] (left top corner)is the source and maze[N-1][N-1](right bot­tom cor­ner) is destination.
 * There are few cells which are blocked, means rat can­not enter into those cells.
 *
 *
 * Rat can move in any direction ( left, right, up and down).
 *
 * Created by ravirajmulasa on 9/19/16.
 */
public final class RatInAMaze {

    private static final class Cell {
        private final int row;
        private final int col;
        Cell(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString(){
            return String.format("(%d,%d)", this.row, this.col);
        }
    }

    private static final int[][] MOVES = new int [][] {
         {-1, 0}
        ,{1, 0}
        ,{0,-1}
        ,{0, 1}
    };

    public static void main(String[] args) {

//        final int[][] maze = {
//                {1, 0, 1, 1, 1},
//                {1, 0, 1, 0, 1},
//                {1, 0, 0, 1, 1},
//                {1, 1, 1, 1, 0},
//                {0, 1, 0, 1, 1}
//        };
//        List<Cell> path = new LinkedList<>();
//        findPath(maze, new Cell(4,1 ), new Cell(4,4), path);
//        System.out.println(path);

        final int[][] maze1 = {
                { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 0, 1, 0, 0 },
                { 1, 1, 1, 1 }
        };
//        List<Cell> path = new LinkedList<>();
//        findPath(maze1, new Cell(0,0 ), new Cell(3,3), path);

        List<Cell> path = findPath(maze1, new Cell(0,0 ), new Cell(3,3));
        System.out.println(path);
    }

    private static List<Cell> findPath(int[][] maze, Cell start, Cell end) {

        final List<Cell> path = new LinkedList<>();

        if(maze[start.row][start.col] == 1) {
            // Choose
            maze[start.row][start.col] = 0;
            path.add(start);

            if(start.row == end.row && start.col == end.col) return path;

            // Explore
            for (final int[] move: MOVES) {
                final int x = start.row + move[0];
                final int y = start.col + move[1];
                if(x < 0 || x >= maze.length || y < 0 || y >=maze[x].length) continue;
                path.addAll(findPath(maze, new Cell(x,y), end));
            }

            // Un choose
            maze[start.row][start.col] = 1;
        }
        return path;
    }
}
