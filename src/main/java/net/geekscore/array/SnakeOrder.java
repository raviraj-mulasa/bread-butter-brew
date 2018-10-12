package net.geekscore.array;

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

        System.out.println(Arrays.toString(snakeOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})));
        System.out.println(Arrays.toString(snakeOrder(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}})));

    }

    private static int[] snakeOrder(final int [][] matrix) {
        System.out.println("------- Matrix -----------");
        ArrayUtil.print(matrix);
        return new int[0];
    }
}
