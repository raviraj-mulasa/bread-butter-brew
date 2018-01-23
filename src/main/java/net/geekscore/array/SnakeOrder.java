package net.geekscore.array;

import java.util.Arrays;

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
