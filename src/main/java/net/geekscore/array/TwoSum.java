package net.geekscore.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 13)));
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 0, 11, 15, 6, -2}, 13)));
    }


    public static final int[] twoSum(int[] nums, int target) {

        int[] indices = new int[0];
        if(null == nums && nums.length == 0) {
            return indices;
        }
        if(nums.length == 1 && target == nums[0]) {
            indices = new int[]{0,0};
        } else {
            final Map<Integer, Integer> elemIdxMap = IntStream
                    .rangeClosed(0, nums.length - 1)
                    .boxed()
                    .collect(Collectors.toMap(i -> nums[i], Function.identity()));
            for(int i = 0; i < nums.length; i++) {
                final int elemTwoSum = target - nums[i];
                final Integer elemTwoSumIdx = elemIdxMap.get(Integer.valueOf(elemTwoSum));
                if(null != elemTwoSumIdx && i != elemTwoSumIdx.intValue()) {
                    indices = new int[]{i, elemTwoSumIdx.intValue()};
                    break;
                }
            }
        }
        return indices;
    }
}
