package net.geekscore.array;

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
 * Output:  [1,2,4,7,5,3,6,8,9]
 *
 * NOTE: Note that an n × m grid has m + n − 1 diagonals, each with a variable number of elements
 *
 */
public class DiagonalOrder {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(diagonalOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})));
        System.out.println(Arrays.toString(diagonalOrder(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}})));
        System.out.println(Arrays.toString(diagonalOrder(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}})));
        System.out.println(Arrays.toString(diagonalOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}})));

    }


    private static int[] diagonalOrder(final int [][] matrix) {
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        final int rows = matrix.length;
        int cols = 0;
        if(rows > 0) {
            cols = matrix[0].length;
        }
        final int[] traversal = new int[rows*cols];
        int counter = 0;
        final int diagonals = rows + cols - 1;
        int diagonalElements; /* elements on diagonal */
        int startCol;
//        int startRow;
        for (int diagonal = 1; diagonal <= diagonals; diagonal++) {
            startCol = Math.max(0, diagonal - rows);
//            startRow = Math.max(0, diagonal - cols);
            diagonalElements = Math.min(diagonal, Math.min(rows, (cols - startCol)));
//            diagonalElements = Math.min(diagonal, Math.min(cols, (rows - startRow)));
            for (int i = 0; i < diagonalElements; i++) {
                traversal[counter++] = matrix [Math.min(rows,diagonal)-1-i] [startCol+i];
//                traversal[counter++] = matrix [startRow+i][Math.min(cols,diagonal)-i-1];
            }
        }
        return traversal;
    }
}
