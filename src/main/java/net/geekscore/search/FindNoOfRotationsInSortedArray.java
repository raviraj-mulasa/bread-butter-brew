package net.geekscore.search;

/**
 * * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the no.of rotations.
 *
 * You may assume no duplicate exists in the array.
 * Example 1:
 * Input: [3,4,5,1,2]
 * Output: 3
 *
 * Example 2:
 * Input: [4,5,6,7,0,1,2]
 * Output: 4
 *
 * Example 3:
 * Input: [0,1,2,4,5,6,7]
 * Output: 0
 */

public class FindNoOfRotationsInSortedArray {

    public static void main(String[] args) {
        System.out.println(noOfRotations( new int[]{3,4,5,1,2})); // 3
        System.out.println(noOfRotations(new int[]{4,5,6,7,0,1,2})); // 4
        System.out.println(noOfRotations(new int[]{0,1,2,4,5,6,7})); // 0
    }

    private static int noOfRotations(final int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] <= nums[right]) break;
            final int mid = left + (right - left) / 2;
            final int next = (mid + 1) % nums.length;
            final int prev = (mid + nums.length - 1) % nums.length;
            if (nums[prev] >= nums[mid] && nums[next] >= nums[mid]) {
                left = mid;
                break;
            }
            if (nums[mid] < nums[right]) {
                right = mid - 1;
                continue;
            }

            if (nums[mid] > nums[left]) {
                left = mid + 1;
            }
        }
        return left;
    }

}
