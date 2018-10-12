package net.geekscore.array;

import java.util.Arrays;

/**
 *
 *
 * Given an array of n integers where n > 1, nums, return an array output such that output[i]
 * is equal to the product of all the elements of nums except nums[i].
 *
 * Solve it without division and in O(n).
 *
 * For example, given [1,2,3,4], return [24,12,8,6].
 *
 * Follow up:
 * Could you solve it with constant space complexity?
 * (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4}))); // [24, 12, 8, 6]
        System.out.println(Arrays.toString(productExceptSelf(new int[]{2,2,5,4}))); // [40, 40, 16, 20]
        System.out.println(Arrays.toString(productExceptSelf(new int[]{2,0,5,4}))); // [0, 40, 0, 0]
    }

    private static final int[] productExceptSelf(final int[] nums) {

        final int[] result = new int[nums.length];
        Arrays.fill(result, 1);
        int left = result[0]; // product of all elements to the left except self
        for (int i = 1; i < nums.length; i++) {
            result[i] = left * nums[i-1];
            left = result[i];
        }
        int right = nums[nums.length-1]; // product of all elements to the the left and right except self
        for (int i = nums.length-2; i > -1; i--) {
            result[i] = right * result[i];
            right = right * nums[i];
        }
        return result;
    }

}
