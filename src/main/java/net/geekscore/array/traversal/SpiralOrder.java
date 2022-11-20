//package net.geekscore.array;
//
///**
// * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
// *
// * For example,
// * Given the following matrix:
// * [
// *  [ 1, 2, 3 ],
// *  [ 4, 5, 6 ],
// *  [ 7, 8, 9 ]
// * ]
// *
// * You should return [1,2,3,6,9,8,7,4,5].
// */
//public class SpiralOrder {
//
//    public static void main(String[] args) {
//        int [][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
//        System.out.println("------- Matrix -----------");
//        ArrayUtil.print(matrix);
//        System.out.print("Spiral Order:");
////        spiralOrder(matrix);
//    }
//
//    private static void spiralOrder(int[][] matrix) {
//        int row = 0;
//        for ( int i=0; < matrix.length; i++) {
//            if( i%2 == 0 ) {
//                for (int j = 0; j < matrix[i].length; j++) {
//                    System.out.printf("%4d", matrix[i][j]);
//                }
//            } else {
//                for (int j = matrix[i].length - 1; j >= 0; j--) {
//                    System.out.printf("%4d", matrix[i][j]);
//                }
//            }
//        }
//        System.out.println();
//    }
//}
//
//
////    import java.io.*;
////class MAIN {
////    public static void spiralMatrixPrint(int[][] arr) {
////
////        int rows = arr.length;
////        int cols = arr[0].length;
////        // Defining the boundaries of the matrix.
////        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
////
////        // Defining the direction in which the array is to be traversed.
////        int dir = 1;
////
////        while (top <= bottom && left <= right) {
////
////            if (dir == 1) {    // moving left->right
////                for (int i = left; i <= right; ++i) {
////                    System.out.print(arr[top][i] + " ");
////                }
////                // Since we have traversed the whole first
////                // row, move down to the next row.
////                ++top;
////                dir = 2;
////            }
////            else if (dir == 2) {     // moving top->bottom
////                for (int i = top; i <= bottom; ++i) {
////                    System.out.print(arr[i][right]+ " ");
////                }
////                // Since we have traversed the whole last
////                // column, move left to the previous column.
////                --right;
////                dir = 3;
////            }
////            else if (dir == 3) {     // moving right->left
////                for (int i = right; i >= left; --i) {
////                    System.out.print(arr[bottom][i]+ " ");
////                }
////                // Since we have traversed the whole last
////                // row, move up to the previous row.
////                --bottom;
////                dir = 4;
////            }
////            else if (dir == 4) {     // moving bottom->up
////                for (int i = bottom; i >= top; --i) {
////                    System.out.print(arr[i][left]+ " ");
////                }
////                // Since we have traversed the whole first
////                // col, move right to the next column.
////                ++left;
////                dir = 1;
////            }
////        }
////    }
////    public static void main(String[] args)
////    {
////        // Driver code
////        // Change the following array to test
////        // on some other input
////        int a[][] = { {1,2,3,4},
////                {5,6,7,8},
////                {9,10,11,12}};
////        spiralMatrixPrint(a);
////    }
////}
//}
