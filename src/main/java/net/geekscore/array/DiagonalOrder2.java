package net.geekscore.array;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * DIAGONAL SNAKE ORDER
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
public class DiagonalOrder2 {

    public static void main(String[] args) {

        System.out.println("----------------------");
        diagonalOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}});
        diagonalOrder(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}});
        diagonalOrder(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}});
        diagonalOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}});

        System.out.println("----------------------");
    }

    private static final void diagonalOrder(final int[][] matrix) {
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        final int rows = matrix.length;
        int cols = 0;
        if(rows > 0) cols = matrix[0].length;
        int diagonals = rows + cols - 1, diagonal = -1;

        for (int row = 0; row < rows; row++) {
            List<Integer> diagonalElems = new LinkedList<>();
            for (int startRow = row, startCol = 0 ; startRow >=0 && startCol < cols ; startRow--, startCol++) {
                diagonalElems.add(matrix[startRow][startCol]);
            }
            print(diagonals, ++diagonal, diagonalElems);
        }

        for (int col = 1; col < cols; col++) {
            List<Integer> diagonalElems = new LinkedList<>();
            for (int startCol = col, startRow = rows-1 ; startRow >=0 && startCol < cols ; startRow--, startCol++) {
                diagonalElems.add(matrix[startRow][startCol]);
            }
            print(diagonals, ++diagonal, diagonalElems);
        }
    }

    private static void print(int diagonals, int diagonal, List<Integer> diagonalElems) {
        if(diagonal % 2 != 0 && diagonal < diagonals){
            Collections.reverse(diagonalElems);
        }
        System.out.println(diagonalElems);
    }
}
