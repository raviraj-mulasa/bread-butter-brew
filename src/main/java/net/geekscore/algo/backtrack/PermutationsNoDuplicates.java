package net.geekscore.algo.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * For example,
 *  [1,1,2] have the following unique permutations:
 *      [
 *          [1,1,2],
 *          [1,2,1],
 *          [2,1,1]
 *      ]
 *
 */
public class PermutationsNoDuplicates {

    public static void main(String[] args) {

        Instant start = Instant.now();
        System.out.println(permuteNoDuplicates(new int[]{1,2,3}));
        System.out.println(permuteNoDuplicates(new int[]{4,5}));
        System.out.println(permuteNoDuplicates(new int[]{1,1,2}));
        System.out.println(Duration.between(start, Instant.now()).toNanos());
        System.out.println("-------------------");

        start = Instant.now();
        System.out.println(permutationsUnique(new int[]{1,2,3}));
        System.out.println(permutationsUnique(new int[]{4,5}));
        System.out.println(permutationsUnique(new int[]{1,1,2}));
        System.out.println(Duration.between(start, Instant.now()).toNanos());
        System.out.println("-------------------");
    }

    private static final List<List<Integer>> permuteNoDuplicates(final int[] nums) {
        if(null == nums) return Collections.emptyList();
        final List<Integer> list =  new ArrayList<>(nums.length);
        for (final int num: nums) list.add(num);
        final Set<List<Integer>> permutationsSet = new HashSet<>();
        permuteHelperNoDuplicates(list, new LinkedList(), permutationsSet);
        return new ArrayList<>(permutationsSet);

    }

    private static void permuteHelperNoDuplicates(List<Integer> nums, List<Integer> permutationSoFar, Set<List<Integer>> permutationsSet) {
        if(nums.size() == 0) {
            if(!permutationsSet.contains(permutationSoFar)) {
                final List<Integer> permutation  = new ArrayList<Integer>(permutationSoFar);
                permutationsSet.add(permutation);
            }
        } else {
            for(int i = 0; i < nums.size(); i++) {
                //Choose
                final Integer chosen = nums.remove(i);
                permutationSoFar.add(chosen);

                //Explore
                permuteHelperNoDuplicates(nums, permutationSoFar, permutationsSet);

                // Un-Choose
                nums.add(i, chosen);
                permutationSoFar.remove(permutationSoFar.size() - 1);

            }

        }
    }

    private static List<List<Integer>> permutationsUnique(int[] nums) {
        if(null == nums || nums.length == 0) return Collections.emptyList();
        final boolean[] used = new boolean[nums.length];
        Set<List<Integer>> set = new HashSet<>();
        permutationsUniqueHelper(set, new ArrayList<>(), nums, used);
        return new ArrayList<>(set);
    }

    private static void permutationsUniqueHelper(Set<List<Integer>> permutationsSet, List<Integer> permutationSoFar, int [] nums, final boolean[] used) {
//        System.out.println("permutationsHelper1("+ Arrays.toString(nums)+","+Arrays.toString(used)+","+permutationSoFar+","+permutationsSet+")");
        if(permutationSoFar.size() == nums.length && !permutationsSet.contains(permutationSoFar)) permutationsSet.add(new ArrayList<>(permutationSoFar));
        else{
            for (int i = 0; i < nums.length; i++) {
                if(!used[i]) {
                    permutationSoFar.add(nums[i]);
                    used[i] = true;
                    permutationsUniqueHelper(permutationsSet, permutationSoFar, nums, used);
                    permutationSoFar.remove(permutationSoFar.size() - 1);
                    used[i] = false;
                }
            }
        }
    }
}
