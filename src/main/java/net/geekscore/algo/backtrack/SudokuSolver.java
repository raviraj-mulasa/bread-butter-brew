package net.geekscore.algo.backtrack;

import net.geekscore.array.ArrayUtil;

public class SudokuSolver {

    public static void main(String[] args) {
        final int[][] sudoku =  {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        System.out.println("---------");
        solve(sudoku);
        ArrayUtil.print(sudoku);


        final int[][] sudoku1 =  {
                {0, 0, 3, 4},
                {0, 0, 1, 2},
                {0, 1, 4, 0},
                {0, 3, 0, 1}
        };
        System.out.println("---------");
        solve(sudoku1);
        ArrayUtil.print(sudoku1);
    }

    private static final boolean solve(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if(sudoku[i][j] == 0) {
                    for (int num = 1; num <= sudoku.length; num++) {
                        if(isSafe(sudoku, i, j, num)) {
                            sudoku[i][j] = num;
                            if(solve(sudoku)) return true;
                            sudoku[i][j] = 0;
                        }
                    }
                    return false;  // we tried all the numbers , none returned true
                }
            }
        }
        return true;
    }

    private static boolean isSafe(int[][] sudoku, int row, int col, int num) {
        final int blkSize = (int)Math.sqrt(sudoku.length);
        final int blkRow = blkSize*(row/blkSize) , blkCol = blkSize*(col/blkSize);
        for (int i = 0; i < sudoku.length; i++) {
            if(sudoku[row][i] != 0 && sudoku[row][i] == num) return false;
            if(sudoku[i][col] != 0 && sudoku[i][col] == num) return false;
            if(sudoku[blkRow+(i/blkSize)][blkCol+(i%blkSize)] != 0 && sudoku[blkRow+(i/blkSize)][blkCol+(i%blkSize)] == num) return false;
        }
        return true;
    }
}
