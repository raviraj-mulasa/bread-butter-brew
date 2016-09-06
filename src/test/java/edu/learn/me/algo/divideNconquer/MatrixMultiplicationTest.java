package edu.learn.me.algo.divideNconquer;


import org.junit.Assert;
import org.junit.Test;


/**
 * Created by ravirajmulasa on 9/3/16.
 */
public class MatrixMultiplicationTest {

    @Test
    public void testStandard() {
        final int a[][] =  {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

        final int aSquared[][] = {{90, 100, 110, 120}, {202, 228, 254, 280}, {314, 356, 398, 440}, {426, 484, 542, 600}};
        Assert.assertArrayEquals(aSquared, MatrixMultiplication.standard(a, a));

        final int b[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        final int product[][] = {{70, 80, 90}, {158, 184, 210}, {246, 288, 330}, {334, 392, 450}};
        Assert.assertArrayEquals(product, MatrixMultiplication.standard(a, b));

        final int c[][] = {{1, 2}, {3, 4}, {5, 6}};
        Assert.assertNull(MatrixMultiplication.standard(a, c));
    }


    @Test
    public void testDivideNConquer() {
        final int a[][] =  {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

        final int aSquared[][] = {{90, 100, 110, 120}, {202, 228, 254, 280}, {314, 356, 398, 440}, {426, 484, 542, 600}};
        Assert.assertArrayEquals(aSquared, MatrixMultiplication.standard(a, a));

        final int b[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        final int product[][] = {{70, 80, 90}, {158, 184, 210}, {246, 288, 330}, {334, 392, 450}};
        Assert.assertArrayEquals(product, MatrixMultiplication.standard(a, b));

        final int c[][] = {{1, 2}, {3, 4}, {5, 6}};
        Assert.assertNull(MatrixMultiplication.standard(a, c));
    }

    @Test
    public void testStrassen() {
        final int a[][] =  {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

        final int aSquared[][] = {{90, 100, 110, 120}, {202, 228, 254, 280}, {314, 356, 398, 440}, {426, 484, 542, 600}};
        Assert.assertArrayEquals(aSquared, MatrixMultiplication.standard(a, a));

        final int b[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        final int product[][] = {{70, 80, 90}, {158, 184, 210}, {246, 288, 330}, {334, 392, 450}};
        Assert.assertArrayEquals(product, MatrixMultiplication.standard(a, b));

        final int c[][] = {{1, 2}, {3, 4}, {5, 6}};
        Assert.assertNull(MatrixMultiplication.standard(a, c));
    }


}
