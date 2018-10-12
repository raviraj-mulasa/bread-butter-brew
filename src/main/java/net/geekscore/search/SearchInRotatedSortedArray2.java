package net.geekscore.search;

import java.util.Arrays;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *
 * You are given a target value to search. If found in the array return true, otherwise return false.
 *
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 *
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 *
 * Follow up:
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 */
public class SearchInRotatedSortedArray2 {

    public static void main(String[] args) {
        final int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(String.format(
                "%d found in %s ? %b"
                ,4
                , Arrays.toString(nums)
                ,findInRotatedSortedArray(nums, 4)
        )); // true

        System.out.println(String.format(
                "%d found in %s ? %b"
                ,3
                , Arrays.toString(nums)
                ,findInRotatedSortedArray(nums, 3)
        )); // false

        final int[] nums1 = new int[]{2,5,6,0,0,1,2};
        System.out.println(String.format(
                "%d found in %s ? %b"
                ,0
                , Arrays.toString(nums1)
                ,findInRotatedSortedArray(nums1, 0)
        )); // true

        System.out.println(String.format(
                "%d found in %s ? %b"
                ,3
                , Arrays.toString(nums1)
                ,findInRotatedSortedArray(nums1, 3)
        )); // false

        final int[] nums2 = new int[]{1,1,3,1};
        System.out.println(String.format(
                "%d found in %s ? %b"
                ,3
                , Arrays.toString(nums2)
                ,findInRotatedSortedArray(nums2, 3)
        )); // true

        System.out.println(String.format(
                "%d found in %s ? %b"
                ,1
                , Arrays.toString(nums2)
                ,findInRotatedSortedArray(nums2, 1)
        )); // true

        System.out.println(String.format(
                "%d found in %s ? %b"
                ,0
                , Arrays.toString(nums2)
                ,findInRotatedSortedArray(nums2, 0)
        )); // false

        final int[] nums3 = new int[]{3,1};
        System.out.println(String.format(
                "%d found in %s ? %b"
                ,1
                , Arrays.toString(nums3)
                ,findInRotatedSortedArray(nums3, 1)
        )); // true

        System.out.println(String.format(
                "%d found in %s ? %b"
                ,3
                , Arrays.toString(nums3)
                ,findInRotatedSortedArray(nums3, 3)
        )); // true


        final int[] nums4 = new int[]{1,3};
        System.out.println(String.format(
                "%d found in %s ? %b"
                ,1
                , Arrays.toString(nums4)
                ,findInRotatedSortedArray(nums4, 1)
        )); // true

        System.out.println(String.format(
                "%d found in %s ? %b"
                ,3
                , Arrays.toString(nums4)
                ,findInRotatedSortedArray(nums4, 3)
        )); // true


        final int[] nums5 = new int[]{3,1,1};
        System.out.println(String.format(
                "%d found in %s ? %b"
                ,3
                , Arrays.toString(nums5)
                ,findInRotatedSortedArray(nums5, 3)
        )); // true

        System.out.println(String.format(
                "%d found in %s ? %b"
                ,1
                , Arrays.toString(nums5)
                ,findInRotatedSortedArray(nums5, 1)
        )); // true

    }

    private static boolean findInRotatedSortedArray(int[] nums, int target) {
        if(nums != null && nums.length > 0) {

            int left = 0 , right = nums.length - 1;

            while (left <= right) {

                final int mid = left + (right - left) / 2;

                if(nums[mid] == target) return true;

                else if(nums[right] == nums[mid]) right--;

                else if(nums[mid] <= nums[right]) {
                    if (target > nums[mid] && target <= nums[right])
                        left = mid + 1;
                    else
                        right = mid - 1;
                }

                else if(nums[left] <= nums[mid]) {
                    if (target < nums[mid] && target >= nums[left])
                        right = mid - 1;
                    else
                        left = mid + 1;
                }
            }

        }
        return false;

    }
}
