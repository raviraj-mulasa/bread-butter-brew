package net.geekscore.search;

import java.util.Arrays;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 *
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 * For example,
 * Consider the following matrix:
 * [
 *  [1,   3,  5,  7],
 *  [10, 11, 16, 20],
 *  [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 */
public class Search2DMatrix {

    public static void main(String[] args) {

        System.out.println(searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7}
                                , {10, 11, 16, 20}
                                , {23, 30, 34, 50}
                        }
                , 3)
        ); // true

        System.out.println(searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7}
                                , {10, 11, 16, 20}
                                , {23, 30, 34, 50}
                        }
                , 21)
        ); // false

        System.out.println(searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7}
                                , {10, 11, 16, 20}
                                , {23, 30, 34, 50}
                        }
                , 4)
        ); // false

        System.out.println(searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7}
                                , {10, 11, 16, 20}
                                , {23, 30, 34, 50}
                        }
                , 15)
        ); // false

        System.out.println(searchMatrix(new int[][]
                        {
                                  {1, 3, 5, 7}
                                , {10, 11, 16, 20}
                                , {23, 30, 34, 50}
                        }
                , 23)
        ); // true

        System.out.println(searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7}
                                , {10, 11, 16, 20}
                                , {23, 30, 34, 50}
                        }
                , 50)
        ); // true

        System.out.println(searchMatrix(new int[][]
                        {{}}
                , 50)
        ); // false

    }


    private static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int left = 0 , right = matrix.length - 1;
        while(left <= right) {
            final int mid = left + (right-left)/2;
            if(Arrays.binarySearch(matrix[mid], target) >= 0) return true;
            if(matrix[mid][0] > target ) right = mid - 1;
            else left = mid+1;
        }
        return false;
    }
}
