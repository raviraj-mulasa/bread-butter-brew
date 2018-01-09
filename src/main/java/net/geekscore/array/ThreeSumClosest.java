package net.geekscore.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 **/
public class ThreeSumClosest {

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1)); // 2
        System.out.println(threeSumClosest(new int[]{1,1,1,0}, -100)); // 2
        System.out.println(threeSumClosest(new int[]{1,1,1,0}, 100)); // 3

    }

    private static final int threeSumClosest(int[] nums, int target) {
        return sumClosestTriplet(nums, target).stream().mapToInt(Integer::intValue).sum();
    }

    private static final List<Integer> sumClosestTriplet(int[] nums, int target) {
        if(null == nums || nums.length < 1){
            return Collections.EMPTY_LIST;
        }

        Arrays.sort(nums);
        List<Integer> triplet = Collections.EMPTY_LIST;
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length-2; i++) {

            int left    = i + 1;
            int right   = nums.length - 1;

            while (left < right) {
                final int sum = nums[i] + nums[left] + nums[right];
                final int diff = Math.abs(target - sum);
                if (diff <= minDiff) {
                    minDiff = diff;
                    triplet  = Arrays.asList(nums[i], nums[left], nums[right]);
                    if(diff == 0) return triplet;
                }
                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }

        }
        return triplet;

    }
}
