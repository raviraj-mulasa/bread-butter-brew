package net.geekscore.search;

import java.util.Arrays;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search.
 * If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        final int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(String.format(
                "%d found at %d in %s"
                ,4
                , searchInRotatedSortedArray(nums, 4)
                , Arrays.toString(nums)
        )); // 0

        System.out.println(String.format(
                "%d found at %d in %s"
                ,7
                , searchInRotatedSortedArray(nums, 7)
                , Arrays.toString(nums)
        )); // 3

        final int[] nums1 = new int[]{5,6,7,0,1,2,4};
        System.out.println(String.format(
                "%d found at %d in %s"
                ,4
                , searchInRotatedSortedArray(nums1, 4)
                , Arrays.toString(nums1)
        )); // 6

        System.out.println(String.format(
                "%d found at %d in %s"
                ,7
                , searchInRotatedSortedArray(nums1, 7)
                , Arrays.toString(nums1)
        )); // 2

        System.out.println(String.format(
                "%d found at %d in %s"
                ,0
                , searchInRotatedSortedArray(nums1, 0)
                , Arrays.toString(nums1)
        )); // 3

        System.out.println(String.format(
                "%d found at %d in %s"
                ,3
                , searchInRotatedSortedArray(nums1, 3)
                , Arrays.toString(nums1)
        )); // -1

        final int[] nums2 = new int[]{5,5,7,0,1,2,1};
        System.out.println(String.format(
                "%d found at %d in %s"
                ,5
                , searchInRotatedSortedArray(nums2, 5)
                , Arrays.toString(nums2)
        )); // 1 or 0

        System.out.println(String.format(
                "%d found at %d in %s"
                ,1
                , searchInRotatedSortedArray(nums2, 1)
                , Arrays.toString(nums2)
        )); // 4 or 6
    }

    private static int searchInRotatedSortedArray(final int[] nums, int target) {
        if(nums != null && nums.length > 0) {
            int left = 0, right = nums.length-1;
            while (left <= right) {

                final int mid = left + (right - left)/2;

                if(nums[mid] == target) return mid;

                else if(nums[mid] <= nums[right]) { // right side is increasing
                    if(target > nums[mid] && target <= nums[right])
                        left = mid+1;
                    else
                        right = mid-1;
                }
                else if( nums[mid] >= nums[left]) { // left side is increasing
                    if (nums[left] <= target && target < nums[mid])
                        right = mid - 1;
                    else
                        left = mid + 1;

                }
            }
        }
        return -1;
    }
}
