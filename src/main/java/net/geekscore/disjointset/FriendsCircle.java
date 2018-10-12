package net.geekscore.disjointset;


import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 *
 * There are N students in a class. Some of them are friends, while some are not.
 * Their friendship is transitive in nature.
 * For example, if A is a direct friend of B, and B is a direct friend of C,
 * then A is an indirect friend of C. And we defined a friend circle is a group of students who are
 * direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class.
 * If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
 * And you have to output the total number of friend circles among all the students.
 *
 * Example 1:
 * Input: [
 *  [1,1,0],
 *  [1,1,0],
 *  [0,0,1]
 * ]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 *
 * Example 2:
 * Input: [
 *  [1,1,0],
 *  [1,1,1],
 *  [0,1,1]
 * ]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 *
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 *
 * Created by ravirajmulasa on 8/14/17.
 */
public class FriendsCircle {

    public static void main(String[] args) {

        final char[][] friends = {
                 {'Y','Y','N'}
                ,{'Y','Y','N'}
                ,{'N','N','Y'}
        };
        final char[][] friends2 = {
                {'Y','Y','N'}
                ,{'Y','Y','Y'}
                ,{'N','Y','Y'}
        };

        final char[][] friends3 = {
                {'Y','N','N'}
                ,{'N','Y','N'}
                ,{'N','N','Y'}
        };

        final char[][] friends4 = {
                {'Y','Y','N','N','N','N','N','Y','N','N','N','N','N','N','N'},
                {'Y','Y','N','N','N','N','N','N','N','N','N','N','N','N','N'},
                {'N','N','Y','N','N','N','N','N','N','N','N','N','N','N','N'},
                {'N','N','N','Y','N','Y','Y','N','N','N','N','N','N','N','N'},
                {'N','N','N','N','Y','N','N','N','N','Y','Y','N','N','N','N'},
                {'N','N','N','Y','N','Y','N','N','N','N','Y','N','N','N','N'},
                {'N','N','N','Y','N','N','Y','N','Y','N','N','N','N','Y','N'},
                {'Y','N','N','N','N','N','N','Y','Y','N','N','N','N','N','N'},
                {'N','N','N','N','N','N','Y','Y','Y','N','N','N','N','Y','N'},
                {'N','N','N','N','Y','N','N','N','N','Y','N','Y','N','N','Y'},
                {'N','N','N','N','Y','Y','N','N','N','N','Y','Y','N','N','N'},
                {'N','N','N','N','N','N','N','N','N','Y','Y','Y','N','N','N'},
                {'N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N'},
                {'N','N','N','N','N','N','Y','N','Y','N','N','N','N','Y','N'},
                {'N','N','N','N','N','N','N','N','N','Y','N','N','N','N','Y'}
        };

        final char[][] friends5 = {
                 {'Y','Y','Y','N','Y','Y','Y','N','N','N'}
                ,{'Y','Y','N','N','N','N','N','Y','N','N'}
                ,{'Y','N','Y','N','N','N','N','N','N','N'}
                ,{'N','N','N','Y','Y','N','N','N','Y','N'}
                ,{'Y','N','N','Y','Y','N','N','N','N','N'}
                ,{'Y','N','N','N','N','Y','N','N','N','N'}
                ,{'Y','N','N','N','N','N','Y','N','Y','N'}
                ,{'N','Y','N','N','N','N','N','Y','N','Y'}
                ,{'N','N','N','Y','N','N','Y','N','Y','Y'}
                ,{'N','N','N','N','N','N','N','Y','Y','Y'}
        };




        Instant start = Instant.now();
        System.out.println(friendCirclesDFS(friends)); // 2
        System.out.println(friendCirclesDFS(friends2)); // 1
        System.out.println(friendCirclesDFS(friends3)); // 3
        System.out.println(friendCirclesDFS(friends4)); // 3
        System.out.println(friendCirclesDFS(friends5)); // 1
        System.out.println("DFS time taken "+ Duration.between(start, Instant.now()).toNanos());

        start = Instant.now();
        System.out.println(friendCirclesUnionFind(friends)); // 2
        System.out.println(friendCirclesUnionFind(friends2)); // 1
        System.out.println(friendCirclesUnionFind(friends3)); // 3
        System.out.println(friendCirclesUnionFind(friends4)); // 3
        System.out.println(friendCirclesUnionFind(friends5)); // 1
        System.out.println("Union-Find time taken "+ Duration.between(start, Instant.now()).toNanos());

    }


    private static final int friendCirclesDFS(final char[][] friends) {
        if (friends == null || friends.length < 1) {
            return 0;
        }
        int friendCircles = 0;
        final BitSet visited = new BitSet(friends.length);
        for (int i = 0; i < friends.length; i++) {
            if (!visited.get(i)) {
                friendCircles += 1;
                visited.set(i);
                dfs(friends, i, visited);
            }
        }
        return friendCircles;
    }

    private static final void dfs(final char[][] friends, final Integer myId, final BitSet visited) {
        for (int i = 0; i < friends.length; i++) {
            if (!visited.get(i) && myId != i && 'Y' == friends[myId][i]) {
                visited.set(i);
                dfs(friends, i, visited);
            }
        }
    }

    private static final int friendCirclesBFS(final char[][] friends) {
        return 0;
    }


    private static final int friendCirclesUnionFind(final char[][] friends) {
        if (friends == null || friends.length < 1) {
            return 0;
        }
        final int[] parent = new int[friends.length];
        for (int i = 0; i < parent.length; i++) parent[i] = i;  // makeSet

        for (int i = 0; i < friends.length; i++) {
            for (int j = 0; j < friends[i].length; j++) {
                if(friends[i][j] == 'Y') {
                    union(parent, i, j); // Both are friends. So, merge into a single set
                }
            }
        }
        final Set<Integer> circles = new HashSet<>();
        for (int i = 0; i < friends.length; i++) {
            circles.add(findSet(parent, i));
        }
        return circles.size();
    }

    private static final int findSet(final int[] parent, int x) {
        if(parent[x] == x) return x;
        parent[x] = findSet(parent, parent[x]); // path compression
        return parent[x];
    }

    private static void union(final int[] parent, int x, int y) {
        int parentX = findSet(parent, x);
        int parentY = findSet(parent, y);
        if(parentX  != parentY) { // union
            parent[parentY] = parentX;
        }
    }
}
