package net.geekscore.search;

import java.util.Arrays;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 *
 * This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * For example,
 *
 * Consider the following matrix:
 * [
 *  [1,   4,  7, 11, 15],
 *  [2,   5,  8, 12, 19],
 *  [3,   6,  9, 16, 22],
 *  [10, 13, 14, 17, 24],
 *  [18, 21, 23, 26, 30]
 * ]
 *
 * Given target = 5, return true.
 * Given target = 20, return false.
 */
public class Search2DMatrix2 {

    public static void main(String[] args) {

        System.out.println(searchMatrix(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 5)
        ); // true

        System.out.println(searchMatrix(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 20)
        ); // false


        System.out.println(searchMatrix(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 26)
        ); // true

        System.out.println(searchMatrix(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 15)
        ); // true


        System.out.println(searchMatrix(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 0)
        ); // false

        System.out.println(searchMatrix(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 32)
        ); // false

        System.out.println(searchMatrix(new int[][]
                        {
                                {-1}
                                ,{-1}
                        }
                , 0)
        ); // false

        System.out.println("--------------------");


        System.out.println(searchMatrix1(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 5)
        ); // true

        System.out.println(searchMatrix1(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 20)
        ); // false


        System.out.println(searchMatrix(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 26)
        ); // true

        System.out.println(searchMatrix1(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 15)
        ); // true


        System.out.println(searchMatrix1(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 0)
        ); // false

        System.out.println(searchMatrix1(new int[][]
                        {
                                {1,   4,  7, 11, 15}
                                , {2,   5,  8, 12, 19}
                                , {3,   6,  9, 16, 22}
                                , {10, 13, 14, 17, 24}
                                , {18, 21, 23, 26, 30}
                        }
                , 32)
        ); // false


        System.out.println(searchMatrix1(new int[][]
                        {
                                {-1}
                                ,{-1}
                        }
                , 0)
        ); // false
        
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0 , column = matrix[row].length-1;
        while(row < matrix.length && column > -1) {
            if(target == matrix[row][column]) return true;
            if(target < matrix[row][column]) column--;
            else row++;
        }
        return false;
    }


    private static boolean searchMatrix1(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length-1, column = 0;
        while(row > -1 && column < matrix[row].length) {
            if(target == matrix[row][column]) return true;
            if(target > matrix[row][column]) column++;
            else row--;
        }
        return false;
    }
}
