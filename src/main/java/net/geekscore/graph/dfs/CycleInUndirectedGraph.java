package net.geekscore.graph.dfs;

import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

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
        graphHasCycle.addEdge('f', 'e', 0);
        System.out.println("------------------");
        System.out.println(graphHasCycle);
        System.out.println("Cycle Present "+detectCycle(graphHasCycle));

        final IGraph<Character> graphHasCycle1 = new Graph<>(false);
        graphHasCycle1.addEdge('a','a', 0);
        System.out.println("------------------");
        System.out.println(graphHasCycle1);
        System.out.println("Cycle Present "+detectCycle(graphHasCycle1));

        final IGraph<Character> graphHasCycle2 = new Graph<>(false);
        graphHasCycle2.addEdge('a','b', 0);
        graphHasCycle2.addEdge('b','a', 0);
        System.out.println("------------------");
        System.out.println(graphHasCycle2);
        System.out.println("Cycle Present "+detectCycle(graphHasCycle2));

        final IGraph<Character> graphHasCycle3 = new Graph<>(false);
        graphHasCycle3.addEdge('a','b', 0);
        graphHasCycle3.addEdge('a', 'f', 0);
        graphHasCycle3.addEdge('b', 'c', 0);
        graphHasCycle3.addEdge('b', 'e', 0);
        graphHasCycle3.addEdge('d', 'e', 0);
        graphHasCycle3.addEdge('d', 'a', 0);
        System.out.println("------------------");
        System.out.println(graphHasCycle3);
        System.out.println("Cycle Present "+detectCycle(graphHasCycle3));

        System.out.println("--- ********** ---");

        final IGraph<Character> graphHasNoCycle = new Graph<>(false);
        graphHasNoCycle.addEdge('a','b', 0);
        graphHasNoCycle.addEdge('a', 'f', 0);
        graphHasNoCycle.addEdge('b', 'c', 0);
        graphHasNoCycle.addEdge('b', 'e', 0);
        graphHasNoCycle.addEdge('d', 'e', 0);
        System.out.println("------------------");
        System.out.println(graphHasNoCycle);
        System.out.println("Cycle Present "+detectCycle(graphHasNoCycle));

        final IGraph<Character> graphHasNoCycle1 = new Graph<>(false);
        graphHasNoCycle1.addEdge('a','b', 0);
        graphHasNoCycle1.addEdge('c', 'd', 0);
        graphHasNoCycle1.addEdge('e', 'f', 0);
        System.out.println("------------------");
        System.out.println(graphHasNoCycle1);
        System.out.println("Cycle Present "+detectCycle(graphHasNoCycle1));


    }

    private static boolean detectCycle(IGraph<Character> graph) {
        final Set<Character> vertices = graph.vertices();
        final Set<Character> visited  = new HashSet<>(vertices.size());
        for (Character vertex: vertices) {
            if(!visited.contains(vertex) && dfs(vertex, graph, visited, null)) return true;
        }
        return false;
    }

    private static boolean dfs(final Character vertex, final IGraph<Character> graph, final Set<Character> visited, final Character parent) {
//        System.out.println("Exploring "+vertex);
        if(visited.contains(vertex)) return true;
        visited.add(vertex);
        final List<Character> neighbors = graph.neighbors(vertex);
        if (null != neighbors){
            for (Character neighbor: neighbors) {
                if(!visited.contains(neighbor)){
                    if(dfs(neighbor, graph, visited, vertex)) return true;
                }
                else if (neighbor != parent) return true;
            }
        }
        return false;

    }
}
