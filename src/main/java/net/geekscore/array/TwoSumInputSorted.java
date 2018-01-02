package net.geekscore.array;

import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

 The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

 You may assume that each input would have exactly one solution and you may not use the same element twice.

 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2
 */

public class TwoSumInputSorted {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSumSorted(new int[]{2, 7, 11, 15}, 9))); // [1,2]
        System.out.println(Arrays.toString(twoSumSorted(new int[]{2,3,4}, 6))); // [1,3]
        System.out.println(Arrays.toString(twoSumSorted(new int[]{0,1}, 2))); // [1,3]
    }

    private static final int[] twoSumSorted(int[] nums, int target) {
        if(null == nums || nums.length == 0) {
            return new int[0];
        }
        int left  = 0, right = nums.length - 1;
        while (left < right) {
            final int sum = nums[left] + nums[right];
            if(target - sum == 0) {
                // Target reached
                return new int[]{left+1, right+1};
            }
            if(sum > target) {
                // we added a bigger number from right, decrement right and try closer to the sum
                right--;
            } else {
                left++;
            }
        }
        return new int[0];
    }
}
