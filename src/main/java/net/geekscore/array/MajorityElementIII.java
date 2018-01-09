package net.geekscore.array;

import java.util.*;

/**
 * Given an array of integers and a number k, the majority number is the number that occurs more than
 * 1/k of the size of the array.
 *
 * Example
 * Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
 */
public class MajorityElementIII {

    public static void main(String[] args) {
        System.out.println(elementsAppearingN_K(new int[0], 3)); // []
        System.out.println(elementsAppearingN_K(new int[]{4,6,2,4,5,0,2,2,2,2,2,2,2,2,2}, 3)); // [2]
        System.out.println(elementsAppearingN_K(new int[]{1, 1, 1, 1, 2, 2, 2}, 3)); // [1, 2]
        System.out.println(elementsAppearingN_K(new int[]{2,2,3,8,7,9,9,6}, 3)); // []
        System.out.println(elementsAppearingN_K(new int[]{2}, 3)); // [2]
        System.out.println(elementsAppearingN_K(new int[]{2,2}, 3)); // [2]
        System.out.println(elementsAppearingN_K(new int[]{2,1,2,4,7}, 3)); // [2]
    }

    /**
     *
     * @param nums
     * @return Majority Element: appears more than ⌊ n/3 ⌋ times using Boyer–Moore majority vote algorithm.
     * There cannot be more than 2 elements that appear more than ⌊ n/3 ⌋ times since
     * (k-1)[(n/k)+1] <= n < k[(n/k)+1]
     *
     */
    private static List<Integer> elementsAppearingN_K(final int[] nums, final int k) {
        if(nums == null || nums.length < 1) {
            return Collections.EMPTY_LIST;
        }
        final int[] candidates = new int[k-1];
        final int[] count = new int[k-1];
        for (final int num: nums) {
            boolean settled = false;

            for (int i = 0; i < candidates.length; i++) {
                if(num == candidates[i]){ // Found the one of the candidates again
                    count[i]++;
                    settled = true;
                    break;
                }
            }
            if(settled) continue;

            for (int i = 0; i < candidates.length; i++) {
                if(0 == count[i]){ // For all candidates whose count is 0, reset
                    count[i] =  num;
                    candidates[i] =  num;
                    settled = true;
                }
            }
            if(settled) continue;

            for (int i = 0; i < candidates.length; i++) {
                count[i]--;
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
                if(count[i] > nums.length / k){
                    elements.add(Integer.valueOf(candidates[i]));
                }
            }
        }
        return new ArrayList<>(elements);
    }
}
