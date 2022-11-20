package net.geekscore.array.traversal;

import net.geekscore.array.ArrayUtil;

import java.util.Arrays;


/**
 *
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order
 * as shown in the below image.
 * Example:
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output:  [1,4,2,7,5,3,8,6,9]
 *
 * NOTE: Note that an n × m grid has m + n − 1 diagonals, each with a variable number of elements
 *
 */
public class DiagonalOrder {

    public static void main(String[] args) {

        System.out.println("----------------------");

        System.out.println(Arrays.toString(diagonalOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})));
        System.out.println(Arrays.toString(diagonalOrder(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}})));
        System.out.println(Arrays.toString(diagonalOrder(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}})));
        System.out.println(Arrays.toString(diagonalOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}})));

        System.out.println("----------------------");

        System.out.println(Arrays.toString(diagonalOrder0(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})));
        System.out.println(Arrays.toString(diagonalOrder0(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}})));
        System.out.println(Arrays.toString(diagonalOrder0(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}})));
        System.out.println(Arrays.toString(diagonalOrder0(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}})));

        System.out.println("----------------------");

        System.out.println(Arrays.toString(diagonalOrder1(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})));
        System.out.println(Arrays.toString(diagonalOrder1(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}})));
        System.out.println(Arrays.toString(diagonalOrder1(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}})));
        System.out.println(Arrays.toString(diagonalOrder1(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}})));


        System.out.println("----------------------");

        System.out.println(Arrays.toString(diagonalOrderA(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})));
        System.out.println(Arrays.toString(diagonalOrderA(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}})));
        System.out.println(Arrays.toString(diagonalOrderA(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}})));
        System.out.println(Arrays.toString(diagonalOrderA(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}})));

        System.out.println("----------------------");

        System.out.println(Arrays.toString(diagonalOrderB(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})));
        System.out.println(Arrays.toString(diagonalOrderB(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}})));
        System.out.println(Arrays.toString(diagonalOrderB(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}})));
        System.out.println(Arrays.toString(diagonalOrderB(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}})));


    }


    private static int[] diagonalOrder(final int [][] matrix) {
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final int[] traversal = new int[rows*cols];
        int i=0;
        for (int row = 0; row < rows; row++) {
            for (int startRow = row, startCol = 0; startRow >= 0 && startCol < cols; startRow--, startCol++) {
                traversal[i++] = matrix[startRow][startCol];
            }
        }
        for (int col = 1; col < cols; col++) {
            for (int startCol = col, startRow = rows-1; startCol < cols; startCol++, startRow--) {
                traversal[i++] = matrix[startRow][startCol];
            }
        }
        return traversal;
    }

    private static int[] diagonalOrder0(final int [][] matrix) {
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final int diagonals = rows + cols - 1;
        final int[] traversal = new int[rows*cols];
        int i=0;
        for (int diagonal = 0; diagonal < diagonals; diagonal++) {
            for (int row = Math.max(0, diagonal-cols+1), col = Math.min(diagonal, cols-1); col >= 0 && row < rows; row++, col--) {
                traversal[i++]=matrix[row][col];
            }
        }
        return traversal;
    }


    private static int[] diagonalOrder1(final int [][] matrix) {
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final int[] traversal = new int[rows*cols];
        int i=0;
        final int diagonals = rows + cols - 1;
        for (int diagonal = 0; diagonal < diagonals; diagonal++) {
            for (int row = Math.min(diagonal, rows-1), col = Math.max(0, diagonal-rows+1); col < cols && row >= 0; row--, col++) {
                traversal[i++]=matrix[row][col];
            }
        }
        return traversal;
    }


    private static int[] diagonalOrderA(final int [][] matrix) {
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final int[] traversal = new int[rows*cols];
        int counter = 0;
        final int diagonals = rows + cols - 1;
        int diagonalElements; /* elements on diagonal */
        int startCol;
        for (int diagonal = 1; diagonal <= diagonals; diagonal++) {
            startCol = Math.max(0, diagonal - rows);
            diagonalElements = Math.min(diagonal, Math.min(rows, (cols - startCol)));
            for (int i = 0; i < diagonalElements; i++) {
                traversal[counter++] = matrix [Math.min(rows,diagonal)-1-i] [startCol+i];
            }
        }
        return traversal;
    }

    private static int[] diagonalOrderB(final int [][] matrix) {
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final int[] traversal = new int[rows*cols];
        int counter = 0;
        final int diagonals = rows + cols - 1;
        int diagonalElements; /* elements on diagonal */
        int startRow;
        for (int diagonal = 1; diagonal <= diagonals; diagonal++) {
            startRow = Math.max(0, diagonal - cols);
            diagonalElements = Math.min(diagonal, Math.min(cols, (rows - startRow)));
            for (int i = 0; i < diagonalElements; i++) {
                traversal[counter++] = matrix [startRow+i][Math.min(cols,diagonal)-i-1];
            }
        }
        return traversal;
    }
}
