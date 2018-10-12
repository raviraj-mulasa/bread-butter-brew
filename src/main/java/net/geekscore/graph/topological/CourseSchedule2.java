package net.geekscore.graph.topological;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1. Some courses may have prerequisites,
 * for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you
 * should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So the correct course order is [0,1]
 *
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take.
 * To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3].
 * Another correct ordering is[0,2,1,3].
 *
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule2 {

    enum State {
        NOT_DISCOVERED,
        DISCOVERED,
        EXPLORED
    };

    public static void main(String[] args) {
        // [0,1]
        System.out.println(findOrder(2, new int[][]{{1, 0}}));
        // [0,1,2,3] or [0,2,1,3]
        System.out.println(findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));

        // [0,1,2,3] in any order - Don't matter
        System.out.println(findOrder(4, new int[][]{{}}));

        // Cycle present - not A DAG
        System.out.println(findOrder(2, new int[][]{{1, 0}, {0, 1}}));

    }

    private static List<Integer> findOrder(int numCourses, int[][] prerequisites) {
        // Independent courses - order does not matter
        Deque<Integer> topologicalSort = new LinkedList<>();
        if(numCourses > 0 && (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length ==0) ) {
            for (int i = 0; i < numCourses; i++) topologicalSort.add(i);
            return (List)topologicalSort;
        }

        final Map<Integer, State> states = new HashMap<>();
        for (int i = 0; i < numCourses; i++) states.put(i, State.NOT_DISCOVERED);

        final Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (final int[] prerequisite: prerequisites) {
            final List<Integer> list = adjList.getOrDefault(prerequisite[0],  new LinkedList<>());
            list.add(prerequisite[1]);
            adjList.put(prerequisite[0],list);
        }

        topologicalSort = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(dfs(i, adjList, topologicalSort, states)) return Collections.emptyList();
        }
        return (List)topologicalSort;
    }

    private static boolean dfs(final Integer course, final Map<Integer, List<Integer>> adjList, Deque<Integer> topologicalSort, final Map<Integer, State> states) {
        if(states.get(course) == State.NOT_DISCOVERED) {
            states.put(course, State.DISCOVERED);
            if(adjList.containsKey(course)) {
                for (final Integer prerequisite:adjList.get(course)) {
                    if(states.get(prerequisite) == State.DISCOVERED) return true; // cycle detected
                    if(dfs(prerequisite, adjList, topologicalSort, states)) return true; // cycle detected
                }
            }
            states.put(course, State.EXPLORED);
            topologicalSort.offerLast(course);
        }
        return false;

    }
}
