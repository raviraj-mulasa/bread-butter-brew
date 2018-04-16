package net.geekscore.search;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 *
 * Example 2:
 * Input: [1,3,5,6], 2
 * Output: 1
 *
 * Example 3:
 * Input: [1,3,5,6], 7
 * Output: 4
 *
 * Example 1:
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        System.out.println(position(new int[]{1,3,5,6}, 5)); // 2
        System.out.println(position(new int[]{1,3,5,6}, 2)); // 1
        System.out.println(position(new int[]{1,3,5,6}, 7)); // 4
        System.out.println(position(new int[]{1,3,5,6}, 0)); // 0
    }

    private static int position(final int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        while (low <= high) {
            final int mid = low+(high-low)/2;
            if(nums[mid] < target) low = mid+1;
            else high = mid-1;
        }
        return low;
    }
}
