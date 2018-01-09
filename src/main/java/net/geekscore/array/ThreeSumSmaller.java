package net.geekscore.array;

import java.util.Arrays;

/**
 *
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n
 * that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 * Follow up: Could you solve it in O(n2) runtime?
 *
 *
 */
public class ThreeSumSmaller {

    public static void main(String[] args) {
        System.out.println(sumSmallerTriplets(new int[0], 0)); // 0
        System.out.println(sumSmallerTriplets(new int[]{-2, 0, 1, 3}, 2)); // 2
        System.out.println(sumSmallerTriplets(new int[]{-2,1,-1,2}, -2)); // 0
        System.out.println(sumSmallerTriplets(new int[]{3,1,0,-2}, 4));  // 3
        System.out.println(sumSmallerTriplets(new int[]{-1,1,-1,-1}, -1));  // 1

    }


    private static final int sumSmallerTriplets(int[] nums, int target) {
        if(null == nums || nums.length < 3){
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length-2; i++) {
            int left    = i + 1;
            int right   = nums.length - 1;
            while (left < right) {
                final int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    count += (right - left);
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }
}
