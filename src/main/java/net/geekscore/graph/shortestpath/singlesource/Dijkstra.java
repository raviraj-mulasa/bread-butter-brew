package net.geekscore.graph.shortestpath.singlesource;

import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

import java.util.*;

/**
 * Shortest path network.
 *  Directed graph G = (V, E).
 *  Source s, destination t.
 *  Length le = length of edge e.
 *
 * Shortest path problem: find shortest directed path from s to t.
 *
 * Works on BOTH Directed and un-directed graph
 *
 * POSITIVE EDGE WEIGHTS or LENGTHS
 *
 */
public class Dijkstra {

    private class VertexDistance {

    }

    public static void main(String[] args) {

        final IGraph<Integer> graph = new Graph<>(true);
        graph.addEdge(1,2, 16);
        graph.addEdge(1,5, 8);
        graph.addEdge(1,6, 4);
        graph.addEdge(2,3, 2);
        graph.addEdge(4,2, 5);
        graph.addEdge(4,3, 6);
        graph.addEdge(5,2, 7);
        graph.addEdge(5,4, 1);
        graph.addEdge(6,5, 3);

        System.out.println("-------");
        System.out.println(graph);



    }

    private static final Set<Integer> shortestPath(final Integer start, final IGraph<Integer> graph) {

        final Map<Integer, Integer> distances = new HashMap<>();
        for (final Integer vertex: graph.vertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        final Map<Integer, Integer> parent = new HashMap<>();
        for (final Integer vertex: graph.vertices()) {
            distances.put(vertex, null);
        }

        Set<Integer> shortestPath = new HashSet<>();

        distances.put(start, 0); // distance from start to

        return shortestPath;

    }

//    private static final Integer extractMin()
}
