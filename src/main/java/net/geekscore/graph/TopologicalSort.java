package net.geekscore.graph;

import java.util.*;

public class TopologicalSort {

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

        System.out.println(graph);
        System.out.println("-------");

        System.out.println("Neighbors of a "+graph.neighbors("a"));
        System.out.println("Neighbors of b "+graph.neighbors("b"));
        System.out.println("Neighbors of f "+graph.neighbors("f"));
        System.out.println("-------");

        System.out.println("Topological Sort"+topologicalSort(graph));

    }

    private static final List<String> topologicalSort(final IGraph graph){
        final Set<String> vertices = graph.vertices();
        final Set<String> visited = new HashSet<>(vertices.size());
        final Stack<String> stack = new Stack<>();
        for (final String vertex: vertices) {
            if(!visited.contains(vertex)) {
                topologicalSortHelper(vertex, graph, stack, visited);
            }
        }
        Collections.reverse(stack);
        return stack;
    }

    private static final void topologicalSortHelper(final String vertex, final IGraph graph, final Stack<String> stack, final Set<String> visited){
       visited.add(vertex);
       final List<String> neighbors = graph.neighbors(vertex);
       if(null != neighbors || !neighbors.isEmpty()){
           for (final String neighbor:neighbors) {
               if(!visited.contains(neighbor)) {
                   topologicalSortHelper(neighbor, graph, stack, visited);
               }
           }
       }
       stack.push(vertex);
    }

}
