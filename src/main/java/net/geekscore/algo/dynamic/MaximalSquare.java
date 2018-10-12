package net.geekscore.algo.dynamic;

import net.geekscore.array.ArrayUtil;

import java.util.Arrays;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and
 * return its area.
 *
 * For example, given the following matrix:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Return 4.
 */
public class MaximalSquare {

    public static void main(String[] args) {

        int[][] matrix = new int[][] {
                 {1,0,1,0,0}
                ,{1,1,1,1,1}
                ,{1,1,1,1,1}
                ,{1,1,1,1,1}
        };

        int[][] matrix1 = new int[][] {
                 {1,0,1,0,0}
                ,{1,0,1,1,1}
                ,{1,1,1,1,1}
                ,{1,0,0,1,0}
        };

        System.out.println(maximalSquareArea(matrix)); // 9
        System.out.println(maximalSquareArea(matrix1)); // 4

        System.out.println(maximalSquareAreaOptimalInSize(matrix)); // 9
        System.out.println(maximalSquareAreaOptimalInSize(matrix1)); // 4

    }

    private static int maximalSquareArea(int[][] matrix) {

        final int size[][] = new int[matrix.length][matrix[0].length];
        int maxSquareSize = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            size[0][j] = matrix[0][j];
            maxSquareSize = Math.max(maxSquareSize, size[0][j]);
        }
        for (int i = 0; i < matrix.length; i++) {
            size[i][0] = matrix[i][0];
            maxSquareSize = Math.max(maxSquareSize, size[i][0]);
        }
//        ArrayUtil.print(size);
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if(1 == matrix[i][j]) {
                    size[i][j] = Math.min(size[i-1][j], Math.min(size[i-1][j-1], size[i][j-1]))+1;
                    maxSquareSize = Math.max(maxSquareSize, size[i][j]);
                }

            }
        }
//        System.out.println();
//        ArrayUtil.print(size);
        return maxSquareSize*maxSquareSize;
    }

    private static int maximalSquareAreaOptimalInSize(int[][] matrix) {

        final int currRow[] = new int[matrix.length];
        final int prevRow[] = new int[matrix.length];
        int maxSquareSize = 0;

        for (int i = 0; i < matrix.length; i++) {
            prevRow[i] = matrix[i][0];
            maxSquareSize = Math.max(maxSquareSize, prevRow[i]);
        }

        for (int j = 1; j < matrix[0].length; j++) {
            currRow[0] = matrix[0][j];
            maxSquareSize = Math.max(maxSquareSize, currRow[0]);
            for (int i = 1; i < matrix.length; i++) {
                if(1 == matrix[i][j]) {
                    currRow[i] = Math.min(currRow[i-1], Math.min(prevRow[i-1], prevRow[i]))+1;
                    maxSquareSize = Math.max(maxSquareSize, currRow[i]);
                }

            }
//            System.out.println(Arrays.toString(prevRow));
//            System.out.println(Arrays.toString(currRow));
            System.arraycopy(currRow, 0, prevRow, 0, currRow.length);
            Arrays.fill(currRow, 0);
        }

        return maxSquareSize*maxSquareSize;
    }
}
