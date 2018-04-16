package net.geekscore.search;

import java.util.Arrays;

/**
 * Given an array of integers sorted in ascending order, find the starting and ending position of a
 * given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].


 */
public class SearchForARange {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(search4Range(new int[]{5, 7, 7, 8, 8, 10}, 8))); // [3,4]
        System.out.println(Arrays.toString(search4Range(new int[]{5, 7, 7, 8, 8, 10}, 7))); // [1,2]
        System.out.println(Arrays.toString(search4Range(new int[]{5, 6, 7, 8, 8, 10}, 6))); // [1,1]
        System.out.println(Arrays.toString(search4Range(new int[]{5, 5, 7, 8, 8, 10}, 5))); // [0,1]
        System.out.println(Arrays.toString(search4Range(new int[]{}, 0))); // [-1,-1]
        System.out.println(Arrays.toString(search4Range(new int[]{0}, 1))); // [-1,-1]
        System.out.println(Arrays.toString(search4Range(new int[]{1}, 1))); // [0,0]
        System.out.println(Arrays.toString(search4Range(new int[]{1}, 0))); // [-1,-1]
    }

    private static int[] search4Range(final int[] nums, int target) {
        final int[] range = {-1, -1};
        int low = 0;
        int high = nums.length-1;
        while (low <= high) {
//            System.out.println("BEFORE Low "+low+" High "+high);
            final int mid = low+(high-low)/2;
            if(nums[mid] < target) {
                low = mid + 1;
            }else {
                high= mid - 1;
            }
        }
        range[0] = (low >= nums.length || nums[low] != target) ? -1 : low;
        high = nums.length-1;
        while (low <= high) {
//            System.out.println("AFTER Low "+low+" High "+high);
            final int mid = low+(high-low)/2;
            if(nums[mid] <= target) {
                low = mid + 1;
            }else {
                high= mid - 1;
            }
        }
        range[1] = (high < 0 || nums[high] != target) ? -1 : high;
        return range;
    }
}
