package net.geekscore.algo.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 *
 * Also knows as PowerSet
 *
 * Given a set of distinct integers, nums, return all possible combinations (the power set).
 * Note: The solution set must not contain duplicate combinations.
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

public class Combinations {

    public static void main(String[] args) {

        final int[] nums  = new int[]{1,2,2};
        final int[] nums1 = new int[]{1,2,3};
        final int[] nums2 = new int[]{1,1,1};

        Instant start = Instant.now();

        System.out.println("Combinations of "+Arrays.toString(nums)+" : "+combinations(nums));
        System.out.println("Combinations of "+Arrays.toString(nums1)+" : "+combinations(nums1));
        System.out.println("Combinations of "+Arrays.toString(nums2)+" : "+combinations(nums2));

        System.out.println(Duration.between(start, Instant.now()).toNanos());

        System.out.println("-------------------");

        start = Instant.now();

        System.out.println("Power Set of "+Arrays.toString(nums)+" : "+ powerSet(nums));
        System.out.println("Power Set of "+Arrays.toString(nums1)+" : "+ powerSet(nums1));
        System.out.println("Power Set of "+Arrays.toString(nums2)+" : "+ powerSet(nums2));

        System.out.println(Duration.between(start, Instant.now()).toNanos());



    }

    private static List<List<Integer>> combinations(int[] nums) {
        if(null == nums || nums.length == 0) return Collections.emptyList();
        final List<List<Integer>> combinationsList = new LinkedList<>();
        List<Integer> numsList = new ArrayList<>(nums.length);
        for (final int num: nums) numsList.add(num);
        combinationsHelper(numsList, combinationsList, new LinkedList<>());
        return combinationsList;
    }

    private static void combinationsHelper(List<Integer> numsList, List<List<Integer>> combinations,List<Integer> combinationSoFar) {
//        System.out.println("combinationsHelper("+numsList+","+combinations+","+combinationSoFar+")");
        if(numsList.size()  == 0) combinations.add(new ArrayList<>(combinationSoFar));
        else {
            //Choose
            final int chosen = numsList.get(0);

            //Explore  with element chosen
            combinationSoFar.add(chosen);
            combinationsHelper(numsList.subList(1, numsList.size()), combinations, combinationSoFar);

            //Explore  with out element chosen
            combinationSoFar.remove(combinationSoFar.size() - 1);
            combinationsHelper(numsList.subList(1, numsList.size()), combinations, combinationSoFar);


        }
    }

    private static List<List<Integer>> powerSet(int[] nums) {
        final List<List<Integer>> subSets = new LinkedList<>();
        subSetEndingAtIndex(subSets, new LinkedList<>(), nums, 0);
        return subSets;
    }


    private static void subSetEndingAtIndex(List<List<Integer>> subSets, List<Integer> subSetSoFar, int[] nums, int index) {
//        System.out.println("subSetEndingAtIndex("+subSets+","+subSetSoFar+","+Arrays.toString(nums)+","+index+")");
        subSets.add(new ArrayList<>(subSetSoFar));
        for (int i = index; i < nums.length; i++) {
            subSetSoFar.add(nums[i]); //Choose
            subSetEndingAtIndex(subSets, subSetSoFar, nums, i+1); //Explore  with element chosen
            subSetSoFar.remove(subSetSoFar.size() - 1); // backtrack by un choosing
        }
    }

}
