package net.geekscore.bit;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

public class SingleNumber {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1,2,4,3,2,3,4})); // 1
        System.out.println(singleNumber(new int[]{-11,2,-4,-11,2,3,-4})); // 3
    }

    private static int singleNumber(int[] nums) {
        int num = 0;
        for (int num1 : nums) num ^= num1;
        return num;
    }
}
