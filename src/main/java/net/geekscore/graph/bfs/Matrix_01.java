package net.geekscore.graph.bfs;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import net.geekscore.array.ArrayUtil;
import java.util.Deque;
import java.util.LinkedList;

/**
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

        System.out.println("--------------");
        ArrayUtil.print(updateMatrix(new int[][]{
                {0,0,0}
                ,{0,1,0}
                ,{0,0,0}
        }));
        System.out.println("--------------");
        ArrayUtil.print(updateMatrix(new int[][]{
                {0,0,0}
                ,{0,1,0}
                ,{1,1,1}
        }));

        System.out.println("--------------");
        ArrayUtil.print(updateMatrix(new int[][]{
                {1,0,0}
                ,{1,1,0}
                ,{1,1,1}
        }));
        System.out.println("--------------");
        ArrayUtil.print(updateMatrix(new int[][]{
                {0,0,0}
                ,{0,1,0}
                ,{0,1,1}
        }));


        System.out.println("===============");

        System.out.println("--------------");
        ArrayUtil.print(updateMatrixBFS(new int[][]{
                {0,0,0}
                ,{0,1,0}
                ,{0,0,0}
        }));
        System.out.println("--------------");
        ArrayUtil.print(updateMatrixBFS(new int[][]{
                {0,0,0}
                ,{0,1,0}
                ,{1,1,1}
        }));
        System.out.println("--------------");
        ArrayUtil.print(updateMatrixBFS(new int[][]{
                {1,0,0}
                ,{1,1,0}
                ,{1,1,1}
        }));
        System.out.println("--------------");
        ArrayUtil.print(updateMatrixBFS(new int[][]{
                {0,0,0}
                ,{0,1,0}
                ,{0,1,1}
        }));

        System.out.println("===============");

        System.out.println("--------------");
        ArrayUtil.print(updateMatrixDFS(new int[][]{
                {0,0,0}
                ,{0,1,0}
                ,{0,0,0}
        }));
        System.out.println("--------------");
        ArrayUtil.print(updateMatrixDFS(new int[][]{
                {0,0,0}
                ,{0,1,0}
                ,{1,1,1}
        }));
        System.out.println("--------------");
        ArrayUtil.print(updateMatrixDFS(new int[][]{
                {1,0,0}
                ,{1,1,0}
                ,{1,1,1}
        }));
        System.out.println("--------------");
        ArrayUtil.print(updateMatrixDFS(new int[][]{
                {0,0,0}
                ,{0,1,0}
                ,{0,1,1}
        }));
    }

    private static final int[][] MOVES = {
            {-1,0}, // vertical up
            {1,0},  // vertical down
            {0,-1}, //  horizontal left
            {0,1} // horizontal right
    };

    private static final int[][] updateMatrixBFS(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return matrix;
        }
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final Deque<int[]> queue = new LinkedList<>();
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(matrix[i][j] == 0) queue.offerFirst(new int[]{i, j});
                else matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        while (!queue.isEmpty()) {
            final int[]  curr = queue.removeLast();
            int currVal = matrix[curr[0]][curr[1]];
            for (final int[] MOVE : MOVES) {
                final int moveX = curr[0] + MOVE[0];
                final int moveY = curr[1] + MOVE[1];
                if (moveX < 0 || moveX >= rows || moveY < 0 || moveY >= cols || matrix[moveX][moveY] <= currVal + 1)
                    continue;
                queue.offerFirst(new int[]{moveX, moveY});
                matrix[moveX][moveY] = currVal + 1;
            }
        }
        return matrix;
    }


    // ================== Approach 2 =================
    private static final int[][] updateMatrixDFS(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return matrix;
        }
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 1) {
                    int minSteps = Integer.MAX_VALUE;
                    for (final int[] dir: new int[][]{{-1,0}, {0,-1}}) { // left and right
                        int x = i + dir[0];
                        int y = j + dir[1];
                        int steps = 0;
                        while (x >=0 && x < matrix.length && y >=0 && y < matrix[x].length && matrix[x][y] != 0) {
                            x += dir[0];
                            y += dir[1];
                            steps++;
                        }
                        if(steps > 0) minSteps = Math.min(minSteps, steps);
                    }
                    if(minSteps != Integer.MAX_VALUE) matrix[i][j] += minSteps;
                }
            }
        }
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <  matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    for (final int[] dir: new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}}) { // up and down
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >=0 && x < matrix.length && y >=0 && y < matrix[x].length && matrix[x][y] > 1) {
                            matrix[x][y] = 1;
                        }
                    }
                }
            }
        }
        return matrix;

    }

    // ================== Approach 3 =================
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
