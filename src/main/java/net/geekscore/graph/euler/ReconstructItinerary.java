package net.geekscore.graph.euler;


import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 *
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical
 * order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 *
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 *
 * Example 2:
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 * But it is larger in lexical order.


 */
public class ReconstructItinerary {

    public static void main(String[] args) {

        System.out.println(findItinerary(new String[][]{
                {"MUC", "LHR"}
                ,{"JFK", "MUC"}
                ,{"SFO", "SJC"}
                ,{"LHR", "SFO"}
        })); // ["JFK", "MUC", "LHR", "SFO", "SJC"]

        System.out.println(findItinerary(new String[][]{
                {"JFK","SFO"}
                ,{"JFK","ATL"}
                ,{"SFO","ATL"}
                ,{"ATL","JFK"}
                ,{"ATL","SFO"}
        })); // ["JFK","ATL","JFK","SFO","ATL","SFO"]

    }

    private static List<String> findItinerary(String[][] tickets) {

        if (tickets == null || tickets.length == 0) return Collections.emptyList();
        final Map<String, PriorityQueue<String>> adjList = new HashMap<>();


        for (final String[] ticket : tickets) {
            final PriorityQueue<String> priorityQueue = adjList.getOrDefault(ticket[0], new PriorityQueue<>());
            priorityQueue.add(ticket[1]);
            adjList.put(ticket[0], priorityQueue);
        }
//        final List<String> result = new LinkedList<>();
//        dfs("JFK", adjList, result);
//        return result;

        return dfs(adjList);
    }

    private static void dfs(final String airport, final Map<String, PriorityQueue<String>> adjList, final List<String> result) {
        while (adjList.containsKey(airport) && !adjList.get(airport).isEmpty()) {
            dfs(adjList.get(airport).poll(), adjList, result);
        }
        result.add(0, airport);
    }

    private static List<String> dfs(final Map<String, PriorityQueue<String>> adjList) {
        final Deque<String> stack = new LinkedList<>();
        List<String> result = new LinkedList<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            String top = stack.peek();
            while (adjList.containsKey(top) && !adjList.get(top).isEmpty()) {
                stack.push(adjList.get(top).poll());
                top = stack.peek();
            }
            result.add(0, stack.pop());
        }
        return result;
    }

}
