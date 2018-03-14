package net.geekscore.graph.dfs;

import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CycleInDirectedGraph {

    enum State {
        NOT_DISCOVERED,
        DISCOVERED,
        EXPLORED
    };

    public static void main(String[] args) {

        final IGraph<Integer> graphHasCycle = new Graph<>(true);
        graphHasCycle.addEdge(0, 1, 0);
        graphHasCycle.addEdge(1, 2, 0);
        graphHasCycle.addEdge(2, 3, 0);
        graphHasCycle.addEdge(3, 4, 0);
        graphHasCycle.addEdge(4, 5, 0);
        graphHasCycle.addEdge(5, 6, 0);
        graphHasCycle.addEdge(6, 0, 0);

        System.out.println("-----------");
        System.out.println(graphHasCycle);
        System.out.println("Graph has a cycle: "+hasCycle(graphHasCycle));


        System.out.println("-----------");
        final IGraph<Integer> graphHasCycle1 = new Graph<>(true);
        graphHasCycle1.addEdge(0, 1, 0);
        graphHasCycle1.addEdge(1, 2, 0);
        graphHasCycle1.addEdge(2, 0, 0);
        System.out.println(graphHasCycle1);
        System.out.println("Graph has a cycle: "+hasCycle(graphHasCycle1));


        System.out.println("-----------");
        final IGraph<Integer> graphHasCycle2 = new Graph<>(true);
        graphHasCycle2.addEdge(0, 0, 0);
        System.out.println(graphHasCycle2);
        System.out.println("Graph has a cycle: "+hasCycle(graphHasCycle2));


        System.out.println("-----------");
        final IGraph<Integer> graphHasCycle3 = new Graph<>(true);
        graphHasCycle3.addEdge(0, 1, 0);
        graphHasCycle3.addEdge(0, 2, 0);
        graphHasCycle3.addEdge(1, 3, 0);
        graphHasCycle3.addEdge(3, 2, 0);
        graphHasCycle3.addEdge(2, 1, 0);
        System.out.println(graphHasCycle3);
        System.out.println("Graph has a cycle: "+hasCycle(graphHasCycle3));


        System.out.println("-----------");
        final IGraph<Integer> graphHasNoCycle = new Graph<>(true);
        graphHasNoCycle.addEdge(0, 1, 0);
        graphHasNoCycle.addEdge(1, 2, 0);
        graphHasNoCycle.addEdge(1, 3, 0);
        graphHasNoCycle.addEdge(0, 5, 0);
        graphHasNoCycle.addEdge(3, 4, 0);
        graphHasNoCycle.addEdge(5, 2, 0);

        System.out.println(graphHasNoCycle);
        System.out.println("Graph has a cycle: "+hasCycle(graphHasNoCycle));


        System.out.println("-----------");
        final IGraph<Character> graphHasNoCycle1 = new Graph<>(true);
        graphHasNoCycle1.addEdge('a', 'c', 0);
        graphHasNoCycle1.addEdge('b', 'c', 0);
        graphHasNoCycle1.addEdge('b', 'd', 0);
        graphHasNoCycle1.addEdge('c', 'e', 0);
        graphHasNoCycle1.addEdge('d', 'f', 0);
        graphHasNoCycle1.addEdge('e', 'f', 0);
        graphHasNoCycle1.addEdge('e', 'h', 0);
        graphHasNoCycle1.addEdge('f', 'g', 0);


        System.out.println(graphHasNoCycle1);
        System.out.println("Graph has a cycle: "+hasCycle(graphHasNoCycle1));

        System.out.println("-----------");
        final IGraph<Integer> graphHasNoCycle2 = new Graph<>(true);
        graphHasNoCycle2.addEdge(0, 1, 0);
        graphHasNoCycle2.addEdge(0, 2, 0);
        graphHasNoCycle2.addEdge(0, 6, 0);
        graphHasNoCycle2.addEdge(0, 5, 0);
        graphHasNoCycle2.addEdge(5, 3, 0);
        graphHasNoCycle2.addEdge(5, 4, 0);
        graphHasNoCycle2.addEdge(4, 3, 0);
        graphHasNoCycle2.addEdge(6, 4, 0);
        graphHasNoCycle2.addEdge(7, 8, 0);
        graphHasNoCycle2.addEdge(9, 10, 0);
        graphHasNoCycle2.addEdge(9, 11, 0);
        graphHasNoCycle2.addEdge(9, 12, 0);
        graphHasNoCycle2.addEdge(11, 12, 0);

        System.out.println(graphHasNoCycle2);
        System.out.println("Graph has a cycle: "+hasCycle(graphHasNoCycle2));
    }

    private static <T extends Comparable> boolean hasCycle(IGraph<T> graph) {
        final Set<T> vertices = graph.vertices();
        final Map<T, State> states = new HashMap<>(vertices.size());
        for (final T vertex: vertices) states.put(vertex, State.NOT_DISCOVERED); /* Initially, all vertices not discovered */
        for (final T vertex: vertices) if(cycledDFS(vertex, graph, states)) return true;
        return false;
    }

    private static <T extends Comparable> boolean cycledDFS(final T vertex, final IGraph<T> graph, final Map<T, State> states) {
        if(states.get(vertex) == State.NOT_DISCOVERED) {
            states.put(vertex, State.DISCOVERED);
            final List<T> neighbors = graph.neighbors(vertex);
            if (null != neighbors){
                for (T neighbor: neighbors) {
                    final State neighborState = states.get(neighbor);
                    if(neighborState == State.DISCOVERED) return true;  /* Already discovered the neighbor */
                    if(cycledDFS(neighbor, graph, states)) return true; /* The vertex is not discovered yet, lets check if we can find a cycle from here */
                }
            }
            states.put(vertex, State.EXPLORED);
        }
        return false;
    }
}
