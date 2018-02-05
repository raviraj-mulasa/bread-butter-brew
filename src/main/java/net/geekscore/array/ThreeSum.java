package net.geekscore.array;

import java.util.*;

/**
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Notice
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 *
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 *  A solution set is:
 *  [
 *      [-1, 0, 1],
 *      [-1, -1, 2]
 *  ]
 *
 **/

public class ThreeSum {

    public static void main(String[] args) {

        System.out.println("---------------------------");
        System.out.println(triplets(new int[]{-1,0,1,2,-1,-4}, 0)); // 3
        System.out.println(triplets(new int[]{0,0,0,0}, 0)); // 3
        System.out.println(triplets(new int[]{0,0,0,0,0}, 0)); // 6
        System.out.println(triplets(new int[]{-2,0,1,1,2}, 0)); // 2

        System.out.println("---------------------------");
        System.out.println(threeSumSort(new int[]{-1,0,1,2,-1,-4}, 0)); // [[-1, 2, -1], [-1, 0, 1], [0, 1, -1]]
        System.out.println(threeSumSort(new int[]{0,0,0,0}, 0)); // [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
        System.out.println(threeSumSort(new int[]{0,0,0,0,0}, 0)); // [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]
        System.out.println(threeSumSort(new int[]{-2,0,1,1,2}, 0)); // [[-2, 0, 2], [-2, 1, 1]]

        System.out.println("---------------------------");
        System.out.println(threeSumSortNoDups(new int[]{-1,0,1,2,-1,-4}, 0)); //[[-1,-1,2],[-1,0,1]]
        System.out.println(threeSumSortNoDups(new int[]{0,0,0,0}, 0)); // [[0,0,0]]
        System.out.println(threeSumSortNoDups(new int[]{0,0,0,0,0}, 0)); // [[0,0,0]]
        System.out.println(threeSumSortNoDups(new int[]{-2,0,1,1,2}, 0)); // [[-2,0,2],[-2,1,1]]
    }

    private static  List<List<Integer>> threeSumSortNoDups(final int nums[], final int target) {

        if(null == nums && nums.length == 0) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        final List<List<Integer>> triplets = new LinkedList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i-1] ) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                final int sum = nums[i] + nums[left] + nums[right];
                if(sum == target) {
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    triplets.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                }
                else if(sum < target) left++;
                else right--;
            }
        }
        return triplets;

    }


    private static  List<List<Integer>> threeSumSort(final int nums[], final int target) {
        if(null == nums && nums.length == 0) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        final List<List<Integer>> triplets = new LinkedList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            final int targetRemaining = target - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                final int sum = nums[left] + nums[right];
                if(sum == targetRemaining) {
                    if(nums[left] != nums[left + 1] && nums[right] != nums[right - 1]) {
                        triplets.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                    } else {
                        if(nums[left] == nums[left + 1]) {
                            triplets.add(Arrays.asList(nums[i], nums[left++], nums[right]));
                        } else {
                            triplets.add(Arrays.asList(nums[i], nums[left], nums[right--]));
                        }
                    }
                }
                else if(sum < targetRemaining) left++;
                else right--;
            }
        }
        return triplets;
    }

    private static  int triplets(final int nums[], final int target) { // todo: fix
        if(null == nums && nums.length == 0) {
            return 0;
        }
        int triplets = 0;
        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 2; i++) {
            final int twoSum = target - nums[i];
            triplets += map.getOrDefault(twoSum, pairs(i, nums, twoSum));
            map.put(nums[i], map.getOrDefault(nums[i], pairs(i, nums, nums[i])) + 1);
        }
        return triplets;
    }

    private static int pairs(int i, int[] nums, int target) {
        final Map<Integer, Integer> map = new HashMap<>();
        int pairs = 0;
        for (int j = i+1; j < nums.length; j++) {
            if(nums[j] != nums[i]) {
                pairs += map.getOrDefault(target - nums[j], 0);
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            }
        }
        return pairs;
    }



}
