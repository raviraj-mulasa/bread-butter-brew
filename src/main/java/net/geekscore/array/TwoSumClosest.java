package net.geekscore.array;

import java.util.Arrays;

/**
 *
 * Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.
 *
 * Return the difference between the sum of the two integers and the target.
 * Example
 *  Given array nums = [-1, 2, 1, -4], and target = 4.The minimum difference is 1. (4 - (2 + 1) = 1).
 *
 **/

public class TwoSumClosest {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSumClosest(new int[]{-1, 2, 1, -4}, 4)));
        System.out.println(Arrays.toString(twoSumClosest(new int[]{1, 60, -10, 70, -80, 85}, 6 )));
        System.out.println(Arrays.toString(twoSumClosest(new int[]{2, 7, 11, 15}, 9)));

    }

    private static final int[] twoSumClosest(int[] nums, int target) {
        if(null == nums || nums.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums);
        int left    = 0, right = nums.length - 1, minLeft = left, minRight = right;
        int minDiff = Integer.MAX_VALUE;
        while (left < right) {
            final int sum = nums[left] + nums[right];
            final int diff= Math.abs(target - sum);
            if(diff == 0) {
                // target reached
                minLeft = left;
                minRight= right;
                break;
            }
            if(diff < minDiff) {
                minDiff = diff;
                minLeft = left;
                minRight= right;
            }
            if(sum > target) {
                // we added a bigger number from right, decrement right and try closer to the sum
                right--;
            } else {
                left++;
            }
        }
        return new int[]{nums[minLeft], nums[minRight]};
    }
}
