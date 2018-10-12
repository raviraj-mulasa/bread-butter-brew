package net.geekscore.disjointset;

import java.util.Arrays;

/**
 * N couples sit in 2N seats arranged in a row and want to hold hands.
 * We want to know the minimum number of swaps so that every couple is sitting side by side.
 * A swap consists of choosing any two people, then they stand up and switch seats.
 *
 * The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in
 * order, the first couple being (0, 1), the second couple being (2, 3), and so on with the
 * last couple being (2N-2, 2N-1).
 *
 * The couples' initial seating is given by row[i] being the value of the person who is initially
 * sitting in the i-th seat.
 *
 * Example 1:
 * Input: row = [0, 2, 1, 3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 *
 * Example 2:
 * Input: row = [3, 2, 0, 1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 * Note:
 * len(row) is even and in the range of [4, 60].
 * row is guaranteed to be a permutation of 0...len(row)-1.
 */
public class CouplesHoldingHands {

    public static void main(String[] args) {
        System.out.println(minSwapsCouples(new int[]{0, 2, 1, 3})); // 1
        //System.out.println(minSwapsCouples(new int[]{0, 2, 1, 4, 3, 5})); // 2
        System.out.println(minSwapsCouples(new int[]{3, 2, 0, 1})); // 0
        //System.out.println(minSwapsCouples(new int[]{0, 1, 2, 4, 3, 5})); // 1
        System.out.println(minSwapsCouples(new int[]{1,4,0,5,8,7,6,3,2,9})); // 3
    }

    private static int minSwapsCouples(int[] row) {
        final int[] parent = new int[row.length];
        int res=0;
        for (int i = 0; i < parent.length-1; i+=2) {
            parent[row[i]] = row[i]/2;
            parent[row[i+1]] = row[i+1]/2;
        }
        System.out.println(Arrays.toString(row));
        System.out.println(Arrays.toString(parent));
        for (int i = 0; i < parent.length; i+=2) {
            System.out.println(parent[row[i]]+"  "+parent[row[i+1]]);
//            parent[row[i]] = row[i]/2;
//            parent[row[i+1]] = row[i+1]/2;
            if(parent[row[i]] != parent[row[i+1]]) res++;
        }
        return (row.length - res)/2;
    }

    private static void makeSet(int[] parent, int coupleElement) {
        if(parent[coupleElement] == -1) {
            System.out.println(coupleElement +"  "+coupleElement/2);
            parent[coupleElement] = coupleElement/2;
        }
    }

    private static int union(final int[] parent, final int first, final int second) {
        final int pair1 = findSet(parent, first);
        final int pair2 = findSet(parent, second);
        if(pair1 != pair2) {
            return 1;
        }
        return 0;
    }

    private static int findSet(int[] parent, int coupleId) {
        if(parent[coupleId] == coupleId) return coupleId;
        parent[coupleId] = findSet(parent, parent[coupleId]);
        return parent[coupleId];
    }

}
