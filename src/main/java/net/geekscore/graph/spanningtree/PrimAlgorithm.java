package net.geekscore.graph.spanningtree;

import net.geekscore.graph.Edge;
import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

import java.util.Collections;
import java.util.List;

public class PrimAlgorithm {


    public static void main(String[] args) {

        final IGraph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 2, 6);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 5);
        graph.addEdge(2, 5, 3);
        graph.addEdge(3, 5, 6);
        graph.addEdge(5, 6, 6);
        graph.addEdge(3, 6, 4);
        graph.addEdge(4, 6, 2);

        System.out.println("------------------");
        System.out.println(graph);
        // [[1,3], [4,6], [2,5], [3,6], [2,3]]
        System.out.println(minSpanningTree(graph));

    }

    private static List<Edge> minSpanningTree(final IGraph<Integer> graph) {
        return Collections.emptyList();
    }
}
