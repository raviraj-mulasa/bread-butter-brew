package net.geekscore.disjointset;

import java.util.*;

/**
 * Created by ravirajmulasa on 8/14/17.
 */
public class FriendsCircle {

    public static void main(String[] args) {

        final char[][] friends = {
                {'Y','Y','N'}
                , {'Y','Y','N'}
                ,{'N','N','Y'}
        };
        final char[][] friends1 = {
                {'Y','Y','N'}
                , {'Y','Y','Y'}
                ,{'N','Y','Y'}
        };

        final String[] friends2 = {"YYN", "YYN", "NNY"};
        final String[] friends3 = {"YYN", "YYY", "NYY"};

        System.out.println(friendCirclesCount(friends));
        System.out.println(friendCirclesCount(friends1));
        System.out.println(friendCircles(friends2));
        System.out.println(friendCircles(friends3));
    }

    private static final int friendCircles(String[] friends) {
        if(null == friends || friends.length == 0){
            return 0;
        }
        final char[][] friends2DCharArray = new char[friends.length][];
        for (int i = 0; i < friends.length; i++) {
            friends2DCharArray[i] = friends[i].toCharArray();
        }
        return friendCirclesCount(friends2DCharArray);
    }

    private static final int friendCirclesCount(final char[][] friends) {
        if (friends == null || friends.length < 1) {
            return 0;
        }
        final BitSet visited = new BitSet(friends.length);
//        final boolean[] visited = new boolean[friends.length];
//        Arrays.fill(visited, Boolean.FALSE); // No node was visited yet.

        int friendCircles = 0;

        for (int i = 0; i < friends.length; i++) {
//            if (!visited[i]) {
//                friendCircles += 1;
//                visited[i]  = Boolean.TRUE;
//                friendCircle(friends, i, visited);
//            }
            if (!visited.get(i)) {
                friendCircles += 1;
                visited.set(i);
                friendCircle(friends, i, visited);
            }
        }
        return friendCircles;
    }

    private static final void friendCircle(final char[][] friends, final Integer myId, final BitSet visited) {
        for (int i = 0; i < friends.length; i++) {
            if (!visited.get(i) && myId != i && 'Y' == friends[myId][i]) {
                visited.set(i);
                friendCircle(friends, i, visited);
            }
        }
    }
}
