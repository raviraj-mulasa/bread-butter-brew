package net.geekscore.algo.backtrack;

import java.util.Arrays;

/**
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater
 * permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order
 * (ie, sorted in ascending order).
 *
 * The replacement must be in-place, do not allocate extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are
 * in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {

    public static void main(String[] args) {

        final int[] nums = new int[]{1,2,3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [1,3,2]

        final int[] nums1 = new int[]{3,2,1};
        nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1)); // [1,2,3]

        final int[] nums2 = new int[]{1,1,5};
        nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2)); // [1,5,1]

        final int[] nums3 = new int[]{1,1};
        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3)); // [1,1]

        final int[] nums4 = new int[]{2,3,1};
        nextPermutation(nums4);
        System.out.println(Arrays.toString(nums4)); // [3,1,2]

    }

    private static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int p = nums.length-1;
        while (p > 0) {
            if(nums[p-1] < nums[p--]) break;
        }
        int q = p+1;
        while (q < nums.length-1) {
            if(nums[q] > nums[p]) break;
            else q++;
        }
        if(p == 0) {
            reverse(nums, 0, nums.length-1);
            return;
        }
        swap(nums, p, q);
        if(p < nums.length-1) {
            reverse(nums, p+1, nums.length-1);
        }
    }

    private static void reverse(int[] nums, int begin, int end) {
        while (begin <  end) {
            if(nums[begin] != nums[end]) {
                swap(nums, begin, end);
            }
            begin++;
            end--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
