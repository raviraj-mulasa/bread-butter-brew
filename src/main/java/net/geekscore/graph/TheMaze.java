package net.geekscore.graph;

import java.util.Arrays;

/**
 * There is a ball in a maze with empty spaces and walls.
 * The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling
 * until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at
 * the destination.
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
 * You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column indexes.
 *
 * Example 1
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 *
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 * Example 2
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 *
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination.
 *
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could
 * assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

 */
public class TheMaze {

    private static final int[][] MOVES = new int[][] {
            {-1,0}
            ,{1,0}
            ,{0,-1}
            ,{0,1}
    };

    public static void main(String[] args) {
        final int[][] grid = new int[][] {
                {0,0,1,0,0}
                ,{0,0,0,0,0}
                ,{0,0,0,1,0}
                ,{1,1,0,1,1}
                ,{0,0,0,0,0}
        };
        System.out.println(hasPath(grid, new int[]{0, 4}, new int[]{4, 4})); // true

        final int[][] grid1 = new int[][] {
                {0,0,1,0,0}
                ,{0,0,0,0,0}
                ,{0,0,0,1,0}
                ,{1,1,0,1,1}
                ,{0,0,0,0,0}
        };
        System.out.println(hasPath(grid1, new int[]{0,4}, new int[]{3, 2})); // false

        final int[][] grid2 = new int[][] {
                 {0,0,1,0,0}
                ,{0,0,0,0,0}
                ,{0,0,0,1,0}
                ,{1,1,0,1,1}
                ,{0,0,0,0,0}
        };
        System.out.println(hasPath(grid2, new int[]{0,4}, new int[]{1, 2})); // true
    }


    private static final boolean hasPath(int[][] maze, int[] start, int[] destination) {
        final boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }

    private static final boolean dfs(int[][] maze, int[] start, int[] destination, final boolean[][] visited) {
        visited[start[0]][start[1]] = true;
        if(Arrays.equals(start, destination)) return true;
        for (final int[] move: MOVES) {
            int x = start[0];
            int y = start[1];
            while (canRoll(x, y, maze)) { // roll in on direction :P
                x+= move[0];
                y+= move[1];
            }
            x -= move[0]; y -= move[1]; // when loop terminates, we will hit a border or a wall
            if(canRoll(x,y,maze) && !visited[x][y]) {
                if(dfs(maze, new int[]{x, y}, destination, visited)) return true;
            }
        }
        return false;
    }

    private static boolean canRoll(final int x, final int y, final int[][] maze) {
        return (x >= 0 && x < maze.length && y >= 0 && y < maze[x].length && maze[x][y] != 1);
    }

}
