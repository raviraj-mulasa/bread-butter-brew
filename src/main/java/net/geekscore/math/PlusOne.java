package net.geekscore.math;

import java.util.Arrays;

/**
 * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class PlusOne {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{9,9,9}))); // [1,0,0,0]
        System.out.println(Arrays.toString(plusOne(new int[]{9,9,0}))); // [9.9.1]
        System.out.println(Arrays.toString(plusOne(new int[]{0,0,0}))); // [0.0.1]
    }

    private static int[] plusOne(int[] digits) {
        if(null == digits || digits.length == 0) return new int[0];
        int carry = 1;
        int i = digits.length -1;
        final int[] result = new int[digits.length+1];
        while (i > -1) {
            final int sum = digits[i] + carry;
            result[i+1] = sum%10;
            carry = sum/10;
            i--;
        }
        if(carry == 1) {
            result[0] = carry;
            return result;
        }
        return Arrays.copyOfRange(result, 1, result.length);

    }
}
