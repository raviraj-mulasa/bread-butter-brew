package net.geekscore.array.rotation;

import net.geekscore.array.ArrayUtil;

/**
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 * Note:
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 * Given input matrix =
 *  [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 *  ],
 *
 *  rotate the input matrix in-place such that it becomes:
 *  [
 *    [7,4,1],
 *    [8,5,2],
 *    [9,6,3]
 *  ]
 *
 *  Example 2:
 *
 *  Given input matrix =
 *  [
 *      [ 5, 1, 9,11],
 *      [ 2, 4, 8,10],
 *      [13, 3, 6, 7],
 *      [15,14,12,16]
 *  ],
 *
 *  rotate the input matrix in-place such that it becomes
 *  [
 *      [15,13, 2, 5],
 *      [14, 3, 4, 1],
 *      [12, 6, 8, 9],
 *      [16, 7,10,11]
 *  ]
 */
public class Rotate2DRecursion {

    public static void main(String[] args) {

        for (int degrees = 90; degrees <= 360 ; degrees+=90) {

            int[][] rotated2DArray = rotateRecursion(new int[][]{
                    {1,2,3}
                    ,{4,5,6}
                    ,{7,8,9}
            });
            System.out.println(String.format(" ------ Clockwise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);

            rotated2DArray = rotateRecursion(new int[][]{
                    {5, 1, 9,11}
                    ,{2, 4, 8,10}
                    ,{13, 3, 6, 7}
                    ,{15,14,12,16}
            });
            System.out.println(String.format(" ------ Clockwise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);

            rotated2DArray = rotateRecursion(new int[][]{
                    {0, 1, 2, 3, 4}
                    ,{5, 6, 7, 8, 9}
                    ,{10, 11, 12, 13, 14}
                    ,{15, 16, 17, 18, 19}
                    ,{20, 21, 22, 23, 24}
            });
            System.out.println(String.format(" ------ Clockwise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);


            rotated2DArray = rotateRecursion(new int[][]{
                    {0, 1, 2, 3, 4, 5}
                    ,{6, 7, 8, 9, 10, 11}
                    ,{12, 13, 14, 15, 16, 17}
                    ,{18, 19, 20, 21, 22, 23}
                    ,{24, 25, 26, 27, 28, 29}
                    ,{30, 31, 32, 33, 34, 35}
            });
            System.out.println(String.format(" ------ Clockwise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);
        }


        // ANTI-CLOCK WISE

        for (int degrees = 90; degrees <= 360 ; degrees+=90) {

            int[][] rotated2DArray = rotateRecursion(new int[][]{
                    {1,2,3}
                    ,{4,5,6}
                    ,{7,8,9}
            });
            System.out.println(String.format(" ------ Anti-Clock wise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);


            rotated2DArray = rotateRecursion(new int[][]{
                    {5, 1, 9,11}
                    ,{2, 4, 8,10}
                    ,{13, 3, 6, 7}
                    ,{15,14,12,16}
            });
            System.out.println(String.format(" ------ Anti-Clock wise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);


            rotated2DArray = rotateRecursion(new int[][]{
                    {0, 1, 2, 3, 4}
                    ,{5, 6, 7, 8, 9}
                    ,{10, 11, 12, 13, 14}
                    ,{15, 16, 17, 18, 19}
                    ,{20, 21, 22, 23, 24}
            });
            System.out.println(String.format(" ------ Anti-Clock wise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);


            rotated2DArray = rotateRecursion(new int[][]{
                    {0, 1, 2, 3, 4, 5}
                    ,{6, 7, 8, 9, 10, 11}
                    ,{12, 13, 14, 15, 16, 17}
                    ,{18, 19, 20, 21, 22, 23}
                    ,{24, 25, 26, 27, 28, 29}
                    ,{30, 31, 32, 33, 34, 35}
            });
            System.out.println(String.format(" ------ Anti-Clock wise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);
        }
    }




    private static final int[][] rotateRecursion(int[][] matrix) {
        // TODO
        return matrix;
    }




}
