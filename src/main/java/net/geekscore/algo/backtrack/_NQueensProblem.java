package net.geekscore.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Problem: Given a board of size n x n, Find all the ways to place n queens
 * so that they won't attack each other.
 *
 * 2 Queens , Q1 at (i,j) and Q2 at (k,l) are attacking each other when
 *
 *  i == k              => same row
 *  j == l              => same column
 *  |i - k| == |j - l|  => same diagonal
 *
 *
 * Created by ravirajmulasa on 9/15/16.
 */
public final class _NQueensProblem {

    private _NQueensProblem(){
    }

    private static final class Position  {

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

    private  static final int QUEENS                    = 8;
    private  static final int[] COL_SELECTED_IN_EACH_ROW= new int[QUEENS];
    private  static final List<Position> POSITIONS      = new ArrayList<>(QUEENS);

    public static void main(String[] args) {
        placeQueens(0);
        positions();
    }


    private static void placeQueens(int row) {

        for (int col = 0; col < QUEENS; col++) {

            if(! isAttacked(row, col)) {

                COL_SELECTED_IN_EACH_ROW[row] = col;

                if(row == QUEENS - 1) { //Placed queens in all the rows
                    for (int i = 0; i < QUEENS; i++) {
                        POSITIONS.add(new Position(i, COL_SELECTED_IN_EACH_ROW[i]));
                    }
                }else {
                    placeQueens(row + 1); //Place the queen in the next row
                }
            }
        }
    }



    private static  boolean isAttacked(int row, int col) {

        for (int _row = 0; _row <= row -1; _row++) {
            if(COL_SELECTED_IN_EACH_ROW[_row] == col // same column
                ||  Math.abs(row - _row) == Math.abs(COL_SELECTED_IN_EACH_ROW[_row] - col)) // same diagonal)
            {
                return true;
            }
        }
        return false;
    }


    private static void positions(){
        for (int i = 1; i <= POSITIONS.size(); i++) {
            System.out.print(POSITIONS.get(i-1) + "\t");
            if(i % QUEENS == 0) {
                System.out.println();
            }
        }

    }


}
