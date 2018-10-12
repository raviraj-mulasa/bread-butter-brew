package net.geekscore.disjointset;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 *  write a function to find the number of connected components in an undirected graph.
 *
 *  Example 1:
 *  0          3
 *  |          |
 *  1 --- 2    4
 *
 *  Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 *
 *  Example 2:
 *  0           4
 *  |           |
 *  1 --- 2 --- 3
 *
 *  Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 */
public class ConnectedCompUndirGrph {

    public static void main(String[] args) {
        System.out.println(countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}})); // 2
        System.out.println(countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 3},{3, 4}})); // 1
    }

    private static int countComponents(int n, int[][] edges) {
        final int[] parent = new int[n];
        for (int i = 0; i < n; i++) makeSet(parent, i);
        for (final int[] edge: edges) {
            union(parent, edge[0], edge[1]);
        }
        final Set<Integer> connected = new HashSet<>();
        for (int i = 0; i < n; i++) {
            connected.add(findSet(parent, i));
        }
        return connected.size();
    }

    private static void makeSet(final int[] parent, int x) {
        parent[x]  = x;
    }

    private static int findSet(final int[] parent, int x) {
        if(parent[x]  == x) return x;
        parent[x] = findSet(parent, parent[x]); // path compression
        return parent[x];
    }

    private static void union(final int[] parent, int x, int y) {
        final int parentX = findSet(parent, x);
        final int parentY = findSet(parent, y); // union by parent
        if(parentX != parentY) {
            parent[parentY] = parentX;
        }
    }
}
