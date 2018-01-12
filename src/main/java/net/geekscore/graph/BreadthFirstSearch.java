package net.geekscore.graph;

import java.util.*;

public class BreadthFirstSearch {

    public static void main(String[] args) {
        final IGraph<String> graph = new Graph<>(true);
        graph.addEdge("a","b", 0);
        graph.addEdge("a", "c", 0);
        graph.addEdge("a", "g", 0);
        graph.addEdge("b", "d", 0);
        graph.addEdge("b", "e", 0);
        graph.addEdge("c", "d", 0);
        graph.addEdge("d", "e", 0);
        graph.addEdge("d", "f", 0);
        graph.addEdge("g", "c", 0);
        graph.addEdge("f", "g", 0);


        System.out.println(graph);
        System.out.println("-------");

        System.out.println(graph.neighbors("a"));
        System.out.println(graph.neighbors("e"));
        System.out.println(graph.neighbors("g"));

//        System.out.println("-------");
//        System.out.println(bfs("g", graph));
//        System.out.println(bfs("a", graph));

        System.out.println("-------");
//        System.out.println(bfsIterative("g", graph));
        System.out.println(bfsIterative("a", graph));

    }

    private static List<String> bfs(IGraph graph){
        final List<String> vertices = new LinkedList<>();
        return vertices;
    }


    /**
     *
     * @param v
     * @param graph
     * @return Breath First traversal of a graph starting at v iteratively
     */
    private static List<String> bfsIterative(final String v, final IGraph graph){
        final Set<String> visited = new HashSet<>();
        final List<String> vertices = new LinkedList<>();
        final Deque<String> queue = new LinkedList<>();
        queue.offerFirst(v);
        while (!queue.isEmpty()) {
            System.out.println("Queue "+queue);
            final String vertex = queue.removeLast();
            if (!visited.contains(vertex)) {
                System.out.println("Visiting "+vertex);
                vertices.add(vertex);
                visited.add(vertex);
                final List<String> neighbors = graph.neighbors(vertex);
                if (neighbors != null) {
                    for (String neighbor : neighbors) {
                        System.out.println("Queuing "+neighbor);
                        queue.offerFirst(neighbor);
                    }
                }
            }
        }
        return vertices;
    }
}
