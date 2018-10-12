package net.geekscore.array;


import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the
 * relative order of the non-zero elements.
 *
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {

    public static void main(String[] args) {
        final int[] nums = new int[]{0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(nums));
        moveZerosEnd(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println("---------");

        final int[] nums1 = new int[]{0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(nums1));
        moveZerosBegin(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    private static void moveZerosEnd(final int[] nums) {
        if(null == nums || nums.length <= 0) return;
        int leftMostZero = 0; // assume zero is at the left most position
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                // we found a non-zero at i, so swap
                int temp = nums[i];
                nums[i] = nums[leftMostZero];
                nums[leftMostZero] = temp;
                leftMostZero++; // we filled the left most zero with non-zero and we move to end
            }
        }
    }

    private static void moveZerosBegin(final int[] nums) {
        if(null == nums || nums.length <= 0) return;
        int rightMostZero = nums.length-1; // assume zero is at the right most position
        for (int i = nums.length-1; i > -1; i--) {
            if(nums[i] != 0) {
                // we found a non-zero at i, so swap
                int temp = nums[i];
                nums[i] = nums[rightMostZero];
                nums[rightMostZero] = temp;
                rightMostZero--; // we filled the right most zero with non-zero and we move to begin
            }
        }
    }
}
