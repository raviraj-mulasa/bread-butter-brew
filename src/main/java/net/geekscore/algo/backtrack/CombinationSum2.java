package net.geekscore.algo.backtrack;

import java.util.*;

/**
 *
 *
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations
 * in C where the candidate numbers sums to T.
 *
 * Each number in C may only be used once in the combination.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 *
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        System.out.println(combinations(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(combinations(new int[]{1}, 1));
    }

    private static List<List<Integer>> combinations(int[] nums, int target) {
        if(nums == null || nums.length == 0) return Collections.emptyList();
        final List<List<Integer>> combinations = new LinkedList<>();
        Arrays.sort(nums);
        combinationsSumToTarget(nums, target, combinations, new LinkedList<>(), 0);
        return combinations;

    }

    private static void combinationsSumToTarget(int[] nums, int target, List<List<Integer>> combinations, List<Integer> combinationSoFar, int start) {
        if(target < 0) return;
        if(target == 0) {
            combinations.add(new ArrayList<>(combinationSoFar));
            return;
        }
        if (start >= nums.length) return;
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]) continue;
            combinationSoFar.add(nums[i]);
            combinationsSumToTarget(nums, target - nums[i], combinations, combinationSoFar, i+1);
            combinationSoFar.remove(combinationSoFar.size() - 1);
        }

    }
}
