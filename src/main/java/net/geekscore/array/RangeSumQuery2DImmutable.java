package net.geekscore.array;

/**
 * Given a 2D matrix matrix, find the runningMatrixSum of the elements inside the rectangle defined by its
 * upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by
 * (row1, col1) = (2, 1) and (row2, col2) =(4, 3), which contains runningMatrixSum = 8.
 *
 * Example:
 * Given matrix = [
 ,{3, 0, 1, 4, 2}
 ,{5, 6, 3, 2, 1}
 ,{1, 2, 0, 1, 5}
 ,{4, 1, 0, 1, 7}
 ,{1, 0, 3, 0, 5]
 *]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 *
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DImmutable {

    public static void main(String[] args) {

        final int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2}
                ,{5, 6, 3, 2, 1}
                ,{1, 2, 0, 1, 5}
                ,{4, 1, 0, 1, 7}
                ,{1, 0, 3, 0, 5}
        };

        final NumMatrix numMatrix = new NumMatrix(matrix);

        System.out.println(rangeSumBruteForce(matrix, 2, 1, 4, 3));  // 8
        System.out.println(rangeSumBruteForce(matrix, 1, 1, 2, 2));  // 11
        System.out.println(rangeSumBruteForce(matrix, 1, 2, 2, 4));  // 12

        System.out.println("----------");

        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));  // 8
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));  // 11
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));  // 12
    }

    private static final int rangeSumBruteForce(int[][] matrix, int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2 ; i++) {
            for (int j = col1; j <= col2; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    private static class NumMatrix {

        private int[][] matrix = new int[0][0];

        private int[][] runningMatrixSum = new int[0][0];

        private NumMatrix(int[][] matrix) {
            if(null != matrix && 0 < matrix.length) {
                this.matrix = matrix;
                this.runningMatrixSum = new int[this.matrix.length+1][this.matrix[0].length+1];
                this.computeRunningMatrixSum();
            }

        }

        private int sumRegion(int row1, int col1, int row2, int col2) {
            return this.runningMatrixSum[row2+1][col2+1] // next diagonal - down row, right col
                    - this.runningMatrixSum[row1][col2+1] // right col
                    - this.runningMatrixSum[row2+1][col1] // down row
                    + this.runningMatrixSum[row1][col1]; // add myself
        }
        
        private void computeRunningMatrixSum() {
            for (int i = 1; i < this.runningMatrixSum.length ; i++) {
                for (int j = 1; j < this.runningMatrixSum[i].length ; j++) {
                    this.runningMatrixSum[i][j] =
                            this.runningMatrixSum[i-1][j] // top row
                            + this.runningMatrixSum[i][j-1] // left col
                            - this.runningMatrixSum[i-1][j-1] // diagonal because we add twice
                            + this.matrix[i-1][j-1]; // add myself
                }
            }
        }
    }

}
