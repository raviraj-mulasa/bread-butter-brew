package net.geekscore.graph.spanningtree;

import net.geekscore.graph.Edge;
import net.geekscore.graph.EdgeWeightComparator;
import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

import java.util.*;

/**
 *
 */
public final class KruskalAlgorithm {

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
        final Set<Integer> vertices = graph.vertices();
        final Map<Integer, Integer> parent = new HashMap<>();
        for (final Integer vertex: vertices) {
            makeSet(parent, vertex);
        }
        final List<Edge> edges = graph.edges();
        Collections.sort(edges, new EdgeWeightComparator());
        final List<Edge> result = new LinkedList<>();
        for (final Edge edge: edges) {
            final Integer parentTo = findSet(parent, (Integer)edge.to().getValue());
            final Integer parentFrom = findSet(parent, (Integer)edge.from().getValue());
            if(parentTo.compareTo(parentFrom) != 0) { // Union by parent
                parent.put(parent.get(parentFrom), parentTo);
                result.add(edge);
            }
        }
        return result;
    }

    private static void makeSet(final Map<Integer, Integer> parent, Integer vertex) {
        parent.put(vertex, vertex);
    }

    private static int findSet(final Map<Integer, Integer> parent, Integer vertex) {
        final Integer root = parent.get(vertex);
        if(vertex.compareTo(root) == 0) return vertex;
        parent.put(root, findSet(parent, root));
        return parent.get(root);
    }

}
