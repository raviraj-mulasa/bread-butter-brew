package net.geekscore.bit;

/**
 *
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 *
 * Example 1
 * Input: [3,0,1] Output: 2
 *
 * Example 2
 * Input: [9,6,4,2,3,5,7,0,1] Output: 8
 *
 * Note:
 * Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant extra space complexity?
 */
public class MissingNumber {

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[] {3,0,1})); // 2
        System.out.println(missingNumber(new int[] {9,6,4,2,3,5,7,0,1})); // 8
        System.out.println(missingNumber(new int[] {0})); // 1
        System.out.println(missingNumber(new int[] {})); // 0
    }

    private static int missingNumber(int[] nums) {
        int sum = 0;
        for(final int num: nums) sum += num;
        return ((nums.length + 1) * nums.length) / 2 - sum;
    }
}
