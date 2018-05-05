package net.geekscore.problems;

/**
 *
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a
 * cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the
 * following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * Write a function to compute the next state (after one update) of the board given its current state.  Follow up:  Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells. In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */
public class GameOfLife {

    private static final int UNDER_POPULATION_NEIGHBOR_COUNT = 2; // less - live to dead
    private static final int OVER_POPULATION_NEIGHBOR_COUNT = 3; // more - live to dead

    private static final int REVIVAL_NEIGHBOR_COUNT = 3; // exact check -  dead to live

    private static final int MARK_FOR_DEATH = Integer.MIN_VALUE;
    private static final int MARK_FOR_REVIVAL = Integer.MAX_VALUE;

    private static final int[][] MOVES = {
            {0, 1}, // horizontal right
            {0, -1}, // horizontal left
            {-1, 0}, // vertical up
            {1, 0}, // vertical down
            {-1, -1}, // diagonal left up
            {1, -1}, // diagonal left down
            {1, 1}, // diagonal right up
            {-1, 1}, // diagonal right down
    };

    public static void main(String[] args) {

        final int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        System.out.println("---------------");
        print(board);
        System.out.println("after game of life");
        final int[][] boardUpdated = gameOfLife(board);
        print(boardUpdated);
        System.out.println("after game of life In place");
        gameOfLifeInPlace(board);
        print(board);


        final int[][] board1 = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        System.out.println("---------------");
        print(board1);
        System.out.println("after game of life");
        final int[][] board1Updated = gameOfLife(board1);
        print(board1Updated);
        System.out.println("after game of life In place");
        gameOfLifeInPlace(board1);
        print(board1);


        final int[][] board2 = {
                { 1, 1},
                { 1, 0}
        };
        System.out.println("---------------");
        print(board2);
        System.out.println("after game of life");
        final int[][] board2Updated = gameOfLife(board2);
        print(board2Updated);
        System.out.println("after game of life In place");
        gameOfLifeInPlace(board2);
        print(board2);
    }

    private static int[][] gameOfLife(int[][] board) {

        final int[][] next =  new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, next[i], 0, board[i].length);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                final int alive = aliveNeighbors(board, i, j);
                if(board[i][j] == 1) {
                    if(alive > 3 || alive < 2) next[i][j] = 0;
                } else {
                    if(alive == 3) next[i][j] = 1;
                }
            }
        }

        return next;
    }

    private static int aliveNeighbors(int[][] current, int x, int y) {
        int alive = 0;
        for(final int[] move : MOVES) {
            final int neighborX = x + move[0];
            final int neighborY = y + move[1];
            if (neighborX < 0 || neighborX >= current.length || neighborY < 0 || neighborY >= current[neighborX].length)
                continue;
            if (current[neighborX][neighborY] == 1) alive++;
        }
        return alive;
    }





    private static void gameOfLifeInPlace(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                final int alive = aliveNeighbors(i, j, board);
                if(board[i][j] == 1) {
                    if(alive < UNDER_POPULATION_NEIGHBOR_COUNT || alive > OVER_POPULATION_NEIGHBOR_COUNT)
                        board[i][j] = MARK_FOR_DEATH;
                } else {
                    if(alive == REVIVAL_NEIGHBOR_COUNT) {
                        board[i][j] = MARK_FOR_REVIVAL;
                    }
                }
            }
        }
        update(board);
    }

    private static int aliveNeighbors(int i, int j, int[][] board) {
        int alive = 0;
        for(final int[] move : MOVES) {
            final int neighborX = i+move[0];
            final int neighborY = j+move[1];
            if(neighborX < 0 || neighborX >= board.length || neighborY < 0 || neighborY >=  board[neighborX].length) continue;
            if(board[neighborX][neighborY] == 1 || board[neighborX][neighborY] == MARK_FOR_DEATH) alive++;
        }
        return alive;
    }

    private static void print(int[][] board) {
        for (final int[] boardRow : board) {
            for (final int boardRowCol : boardRow) {
                if (boardRowCol == 0) System.out.print(".");
                else System.out.print("*");
            }
            System.out.println();
        }
    }

    private static void update(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == MARK_FOR_REVIVAL) board[i][j] = 1;
                if (board[i][j] == MARK_FOR_DEATH) board[i][j] = 0;
            }
        }
    }


}

