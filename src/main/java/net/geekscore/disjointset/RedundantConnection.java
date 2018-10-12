package net.geekscore.disjointset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional edge added. The added edge has two different vertices chosen from 1 to N, and
 * was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with
 * u < v, that represents an undirected edge connecting nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 * The answer edge [u, v] should be in the same format, with u < v.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *  1
 * / \
 *2 - 3
 *
 *
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 *  5 - 1 - 2
 *      |   |
 *      4 - 3
 *
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
public class RedundantConnection {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRedundantConnection(new int[][]{
                {1,2}
                ,{1,3}
                ,{2,3}
        }))); // [2,3]

        System.out.println(Arrays.toString(findRedundantConnection(new int[][]{
                {1,2}
                ,{2,3}
                ,{3,4}
                ,{1,4}
                ,{1,5}
        }))); // [1,4]
    }

    private static int[] findRedundantConnection(int[][] edges) {

        final Map<Integer, Integer> parent = new HashMap<>();
        int[] redundantEdge = new int[0];
        for (final int[] edge: edges) {
            makeSet(parent, edge[0]);
            makeSet(parent, edge[1]);
            if(union(parent, edge[0], edge[1])) {
                redundantEdge = edge;
            }
        }
        return redundantEdge;

    }

    private static void makeSet(final Map<Integer, Integer> parent, Integer vertex) {
        if(!parent.containsKey(vertex)) parent.put(vertex, vertex);
    }

    private static Integer findSet(final Map<Integer, Integer> parent, Integer vertex) {
        Integer root = parent.get(vertex);
        if(vertex.compareTo(root) == 0) return vertex;
        root = findSet(parent, root);
        return parent.get(root);
    }

    private static boolean union(final Map<Integer, Integer> parent, final Integer vertex1, final Integer vertex2) {
        final Integer root1 = findSet(parent, vertex1);
        final Integer root2 = findSet(parent, vertex2);
        if(root1.compareTo(root2) != 0) {
            parent.put(parent.get(root2), root1);
            return false;
        }
        return true; // redundant
    }
}
