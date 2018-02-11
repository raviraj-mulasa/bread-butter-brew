package net.geekscore.algo.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class CombinationsNoDuplicates {

    public static void main(String[] args) {

        final int[] nums  = new int[]{1,2,2};
        final int[] nums1 = new int[]{1,2,3};
        final int[] nums2 = new int[]{1,1,1};

        Instant start = Instant.now();

        System.out.println("Combinations No Duplicates "+ Arrays.toString(nums)+" : "+ combinationsNoDupsBySort(nums));
        System.out.println("Combinations No Duplicates "+Arrays.toString(nums1)+" : "+ combinationsNoDupsBySort(nums1));
        System.out.println("Combinations No Duplicates "+Arrays.toString(nums2)+" : "+ combinationsNoDupsBySort(nums2));

        System.out.println(Duration.between(start, Instant.now()).toNanos());

        System.out.println("-------------------");

        start = Instant.now();

        System.out.println("Power Set of "+Arrays.toString(nums)+" : "+ uniqueCombinations(nums));
        System.out.println("Power Set of "+Arrays.toString(nums1)+" : "+ uniqueCombinations(nums1));
        System.out.println("Power Set of "+Arrays.toString(nums2)+" : "+ uniqueCombinations(nums2));

        System.out.println(Duration.between(start, Instant.now()).toNanos());


    }

    private static List<List<Integer>> combinationsNoDupsBySort(int[] nums) {
        final List<List<Integer>> subSets = new LinkedList<>();
        Arrays.sort(nums);
        uniqueCombinationsEndingAtIndex(subSets, new LinkedList<>(), nums, 0);
        return subSets;
    }


    private static void uniqueCombinationsEndingAtIndex(List<List<Integer>> subSets, List<Integer> subSetSoFar, int[] nums, int index) {
//        System.out.println("uniqueCombinationsEndingAtIndex("+subSets+","+subSetSoFar+","+Arrays.toString(nums)+","+index+")");
        subSets.add(new ArrayList<>(subSetSoFar));
        for (int i = index; i < nums.length; i++) {
            if(i > index && nums[i] == nums[i-1]) continue; // skip duplicates
            subSetSoFar.add(nums[i]); //Choose
            uniqueCombinationsEndingAtIndex(subSets, subSetSoFar, nums, i+1); //Explore  with element chosen
            subSetSoFar.remove(subSetSoFar.size() - 1); // backtrack by un choosing
        }
    }

    private static List<List<Integer>> uniqueCombinations(int[] nums) {
        final Set<List<Integer>> uniqueCombinations = new HashSet<>();
        combinationsHelper(nums, uniqueCombinations, new LinkedList<>());
        return new ArrayList<>(uniqueCombinations);
    }

    private static void combinationsHelper(int[] nums, Set<List<Integer>> uniqueCombinations, List<Integer> combinationSoFar) {
//        System.out.println("combinationsHelper("+Arrays.toString(nums)+","+uniqueCombinations+","+combinationSoFar+")");
        if(nums == null || nums.length  == 0){
            if(!uniqueCombinations.contains(combinationSoFar)) {
                uniqueCombinations.add(new ArrayList<>(combinationSoFar));
            }
        } else {
            //Choose
            final int chosen = nums[0];
            //Explore  with element chosen
            combinationSoFar.add(chosen);
            combinationsHelper(Arrays.copyOfRange(nums,1, nums.length), uniqueCombinations, combinationSoFar);
            //Explore  with out element chosen -Unchoose
            combinationSoFar.remove(combinationSoFar.size() - 1);
            combinationsHelper(Arrays.copyOfRange(nums,1, nums.length), uniqueCombinations, combinationSoFar);

        }
    }
}
