package net.geekscore.graph;

import java.util.Arrays;

/**
 *
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
 * Output: 12
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 * The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
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
 * Output: -1
 * Explanation: There is no way for the ball to stop at the destination.
 *
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could
 * assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class TheMaze2 {

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
        System.out.println(shortestDistance(grid, new int[]{0, 4}, new int[]{4, 4})); // 12

        final int[][] grid1 = new int[][] {
                {0,0,1,0,0}
                ,{0,0,0,0,0}
                ,{0,0,0,1,0}
                ,{1,1,0,1,1}
                ,{0,0,0,0,0}
        };
        System.out.println(shortestDistance(grid1, new int[]{0,4}, new int[]{3, 2})); // -1

        final int[][] grid2 = new int[][] {
                {0,0,1,0,0}
                ,{0,0,0,0,0}
                ,{0,0,0,1,0}
                ,{1,1,0,1,1}
                ,{0,0,0,0,0}
        };
        System.out.println(shortestDistance(grid2, new int[]{0,4}, new int[]{1, 2})); // true
    }


    private static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        final boolean[][] visited = new boolean[maze.length][maze[0].length];
        final int[][] distance = new int[maze.length][maze[0].length];
        for (final int[] _distance: distance) {
            Arrays.fill(_distance, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        dfs(maze, start, destination, visited, distance);
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }

    private static void dfs(int[][] maze, int[] start, int[] destination, final boolean[][] visited,  final int[][] distance) {
        visited[start[0]][start[1]] = true;
        if(Arrays.equals(start, destination)) return;
        for (final int[] move: MOVES) {
            int x = start[0];
            int y = start[1];
            int steps = -1;
            while (canRoll(x, y, maze)) { // roll in on direction :P
                steps++;
                x+= move[0];
                y+= move[1];
            }
            x -= move[0]; y -= move[1]; // when loop terminates, we will hit a border or a wall
            if (distance[start[0]][start[1]] + steps < distance[x][y]) {
                distance[x][y] = distance[start[0]][start[1]] + steps;
                if(canRoll(x, y, maze)) dfs(maze, new int[]{x,y}, destination, visited, distance);
            }
        }
    }

    private static boolean canRoll(final int x, final int y, final int[][] maze) {
        return (x >= 0 && x < maze.length && y >= 0 && y < maze[x].length && maze[x][y] != 1);
    }
}
