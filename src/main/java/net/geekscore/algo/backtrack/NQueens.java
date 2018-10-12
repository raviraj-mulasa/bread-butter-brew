package net.geekscore.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens
 * attack each other.
 * 2 Queens , Q1 at (i,j) and Q2 at (k,l) are attacking each other when
 *
 *  i == k              => same row
 *  j == l              => same column
 *  |i - k| == |j - l|  => same diagonal
 *
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
 * both indicate a queen and an empty space respectively.
 *
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * [
 *  [
 *   ".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."
 *  ],
 *  [
 *  "..Q.",  // Solution 2
 *  "Q...",
 *  "...Q",
 *  ".Q.."
 *  ]
 * ]
 *
 */
public final class NQueens {

    static final class Position  {
        private final int row;
        private final int col;
        Position(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString(){
            return String.format("(%d, %d)", this.row, this.col);
        }

    }

    public static void main(String[] args) {

        System.out.println("---");
        System.out.println(solveNQueens(2));


        System.out.println("---");
        System.out.println(solveNQueens(4));

        System.out.println("---");
        System.out.println(solveNQueens(6));

        System.out.println("---");
        System.out.println(solveNQueens(8));

    }

    private static List<List<String>> solveNQueens(int n) {
        List<List<Position>> positionsList = new LinkedList<>();
        placeQueens(0, n, new LinkedList<>(), positionsList);

        // Print the board
        final List<List<String>> solutions = new LinkedList<>();
        for (final List<Position> positions: positionsList) {
            final char[][] solutionBoard = new char[n][n];
            for (final char[] row: solutionBoard) {
                Arrays.fill(row, '.');
            }
            for (final Position position: positions) {
                solutionBoard[position.row][position.col] = 'Q';
            }
            final List<String> solution = new LinkedList<>();
            for (final char[] row: solutionBoard) {
                solution.add(String.valueOf(row));
            }
            solutions.add(solution);
        }
        return solutions;
    }


    public static void placeQueens(final int col, final int queens, final List<Position> positionsSoFar, final List<List<Position>> positionsList) {

        if(col >= queens) return;

        for (int row = 0; row < queens; row++) {
            // Choose
            final Position chosen = new Position(row, col);
            positionsSoFar.add(chosen);
            // Safe to add the chosen position
            if(isSafe(positionsSoFar) ) {
                if(positionsSoFar.size() == queens && col == queens-1) {
                    positionsList.add(new ArrayList<>(positionsSoFar));
                }
                // After choosing the safe position, we explore from here
                placeQueens(col+1, queens, positionsSoFar, positionsList);
            }
            // Un choose
            positionsSoFar.remove(positionsSoFar.size()-1);
        }
    }

    private static  boolean isSafe(final List<Position> positions) {
        final Position chosen = positions.get(positions.size()-1);
        for (int i = 0; i < positions.size()-1; i++) {
            if(chosen.row == positions.get(i).row) return false;
            if(Math.abs(chosen.row - positions.get(i).row) == Math.abs(chosen.col - positions.get(i).col)) return false;
        }
        return true;
    }





}
