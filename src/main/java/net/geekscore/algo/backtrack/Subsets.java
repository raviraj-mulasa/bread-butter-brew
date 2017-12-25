package net.geekscore.algo.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 */

public class Subsets {

    public static void main(String[] args) {
//        int[] nums = new int[]{1,2,3};
//        subsets(nums);
        int[] nums = new int[]{1,2,2};
        subsets(nums);
    }

    private static void subsets(int[] nums) {
        subsetsHelper(nums, new LinkedList<>());
    }

    private static void subsetsHelper(int[] nums, List<Integer> subsetSoFar) {
        System.out.println("subsetsHelper("+Arrays.toString(nums)+","+subsetSoFar+")");
        if(nums == null || nums.length  == 0){
            System.out.println(subsetSoFar);
        } else {
            //Choose
            final int chosen = nums[0];

            //Explore  with element chosen
            subsetSoFar.add(chosen);
            subsetsHelper(Arrays.copyOfRange(nums,1, nums.length), subsetSoFar);

            //Explore  with out element chosen
            subsetSoFar.remove(subsetSoFar.size() - 1);
            subsetsHelper(Arrays.copyOfRange(nums,1, nums.length), subsetSoFar);

            //Un-choose
            nums[0] = chosen;
        }

    }

}
