package net.geekscore.array;

import java.util.*;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 *
 * Example
 * Given [1, 2, 1, 2, 1, 3, 3], return 1.
 *
 * https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
 *
 **/
public class MajorityElementII {

    public static void main(String[] args) {
        System.out.println(elementsAppearingN_3(new int[0])); // []
        System.out.println(elementsAppearingN_3(new int[]{4,6,2,4,5,0,2,2,2,2,2,2,2,2,2})); // [2]
        System.out.println(elementsAppearingN_3(new int[]{1, 1, 1, 1, 2, 2, 2})); // [1, 2]
        System.out.println(elementsAppearingN_3(new int[]{2,2,3,8,7,9,9,6})); // []
        System.out.println(elementsAppearingN_3(new int[]{2})); // [2]
        System.out.println(elementsAppearingN_3(new int[]{2,2})); // [2]
        System.out.println(elementsAppearingN_3(new int[]{2,1,2,4,7})); // [2]
    }

    /**
     *
     * @param nums
     * @return Majority Element: appears more than ⌊ n/3 ⌋ times using Boyer–Moore majority vote algorithm.
     * There cannot be more than 2 elements that appear more than ⌊ n/3 ⌋ times since
     * 2[(n/3)+1] <= n < 3[(n/3)+1]
     *
     */
    private static List<Integer> elementsAppearingN_3(final int[] nums) {
        if(nums == null || nums.length < 1) {
            return Collections.EMPTY_LIST;
        }
        final int[] candidates = new int[2];
        final int[] count = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == candidates[0]){
                count[0]++;
            } else if(nums[i] == candidates[1]){
                count[1]++;
            } else if(count[0] == 0){
                count[0] = 1;
                candidates[0] = nums[i];
            } else if(count[1] == 0){
                count[1] = 1;
                candidates[1] = nums[i];
            } else {
                count[0]--;
                count[1]--;
            }
        }
//        System.out.println("Candidates: "+ Arrays.toString(candidates));
        final Set<Integer> elements = new HashSet<>();
        Arrays.fill(count, 0);
        for (final int num: nums) {
            for (int i = 0; i < candidates.length; i++) {
                if(num == candidates[i]){
                    count[i]++;
                }
                if(count[i] > nums.length / 3){
                    elements.add(Integer.valueOf(candidates[i]));
                }
            }
        }
        return new ArrayList<>(elements);
    }
}
