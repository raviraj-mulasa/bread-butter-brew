package net.geekscore.algo.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Given a collection of distinct numbers, return all possible permutations.
 * For example,
 *  [1,2,3] have the following permutations:
 *  [
 *      [1,2,3],
 *      [1,3,2],
 *      [2,1,3],
 *      [2,3,1],
 *      [3,1,2],
 *      [3,2,1]
 *  ]
 */
public class Permutations {

    public static void main(String[] args) {

        Instant start = Instant.now();
        System.out.println(permutations(new int[]{1,2,3}));
        System.out.println(permutations(new int[]{4,5}));
        System.out.println(permutations(new int[]{1,1,2}));
        System.out.println(Duration.between(start, Instant.now()).toNanos());
        System.out.println("-------------------");

        start = Instant.now();
        System.out.println(permutations1(new int[]{1,2,3}));
        System.out.println(permutations1(new int[]{4,5}));
        System.out.println(permutations1(new int[]{1,1,2}));
        System.out.println(Duration.between(start, Instant.now()).toNanos());
        System.out.println("-------------------");

    }

    private static final List<List<Integer>> permutations(int[] nums) {
        if(null == nums){
            return Collections.emptyList();
        }
        final List<Integer> numsList = new ArrayList<>(nums.length);
        for (int num : nums) numsList.add(num);

        final List<List<Integer>> permutationsList = new LinkedList<>();
        permutationsHelper(numsList, new LinkedList<>(), permutationsList);
        return permutationsList;

    }

    private static void permutationsHelper(List<Integer> nums, List<Integer> permutationSoFar, List<List<Integer>> permutationsList) {
//        System.out.println("permutationsHelper("+nums+","+permutationSoFar+","+permutationsList+")");
        if(nums.size() == 0) {
            final List<Integer> permutation  = new ArrayList<Integer>(permutationSoFar);
            permutationsList.add(permutation);
        } else {
            for(int i = 0; i < nums.size(); i++) {

                //Choose
                final Integer chosen = nums.remove(i);
                permutationSoFar.add(chosen);

                //Explore
                permutationsHelper(nums, permutationSoFar, permutationsList);

                // Un-Choose
                nums.add(i, chosen);
                permutationSoFar.remove(permutationSoFar.size() - 1);
            }

        }
    }

    private static List<List<Integer>> permutations1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permutationsHelper1(list, new ArrayList<>(), nums);
        return list;
    }

    private static void permutationsHelper1(List<List<Integer>> permutationsList, List<Integer> permutationSoFar, int [] nums) {
//        System.out.println("permutationsHelper1("+ Arrays.toString(nums)+","+permutationSoFar+","+permutationsList+")");
        if(null == nums || nums.length == 0) return;
        if(permutationSoFar.size() == nums.length) permutationsList.add(new ArrayList<>(permutationSoFar));
        else{
            for (int num : nums) {
                if (permutationSoFar.contains(num)) continue; // element already exists, skip
                permutationSoFar.add(num);
                permutationsHelper1(permutationsList, permutationSoFar, nums);
                permutationSoFar.remove(permutationSoFar.size() - 1);
            }
        }
    }
}
