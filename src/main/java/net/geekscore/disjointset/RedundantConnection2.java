package net.geekscore.disjointset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root)
 * for which all other nodes are descendants of this node, plus every node has exactly one parent,
 * except for the root node which has no parents.
 *
 * The given input is a directed graph that started as a rooted tree with N nodes
 * (with distinct values 1, 2, ..., N), with one additional directed edge added.
 * The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that
 * represents a directed edge connecting nodes u and v, where u is a parent of child v.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 *   1
 *  / \
 * v   v
 * 2-->3
 *
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 *
 *  5 <- 1 -> 2
 *       ^    |
 *       |    v
 *       4 <- 3
 *
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
public class RedundantConnection2 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRedundantDirectedConnection(new int[][]{
                {1,2}
                ,{1,3}
                ,{2,3}
        }))); // [2,3]

        System.out.println(Arrays.toString(findRedundantDirectedConnection(new int[][]{
                {1,2}
                ,{2,3}
                ,{3,4}
                ,{4,1}
                ,{1,5}
        }))); // [4,1]

        System.out.println(Arrays.toString(findRedundantDirectedConnection(new int[][]{
                {2,1}
                ,{3,1}
                ,{4,2}
                ,{1,4}
        }))); // [2,1]
    }

    private static int[] findRedundantDirectedConnection(int[][] edges) {

        final Map<Integer, Integer> parent = new HashMap<>();
        int[] redundantEdge = new int[0];
        for (final int[] edge: edges) {
            makeSet(parent, edge[0]);
            makeSet(parent, edge[1]);
            if(union(parent, edge[0], edge[1])) {
                redundantEdge = edge;
            }
            System.out.println(edge[0]+" -> "+edge[1]+" == "+findSet(parent, edge[0])+" -> "+findSet(parent, edge[1]));
        }
        return redundantEdge;
    }

    private static void makeSet(final Map<Integer, Integer> parent, final Integer vertex) {
       if(!parent.containsKey(vertex)) parent.put(vertex, vertex);
    }

    private static Integer findSet(final Map<Integer, Integer> parent, final Integer vertex) {
        Integer root = parent.get(vertex);
        if(root.compareTo(vertex) == 0) return vertex;
        root = findSet(parent, root);
        return parent.get(root);
    }

    private static boolean union(final Map<Integer, Integer> parent, final Integer vertex1,  final Integer vertex2) {
        final Integer root1 = findSet(parent, vertex1);
        final Integer root2 = findSet(parent, vertex2);
        if(root1.compareTo(root2) != 0) {
            parent.put(parent.get(root1), root2);
            return false;
        }
        return true;
    }
}
