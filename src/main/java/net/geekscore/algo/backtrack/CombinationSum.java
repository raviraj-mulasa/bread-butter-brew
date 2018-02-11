package net.geekscore.algo.backtrack;

import java.util.*;

/**
 *
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 *
 * The same repeated number may be chosen from C unlimited number of times.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 * [
 *  [7],
 *  [2, 2, 3]
 * ]
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(combinations(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinations(new int[]{1, 2, 3}, 4));
    }

    private static List<List<Integer>> combinations(int[] nums, int target) {
        if(nums == null || nums.length == 0) return Collections.emptyList();
        final List<List<Integer>> combinations = new LinkedList<>();
        combinationsSumToTargetRepeatAllowed(nums, target, combinations, new LinkedList<>(),0);
        return combinations;

    }

    private static void combinationsSumToTargetRepeatAllowed(int[] nums, int target, List<List<Integer>> combinations, List<Integer> combinationSoFar, int start) {
        if(target < 0) return;
        if(target == 0) {
            combinations.add(new ArrayList<>(combinationSoFar));
            return;
        }
        for(int i = start; i < nums.length; i++) {
            combinationSoFar.add(nums[i]);
            combinationsSumToTargetRepeatAllowed(nums, target - nums[i], combinations, combinationSoFar, i);
            combinationSoFar.remove(combinationSoFar.size() - 1);
        }
    }
}