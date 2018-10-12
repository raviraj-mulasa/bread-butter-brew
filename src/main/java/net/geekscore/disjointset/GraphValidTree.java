package net.geekscore.disjointset;

import java.util.HashSet;
import java.util.Set;

/**
 *  Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 *  write a function to check whether these edges make up a valid tree.
 *
 *  For example:
 *  Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 *  Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 *
 *  Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 *  [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class GraphValidTree {

    public static void main(String[] args) {
        System.out.println(validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}})); // true
        System.out.println(validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}})); // false
        System.out.println(validTree(4, new int[][]{{0, 1}, {2, 3}})); // false
        System.out.println(validTree(4, new int[][]{{0, 1}, {2, 3}, {1, 2}})); // true
    }

    private static boolean validTree(int n, int[][] edges) {
        final int[] root = new int[n];
        for (int i = 0; i < n; i++) makeSet(root, i); // makeSet

        for (final int[] edge: edges) {
            if(union(root, edge[0], edge[1])) { // Cycle present
                return false;
            }
        }
        final Set<Integer> connected = new HashSet<>();
        for (int i = 0; i < n; i++) connected.add(findSet(root, i));
        return connected.size() == 1; // If all nodes connected , then we have one connected component
    }

    private static void makeSet(final int[] parent, int x) {
        parent[x]  = x;
    }

    private static int findSet(final int[] root, final int node) {
        if(root[node] == node) return node;
        root[node] = findSet(root, root[node]);
        return root[node];
    }

    private static boolean union(final int[] root, final int node1, final int node2) {
        final int root1 = findSet(root, node1);
        final int root2 = findSet(root, node2);
        if(root1 != root2) {
            root[root2] = root1;
            return false;
        }
        return true;// already merged or union performed as they point ot same root, cycle
    }
}
