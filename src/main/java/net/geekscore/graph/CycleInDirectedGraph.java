package net.geekscore.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CycleInDirectedGraph {

    enum Color {
        WHITE, // Not Visited
        GRAY, // Visiting
        BLACK // Visited
    };

    public static void main(String[] args) {

        final IGraph<Integer> graphHasCycle = new Graph<>(true);
        graphHasCycle.addEdge(0, 1, 0);
        graphHasCycle.addEdge(1, 2, 0);
        graphHasCycle.addEdge(1, 3, 0);
        graphHasCycle.addEdge(0, 3, 0);
        graphHasCycle.addEdge(3, 0, 0);
        graphHasCycle.addEdge(3, 4, 0);
        graphHasCycle.addEdge(4, 4, 0);

        System.out.println(graphHasCycle);
        System.out.println("-----------");
        System.out.println("Graph has a cycle: "+hasCycle(graphHasCycle));
    }

    private static boolean hasCycle(IGraph<Integer> graph) {
        final Set<Integer> vertices = graph.vertices();
        final Map<Integer, Color> colors = new HashMap<>(vertices.size());
        for (final Integer vertex: vertices) {
//            Initialize colors of all vertices to WHITE-
            colors.put(vertex, Color.WHITE);
        }
        for (final Integer vertex: vertices) {
//            Check dfs traversal from any vertex results in a cycle
            if(colors.get(vertex) == Color.WHITE && detectCycleHelper(vertex, graph, colors)) return true;
        }
        return false;
    }

    private static boolean detectCycleHelper(final Integer vertex, final IGraph<Integer> graph, final Map<Integer, Color> colors) {
        colors.put(vertex, Color.GRAY);
        final List<Integer> neighbors = graph.neighbors(vertex);
        if (null != neighbors){
            for (Integer neighbor: neighbors) {
                if(colors.get(neighbor) == Color.GRAY) {
//                    Visiting this vertex again
                    return true;
                }
                if(colors.get(neighbor) == Color.WHITE && detectCycleHelper(neighbor, graph, colors)) {
//                    this vertex not visited yet, lets check if we can find a cycle from here
                    return true;
                }
            }
            colors.put(vertex, Color.BLACK);
        }
        return false;
    }
}
