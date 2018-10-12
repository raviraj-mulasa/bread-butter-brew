package net.geekscore.disjointset;

import java.util.*;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 *
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        // 4
        System.out.println(longestConsecutiveSequence(new int[]{100, 4, 200, 1, 3, 2})); // 4
        // 4
        System.out.println(longestConsecutiveSequence(new int[]{100, 1, 200, -1, 0, 2}));
        // 3
        System.out.println(longestConsecutiveSequence(new int[]{100, 101, 1, 200, 199, 0, 2}));
        // 5
        System.out.println(longestConsecutiveSequence(new int[]{4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3})); // 5
        // 3
        System.out.println(longestConsecutiveSequence(new int[]{2147483647, -2147483647, Integer.MAX_VALUE, Integer.MIN_VALUE}));

        System.out.println("------------------");

        // 4
        System.out.println(longestConsecutiveSequence1(new int[]{100, 4, 200, 1, 3, 2})); // 4
        // 4
        System.out.println(longestConsecutiveSequence1(new int[]{100, 1, 200, -1, 0, 2}));
        // 3
        System.out.println(longestConsecutiveSequence1(new int[]{100, 101, 1, 200, 199, 0, 2}));
        // 5
        System.out.println(longestConsecutiveSequence1(new int[]{4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3})); // 5
        // 3
        System.out.println(longestConsecutiveSequence1(new int[]{2147483647, -2147483647, Integer.MAX_VALUE, Integer.MIN_VALUE}));


        System.out.println("------------------");
        // 4
        System.out.println(longestConsecutiveSequence1(new int[]{100, 4, 200, 1, 3, 2})); // 4
        // 4
        System.out.println(longestConsecutiveSequence1(new int[]{100, 1, 200, -1, 0, 2}));
        // 3
        System.out.println(longestConsecutiveSequence1(new int[]{100, 101, 1, 200, 199, 0, 2}));
        // 5
        System.out.println(longestConsecutiveSequence1(new int[]{4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3})); // 5
        // 3
        System.out.println(longestConsecutiveSequence1(new int[]{2147483647, -2147483647, Integer.MAX_VALUE, Integer.MIN_VALUE}));


        System.out.println("------------------");
        // 4
        System.out.println(longestConsecutiveSequence2(new int[]{100, 4, 200, 1, 3, 2})); // 4
        // 4
        System.out.println(longestConsecutiveSequence2(new int[]{100, 1, 200, -1, 0, 2}));
        // 3
        System.out.println(longestConsecutiveSequence2(new int[]{100, 101, 1, 200, 199, 0, 2}));
        // 5
        System.out.println(longestConsecutiveSequence2(new int[]{4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3})); // 5
        // 3
        System.out.println(longestConsecutiveSequence2(new int[]{2147483647, -2147483647, Integer.MAX_VALUE, Integer.MIN_VALUE}));
    }

    private static int longestConsecutiveSequence(final int[] nums) {
        final Map<Integer, Integer> parent = new HashMap<>();
        for (final int num: nums) makeSet(parent, num);
        for (final int num: nums) {
            if(!parent.containsKey(num +1)) {
                int prev = num -1;
                while (parent.containsKey(prev)) {
                    union(parent, num, prev);
                    prev -= 1;
                }
            }
        }
        final Map<Integer, Set<Integer>> consecutiveFreqMap = new HashMap<>();
        for (final int num: nums) {
            final int root = findSet(parent, num);
            final Set<Integer> set = consecutiveFreqMap.getOrDefault(root, new HashSet<>());
            set.add(num);
            consecutiveFreqMap.put(root,set);
        }

        Set<Integer> longestConsecutiveSequence = Collections.emptySet();
        for (final Set<Integer> value: consecutiveFreqMap.values()) {
            if(value.size() > longestConsecutiveSequence.size()) {
                longestConsecutiveSequence = value;
            }
        }
        return longestConsecutiveSequence.size();
    }

    private static void makeSet(final Map<Integer, Integer> parent, int num) {
        if(!parent.containsKey(num)) parent.put(num, num);
    }

    private static Integer findSet(final Map<Integer, Integer> parent, int num) {
        Integer root = parent.get(num);
        if(num == root) return num;
        root = findSet(parent, root);
        return parent.get(root);
    }

    private static void union(final Map<Integer, Integer> parent, int num1, int num2) {
        Integer root1 = findSet(parent, num1);
        Integer root2 = findSet(parent, num2);
        if(root1.compareTo(root2) != 0) {
            parent.put(parent.get(root2), root1);
        }
    }

    //  Using Set and walk the increment/decrement  streak
    private static int longestConsecutiveSequence1(final int[] nums) {

        if(null == nums || nums.length == 0) {
            return 0;
        }
        final Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLength = 0;
        for (int num : nums) {
            final Integer prev = num - 1;
            if (!set.contains(prev)) {
                Integer next = num + 1;
                while (set.contains(next)) {
                    next++;
                }
                maxLength = Math.max(maxLength, next - num);
            }
        }
        return maxLength;
    }


    /**
     * We will use HashMap. The key thing is to keep track of the sequence length and store that in the
     //    boundary points of the sequence. For example, as a result, for sequence {1, 2, 3, 4, 5},
     //    map.get(1) and map.get(5) should both return 5.
     */
    private static int longestConsecutiveSequence2(final int[] nums) {
        if(null == nums || nums.length == 0) {
            return 0;
        }
        final Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        for (int num : nums) {
            if(map.containsKey(num)) continue;

            Integer prev = map.getOrDefault(num - 1, 0);
            Integer next = map.getOrDefault(num + 1, 0);

            final int length = next + prev + 1;
            map.put(num, length);
            // extend the length to the boundary(s)
            // of the sequence
            // will do nothing if n has no neighbors
            map.put(num - prev, length);
            map.put(num + next, length);

            maxLength = Math.max(length, maxLength);
        }
        return maxLength;
    }
}
