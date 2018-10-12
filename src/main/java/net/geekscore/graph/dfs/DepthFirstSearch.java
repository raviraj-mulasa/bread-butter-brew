package net.geekscore.graph.dfs;

import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

import java.util.*;

public class DepthFirstSearch {

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
        graph.addEdge("a", "a", 0);


        System.out.println("-------");
        System.out.println(graph);


        System.out.println("-------");
        System.out.println("DFS of graph "+dfs(graph));

        System.out.println("-------");
        System.out.println("DFS of graph from 'g' "+dfs("g", graph));
        System.out.println("DFS of graph from 'a' "+dfs("a", graph));

        System.out.println("-------");
        System.out.println("DFS of graph from 'g' "+dfsIterative("g", graph));
        System.out.println("DFS of graph from 'a' "+dfsIterative("a", graph));

    }

    private static final <T extends Comparable> List<T> dfs(IGraph<T> graph){
        final Set<T> visited    = new HashSet<>();
        final List<T> traversed = new LinkedList<>();
        for (final T vertex: graph.vertices()) dfs(vertex, visited, traversed, graph);
        return traversed;
    }


    private static <T extends Comparable> List<T> dfs(T v, IGraph<T> graph){
        final Set<T> visited = new HashSet<>();
        final List<T> traversed = new LinkedList<>();
        dfs(v, visited, traversed, graph);
        return traversed;
    }


    private static <T extends Comparable> void dfs(final T v, final Set<T> visited, final List<T> traversed, final IGraph<T> graph){
        if(!visited.contains(v)) {
            visited.add(v);
            traversed.add(v);
            List<T> neighbors = graph.neighbors(v);
            if(null != neighbors) {
                for (T neighbor: neighbors) dfs(neighbor, visited, traversed, graph);
            }

        }
    }

    private static <T extends Comparable> List<T> dfsIterative(final T v, final IGraph<T> graph){
        final Set<T> visited = new HashSet<>();
        final List<T> traversed = new LinkedList<>();
        final Stack<T> stack = new Stack<>();
        stack.push(v);
        while (!stack.empty()) {
            final T top = stack.pop();
            if (!visited.contains(top)) {
                visited.add(top);
                final List<T> neighbors = graph.neighbors(top);
                if (neighbors != null) {
                    for (final T neighbor : neighbors) {
                        stack.push(neighbor);
                    }
                }
                traversed.add(top);
            }
        }
        return traversed;
    }


}
