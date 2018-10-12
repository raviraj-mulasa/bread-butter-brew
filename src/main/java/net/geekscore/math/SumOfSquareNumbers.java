package net.geekscore.math;

/**
 *
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b
 * such that a2 + b2 = c.
 *
 * Example 1:
 *  Input: 5
 *  Output: True  Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 *  Input: 3
 *  Output: False
 */
public class SumOfSquareNumbers {

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(0)); // true
        System.out.println(judgeSquareSum(1)); // true
        System.out.println(judgeSquareSum(2)); // true
        System.out.println(judgeSquareSum(3)); // false
        System.out.println(judgeSquareSum(5)); // true
        System.out.println(judgeSquareSum(7)); // false
        System.out.println(judgeSquareSum(8));  // true
        System.out.println(judgeSquareSum(10)); // true
        System.out.println(judgeSquareSum(9)); // true
        System.out.println(judgeSquareSum(4)); // true
    }

    private static boolean judgeSquareSum(int c) {
        if(c==0) return true;
        for(int i = 0, j = (int) Math.sqrt(c); i <= j;) {
            final int sumSquared = i * i + j * j;
            if(sumSquared == c) return true;
            if(sumSquared < c) i++;
            else j--;
        }
        return false;
    }
}
