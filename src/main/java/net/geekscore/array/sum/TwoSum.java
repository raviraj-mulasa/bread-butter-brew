package net.geekscore.array.sum;

import java.util.*;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,return [0, 1].
 *
 **/

public class TwoSum {

    public static void main(String[] args) {
        System.out.println(twoSumMap(new int[]{2, 7, 11, 15}, 9)); //  [[2, 7]]
        System.out.println(twoSumMap(new int[]{2, 7, 11, 15}, 13)); // [[2, 11]]
        System.out.println(twoSumMap(new int[]{2, 7, 0, 5, 9, 6, -2}, 7)); // [[7, 0], [2, 5], [9, -2]]
        System.out.println(twoSumMap(new int[]{2, 7, 0, 11, 15, 6, -2}, 13)); // [[2, 11], [7, 6], [15, -2]]

        System.out.println(twoSumMap(new int[]{2, 2, 2, 2}, 4)); // [[2, 2], [2, 2] , [2, 2], [2, 2], [2, 2] , [2, 2]]
        System.out.println(twoSumMap(new int[]{2, 2, 2}, 4)); // [[2, 2], [2, 2] , [2, 2]]
        System.out.println(twoSumMap(new int[]{2, 2}, 4)); // [[2, 2]]


        System.out.println("--------------------------------------");
        System.out.println(pairsMap(new int[]{2, 7, 11, 15}, 9)); // 1
        System.out.println(pairsMap(new int[]{2, 7, 11, 15}, 13)); // 1
        System.out.println(pairsMap(new int[]{2, 7, 0, 5, 9, 6, -2}, 7)); // 3
        System.out.println(pairsMap(new int[]{2, 7, 0, 11, 15, 6, -2}, 13)); // 3

        System.out.println(pairsMap(new int[]{2, 2, 2, 2}, 4)); // 6
        System.out.println(pairsMap(new int[]{2, 2, 2}, 4)); // 3
        System.out.println(pairsMap(new int[]{2, 2}, 4)); // 1


        System.out.println("--------------------------------------");
        System.out.println(pairsSort(new int[]{2, 7, 11, 15}, 9)); // 1
        System.out.println(pairsSort(new int[]{2, 7, 11, 15}, 13)); // 1
        System.out.println(pairsSort(new int[]{2, 7, 0, 5, 9, 6, -2}, 7)); // 3
        System.out.println(pairsSort(new int[]{2, 7, 0, 11, 15, 6, -2}, 13)); // 3

        System.out.println(pairsSort(new int[]{2, 2, 2, 2}, 4)); // 6
        System.out.println(pairsSort(new int[]{2, 2, 2}, 4)); // 3
        System.out.println(pairsSort(new int[]{2, 2}, 4)); // 1
    }


    private static final List<List<Integer>> twoSumMap(int[] nums, int target) {

        if(null == nums && nums.length == 0) return Collections.emptyList();
        List<List<Integer>> twoSumPairs = new LinkedList<>();
        final Map<Integer, Integer> map = new HashMap<>();
        for(final Integer num: nums) {
            for (int i = 0; i < map.getOrDefault(target- num, 0); i++) {
                twoSumPairs.add(Arrays.asList((target-num), num));
            }
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        return twoSumPairs;
    }


    private static final int pairsMap(int[] nums, int target) {
        if(null == nums && nums.length == 0) return 0;
        final Map<Integer, Integer> map = new HashMap<>();
        int pairs = 0;
        for(final Integer num: nums) {
            pairs += map.getOrDefault(target- num, 0);
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        return pairs;
    }


    private static final int pairsSort(int[] nums, int target) { // todo: fix
        if(null == nums && nums.length == 0) return 0;
        Arrays.sort(nums);
        int pairs = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            final int sum = nums[left] + nums[right];
            if (sum == target) {
                pairs++;
                while (left < right && nums[left] == nums[left+1]) {left++;pairs++;}
                while (right >= 1 && nums[right] == nums[right-1]) {right--;pairs++;}
                left++;
                right--;
            } else if(sum < target) left++;
            else right--;
        }
        return pairs;
    }
}
