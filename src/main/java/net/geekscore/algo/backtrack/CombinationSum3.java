package net.geekscore.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers
 * from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 *
 */

public class CombinationSum3 {

    public static void main(String[] args) {

        System.out.println(combinationKOfNSum2Target(7, 3)); // [[1, 2, 4]]
        System.out.println(combinationKOfNSum2Target(9, 3)); // [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
        System.out.println(combinationKOfNSum2Target(0, 3)); // []
        System.out.println(combinationKOfNSum2Target(3, 0)); // []
        System.out.println(combinationKOfNSum2Target(1, 1)); // [[1]]
        System.out.println(combinationKOfNSum2Target(18, 2)); // []
        System.out.println(combinationKOfNSum2Target(2, 18)); // []
        System.out.println(combinationKOfNSum2Target(15, 3)); // [[1, 5, 9], [1, 6, 8], [2, 4, 9], [2, 5, 8], [2, 6, 7], [3, 4, 8], [3, 5, 7], [4, 5, 6]]
        System.out.println(combinationKOfNSum2Target(3, 15)); // []
    }

    private static List<List<Integer>> combinationKOfNSum2Target(int n, int k) {
        List<List<Integer>> combinations = new LinkedList<>();
        combinationKOfNSum2Target(combinations, new LinkedList<>(), 1, k, n, n);
        return combinations;
    }

    private static void combinationKOfNSum2Target(List<List<Integer>> combinations, List<Integer> combinationSoFar, int i, int k, int n, int target) {
        if(target < 0) return;
        if(k == 0 && target == 0) {
            combinations.add(new ArrayList<>(combinationSoFar));
            return;
        }
        if(i > 9 || i > n || k < 0) return;
        combinationSoFar.add(i);
        combinationKOfNSum2Target(combinations, combinationSoFar, i+1, k-1, n, target - i); // C(n-1, k-1);
        combinationSoFar.remove(combinationSoFar.size()-1);
        combinationKOfNSum2Target(combinations, combinationSoFar, i+1, k, n, target); // C(n-1, k);
    }
}
