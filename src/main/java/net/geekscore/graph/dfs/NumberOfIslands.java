package net.geekscore.graph.dfs;

/**
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * Example 1:
 *  11110
 *  11010
 *  11000
 *  00000
 * Answer: 1
 *
 * Example 2:
 *  11000
 *  11000
 *  00100
 *  00011
 * Answer: 3
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        final int[][] grid1 = {
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,0},
                {0,0,0,0,0},
        };
        final int[][] grid2 = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1},
        };
        System.out.println(islands(grid1)); // 1
        System.out.println(islands(grid2)); // 3
        System.out.println(islands(new int[0][0])); // 0
        System.out.println(islands(null)); // 0

        System.out.println("----------------"); // 0

        System.out.println(islandsShort(grid1)); // 1
        System.out.println(islandsShort(grid2)); // 3
        System.out.println(islandsShort(new int[0][0])); // 0
        System.out.println(islandsShort(null)); // 0

    }


    private static final int islands(final int[][] grid) {
        if(null == grid || 0 == grid.length) {
            return 0;
        }
        final int rows = grid.length;
        final int cols = grid[0].length;
        final boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            visited[i] = new boolean[cols];
        }
        int islands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    visited[i][j] = true;
                    islands++;
                    dfs(grid, visited, i, j);
                }
            }
        }
        return islands;
    }



    private static final void dfs(final int[][] grid, final boolean [][] visited, final int x, final int y) {
        final int rows = grid.length;
        final int cols = grid[0].length;
        if((x-1) >= 0 && !visited[x-1][y] && grid[x-1][y] == 1) {
            visited[x-1][y] = true;
            dfs(grid, visited, x-1, y); // Vertical up
        }
        if((x+1) < rows && !visited[x+1][y] && grid[x+1][y] == 1) {
            visited[x+1][y] = true;
            dfs(grid, visited, x+1, y); // Vertical down
        }
        if((y-1) >= 0 && !visited[x][y-1] && grid[x][y-1] == 1) {
            visited[x][y-1] = true;
            dfs(grid, visited, x, y-1); // Horizontal left
        }
        if((y+1) < cols && !visited[x][y+1] && grid[x][y+1] == 1) {
            visited[x][y+1] = true;
            dfs(grid, visited, x, y+1); // Horizontal right
        }
    }



    private static final int islandsShort(final int[][] grid) {
        if(null == grid || 0 == grid.length) {
            return 0;
        }
        final int rows = grid.length;
        final int cols = grid[0].length;
        int islands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] == 1) {
                    islands++;
                    dfs(grid, i, j);
                }
            }
        }
        return islands;
    }


    private static final void dfs(final int[][] grid, final int x, final int y) {
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) return;
        grid[x][y] = 0;
        dfs(grid, x-1, y); // Vertical up
        dfs(grid,  x+1, y); // Vertical down
        dfs(grid, x, y-1); // Horizontal left
        dfs(grid, x, y+1); // Horizontal right
    }
}
