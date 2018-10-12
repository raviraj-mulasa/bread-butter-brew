package net.geekscore.algo.dynamic;

import java.util.Arrays;

public class MaximumRectangle {

    public static void main(String[] args) {

        int[][] matrix = new int[][] {
                {1,0,1,0,0}
                ,{1,1,1,1,1}
                ,{1,1,1,1,1}
                ,{1,1,1,1,1}
        };

        int[][] matrix1 = new int[][] {
                {0,0,0,1,0,0,0}
                ,{0,0,1,1,1,0,0}
                ,{0,1,1,1,1,1,0}
        };

        int[][] matrix2 = new int[][] {
                {0,0,0}
                ,{1,1,1}
                ,{1,1,1}
        };

        int[][] matrix3 = new int[][] {
                {1,1,1}
                ,{1,1,1}
                ,{0,0,1}
        };

        System.out.println(maxRectangleArea(matrix));
        System.out.println(maxRectangleArea(matrix1));
        System.out.println(maxRectangleArea(matrix2));
        System.out.println(maxRectangleArea(matrix3));

    }


    private static int maxRectangleArea(final int[][] matrix) {
        if(null == matrix || matrix.length == 0) return 0;
        final int rows  = Math.max(0, matrix.length);
        final int cols  = Math.max(0, matrix[0].length);
        
        final int[] width = new int[cols];
        final int[] left = new int[cols];
        final int[] right = new int[cols];

        Arrays.fill(width, 0);
        Arrays.fill(left, 0);
        Arrays.fill(right, cols);

        int max = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {
                if(matrix[i][j] == 1) width[j] +=1;
                else width[j] = 0;
            }

            int _left = 0;
            for (int j = 0; j < cols; j++) {
                if(matrix[i][j] == 1) left[j] = Math.max(left[j], _left);
                else {
                    left[j] = 0;
                    _left = j+1;
                }
            }

            int _right = cols - 1;
            for (int j = cols - 1; j >= 0; j--) {
                if(matrix[i][j] == 1) right[j] = Math.min(right[j], _right);
                else {
                    right[j] = cols;
                    _right =  j - 1;
                }
            }

            for (int j = 0; j < cols; j++) {
                final int length =  right[j] - left[j] + 1;
                max = Math.max(max, length * width[j]);
            }
//            System.out.println(Arrays.toString(width));
//            System.out.println(Arrays.toString(left));
//            System.out.println(Arrays.toString(right));
//            System.out.println();
        }

        return max;
    }

}
