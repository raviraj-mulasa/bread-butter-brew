package net.geekscore.graph.bfs;

import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

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

        System.out.println("-------");
        System.out.println(graph);
        System.out.println("BFS from 'g' "+ bfs("g", graph));
        System.out.println("BFS from 'a' "+ bfs("a", graph));


        final IGraph<String> graph1 = new Graph<>(false);
        graph1.addEdge("a","b", 0);
        graph1.addEdge("a", "d", 0);
        graph1.addEdge("a", "e", 0);
        graph1.addEdge("b", "e", 0);
        graph1.addEdge("b", "d", 0);
        graph1.addEdge("d", "e", 0);
        graph1.addEdge("a", "c", 0);
        graph1.addEdge("c", "f", 0);
        graph1.addEdge("c", "g", 0);
        graph1.addEdge("f", "g", 0);

        System.out.println("-------");
        System.out.println(graph1);
        System.out.println("BFS from 'g' "+ bfs("g", graph1));
        System.out.println("BFS from 'a' "+ bfs("a", graph1));

    }


    private static final <T extends Comparable> List<T> bfs(final T v, final IGraph<T> graph){
        final Set<T> visited = new HashSet<>();
        final List<T> traversal = new LinkedList<>();
        final Deque<T> queue = new LinkedList<>();
        queue.offerFirst(v);
        while (!queue.isEmpty()) {
            final T vertex = queue.removeLast();
            if (!visited.contains(vertex)) {
                traversal.add(vertex);
                visited.add(vertex);
                final List<T> neighbors = graph.neighbors(vertex);
                if (neighbors != null) {
                    for (final T neighbor : neighbors) {
                        queue.offerFirst(neighbor);
                    }
                }
            }
        }
        return traversal;
    }
}
