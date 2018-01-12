package net.geekscore.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CycleInUndirectedGraph {

    public static void main(String[] args) {

        final IGraph<Character> graphHasCycle = new Graph<>(false);
        graphHasCycle.addEdge('a','b', 0);
        graphHasCycle.addEdge('a', 'f', 0);
        graphHasCycle.addEdge('b', 'c', 0);
        graphHasCycle.addEdge('b', 'e', 0);
        graphHasCycle.addEdge('d', 'e', 0);
        graphHasCycle.addEdge('e', 'b', 0);
        System.out.println(graphHasCycle);
        System.out.println("Cycle Present "+detectCycle(graphHasCycle));


        final IGraph<Character> graphHasNoCycle = new Graph<>(false);
        graphHasNoCycle.addEdge('a','b', 0);
        graphHasNoCycle.addEdge('a', 'f', 0);
        graphHasNoCycle.addEdge('b', 'c', 0);
        graphHasNoCycle.addEdge('b', 'e', 0);
        graphHasNoCycle.addEdge('d', 'e', 0);
        System.out.println(graphHasNoCycle);
        System.out.println("Cycle Present "+detectCycle(graphHasNoCycle));
    }

    private static boolean detectCycle(IGraph<Character> graph) {
        final Set<Character> vertices = graph.vertices();
        final Set<Character> visited  = new HashSet<>(vertices.size());
        for (Character vertex: vertices) {
            if(!visited.contains(vertex)) {
                if(detectCycleHelper(vertex, graph, visited, null)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean detectCycleHelper(final Character vertex, final IGraph<Character> graph, final Set<Character> visited, final Character parent) {
        visited.add(vertex);
        final List<Character> neighbors = graph.neighbors(vertex);
        if (null != neighbors){
            for (Character neighbor: neighbors) {
                if(!visited.contains(neighbor)){
                    if(detectCycleHelper(neighbor, graph, visited, vertex)) {
                        return true;
                    }
                } else if(neighbor.compareTo(parent) != 0 ) {
                    // If an neighbor is visited and that neighbor is not the parent of current vertex, then we have a cycle
                    return true;
                }
            }
        }
        return false;
    }
}
