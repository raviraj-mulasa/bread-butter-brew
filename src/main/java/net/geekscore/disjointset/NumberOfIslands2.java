package net.geekscore.disjointset;

import java.util.*;

/**
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 * 0 0 0
 * 0 0 0
 * 0 0 0
 *
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 *
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 *
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 *
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 *
 * We return the result as an array: [1, 1, 2, 3]
 *
 * Challenge: Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class NumberOfIslands2 {

    public static void main(String[] args) {
        // [1,1,2,3]
        System.out.println(numIslands2(3, 3, new int[][]{{0,0}, {0,1}, {1,2}, {2,1}} ));
        // [1,2,1]
        System.out.println(numIslands2(3, 3, new int[][]{{0,0}, {1,1}, {0,1}} ));
        // [1,2,3,4,3,2,1]
        System.out.println(numIslands2(3, 3, new int[][]{{0,1}, {1,2}, {2,1}, {1,0}, {0,2}, {0,0}, {1,1}} ));
    }

    private static List<Integer> numIslands2(int m, int n, int[][] positions) {

        if(positions == null || positions.length == 0) return Collections.emptyList();

        final int[] parent = new int[m*n];
        Arrays.fill(parent, -1);
        final Set<Integer> positionSet = new HashSet<>();
        for (final int[] position: positions) {
            positionSet.add(position[0] * n + position[1]);
            parent[position[0] * n + position[1]] = position[0] * n + position[1];
        }

        final int[][] dirs = new int[][] {
                {-1,0}
                ,{1,0}
                ,{0,-1}
                ,{0,1}
        };
        final List<Integer> islandsCount = new LinkedList<>();

        for (final int[] position: positions) {

            final int idx = position[0] * n + position[1];
//            if(parent[idx] == -1) parent[idx] = idx;
//            parent[idx] = idx;

            int count = islandsCount.isEmpty() ? 1 : islandsCount.get(islandsCount.size()-1)+1;

            for (final int[] dir: dirs) {

                final int x = position[0] + dir[0];
                final int y = position[1] + dir[1];

                if(x < 0 || x >= m || y < 0 || y >=n || parent[x*n+y] == -1) continue;

                final int movIdx = x*n+y;

                final int root1 = findSet(parent, movIdx);
                final int root2 = parent[idx];
                if(root1 == root2) count--;
                parent[root1] = root2; // union here
            }
            islandsCount.add(count);

        }
        return islandsCount;

    }


    private static int findSet(int[] parent, int a) {
        if(parent[a] == a) return a;
        parent[a] = findSet(parent, parent[a]);
        return parent[a];
    }
}
