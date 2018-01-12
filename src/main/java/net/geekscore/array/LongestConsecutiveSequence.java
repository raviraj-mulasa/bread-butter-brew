package net.geekscore.array;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {

    public static void main(String args[]) {

        final int[] nums1 = {100, 4, 200, 1, 3, 2}; // [1,2,3,4]
        final int[] nums2 = {100, 1, 200, 2, 3, 4};  // [1,2,3,4]
        final int[] nums3 = {100, 1, 200, 2, 3, 5};  // [1,2,3]
        final int[] nums4 = {100, 1, 200, 2, 3, 5, 101, 102, 103}; // [100, 101, 102, 103]
        final int[] nums5 = {0}; // [0]
        final int[] nums6 = {0,3,7,2,5,8,4,6,0,1};
        final int[] nums7 = {9,1,4,7,3,-1,0,5,8,-1,6};
        final int[] nums8 = {-1, -1, 2};
        final int[] nums9 = {-1, -1, 0, 0, 2};
//        System.out.println(longestConsecutiveSequenceLength(nums1));  // 4
//        System.out.println(longestConsecutiveSequenceLength(nums2)); // 4
//        System.out.println(longestConsecutiveSequenceLength(nums3)); // 3
//        System.out.println(longestConsecutiveSequenceLength(nums4)); // 4
//        System.out.println(longestConsecutiveSequenceLength(nums5)); // 1
//        System.out.println(longestConsecutiveSequenceLength(new int[0])); // 0
        System.out.println(longestConsecutiveSequenceLength(nums6)); // 9
//        System.out.println(longestConsecutiveSequenceLength(nums7)); // 7
//        System.out.println(longestConsecutiveSequenceLength(nums8)); // 1
//        System.out.println(longestConsecutiveSequenceLength(nums9)); // 2
    }

    private static int longestConsecutiveSequenceLength(final int[] nums) {

        if(null == nums || nums.length == 0) {
            return 0;
        }
        final Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            final Integer num = Integer.valueOf(nums[i]);
            // Every element is a longest consecutive  sequence
            map.put(num, 1);
//            if(map.containsKey(num)){
//                map.put(num, map.get(num)+ 1);
//            } else {
//                map.put(num, 1);
//            }
        }
        int length = 0;
        int maxLength = 1;
        for(int i = 0; i < nums.length; i++) {
            final Integer num = Integer.valueOf(nums[i]);
            Integer prev = num - 1;
            Integer next = num + 1;
            System.out.println("Prev: "+prev+", Curr: "+num+", Next: "+next);
            if(map.containsKey(prev)) {
                length = map.get(prev) + 1;
                map.put(num, length);
            }
            if(map.containsKey(next)) {
                length = map.get(num) + 1;
                map.put(next, length);
            }
            map.put(num - prev, map.get(prev));
            map.put(num + next, map.get(next));
            maxLength = Math.max(length, maxLength);
            System.out.println(map);
        }

        return maxLength;
    }
}
