package net.geekscore.graph;

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


        System.out.println(graph);
        System.out.println("-------");

        System.out.println("Neighbors of a "+graph.neighbors("a"));
        System.out.println("Neighbors of e "+graph.neighbors("e"));
        System.out.println("Neighbors of g "+graph.neighbors("g"));
        System.out.println("-------");

        System.out.println("DFS of graph "+dfs(graph));
        System.out.println("-------");

        System.out.println("DFS of graph from 'g' "+dfs("g", graph));
        System.out.println("DFS of graph from 'a' "+dfs("a", graph));
        System.out.println("-------");

        System.out.println("DFS of graph from 'g' "+dfsIterative("g", graph));
        System.out.println("DFS of graph from 'a' "+dfsIterative("a", graph));

    }

    private static List<String> dfs(IGraph graph){
        final Set<String> vertices = graph.vertices();
        final Set<String> visited = new HashSet<>(vertices.size());
        final List<String> traversedList = new LinkedList<>();
        for (final String vertex: vertices) {
            if(!visited.contains(vertex)){
                dfs(vertex, visited, traversedList, graph);
            }
        }
        return traversedList;
    }

    /**
     *
     * @param v
     * @param graph
     * @return Depth First traversal of a graph starting at v recursively
     */
    private static List<String> dfs(String v, IGraph graph){
        final Set<String> visited = new HashSet<>();
        final List<String> vertices = new LinkedList<>();
        dfs(v, visited, vertices, graph);
        return vertices;
    }

//    Recursion
    private static void dfs(final String v, final Set<String> visited, List<String> traversedList, final IGraph graph){
        traversedList.add(v);
        visited.add(v);
        List<String> neighbors = graph.neighbors(v);
        if(null != neighbors) {
            for (String neighbor: neighbors) {
                if(!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    dfs(neighbor, visited, traversedList, graph);
                }
            }
        }
    }

    /**
     *
     * @param v
     * @param graph
     * @return Depth First traversal of a graph starting at v iteratively
     */
    private static List<String> dfsIterative(final String v, final IGraph graph){
        final Set<String> visited = new HashSet<>();
        final List<String> vertices = new LinkedList<>();
        final Stack<String> stack = new Stack<>();
        stack.push(v);
        while (!stack.empty()) {
//            System.out.println("Stack "+stack);
            final String vertex = stack.pop();
            if (!visited.contains(vertex)) {
//                System.out.println("Visiting "+vertex);
                vertices.add(vertex);
                visited.add(vertex);
                final List<String> neighbors = graph.neighbors(vertex);
                if (neighbors != null) {
                    for (String neighbor : neighbors) {
//                        System.out.println("Pushing "+neighbor);
                        stack.push(neighbor);
                    }
                }
            }
        }
        return vertices;
    }


}
