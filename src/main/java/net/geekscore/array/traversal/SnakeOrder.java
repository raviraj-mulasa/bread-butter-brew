package net.geekscore.array.traversal;

import net.geekscore.array.ArrayUtil;

import java.util.Arrays;

/**
 *
 * SNAKE ORDER
 *
 * Given an n x n matrix .In the given matrix, you have to print the elements of the matrix in the
 * snake pattern.
 *
 * Examples:
 * Input :mat[][] = {
 *  {10, 20, 30, 40},
 *  {15, 25, 35, 45},
 *  {27, 29, 37, 48},
 *  {32, 33, 39, 50}
 * };
 *
 *  Output : 10 20 30 40 45 35 25 15 27 29
 *  37 48 50 39 33 32
 *
 *  Input :mat[][] = {
 *  {1, 2, 3},
 *  {4, 5, 6},
 *  {7, 8, 9}
 * };
 * Output : 1 2 3 6 5 4 7 8 9
 *
 */
public class SnakeOrder {

    // TODO

    public static void main(String[] args) {

        int [][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        System.out.print("Snake Order: ");
        snakeOrder(matrix);

        matrix = new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        System.out.print("Snake Order: ");
        snakeOrder(matrix);

        matrix = new int[][]{{1,2,3,4,5}, {6,7,8,9,10}};
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        System.out.print("Snake Order:");
        snakeOrder(matrix);

    }

    private static void snakeOrder(final int [][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if(0 == i % 2) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.printf("%4d", matrix[i][j]);
                }
            } else {
                for (int j = matrix[i].length - 1; j >= 0; j--) {
                    System.out.printf("%4d", matrix[i][j]);
                }
            }
        }
        System.out.println();
    }

}
