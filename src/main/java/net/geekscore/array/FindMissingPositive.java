package net.geekscore.array;


/**
 *              Application of Insertion Sort
 *
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 *
 * Your algorithm should run in O(n) time and uses constant space.
 */
public class FindMissingPositive {

    public static void main(String[] args) {
        System.out.println(missingPositive(new int[]{1,2,0})); // 3
        System.out.println(missingPositive(new int[]{3,4,-1,1})); // 2
        System.out.println(missingPositive(new int[]{3,4,-1,-1})); // 1
        System.out.println(missingPositive(new int[]{3,4,0,1})); // 2
        System.out.println(missingPositive(new int[]{-1,2,2,-1})); // 1

        System.out.println(missingPositive(new int[]{2,4,5,6})); // 3
        System.out.println(missingPositive(new int[]{1,2,-5,6})); // 3

        System.out.println(missingPositive(new int[]{-16})); // 1
        System.out.println(missingPositive(new int[]{1})); // 2
        System.out.println(missingPositive(new int[]{100})); // 1
        System.out.println(missingPositive(new int[0])); // 1
        System.out.println(missingPositive(new int[]{4,1,2,3})); // 5
    }

    private static int missingPositive(int nums[]) {
        if(null == nums || nums.length <= 0){
            return 1;
        }
        final int n = nums.length;
        int i = 0;
        while (i < n) {
//            If we encounter a valid integer, then find its correct position and swap. Otherwise we continue.
            if (nums[i] >= 0 &&  nums[i] < n  && nums[nums[i]] != nums[i]) swap(nums, i, nums[i]);
            else i++;
        }
        int k = 1;
        while (k < n && nums[k] == k) k++;
        return k == nums[0] ? k + 1: k; // for  handling case - {1}
    }

    private static void swap(final int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
