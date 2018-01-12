package net.geekscore.graph.bfs;

/**
 *
 * TODO
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1
 * Example 1:
 *  Input:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *  Output:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * Example 2:
 *  Input:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 *  Output:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 *
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 */
public class Matrix_01 {

    public static void main(String[] args) {

    }



    // ================== Approach 2 =================
    private int[][] updateMatrix(int[][] matrix) {
        if(null == matrix || matrix.length == 0) {
            return matrix;
        }
        final int rows = matrix.length;
        final int cols = matrix[0].length;

        // From top left
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == 1) {
                    matrix[i][j] = Integer.MAX_VALUE;
                    updateMin(matrix, i, j, left(matrix, i, j));
                    updateMin(matrix, i, j, up(matrix, i, j));
                }
            }
        }

        // From right bottom
        for(int i = rows - 1; i >= 0; i--) {
            for(int j = cols - 1; j >= 0; j--) {
                updateMin(matrix, i, j, right(matrix, i, j));
                updateMin(matrix, i, j, down(matrix, i, j));
            }
        }
        return matrix;
    }


    private void updateMin(int[][] matrix, int i, int j, int val) {
        if(val != Integer.MAX_VALUE) {
            matrix[i][j] = Math.min(matrix[i][j] ,  1 + val);
        }
    }

    private int left(int[][] matrix, int i, int j) {
        if(j-1 < 0) {
            return Integer.MAX_VALUE;
        }
        return matrix[i][j-1];
    }

    private int right(int[][] matrix, int i, int j) {
        final int cols = matrix[0].length;
        if(j+1 < cols) {
            return matrix[i][j+1];
        }
        return Integer.MAX_VALUE;
    }

    private int up(int[][] matrix, int i, int j) {
        if(i-1 < 0) {
            return Integer.MAX_VALUE;
        }
        return matrix[i-1][j];
    }

    private int down(int[][] matrix, int i, int j) {
        final int rows = matrix.length;
        if(i+1 < rows) {
            return matrix[i+1][j];
        }
        return Integer.MAX_VALUE;
    }
}
