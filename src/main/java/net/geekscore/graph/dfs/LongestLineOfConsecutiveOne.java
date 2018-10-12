package net.geekscore.graph.dfs;


/**
 *
 *  todo
 *
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix.
 * The line could be horizontal, vertical, diagonal or anti-diagonal.
 *
 * Example:
 * Input:
 * [
 *  [0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]
 * ]
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LongestLineOfConsecutiveOne {

    private static final int[][] MOVES = new int[][] {
            {1,0}, // vertical down
            {0,1}, // horizontal righ
            {1,-1}, // anti-diagonal
            {1,1}, // diagonal
    };


    public static void main(String[] args) {

        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                {0,0,0,0}
                ,{0,0,0,0}
                ,{0,0,0,0}
        })); // 0
        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                 {0,0,0,1}
                ,{0,0,1,0}
                ,{0,1,0,0}
                ,{1,0,0,0}
        })); // 4

        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                 {1,0,0,0}
                ,{0,1,0,0}
                ,{0,0,1,0}
                ,{0,0,0,1}
        })); // 4

        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                 {0,1,1,0}
                ,{0,1,1,0}
                ,{0,0,0,1}
        })); // 3

        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                 {1,1,1,1}
                ,{0,1,1,0}
                ,{0,0,0,1}
        })); // 5

        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                 {1,1,1,1}
                ,{0,0,0,0}
                ,{0,0,0,1}
        })); // 4
        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                 {0,0,1,0}
                ,{0,0,1,0}
                ,{1,0,1,0}
        })); // 3
        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                 {1,1,1,1}
                ,{0,1,1,1}
                ,{0,0,1,1}
        })); // 6

        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                 {0,0,0,0}
                ,{0,1,1,0}
                ,{0,0,0,0}
        })); // 2

        System.out.println(longestLineOfConsecutiveOneExtraMemory(new int[][]{
                 {0,0,0,0}
                ,{0,0,0,0}
                ,{0,0,0,1}
        })); // 1

        System.out.println("----------------------");

        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                {0,0,0,0}
                ,{0,0,0,0}
                ,{0,0,0,0}
        })); // 0
        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                {0,0,0,1}
                ,{0,0,1,0}
                ,{0,1,0,0}
                ,{1,0,0,0}
        })); // 4

        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                {1,0,0,0}
                ,{0,1,0,0}
                ,{0,0,1,0}
                ,{0,0,0,1}
        })); // 4

        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                {0,1,1,0}
                ,{0,1,1,0}
                ,{0,0,0,1}
        })); // 3

        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                {1,1,1,1}
                ,{0,1,1,0}
                ,{0,0,0,1}
        })); // 5

        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                {1,1,1,1}
                ,{0,0,0,0}
                ,{0,0,0,1}
        })); // 4
        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                {0,0,1,0}
                ,{0,0,1,0}
                ,{1,0,1,0}
        })); // 3
        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                {1,1,1,1}
                ,{0,1,1,1}
                ,{0,0,1,1}
        })); // 6

        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                {0,0,0,0}
                ,{0,1,1,0}
                ,{0,0,0,0}
        })); // 2

        System.out.println(longestLineOfConsecutiveOneInLine(new int[][]{
                 {0,0,0,0}
                ,{0,0,0,0}
                ,{0,0,0,1}
        })); // 1
    }

    private static final int longestLineOfConsecutiveOneExtraMemory(final int[][] matrix) {
        if(null == matrix || matrix.length == 0) return 0;
        final int[][] ones = new int[matrix.length][matrix[0].length];
        int longestConsecutiveOnes = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                longestConsecutiveOnes = Math.max(dfsExtraMemory(matrix, ones, i, j), longestConsecutiveOnes);
            }
        }
//        ArrayUtil.print(ones);
        return longestConsecutiveOnes;

    }

    private static final int dfsExtraMemory(final int[][] matrix, final int[][] ones, int i, int j) {
        if(matrix[i][j] == 1)  {
            ones[i][j] += 1; // found one increment the count
            for (final int[] move: MOVES) {
                final int x = i + move[0];
                final int y = j + move[1];
                if(x > -1 && x < matrix.length && y > -1 && y < matrix[x].length) {
                    ones[x][y] = Math.min(ones[i][j], dfsExtraMemory(matrix, ones, x, y));
                }
            }
        }
        return ones[i][j];
    }

    private static final int longestLineOfConsecutiveOneInLine(final int[][] matrix) {
        if(null == matrix || matrix.length == 0) return 0;
        int longestConsecutiveOnes = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                longestConsecutiveOnes = Math.max(dfsInLine(matrix, i, j), longestConsecutiveOnes);
            }
        }
//        ArrayUtil.print(matrix);
        return longestConsecutiveOnes;

    }

    private static final int dfsInLine(final int[][] matrix, int i, int j) {
        if(matrix[i][j] == 1)  {
            int count = 0;
            for (final int[] move: MOVES) {
                final int x = i + move[0];
                final int y = j + move[1];
                if(x > -1 && x < matrix.length && y > -1 && y < matrix[x].length) {
                    count = Math.max(count, dfsInLine(matrix, x, y));
                }
            }
            matrix[i][j] += count;
        }
        return matrix[i][j];
    }
}
