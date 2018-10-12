package net.geekscore.array;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * Example:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 *
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 */
public class RangeSumQueryMutable {

    static class NumArray {

        private int[] nums;
        private int[] runningSums;

        private NumArray(int[] nums) {
            if(nums != null && nums.length > 0) {
                this.nums = nums;
                this.runningSums = new int[this.nums.length];
                this.calculateRunningSum();
            }
        }

        private void update(int i, int val) {
            if(i == 0) {
                this.nums[0] = val;
                this.runningSums[0] =  this.nums[0];
            }
            else {
                this.runningSums[i] = (this.runningSums[i]-this.nums[i])+val;
                this.nums[i] = val;
            }
            for (int j = i+1; j < this.nums.length; j++) {
                this.runningSums[j] = this.runningSums[j-1] + nums[j];
            }
        }

        private void calculateRunningSum() {
            this.update(0, this.nums[0]);
        }

        private int sumRange(int i, int j) {
            return i <=0 ? this.runningSums[j] : this.runningSums[j] - this.runningSums[i-1];
        }
    }

    public static void main(String[] args) {
        final NumArray numArray = new NumArray(new int[] {1, 3, 5});
        System.out.println(numArray.sumRange(0,2)); // 9
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0,2)); // 8
        System.out.println(numArray.sumRange(1,2)); // 7

        System.out.println("-------");

        final NumArray numArray1 = new NumArray(new int[] {-1});
        System.out.println(numArray1.sumRange(0,0)); // -1
        numArray1.update(0, 1);
        System.out.println(numArray1.sumRange(0,0)); // 1

    }
}
