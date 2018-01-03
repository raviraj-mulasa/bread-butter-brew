package net.geekscore.array;

import java.util.Arrays;

/**
 *
 * Given an array of integers, find how many pairs in the array such that their sum is bigger than a specific target number.
 * Please return the number of pairs.
 *
 * Example
 * Given numbers = [2, 7, 11, 15], target = 24. Return 1. (11 + 15 is the only pair)
 *
 * Challenge: Do it in O(1) extra space and O(nlogn) time.
 * Tags: Two Pointers Sort
 *
 *
 **/

public class TwoSumBigger {

    public static void main(String[] args) {
        System.out.println(twoSumBiggerPairs(new int[]{2, 7, 11, 15}, 24)); // 1
        System.out.println(twoSumBiggerPairs(new int[]{-1, 2, 1, -4}, 0)); // 2
        System.out.println(twoSumBiggerPairs(new int[]{-1, 3, 1, -4}, 10)); // 0
    }

    private static final int twoSumBiggerPairs(int[] nums, int target) {
        if(nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int pairs = 0, left = 0, right = nums.length - 1;
        while (left < right) {
            final int sum = nums[left] + nums[right];
            if(sum > target) {
                pairs += (right - left); // Index from left to right - 1 will all be counted ???
                right--;
            } else {
                left++;
            }
        }
        return pairs;
    }
}
