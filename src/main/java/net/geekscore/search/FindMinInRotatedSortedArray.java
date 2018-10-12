package net.geekscore.search;

import java.util.Arrays;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 * Example:
 * Input: [3,4,5,1,2]
 * Output: 1
 */
public class FindMinInRotatedSortedArray {

    public static void main(String[] args) {

        final int[] nums = new int[] {4,5,6,7,0,1,2,3};
        System.out.println(String.format(
                "Min in %s is %d"
                , Arrays.toString(nums)
                , minInRotatedSortedArray(nums)
        ));

        final int[] nums1 = new int[] {3,4,5,-2,-1,0,1,2};
        System.out.println(String.format(
                "Min in %s is %d"
                , Arrays.toString(nums1)
                , minInRotatedSortedArray(nums1)
        ));

        final int[] nums2 = new int[] {-2,-1,0,1,2,3,4,5};
        System.out.println(String.format(
                "Min in %s is %d"
                , Arrays.toString(nums2)
                , minInRotatedSortedArray(nums2)
        ));

        final int[] nums3 = new int[] {3,1,2};
        System.out.println(String.format(
                "Min in %s is %d"
                , Arrays.toString(nums3)
                , minInRotatedSortedArray(nums3)
        ));

        final int[] nums4 = new int[] {1,2,3};
        System.out.println(String.format(
                "Min in %s is %d"
                , Arrays.toString(nums4)
                , minInRotatedSortedArray(nums4)
        ));
    }

    private static int minInRotatedSortedArray(final int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {

            if(nums[left] <= nums[right]) return nums[left];

            final int mid = left + (right - left) / 2;
            final int prev = (mid+nums.length-1)%nums.length;
            final int next = (mid+1)%nums.length;
            if(nums[mid] <= nums[prev] && nums[mid] <= nums[next]) return nums[mid];

            if(nums[mid] <= nums[right] ) {
                right = mid - 1;
                continue;
            }
            if(nums[mid] >= nums[left])  {
                left = mid + 1;
            }
        }
        return -1;
    }

}
