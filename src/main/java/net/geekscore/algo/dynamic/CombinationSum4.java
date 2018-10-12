package net.geekscore.algo.dynamic;

import java.util.*;

/**
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 *
 * Example:
 * nums = [1, 2, 3]
 * target = 4
 *
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 *
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 *
 */
public class CombinationSum4 {

    public static void main(String[] args) {
        System.out.println(combinations(new int[]{2, 3, 6, 7}, 7)); // 4
        System.out.println(combinations(new int[]{1, 2, 3}, 4)); // 7
    }

    private static int combinations(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        int[] sequences = new int[target+1];
        sequences[0] = 1;
        for (int sum = 1; sum <= target ; sum++) {
            for (int i = 0; i < nums.length; i++) {
                if(sum  - nums[i] >= 0) {
                    sequences[sum] += sequences[sum - nums[i]];
                }
            }
        }
        return sequences[target];

    }


}
