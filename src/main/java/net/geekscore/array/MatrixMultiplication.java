package net.geekscore.array;


import java.util.Arrays;

/**
 * Created by ravirajmulasa on 9/2/16.
 */
public final class MatrixMultiplication {

    private MatrixMultiplication(){}

    public static final int[][] standard(final int a[][], int b[][]) {

        int rowsInA = 0;
        int colsInA = 0;

        int rowsInB = 0;
        int colsInB = 0;

        if(null != a) {
            rowsInA = Math.max(0, a.length);
            if(null != a[0]) {
                colsInA = Math.max(0, a[0].length);
            }
        }

        if(null != b) {
            rowsInB = Math.max(0, b.length);
            if(null != b[0]) {
                colsInB = Math.max(0, b[0].length);
            }
        }

        if(colsInA != rowsInB) {
//            Matrix multiplication not possible for these dimensions
            return null;
        }

        final int K             = colsInA;
        final int[][] product   = new int[rowsInA][colsInB];

        for (int i = 0; i < rowsInA; i++) {
            product[i] = new int[colsInB];
            Arrays.fill(product[i], 0);
            for (int j = 0; j < colsInB; j++) {
                for (int k = 0; k < K; k++) {
                    product[i][j] += (a[i][k] * b[k][j]);
                }
            }
        }
        return product;
    }


    private static final void print(final int a[][]) {

        if(null != a) {
            int rows = Math.max(0, a.length);
            int cols = 0;
            if(rows > 0 && null != a[0]) {
                cols = Math.max(0, a[0].length);
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(String.format("%s\t", a[i][j]));
                }
                System.out.println();
            }
        }
    }

    public static void main(String args[]) {
        final int a[][] =  {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        final int b[][] =  {{1, 1, 2}, {1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        final int c[][] =  {{1, 1}, {1, 0}, {0, 1}, {0, 0}, {2, 3}};
        System.out.println("------------");
        print(standard(a, a));
        System.out.println("------------");
        print(standard(a, b));
        System.out.println("------------");
        print(standard(a, c));
        System.out.println("------------");
        print(standard(b, c));
    }
}
