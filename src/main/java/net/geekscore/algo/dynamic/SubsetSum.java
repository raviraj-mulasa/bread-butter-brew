package net.geekscore.algo.dynamic;

import java.time.Duration;
import java.time.Instant;

/**
 * Given a set of non-negative integers, and a value sum, determine if there is a subset of
 * the given set with sum equal to given sum.
 *
 * Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output:  True  //There is a subset (4, 5) with sum 9.
 */
public class SubsetSum {

    public static void main(String[] args) {

        System.out.println("---------------");
        Instant start = Instant.now();
        System.out.println(subsetSumRec(new int[]{3, 34, 4, 12, 5, 2}, 9)); // true
        System.out.println(subsetSumRec(new int[]{3, 34, 4, 12, 5, 2}, 53)); // true
        System.out.println(subsetSumRec(new int[]{3, 34, 4, 12, 5, 2}, -1)); // false : all are non-negative
        System.out.println(subsetSumRec(new int[]{3, 34, 4, 12, 5, 2}, 61)); // false
        System.out.println(Duration.between(start, Instant.now()).toNanos());


        System.out.println("---------------");
        start = Instant.now();
        System.out.println(subsetSumDP(new int[]{3, 34, 4, 12, 5, 2}, 9)); // true
        System.out.println(subsetSumDP(new int[]{3, 34, 4, 12, 5, 2}, 53)); // true
        System.out.println(subsetSumDP(new int[]{3, 34, 4, 12, 5, 2}, -1)); // false : all are non-negative
        System.out.println(subsetSumDP(new int[]{3, 34, 4, 12, 5, 2}, 61)); // false
        System.out.println(Duration.between(start, Instant.now()).toNanos());
    }

    private static boolean  subsetSumRec(final int[] nums, final int sum) {
        return subsetSumRecHelper(nums, nums.length-1, sum);
    }

    private static boolean  subsetSumRecHelper(final int[] nums, final int i, final int sum) {
        if(i == 0 && sum != 0 ) return false;
        if(sum == 0) return true;
        if(nums[i] > sum) return subsetSumRecHelper(nums, i-1, sum); // if the number greater than sum, ignore
        return
                subsetSumRecHelper(nums, i-1, sum)  // Exclude the number from sum
                        || subsetSumRecHelper(nums, i-1, sum - nums[i]); // Include the number in the sum
    }


    private static boolean  subsetSumDP(final int[] nums, final int sum) {
        if(sum == 0) return true;
        if(sum < 0) return false;
        final boolean[][] possible = new boolean[nums.length+1][sum+1];
        possible[0][0] = true;
        for (int i = 1; i < nums.length+1; i++) {
            possible[i][0]= true; // Sum is 0
        }
        for (int i = 1; i <= sum; i++) {
            possible[0][i]= false; // element value is 0
        }
        for (int _sum = 1; _sum <= sum; _sum++) {
            for (int i = 1; i <= nums.length; i++) {
                if(nums[i-1] > _sum) {
                    // Exclude
                    possible[i][_sum] = possible[i-1][_sum];
                } else {
                    possible[i][_sum] =
                            possible[i-1][_sum-nums[i-1]] // Include
                            || possible[i-1][_sum]; // Exclude
                }
            }
        }
        return possible[nums.length][sum];
    }
}
