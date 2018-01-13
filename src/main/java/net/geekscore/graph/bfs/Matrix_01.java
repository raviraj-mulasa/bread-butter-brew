package net.geekscore.graph.bfs;

import net.geekscore.array.ArrayUtil;

import java.util.Deque;
import java.util.LinkedList;

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

        final int[][] grid1 = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };

        final int[][] grid2 = {
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };
        ArrayUtil.print(updateMatrix(grid1));
        System.out.println("--------------");
        ArrayUtil.print(updateMatrix(grid2));

    }

    private static final int[][] updateMatrixBFS(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return matrix;
        }
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final boolean[][] visited = new boolean[rows][cols];
        final Deque<Integer> queue = new LinkedList<>();
        int i = 0, j = i;
        queue.offerFirst(matrix[i][j]);
        while (!queue.isEmpty()) {
            if(!visited[i][j]) {
                Integer curr = queue.removeLast();
                final Integer up = up(matrix, i, j);
                if(up != Integer.MAX_VALUE) {
                    curr += up;
                    queue.offerFirst(up);
                }
                final Integer down = down(matrix, i, j);
                if(down != Integer.MAX_VALUE) {
                    curr += down;
                    queue.offerFirst(down);
                }
                final Integer left = left(matrix, i, j);
                final Integer right= down(matrix, i, j);

                matrix[i][j] = curr;

            }

        }
        return matrix;
    }




    // ================== Approach 2 =================
    private static final int[][] updateMatrix(int[][] matrix) {
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


    private static void updateMin(int[][] matrix, int i, int j, int val) {
        if(val != Integer.MAX_VALUE) {
            matrix[i][j] = Math.min(matrix[i][j] ,  1 + val);
        }
    }

    private static int left(int[][] matrix, int i, int j) {
        if(j-1 < 0) {
            return Integer.MAX_VALUE;
        }
        return matrix[i][j-1];
    }

    private static int right(int[][] matrix, int i, int j) {
        final int cols = matrix[0].length;
        if(j+1 < cols) {
            return matrix[i][j+1];
        }
        return Integer.MAX_VALUE;
    }

    private static int up(int[][] matrix, int i, int j) {
        if(i-1 < 0) {
            return Integer.MAX_VALUE;
        }
        return matrix[i-1][j];
    }

    private static int down(int[][] matrix, int i, int j) {
        final int rows = matrix.length;
        if(i+1 < rows) {
            return matrix[i+1][j];
        }
        return Integer.MAX_VALUE;
    }
}
