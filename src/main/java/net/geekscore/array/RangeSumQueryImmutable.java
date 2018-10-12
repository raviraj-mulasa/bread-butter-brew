package net.geekscore.array;

import java.util.Arrays;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 */

public class RangeSumQueryImmutable {

    static class NumArray {

        private final int[] nums;

        private final int[] runningSums;

        private NumArray(int[] nums) {
            this.nums = nums;
            this.runningSums = new int[this.nums.length];
            this.calculateRunningSums();
        }

        private int sumRange(int i, int j) {
            return i <= 0 ? this.runningSums[j] :this.runningSums[j] - this.runningSums[i-1];
        }

        private void calculateRunningSums(){
            int sum = 0;
            for (int i = 0; i < this.nums.length; i++) {
                this.runningSums[i] = sum + this.nums[i];
                sum = this.runningSums[i];
            }
            System.out.println(Arrays.toString(this.runningSums));
        }
    }

    public static void main(String[] args) {

        final NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2)); // 1
        System.out.println(numArray.sumRange(2, 5)); // -1
        System.out.println(numArray.sumRange(0, 5)); // -3
    }

}
