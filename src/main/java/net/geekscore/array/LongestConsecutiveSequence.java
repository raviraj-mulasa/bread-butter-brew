package net.geekscore.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Given an unsorted array of integers, find the length of the longest consecutive element sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {

    public static void main(String args[]) {

        final int[] nums1 = {100, 4, 200, 1, 3, 2};
        final int[] nums2 = {100, 1, 200, 2, 3, 4};
        final int[] nums3 = {100, 1, 200, 2, 3, 5};
        final int[] nums4 = {100, 1, 200, 2, 3, 5, 101, 102, 103};
        final int[] nums5 = {0};
        final int[] nums6 = {0,3,7,2,5,8,4,6,0,1};
        final int[] nums7 = {9,1,4,7,3,-1,0,5,8,-1,6};
        final int[] nums8 = {-1, -1, 2};
        final int[] nums9 = {-1, -1, 0, 0, 2};
        final int[] nums10 = {1, 5, 2, 6, 7, 9, 10, 4};
        final int[] nums11 = {9, 1, 4, 7, 3, -1, 5};

        System.out.println(longestConsecutiveSequenceLength(nums1));  // 4
        System.out.println(longestConsecutiveSequenceLength(nums2)); // 4
        System.out.println(longestConsecutiveSequenceLength(nums3)); // 3
        System.out.println(longestConsecutiveSequenceLength(nums4)); // 4
        System.out.println(longestConsecutiveSequenceLength(nums5)); // 1
        System.out.println(longestConsecutiveSequenceLength(new int[0])); // 0
        System.out.println(longestConsecutiveSequenceLength(nums6)); // 9
        System.out.println(longestConsecutiveSequenceLength(nums7)); // 7
        System.out.println(longestConsecutiveSequenceLength(nums8)); // 1
        System.out.println(longestConsecutiveSequenceLength(nums9)); // 2
        System.out.println(longestConsecutiveSequenceLength(nums10)); // 4
        System.out.println(longestConsecutiveSequenceLength(nums11)); // 3


        System.out.println("-------------------------------------");
        System.out.println(longestConsecutiveSequenceLength1(nums1));  // 4
        System.out.println(longestConsecutiveSequenceLength1(nums2)); // 4
        System.out.println(longestConsecutiveSequenceLength1(nums3)); // 3
        System.out.println(longestConsecutiveSequenceLength1(nums4)); // 4
        System.out.println(longestConsecutiveSequenceLength1(nums5)); // 1
        System.out.println(longestConsecutiveSequenceLength1(new int[0])); // 0
        System.out.println(longestConsecutiveSequenceLength1(nums6)); // 9
        System.out.println(longestConsecutiveSequenceLength1(nums7)); // 7
        System.out.println(longestConsecutiveSequenceLength1(nums8)); // 1
        System.out.println(longestConsecutiveSequenceLength1(nums9)); // 2
        System.out.println(longestConsecutiveSequenceLength1(nums10)); // 4
        System.out.println(longestConsecutiveSequenceLength1(nums11)); // 3
    }



//    We will use HashMap. The key thing is to keep track of the sequence length and store that in the
//    boundary points of the sequence. For example, as a result, for sequence {1, 2, 3, 4, 5},
//    map.get(1) and map.get(5) should both return 5.
    private static int longestConsecutiveSequenceLength(final int[] nums) {
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
            maxLength = Math.max(length, maxLength);

            // extend the length to the boundary(s)
            // of the sequence
            // will do nothing if n has no neighbors
            map.put(num - prev, length);
            map.put(num + next, length);
        }

        return maxLength;
    }


//    Using Set and walk the increment/decrement  streak
    private static int longestConsecutiveSequenceLength1(final int[] nums) {

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
}
