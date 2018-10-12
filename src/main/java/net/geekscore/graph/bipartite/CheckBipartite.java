package net.geekscore.graph.bipartite;

import net.geekscore.graph.Graph;
import net.geekscore.graph.IGraph;

import java.util.*;

/**
 * A graph G is bipartite if there are subsets R and B of the vertex set V such that
 * (a) R ∩ B = ∅;
 * (b) R ∪ B = V ;
 * (c) R != ∅ and B != ∅;
 * (d) Every edge in G is incident to one vertex in R and one vertex in B
 *
 * So a graph is bipartite if you can separate the vertices into a red team and a blue team so that
 * there are no edges between teammates. There may be more than one way to do this.
 *
 * If G is a bipartite graph with a positive number of edges, then G is 2-colorable.
 * If G is bipartite with no edges, it is 1-colorable.
 *
 * A bipartite graph contains no odd cycles.
 * Any graph that contains no odd cycles is necessarily bipartite, as well.
 *
 * Every subgraph H of a bipartite graph G is, itself, bipartite.
 *
 * If G is a bipartite graph and the bipartition of G is X and Y , then
 * v∈X deg(v) =  v∈Y deg(v)
 *
 * Given a bipartite graph G with bipartition X and Y , if |X| != |Y | then
 * there does not exist a perfect matching for G.
 *
 *
 * ---------
 * Matching M in G is a set of edges such that no two edges share a common vertex.
 * Another way to say this is that the set of edges must be pairwise non-adjacent.

 * A MAXIMAL Matching  in G is a matching in G which contains the largest possible number of edges.
 *
 * An AUGMENTING path is a simple path - a path that does not contain cycles - through the graph using only
 * edges with positive capacity from the source to the sink.
 *
 * A matching M in G is a maximum matching if and only if G contains no M-augmenting path.
 *
 * A PERFECT Matching  is a matching where the edges in M are incident on each vertex in V(G).
 * Notice that a perfect matching is also a maximal matching, but a maximal matching need not be
 * a perfect matching
 *
 *  A perfect matching exists on a bipartite graph G with bipartition X and Y if and only
 *  if for every subset S of X we have |S| ≤ |N(S)| and |X| = |Y |.
 *  That is, if for every subset S of X, the number of elements in S is less than or equal to the
 *  number of elements in the neighborhood of S.
 * ----------
 *
 *
 *
 *
 */
public class CheckBipartite {

    enum Color {
        RED,
        BLUE,
        ;
    }

    public static void main(String[] args) {

        final IGraph<Integer> bipartite = new Graph<>(false);
        bipartite.addEdge(1, 11, 0);
        bipartite.addEdge(1, 33, 0);
        bipartite.addEdge(2, 11, 0);
        bipartite.addEdge(2, 44, 0);
        bipartite.addEdge(3, 22, 0);
        bipartite.addEdge(4, 22, 0);
        bipartite.addEdge(5, 33, 0);
        System.out.println("------------------");
        System.out.println(bipartite);
        System.out.println("Is Bipartite: "+ isBipartiteDFS(bipartite));
        System.out.println("Is Bipartite: "+ isBipartiteBFS(bipartite));


        final IGraph<Character> bipartite1 = new Graph<>(false); // Even cycle - so Bipartite
        bipartite1.addEdge('a', 'b', 0);
        bipartite1.addEdge('b', 'c', 0);
        bipartite1.addEdge('c', 'd', 0);
        bipartite1.addEdge('d', 'e', 0);
        bipartite1.addEdge('e', 'f', 0);
        bipartite1.addEdge('f', 'a', 0);
        System.out.println("------------------");
        System.out.println(bipartite1);
        System.out.println("Is Bipartite: "+ isBipartiteDFS(bipartite1));
        System.out.println("Is Bipartite: "+ isBipartiteBFS(bipartite1));

        final IGraph<Character> notBipartite = new Graph<>(false); // Odd cycle - NOT Bipartite
        notBipartite.addEdge('a', 'b', 0);
        notBipartite.addEdge('b', 'c', 0);
        notBipartite.addEdge('c', 'd', 0);
        notBipartite.addEdge('d', 'e', 0);
        notBipartite.addEdge('e', 'a', 0);

        System.out.println("------------------");
        System.out.println(notBipartite);
        System.out.println("Is Bipartite: "+ isBipartiteDFS(notBipartite));
        System.out.println("Is Bipartite: "+ isBipartiteBFS(notBipartite));

        final IGraph<Character> notBipartite1 = new Graph<>(false); // Odd cycle - NOT Bipartite
        notBipartite1.addEdge('a', 'b', 0);
        notBipartite1.addEdge('c', 'b', 0);
        notBipartite1.addEdge('d', 'b', 0);
        notBipartite1.addEdge('c', 'd', 0);

        System.out.println("------------------");
        System.out.println(notBipartite1);
        System.out.println("Is Bipartite: "+ isBipartiteDFS(notBipartite1));
        System.out.println("Is Bipartite: "+ isBipartiteBFS(notBipartite1));


        final IGraph<Character> notBipartite2 = new Graph<>(false); // Loop
        notBipartite2.addEdge('a', 'a', 0);

        System.out.println("------------------");
        System.out.println(notBipartite2);
        System.out.println("Is Bipartite: "+ isBipartiteDFS(notBipartite2));
        System.out.println("Is Bipartite: "+ isBipartiteBFS(notBipartite2));
    }


