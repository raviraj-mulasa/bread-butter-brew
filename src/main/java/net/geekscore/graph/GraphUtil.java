package net.geekscore.graph;


/**
 * Created by ravirajmulasa on 9/7/16.
 */
public final class GraphUtil {

    public static void main(String[] args) {

        final IGraph<Integer> graph = new Graph<>();
        graph.addEdge(1,2, 0);
        graph.addEdge(2, 3, 0);
        graph.addEdge(3, 1, 0);
        graph.addEdge(3, 4, 0);
        graph.addVertex(5);
        System.out.println(graph);
        System.out.println("-------");
        System.out.println(graph.adjacent(3,5));
        System.out.println(graph.adjacent(3,3));
        System.out.println(graph.adjacent(3,2));
        System.out.println(graph.adjacent(3,4));
        System.out.println("-------");
        graph.setEdgeWeight(3,4, 100);
        System.out.println(graph);
        System.out.println("-------");
        System.out.println(graph.getEdgeWeight(3,4));
        System.out.println(graph.getEdgeWeight(4,3));
        System.out.println("-------");
        graph.setVertexValue(5, 500);
        System.out.println(graph);

        System.out.println("====== **** Directed Graph **** ======");

        final IGraph<Integer> graph1 = new Graph<>(true);
        graph1.addEdge(1,2, 0);
        graph1.addEdge(2, 3, 0);
        graph1.addEdge(3, 1, 0);
        graph1.addEdge(3, 4, 0);
        graph1.addVertex(5);
        System.out.println(graph1);
        System.out.println("-------");
        System.out.println(graph1.adjacent(3,5));
        System.out.println(graph1.adjacent(3,3));
        System.out.println(graph1.adjacent(3,2));
        System.out.println(graph1.adjacent(3,4));
        System.out.println("-------");
        graph1.setEdgeWeight(3,4, 100);
        System.out.println(graph1);
        System.out.println("-------");
        System.out.println(graph1.getEdgeWeight(3,4));
//        System.out.println(graph1.getEdgeWeight(4,3));
        System.out.println("-------");
        graph1.setVertexValue(5, 500);
        System.out.println(graph1);
    }
}
