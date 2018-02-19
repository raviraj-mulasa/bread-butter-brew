package net.geekscore.algo.backtrack;

import java.util.*;

/**
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1..n.
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combinations_K_OutOf_N {

    public static void main(String[] args) {

        System.out.println(String.format("Combinations of %d numbers out of %d : ", 2 ,4)+ kOutOfNBacktrack(2,4));
        System.out.println(String.format("Combinations of %d numbers out of %d : ", 3 ,4)+ kOutOfNBacktrack(3,4));
        System.out.println(String.format("Combinations of %d numbers out of %d : ", 1 ,4)+ kOutOfNBacktrack(1,4));
        System.out.println(String.format("Combinations of %d numbers out of %d : ", 2 ,5)+ kOutOfNBacktrack(2,5));

        System.out.println("---------------------");

        System.out.println(String.format("Combinations of %d numbers out of %d : ", 2 ,4)+ kOutOfNFormula(2,4));
        System.out.println(String.format("Combinations of %d numbers out of %d : ", 3 ,4)+ kOutOfNFormula(3,4));
        System.out.println(String.format("Combinations of %d numbers out of %d : ", 1 ,4)+ kOutOfNFormula(1,4));
        System.out.println(String.format("Combinations of %d numbers out of %d : ", 2 ,5)+ kOutOfNFormula(2,5));


        System.out.println("---------------------");

        System.out.println(String.format("Choose %d numbers out of %s : ", 2 ,Arrays.toString(new int[] {1,2,3,4}))
                + kOutOfArray(2,new int[] {1,2,3,4}));
        System.out.println(String.format("Choose %d numbers out of %s : ", 3 ,Arrays.toString(new int[] {1,2,3,4}))
                + kOutOfArray(3,new int[] {1,2,3,4}));
        System.out.println(String.format("Choose %d numbers out of %s : ", 1 ,Arrays.toString(new int[] {1,2,3,4}))
                + kOutOfArray(1,new int[] {1,2,3,4}));
        System.out.println(String.format("Choose %d numbers out of %s : ", 2 ,Arrays.toString(new int[] {1,2,3,4,5}))
                + kOutOfArray(2,new int[] {1,2,3,4,5}));
    }

    private static List<List<Integer>> kOutOfNBacktrack(int k, int n) {
        final List<List<Integer>> combinations = new LinkedList<>();
        kOutOfNBacktrackHelper(combinations, new LinkedList<>(), k, n, 1);
        return combinations;
    }

    private static void kOutOfNBacktrackHelper(List<List<Integer>> combinations, List<Integer> combinationSoFar, int k, int n, int index) {
        if(k==0) {
            combinations.add(new ArrayList<>(combinationSoFar));
            return;
        }
        for (int i = index; i <= n; i++) { // todo : fix here
            combinationSoFar.add(i); // choose
            kOutOfNBacktrackHelper(combinations, combinationSoFar, k-1, n, index+1); // explore
            combinationSoFar.remove(combinationSoFar.size() - 1); // un choose
        }
    }

    // C(n,k) = C(n-1,k) + C(n-1, k-1)
    private static List<List<Integer>> kOutOfNFormula(int k, int n) {
        final List<List<Integer>> combinations = new LinkedList<>();
        kOutOfNFormulaHelper(combinations, new LinkedList<>(), k, n, 1);
        return combinations;
    }
    
    
    private static void kOutOfNFormulaHelper(List<List<Integer>> combinations, List<Integer> combinationSoFar, int k, int n, int start) {
        if(k<=0) {
            combinations.add(new ArrayList<>(combinationSoFar));
            return;
        }
        if(start > n) return;
        combinationSoFar.add(start);
        kOutOfNFormulaHelper(combinations, combinationSoFar, k-1, n, start+1);  // C(n-1,k-1)
        combinationSoFar.remove(combinationSoFar.size()-1);
        kOutOfNFormulaHelper(combinations, combinationSoFar, k, n, start+1);  // C(n-1,k)
    }


    private static List<List<Integer>> kOutOfArray(int k, int[] nums) {
        if(nums == null || nums.length == 0 || k<0) return Collections.emptyList();
        final List<List<Integer>> combinations = new LinkedList<>();
        kOutOfArrayHelper(combinations, new LinkedList<>(), k, nums, 0);
        return combinations;
    }


    private static void kOutOfArrayHelper(List<List<Integer>> combinations, List<Integer> combinationSoFar, int k, int[] nums, int start) {
        if(k==0) {
            combinations.add(new ArrayList<>(combinationSoFar));
            return;
        }
        if(start >= nums.length) return;
        combinationSoFar.add(nums[start]);
        kOutOfArrayHelper(combinations, combinationSoFar, k-1, nums, start+1);  // C(n-1,k-1)
        combinationSoFar.remove(combinationSoFar.size()-1);
        kOutOfArrayHelper(combinations, combinationSoFar, k, nums, start+1);  // C(n-1,k)
    }




}
