package net.geekscore.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 * [
 *  [2],
 *  [1],
 *  [1,2,2],
 *  [2,2],
 *  [1,2],
 *  []
 * ]
 */

public class SubsetsAllowDup {

    public static void main(String[] args) {
        final int[] nums = new int[]{1,2,3};
        subsetsWithDup(nums);
    }

    private static void subsetsWithDup(int[] nums) {
        if(nums == null){
            return;
        }
        if(nums.length == 1){
            System.out.println(nums);
        } else {
            for (int i = 0; i < nums.length; i++) {
                System.out.println(Arrays.asList(nums).subList(0, i));
                System.out.println(Arrays.asList(nums).subList(i, nums.length));
            }
        }

    }

}
