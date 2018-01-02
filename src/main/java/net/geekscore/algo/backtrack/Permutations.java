package net.geekscore.algo.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
        System.out.println(permute(new int[]{1,2,3}));
        System.out.println(permute(new int[]{4,5}));
        System.out.println(permute(new int[]{1,1,2}));
    }

    private static final List<List<Integer>> permute(int[] nums) {
        if(null == nums){
            return Collections.EMPTY_LIST;
        }
        final List<Integer> numsList = new ArrayList<>(nums.length);
        for(int i = 0; i < nums.length; i++) {
            numsList.add(nums[i]);
        }
        final List<List<Integer>> permutationsList = new LinkedList<>();
        permuteHelper(numsList, new LinkedList(), permutationsList);
        return permutationsList;

    }

    private static void permuteHelper(List<Integer> numsList, List<Integer> permutationSoFar, List<List<Integer>> permutationsList) {
        if(numsList.size() == 0) {
            final List<Integer> permutation  = new ArrayList(permutationSoFar);
            permutationsList.add(permutation);
        } else {
            for(int i = 0; i < numsList.size(); i++) {
                //Choose
                final Integer chosen = numsList.get(i);
                permutationSoFar.add(chosen);
                numsList.remove(i);

                //Explore
                permuteHelper(numsList, permutationSoFar, permutationsList);

                // Un-Choose
                numsList.add(i, chosen);
                permutationSoFar.remove(permutationSoFar.size() - 1);

            }

        }
    }
}
