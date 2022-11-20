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
public class Rotate2DArrayLayer {

    public static void main(String[] args) {


        int[][] matrix = new int[][]{
                {1,2,3}
                ,{4,5,6}
                ,{7,8,9}
        };
        ArrayUtil.print(matrix);
        int[][] rotated2DArray = rotateByDegrees(matrix,90);
        ArrayUtil.print(rotated2DArray);
        System.out.println();


        matrix = new int[][]{
                {5, 1, 9,11}
                ,{2, 4, 8,10}
                ,{13, 3, 6, 7}
                ,{15,14,12,16}
        };
        ArrayUtil.print(matrix);
        rotated2DArray = rotateByDegrees(matrix, 90);
        ArrayUtil.print(rotated2DArray);
        System.out.println();

        matrix = new int[][]{
                {0, 1, 2, 3, 4}
                ,{5, 6, 7, 8, 9}
                ,{10, 11, 12, 13, 14}
                ,{15, 16, 17, 18, 19}
                ,{20, 21, 22, 23, 24}
        };
        ArrayUtil.print(matrix);
        rotated2DArray = rotateByDegrees(matrix, 90);
        ArrayUtil.print(rotated2DArray);
        System.out.println();


        matrix = new int[][]{
                {0, 1, 2, 3, 4, 5}
                ,{6, 7, 8, 9, 10, 11}
                ,{12, 13, 14, 15, 16, 17}
                ,{18, 19, 20, 21, 22, 23}
                ,{24, 25, 26, 27, 28, 29}
                ,{30, 31, 32, 33, 34, 35}
        };
        ArrayUtil.print(matrix);
        rotated2DArray = rotateByDegrees(matrix, 90);
        ArrayUtil.print(rotated2DArray);
        System.out.println();
    }


    private static final int[][] rotateByDegrees(int[][] matrix, int degrees) {
        if(degrees < 90) {
            throw new IllegalArgumentException("Bad degrees");
        }
        System.out.printf(" ------ Clockwise Rotating: %d %n", degrees);
        for (int start = 90; start <= degrees ; start+=90) {
            rotateLayerByLayer(matrix);
        }
        return matrix;
    }



    private static final int[][] rotateLayerByLayer(int[][] matrix) {
        int size = matrix.length;
        int layers = size/2;
        for (int layer = 0; layer < layers; layer++) {
            int first = layer;
            int last = size - first - 1;
            for(int elememnt = first; elememnt <= last; elememnt++) {
                System.out.printf("Layer:%d First:%d Last:%d Element:%d\n", layer, first, last, elememnt);
                for (int j = 0; j < last; j++) {
                    System.out.printf("%4d", matrix[elememnt][j]);
                }
                System.out.println();




//                int offset = elememnt - first;
//                System.out.printf("" +
//                                "top:%d; right_side:%d; bottom:%d; left_side:%d\n"
//                        , matrix[first][elememnt]
//                        , matrix[elememnt][last]
//                        , matrix[last][ last - offset]
//                        , matrix[last - offset][first]
//                );
            }
        }
        return matrix;
    }

}
