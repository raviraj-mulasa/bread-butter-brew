package net.geekscore.graph.topological;


import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

import java.util.*;

public class KahnAlgorithmBFS {

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
        // [b, d, a, c, e, h, f, g]
        // [a, b, c, d, e, f, h, g]
        System.out.println("Topological Sort"+topologicalSort(graph));

        final IGraph<String> graph1 = new Graph<>(true);
        graph1.addEdge("a","c", 0);
        graph1.addEdge("b", "c", 0);
        graph1.addEdge("c", "d", 0);
        graph1.addEdge("d", "a", 0);

        System.out.println("-------");
        System.out.println(graph1);
        System.out.println("Topological Sort"+topologicalSort(graph1));
    }

    private static final List<String> topologicalSort(final IGraph<String> graph) {
        if(null == graph || graph.edges() == null || graph.edges().isEmpty()) return Collections.emptyList();


        final Set<String> vertices = graph.vertices();

        final Map<String, Integer> inDegreeMap = new HashMap<>();
        // Build in degree map - O(V+E) time
        for (String vertex: vertices) {
            if(!inDegreeMap.containsKey(vertex)) {
                inDegreeMap.put(vertex, 0);
            }
            for (String neighbor: graph.neighbors(vertex)) {
                inDegreeMap.put(neighbor, inDegreeMap.getOrDefault(neighbor, 0)+1);
            }
        }

        // Enqueue all in degree == 0 vertex
        final Deque<String> queue = new LinkedList<>();
        for (String vertex: inDegreeMap.keySet()) {
            if(inDegreeMap.get(vertex) == 0) queue.offerFirst(vertex);
        }

        List<String> topologicalSort = new LinkedList<>();
        int verticesCovered = 0;
        while (!queue.isEmpty()) {
            final String top = queue.removeLast();
            topologicalSort.add(top);
            for (String neighbor: graph.neighbors(top)) {
                int indegree = inDegreeMap.get(neighbor);
                if(indegree-1 ==0) queue.offerFirst(neighbor);
                inDegreeMap.put(neighbor, indegree-1);
            }
            verticesCovered++;
        }

        if(verticesCovered != vertices.size()) {
            System.out.println("Cycle detected");
            return Collections.emptyList();
        }

        return topologicalSort;

    }


}
