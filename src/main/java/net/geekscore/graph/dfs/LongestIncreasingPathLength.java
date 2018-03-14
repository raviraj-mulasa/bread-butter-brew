package net.geekscore.graph.dfs;

import net.geekscore.array.ArrayUtil;

import java.util.Arrays;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 * nums = [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 *
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 * nums = [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 *
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 *
 */
public class LongestIncreasingPathLength {

    private static final int[][] MOVES = {
            {0,1},
            {0,-1},
            {1,0},
            {-1,0},
    };

    public static void main(String[] args) { // 1,2,6,9
        final int[][] grid = {
                {9,9,4}
                ,{6,6,8}
                ,{2,1,1}
        };
        System.out.println(longestIncreasingPath(grid)); // 4

        final int[][] grid1 = { // 3,4,5,6
                {3,4,5}
                ,{3,2,6}
                ,{2,2,1}
        };
        System.out.println(longestIncreasingPath(grid1)); // 4
    }

    private static final int longestIncreasingPath(final int[][] grid) {

        if(grid == null || grid.length == 0) return 0;

        final int pathLength[][] = new int[grid.length][grid[0].length];
        for (final int[] row: pathLength) {
            Arrays.fill(row, 1); // Every element is of path size 1
        }

        int maxPathLength = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxPathLength = Math.max(maxPathLength, dfs(i,j,grid,pathLength));
            }

        }
        return maxPathLength;
    }

    private static final int dfs(int i, int j, final int[][] grid, final int[][] pathLength) {
        if(pathLength[i][j] == 1) {
            int currMaxPathLength = 0;
            for (final int[] move : MOVES) {
                final int x = i + move[0];
                final int y = j + move[1];
                if (x > -1 && x < grid.length && y > -1 && y < grid[0].length && grid[x][y] > grid[i][j]) {
                    currMaxPathLength = Math.max(currMaxPathLength, dfs(x,y,grid,pathLength));
                }
            }
            pathLength[i][j] += currMaxPathLength;
        }
        return pathLength[i][j];
    }
}
