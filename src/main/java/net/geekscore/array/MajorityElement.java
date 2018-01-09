package net.geekscore.array;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example
 * Given [1, 1, 1, 1, 2, 2, 2], return 1
 *
 * * https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
 *
 */
public class MajorityElement {

    public static void main(String[] args) {
        System.out.println(majorityElementMap(new int[]{4,6,2,4,5,0,2,2,2,2,2,2,2,2,2})); // 2
        System.out.println(majorityElementMap(new int[]{1, 1, 1, 1, 2, 2, 2})); // 1
        System.out.println(majorityElementMap(new int[]{2,2,3,8,7,9,9,6})); // null
        System.out.println(majorityElementMap(new int[]{2})); // null
        System.out.println(majorityElementMap(new int[]{2,2})); // 2
        System.out.println(majorityElementMap(new int[]{2,1,2,4,7})); // 2

        System.out.println("--------------------------------");

        System.out.println(majorityElement(new int[]{4,6,2,4,5,0,2,2,2,2,2,2,2,2,2})); // 2
        System.out.println(majorityElement(new int[]{1, 1, 1, 1, 2, 2, 2})); // 1
        System.out.println(majorityElement(new int[]{2,2,3,8,7,9,9,6})); // null
        System.out.println(majorityElement(new int[]{2})); // null
        System.out.println(majorityElement(new int[]{2,2})); // 2
        System.out.println(majorityElementMap(new int[]{2,1,2,4,7})); // 2
    }

    /**
     *
     * @param nums
     * @return Majority Element: appears more than ⌊ n/2 ⌋ times using Map
     *
     */
    private static Integer majorityElementMap(final int[] nums) {
        if(nums == null || nums.length < 1) {
            return null;
        }
        final Integer nBy2 = Integer.valueOf(nums.length / 2);
        final Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            final Integer num = nums[i];
            Integer numCount  = countMap.get(num);
            if(numCount == null){
                numCount = 1;
            }
            countMap.put(num, numCount + 1);
            if(countMap.get(num).compareTo(nBy2) > 0){
                return num;
            }
        }
        return null;
    }

    /**
     *
     * @param nums
     * @return Majority Element: appears more than ⌊ n/2 ⌋ times using Boyer–Moore majority vote algorithm.
     *
     */
    private static Integer majorityElement(final int[] nums) {
        if(nums == null || nums.length < 1) {
            return null;
        }
        final int candidate = majorityCandidateBoyerMoore(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(candidate == nums[i]){
                count++;
            }
            if(count > nums.length / 2){
                return Integer.valueOf(candidate);
            }
        }
        return null;
    }

    /**
     *
     * @param nums
     * @return Majority candidate using Boyer–Moore majority vote algorithm.
     */
    private static int majorityCandidateBoyerMoore(final int[] nums){
        int votes       = 1;
        int candidate   = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(candidate == nums[i]){
                votes++;
            }else {
                votes--;
            }
            if(votes == 0) {
                candidate = nums[i];
                votes = 1;
            }
        }
        return candidate;
    }

}
