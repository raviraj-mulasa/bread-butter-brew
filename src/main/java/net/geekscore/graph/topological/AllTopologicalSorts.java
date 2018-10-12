package net.geekscore.graph.topological;

import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

import java.util.*;

public class AllTopologicalSorts {

    public static void main(String[] args) {
        final IGraph<String> graph = new Graph<>(true);
        graph.addEdge("a","c", 0);
        graph.addEdge("b", "c", 0);
        graph.addEdge("b", "d", 0);
        graph.addEdge("c", "e", 0);
        graph.addEdge("d", "f", 0);
        graph.addEdge("e", "f", 0);
        graph.addEdge("e", "h", 0);
        graph.addEdge("f", "g", 0);

        System.out.println("-------");
        System.out.println(graph);
        /*
         * [
         * [a, b, c, d, e, f, g, h]
         * [a, b, c, d, e, f, h, g]
         * [a, b, c, d, e, h, f, g]
         * [a, b, c, e, d, f, g, h]
         * [a, b, c, e, d, f, h, g]
         * [a, b, c, e, d, h, f, g]
         * [a, b, c, e, h, d, f, g]
         * [a, b, d, c, e, f, g, h]
         * [a, b, d, c, e, f, h, g]
         * [a, b, d, c, e, h, f, g]
         * [b, a, c, d, e, f, g, h]
         * [b, a, c, d, e, f, h, g]
         * [b, a, c, d, e, h, f, g]
         * [b, a, c, e, d, f, g, h]
         * [b, a, c, e, d, f, h, g]
         * [b, a, c, e, d, h, f, g]
         * [b, a, c, e, h, d, f, g]
         * [b, a, d, c, e, f, g, h]
         * [b, a, d, c, e, f, h, g]
         * [b, a, d, c, e, h, f, g]
         * [b, d, a, c, e, f, g, h]
         * [b, d, a, c, e, f, h, g]
         * [b, d, a, c, e, h, f, g]
         * ]
         */
        System.out.println("Topological Sorts: "+topologicalSorts(graph));

        final IGraph<String> graph1 = new Graph<>(true);
        graph1.addEdge("a","c", 0);
        graph1.addEdge("b", "c", 0);
        graph1.addEdge("c", "d", 0);
        graph1.addEdge("d", "a", 0);

        System.out.println("-------");
        System.out.println(graph1);
        // [] - Cycle present
        System.out.println("Topological Sorts: "+topologicalSorts(graph1));


        final IGraph<Integer> graph2 = new Graph<>(true);
        graph2.addEdge(5, 2, 0);
        graph2.addEdge(5, 0, 0);
        graph2.addEdge(4, 0, 0);
        graph2.addEdge(4, 1, 0);
        graph2.addEdge(2, 3, 0);
        graph2.addEdge(3, 1, 0);

        System.out.println("-------");
        System.out.println(graph2);
        /*
         * [
         * [4, 5, 0, 2, 3, 1]
         * [4, 5, 2, 0, 3, 1]
         * [4, 5, 2, 3, 0, 1]
         * [4, 5, 2, 3, 1, 0]
         * [5, 2, 3, 4, 0, 1]
         * [5, 2, 3, 4, 1, 0]
         * [5, 2, 4, 0, 3, 1]
         * [5, 2, 4, 3, 0, 1]
         * [5, 2, 4, 3, 1, 0]
         * [5, 4, 0, 2, 3, 1]
         * [5, 4, 2, 0, 3, 1]
         * [5, 4, 2, 3, 0, 1]
         * [5, 4, 2, 3, 1, 0]
         * ]
         */
        System.out.println("Topological Sorts: "+topologicalSorts(graph2));
    }

    private static <T extends Comparable> List<List<T>> topologicalSorts(IGraph<T> graph) {
        if(null == graph || graph.edges() == null || graph.edges().isEmpty()) return Collections.emptyList();

        final List<List<T>> topologicalSorts = new LinkedList<>();

        final Set<T> vertices = graph.vertices();
        final Map<T, Integer> inDegreeMap = new HashMap<>();
        for (final T vertex: vertices) {
            if(!inDegreeMap.containsKey(vertex)) inDegreeMap.put(vertex, 0);
            for (final T neighbor: graph.neighbors(vertex)) {
                inDegreeMap.put(neighbor, inDegreeMap.getOrDefault(neighbor, 0)+1);
            }
        }
        final Set<T> visited = new HashSet<>();
        topologicalSortsHelper(inDegreeMap, graph, topologicalSorts, visited, new LinkedList<T>());
        return topologicalSorts;
    }

    private static <T extends Comparable> void  topologicalSortsHelper(final Map<T, Integer> inDegreeMap, final IGraph<T> graph, final List<List<T>> topologicalSorts, final Set<T> visited, List<T> topologicalSortSoFar) {

        final Set<T> vertices = graph.vertices();
        if(topologicalSortSoFar.size() == vertices.size()) {
            topologicalSorts.add(new ArrayList<>(topologicalSortSoFar));
            return;
        }

        for (final T vertex: vertices) {

            if(inDegreeMap.get(vertex) == 0 && !visited.contains(vertex)) {

                // choose
                visited.add(vertex);
                topologicalSortSoFar.add(vertex);
                for(final T neighbor: graph.neighbors(vertex)) {
                    inDegreeMap.put(neighbor, inDegreeMap.getOrDefault(neighbor, 0)-1);
                }

                // Explore
                topologicalSortsHelper(inDegreeMap, graph, topologicalSorts, visited, topologicalSortSoFar);

                // un choose
                for(final T neighbor: graph.neighbors(vertex)) {
                    inDegreeMap.put(neighbor, inDegreeMap.getOrDefault(neighbor, 0)+1);
                }
                topologicalSortSoFar.remove(topologicalSortSoFar.size()-1);
                visited.remove(vertex);


            }
        }
    }
}
