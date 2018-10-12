package net.geekscore.graph.topological;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish
 * all courses?
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So it is possible.
 *
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0,
 * and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule {

    enum State {
        NOT_DISCOVERED,
        DISCOVERED,
        EXPLORED
    };

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][] {{1,0}})); // true
        System.out.println(canFinish(2, new int[][] {{}})); // true
        System.out.println(canFinish(2, new int[][] {{1,0}, {0,1}})); // false
        System.out.println(canFinish(3, new int[][] {{1,0}, {0,2}})); // true
        System.out.println(canFinish(3, new int[][] {{1,0}, {0,2}, {2,1}})); // false
        System.out.println(canFinish(3, new int[][] {{0,1}, {0,2}, {1,2}})); // true

        System.out.println("-------------------------");

        System.out.println(canFinishBFS(2, new int[][] {{1,0}})); // true
        System.out.println(canFinishBFS(2, new int[][] {{}})); // true
        System.out.println(canFinishBFS(2, new int[][] {{1,0}, {0,1}})); // false
        System.out.println(canFinishBFS(3, new int[][] {{1,0}, {0,2}})); // true
        System.out.println(canFinishBFS(3, new int[][] {{1,0}, {0,2}, {2,1}})); // false
        System.out.println(canFinishBFS(3, new int[][] {{0,1}, {0,2}, {1,2}})); // true
    }

    private static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Independent courses
        if(numCourses > 0 && (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length ==0) ) return true;

        final Map<Integer, List<Integer>> adjList = new HashMap<>();
        final Map<Integer, State> states = new HashMap<>();

        for(int i = 0; i < numCourses; i++) {
            states.put(i, State.NOT_DISCOVERED);
        }
        for (final int[] prerequisite : prerequisites) {
            final List<Integer> list = adjList.getOrDefault(prerequisite[0],  new LinkedList<>());
            list.add(prerequisite[1]);
            adjList.put(prerequisite[0], list);
        }

        for(int i = 0; i < numCourses; i++) {
            if(dfs(i, adjList, states)) return false;
        }
        return true;
    }

    private static boolean dfs(final Integer course, final Map<Integer, List<Integer>> adjList, final Map<Integer, State> states) {
        if(states.get(course) == State.NOT_DISCOVERED) {
            states.put(course, State.DISCOVERED);
            if(adjList.containsKey(course)) {
                for (Integer prerequisite: adjList.get(course)) {
                    if(states.get(prerequisite) == State.DISCOVERED) return true;
                    if(dfs(prerequisite, adjList, states)) return true;
                }
            }
            states.put(course, State.EXPLORED);
        }
        return false;
    }

    // Based on Kahn Algorithm
    private static boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        // Independent courses
        if(numCourses > 0 && (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length ==0) ) return true;

        // Create adjacent list based on edges
        final Map<Integer, List<Integer>> adjList = new HashMap<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new LinkedList<>());
        }

        // Calculate in degrees
        final int[] inDegrees = new int[numCourses];
        for (final int[] prerequisite : prerequisites) {
            adjList.get(prerequisite[0]).add(prerequisite[1]);
            inDegrees[prerequisite[1]]++;
        }

        final Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(inDegrees[i] == 0) queue.offerFirst(i); //  I am not prerequisite course
        }

        int coursesCovered = 0;
        while (!queue.isEmpty()) {
            final Integer course = queue.removeLast();
            for (final Integer prerequisite: adjList.get(course)) {
                if(--inDegrees[prerequisite] == 0) queue.offerFirst(prerequisite);
            }
            coursesCovered++;
        }
        return coursesCovered == numCourses;
    }
}
