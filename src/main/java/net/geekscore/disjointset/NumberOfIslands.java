package net.geekscore.disjointset;

import java.util.*;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 *
 * You may assume all four edges of the grid are all surrounded by water.
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 *
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */

public class NumberOfIslands {

    private static final int[][] MOVES = new int[][]{
             {-1, 0}
            ,{1, 0}
            ,{0, 1}
            ,{0, -1}
    };

    public static void main(String[] args) {
        final char[][] grid = new char[][] {
                {'1', '1', '1', '1', '0'}
                ,{'1', '1', '0', '1', '0'}
                ,{'1', '1', '0', '0', '0'}
                ,{'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(grid)); // 1

        final char[][] grid1 = new char[][] {
                {'1', '1', '0', '0', '0'}
                ,{'1', '1', '0', '0', '0'}
                ,{'0', '0', '1', '0', '0'}
                ,{'0', '0', '0', '1', '1'}

        };
        System.out.println(numIslands(grid1)); // 3

        final char[][] grid2 = new char[][] {
                {'1', '0', '0', '1', '0'}
                ,{'0', '0', '1', '0', '1'}
                ,{'1', '0', '0', '0', '0'}
                ,{'0', '1', '0', '1', '0'}

        };
        System.out.println(numIslands(grid2)); // 7
    }

    private static final int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        final int m = grid.length, n = grid[0].length;

        final int[] parent = new int[m * n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    final int idx = i * n + j;
                    if(parent[idx] == -1) parent[idx] = idx; /* makeSet */
                    for (final int[] move: MOVES) {
                        final int x = i + move[0];
                        final int y = j + move[1];
                        if(x < 0 || x >= m || y < 0 || y >= grid[x].length) continue;
                        if(grid[x][y] == '1') {
                            final int movIdx = x*n+y;
                            if(parent[movIdx] == -1) parent[movIdx] = movIdx; /* makeSet */
                            union(parent, idx, movIdx);
                        }
                    }
                }
            }
        }
        final Set<Integer> islands = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    islands.add(findSet(parent, i * n + j));
                }
            }
        }
        return islands.size();

    }

    private static void union(int[] parent, int a, int b) {
        final int parentA = findSet(parent, a);
        final int parentB = findSet(parent, b);
        if(parentA != parentB) { // union
            parent[parentB] = parentA;
        }
    }

    private static int findSet(int[] parent, int a) {
        if(parent[a] == a) return a;
        parent[a] = findSet(parent, parent[a]); // path compression
        return parent[a];
    }


}
