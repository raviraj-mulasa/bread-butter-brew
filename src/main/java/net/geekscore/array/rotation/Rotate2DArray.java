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
public class Rotate2DArray {

    public static void main(String[] args) {

        for (int degrees = 90; degrees <= 360 ; degrees+=90) {

            int[][] rotated2DArray = rotateInMemory(new int[][]{
                    {1,2,3}
                    ,{4,5,6}
                    ,{7,8,9}
            }, true, degrees);
            System.out.println(String.format(" ------ Clockwise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);

            rotated2DArray = rotateInMemory(new int[][]{
                    {5, 1, 9,11}
                    ,{2, 4, 8,10}
                    ,{13, 3, 6, 7}
                    ,{15,14,12,16}
            } , true, degrees);
            System.out.println(String.format(" ------ Clockwise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);

            rotated2DArray = rotateInMemory(new int[][]{
                    {0, 1, 2, 3, 4}
                    ,{5, 6, 7, 8, 9}
                    ,{10, 11, 12, 13, 14}
                    ,{15, 16, 17, 18, 19}
                    ,{20, 21, 22, 23, 24}
            } , true, degrees);
            System.out.println(String.format(" ------ Clockwise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);


            rotated2DArray = rotateInMemory(new int[][]{
                    {0, 1, 2, 3, 4, 5}
                    ,{6, 7, 8, 9, 10, 11}
                    ,{12, 13, 14, 15, 16, 17}
                    ,{18, 19, 20, 21, 22, 23}
                    ,{24, 25, 26, 27, 28, 29}
                    ,{30, 31, 32, 33, 34, 35}
            } , true, degrees);
            System.out.println(String.format(" ------ Clockwise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);
        }

        // ANTI-CLOCK WISE
        for (int degrees = 90; degrees <= 360 ; degrees+=90) {

            int[][] rotated2DArray = rotateInMemory(new int[][]{
                    {1,2,3}
                    ,{4,5,6}
                    ,{7,8,9}
            } , false, degrees);
            System.out.println(String.format(" ------ Anti-Clock wise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);


            rotated2DArray = rotateInMemory(new int[][]{
                    {5, 1, 9,11}
                    ,{2, 4, 8,10}
                    ,{13, 3, 6, 7}
                    ,{15,14,12,16}
            } , false, degrees);
            System.out.println(String.format(" ------ Anti-Clock wise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);


            rotated2DArray = rotateInMemory(new int[][]{
                    {0, 1, 2, 3, 4}
                    ,{5, 6, 7, 8, 9}
                    ,{10, 11, 12, 13, 14}
                    ,{15, 16, 17, 18, 19}
                    ,{20, 21, 22, 23, 24}
            } , false, degrees);
            System.out.println(String.format(" ------ Anti-Clock wise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);


            rotated2DArray = rotateInMemory(new int[][]{
                    {0, 1, 2, 3, 4, 5}
                    ,{6, 7, 8, 9, 10, 11}
                    ,{12, 13, 14, 15, 16, 17}
                    ,{18, 19, 20, 21, 22, 23}
                    ,{24, 25, 26, 27, 28, 29}
                    ,{30, 31, 32, 33, 34, 35}
            } , false, degrees);
            System.out.println(String.format(" ------ Anti-Clock wise Rotating: %d ", degrees));
            ArrayUtil.print(rotated2DArray);

        }


        int[][] rotated2DArray = rotateBy180InMemory(new int[][]{
                {1,2,3}
                ,{4,5,6}
                ,{7,8,9}
        } , true);
        System.out.println(" --------- Clock wise 180 ------");
        ArrayUtil.print(rotated2DArray);

        rotated2DArray = rotateBy180InMemory(new int[][]{
                {5, 1, 9,11}
                ,{2, 4, 8,10}
                ,{13, 3, 6, 7}
                ,{15,14,12,16}
        } , true);
        System.out.println(" --------- Clock wise 180 ------");
        ArrayUtil.print(rotated2DArray);


        // ANTI-CLOCK WISE
        rotated2DArray = rotateBy180InMemory(new int[][]{
                {1,2,3}
                ,{4,5,6}
                ,{7,8,9}
        } , false);
        System.out.println(" --------- Anti-Clock wise 180 ------");
        ArrayUtil.print(rotated2DArray);

        rotated2DArray = rotateBy180InMemory(new int[][]{
                {5, 1, 9,11}
                ,{2, 4, 8,10}
                ,{13, 3, 6, 7}
                ,{15,14,12,16}
        } , false);
        System.out.println(" --------- Anti-Clock wise 180 ------");
        ArrayUtil.print(rotated2DArray);


    }


    private static final int[][] rotateInMemory(int[][] matrix, final boolean clockWise, final int degrees) {
        if(null == matrix || matrix.length == 0){
            return matrix;
        }
        if(degrees % 90 != 0) {
            throw new  IllegalArgumentException(String.format("Invalid degrees %d", degrees));
        }
        System.out.println(" --------- Matrix ------");
        ArrayUtil.print(matrix);
        for (int i = degrees/90; i > 0; i--) {
            transpose(matrix);
            if (clockWise) reverseRows(matrix);
            else reverseColumns(matrix);
        }
        return matrix;
    }


    private static final int[][] rotateRecursion(int[][] matrix, final boolean clockWise, final int degrees) {
        // TODO
        return matrix;
    }

    private static void transpose(final int[][] matrix) {
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = i+1; j < cols; j++) {
                swap(matrix, new int[]{i,j}, new int[]{j,i});
            }
        }
    }

    /**
     *
     * @param matrix
     * @param clockWise or Anti-clockWise
     * @return matrix rotated 180
     */
    private static final int[][] rotateBy180InMemory(int[][] matrix, final boolean clockWise) {
        if(null == matrix || matrix.length == 0){
            return matrix;
        }
        System.out.println(" --------- Matrix ------");
        ArrayUtil.print(matrix);
        if (clockWise) {
            reverseRows(matrix);
            reverseColumns(matrix);
        } else {
            reverseColumns(matrix);
            reverseRows(matrix);
        }
        return matrix;
    }

    private static void reverseRows(int[][] matrix) {
        final int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            reverseRow(matrix, i);
        }
    }

    private static void reverseRow(final int[][] matrix, final int row) {
        int right = matrix[row].length - 1; // cols per row
        int left = 0;
        while (left < right) {
            swap(matrix, new int[]{row, left}, new int[]{row, right});
            left++;
            right--;
        }
    }

    private static void reverseColumns(int[][] matrix) {
        final int cols = matrix[0].length;
        for (int i = 0; i < cols; i++) {
            reverseColumn(matrix, i);
        }
    }


    private static void reverseColumn(final int[][] matrix, final int column) {
        int left = 0;
        int right = matrix.length - 1; // rows in matrix
        while (left < right) {
            swap(matrix, new int[]{left, column}, new int[]{right, column});
            right--;
            left++;
        }
    }

    private static void swap(final int[][] matrix, final int[] ele1Idx, final int[] ele2Idx) {
        if(matrix[ele1Idx[0]][ele1Idx[1]] != matrix[ele2Idx[0]][ele2Idx[1]]) {
            matrix[ele1Idx[0]][ele1Idx[1]]= matrix[ele1Idx[0]][ele1Idx[1]] ^ matrix[ele2Idx[0]][ele2Idx[1]];
            matrix[ele2Idx[0]][ele2Idx[1]] = matrix[ele2Idx[0]][ele2Idx[1]] ^ matrix[ele1Idx[0]][ele1Idx[1]];
            matrix[ele1Idx[0]][ele1Idx[1]] = matrix[ele1Idx[0]][ele1Idx[1]] ^ matrix[ele2Idx[0]][ele2Idx[1]];
        }
    }


}