    private static <T extends Comparable> boolean isBipartiteDFS(final IGraph<T> graph) {
        if(null == graph || graph.vertices() == null || graph.vertices().isEmpty())  return true;
        final Set<T> vertices = graph.vertices();
        Map<T, Color> vertexColorMap = new HashMap<>();
        for (final T vertex: vertices) {
            // start coloring with BLUE
            if(isNotBipartiteDFSHelper(vertex, graph, vertexColorMap, Color.BLUE)) return false;
        }
        return true;
    }

    private static <T extends Comparable> boolean isNotBipartiteDFSHelper(final T vertex, final IGraph<T> graph, Map<T, Color> vertexColorMap, Color current) {
        if(!vertexColorMap.containsKey(vertex)) {
            vertexColorMap.put(vertex, current);
            for (final T neighbor : graph.neighbors(vertex)) {
                if(vertex==neighbor) return true; // Loop
                if(vertexColorMap.get(vertex) == vertexColorMap.get(neighbor)) return true;
                Color next = current == Color.BLUE ? Color.RED : Color.BLUE;
                if (isNotBipartiteDFSHelper(neighbor, graph, vertexColorMap, next)) return true;
            }
        }
        return false;
    }

    private static <T extends Comparable> boolean isBipartiteBFS(final IGraph<T> graph) {
        if(null == graph || graph.vertices() == null || graph.vertices().isEmpty())  return true;
        final Set<T> vertices = graph.vertices();
        Map<T, Color> vertexColorMap = new HashMap<>();
        for (final T vertex: vertices) {
            if(isNotBipartiteBFSHelper(vertex, graph, vertexColorMap)) return false;
        }
        return true;
    }

    private static <T extends Comparable> boolean isNotBipartiteBFSHelper(final T vertex, final IGraph<T> graph, Map<T, Color> vertexColorMap) {
        final Deque<T> queue = new LinkedList<>();
        if(!vertexColorMap.containsKey(vertex)) {
            // not assigned color yet, start coloring with BLUE
            vertexColorMap.put(vertex, Color.BLUE);
            queue.offerFirst(vertex);
        }

        while (!queue.isEmpty()) {
            final T top = queue.removeLast();
            final Color topColor = vertexColorMap.get(top);
            final Color neighborColor = topColor == Color.RED ? Color.BLUE : Color.RED;
            for (final T neighbor : graph.neighbors(top)) {
                if(top == neighbor) return true; // loop
                if(!vertexColorMap.containsKey(neighbor)) { //  not assigned color yet
                    vertexColorMap.put(neighbor, neighborColor);
                    queue.offerFirst(neighbor);
                    continue;
                }
                if(vertexColorMap.get(top) ==  vertexColorMap.get(neighbor)) return true;

            }
        }
        return false;
    }

}
