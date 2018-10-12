package net.geekscore.algo.dynamic;

import java.time.Duration;
import java.time.Instant;

/**
 * Given an array of positive numbers, find the maximum sum of a subsequence with the constraint that no 2
 * numbers in the sequence should be adjacent in the array.
 *
 * 3 2 7 10 should return 13 (sum of 3 and 10)
 * −2, 1, −3, 4, −1, 2, 1, −5, 4  should return 11 (sum of 1, 4, 2, 4)
 * 5, 5, 10, 100, 10, 5 should return 110 (sum of 5, 100, 5)
 *
 */
public class MaxSumNonAdjEle {

    public static void main(String[] args) {
        System.out.println("-----------------");
        Instant start = Instant.now();
        System.out.println(maxSumNonAdjacentElements(new int[]{5, 5, 10, 100, 10, 5})); // 110
        System.out.println(maxSumNonAdjacentElements(new int[]{-2,1,-3,4,-1, 2, 1,-5, 4})); // 11
        System.out.println(maxSumNonAdjacentElements(new int[]{3,2,7,10})); // 13
        System.out.println(Duration.between(start, Instant.now()).toNanos());

        System.out.println("-----------------");
        start = Instant.now();
        System.out.println(maxSumNonAdjacentElementsDP(new int[]{5, 5, 10, 100, 10, 5})); // 110
        System.out.println(maxSumNonAdjacentElementsDP(new int[]{-2,1,-3,4,-1, 2, 1,-5, 4})); // 11
        System.out.println(maxSumNonAdjacentElementsDP(new int[]{3,2,7,10})); // 13
        System.out.println(Duration.between(start, Instant.now()).toNanos());

    }

    private static final int maxSumNonAdjacentElements(final int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        return maxSumNonAdjacentElementsHelper(nums, 1, nums[0], 0);
    }

    private static final int maxSumNonAdjacentElementsHelper(final int[] nums, int i, int sumIncludeI, int sumExcludeI) {
        if(i == nums.length) return Math.max(sumExcludeI, sumIncludeI);
        return maxSumNonAdjacentElementsHelper(nums, i+1, sumExcludeI+nums[i], Math.max(sumExcludeI, sumIncludeI));
    }

    private static final int maxSumNonAdjacentElementsDP(final int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int exclude = 0;
        int include = nums[0];
        for (int i = 1; i < nums.length; i++) {
            final int temp = exclude;
            exclude = Math.max(temp, include);
            include = temp + nums[i];
        }
        return Math.max(exclude, include);
    }


}
