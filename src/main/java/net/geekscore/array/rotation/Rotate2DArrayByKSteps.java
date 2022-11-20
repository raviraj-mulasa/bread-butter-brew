package net.geekscore.array.rotation;

import java.util.Arrays;

/**
 *
 * It is guaranteed that the minimum of m and n will be even.
 *
 *    1 2 3 4
 *   12 1 2 5
 *   11 4 3 6
 *   10 9 8 7
 * As an example rotate the Start matrix by 2:
 *
 *     Start         First           Second
 *      1 2 3 4       2  3  4  5      3  4  5  6
 *     12 1 2 5  ->   1  2  3  6 ->   2  3  4  7
 *     11 4 3 6      12  1  4  7      1  2  1  8
 *     10 9 8 7      11 10  9  8     12 11 10  9
 *
 *  As an example rotate the Start matrix by 7:
 *  1 2 3 4
 * 7 8 9 10
 * 13 14 15 16
 * 19 20 21 22
 * 25 26 27 28
 *
 *  Output
 *  ------
 *      1  2  3  4      2  3  4 10    3  4 10 16    4 10 16 22
 *     7  8  9 10      1  9 15 16    2 15 21 22    3 21 20 28
 *     13 14 15 16 ->  7  8 21 22 -> 1  9 20 28 -> 2 15 14 27 ->
 *     19 20 21 22    13 14 20 28    7  8 14 27    1  9  8 26
 *     25 26 27 28    19 25 26 27    13 19 25 26   7 13 19 25
 *
 *     10 16 22 28    16 22 28 27    22 28 27 26    28 27 26 25
 *      4 20 14 27    10 14  8 26    16  8  9 25    22  9 15 19
 *      3 21  8 26 ->  4 20  9 25 -> 10 14 15 19 -> 16  8 21 13
 *      2 15  9 25     3 21 15 19     4 20 21 13    10 14 20  7
 *      1  7 13 19     2  1  7 13     3  2  1  7     4  3  2  1
 *
 */
public class Rotate2DArrayByKSteps {

//    public static void main(String[] args) {
//
//
//        int[][] matrix = new int[][]{
//                1 2 3 4
//                *   12 1 2 5
//                *   11 4 3 6
//                *   10 9 8 7
//                {1,2,3,4}
//                ,{4,5,6}
//                ,{7,8,9}
//        };
//        ArrayUtil.print(matrix);
//        int[][] rotated2DArray = rotateByKSteps(matrix,90);
//        ArrayUtil.print(rotated2DArray);
//        System.out.println();
//
//
//        matrix = new int[][]{
//                {5, 1, 9,11}
//                ,{2, 4, 8,10}
//                ,{13, 3, 6, 7}
//                ,{15,14,12,16}
//        };
//        ArrayUtil.print(matrix);
//        rotated2DArray = rotateByKSteps(matrix, 90);
//        ArrayUtil.print(rotated2DArray);
//        System.out.println();
//
//        matrix = new int[][]{
//                {0, 1, 2, 3, 4}
//                ,{5, 6, 7, 8, 9}
//                ,{10, 11, 12, 13, 14}
//                ,{15, 16, 17, 18, 19}
//                ,{20, 21, 22, 23, 24}
//        };
//        ArrayUtil.print(matrix);
//        rotated2DArray = rotateByKSteps(matrix, 90);
//        ArrayUtil.print(rotated2DArray);
//        System.out.println();
//
//
//        matrix = new int[][]{
//                {0, 1, 2, 3, 4, 5}
//                ,{6, 7, 8, 9, 10, 11}
//                ,{12, 13, 14, 15, 16, 17}
//                ,{18, 19, 20, 21, 22, 23}
//                ,{24, 25, 26, 27, 28, 29}
//                ,{30, 31, 32, 33, 34, 35}
//        };
//        ArrayUtil.print(matrix);
//        rotated2DArray = rotateByKSteps(matrix, 90);
//        ArrayUtil.print(rotated2DArray);
//        System.out.println();
//    }
//
//    // Additional memory is equal to size of the array
//    private static int[] rotateByKSteps(int[][] array, int k) {
//        if(null == array || array.length == 0) return array;
//        k = k % array.length;
//        if(k < 0) k += array.length;
//        final int[] copy = new int[array.length];
//        for (int i = 0; i < array.length; i++) {
//            copy[i] = array[(i + k) % array.length];
//        }
//        return copy;
//    }




}
