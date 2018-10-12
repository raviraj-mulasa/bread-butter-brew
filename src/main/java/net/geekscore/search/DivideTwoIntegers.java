package net.geekscore.search;

/**
 * Given two integers dividend and divisor, divide two integers without using
 * multiplication, division and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 *
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 *
 * Note:
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit
 * signed integer range: [−231,  231 − 1].
 * For the purpose of this problem, assume that your function returns 231 − 1 when the division
 * result overflows.
 */
public class DivideTwoIntegers {


    public static void main(String[] args) {
        System.out.println(quotient(10, 3)); // 3
        System.out.println(quotient(7, -3)); // -2
        System.out.println(quotient(-11, -3)); // 3
        System.out.println(quotient(0, 9)); // 0

        System.out.println(quotient(-12, -2)); // 6
        System.out.println(quotient(-12, 6)); // -2

        System.out.println(quotient(Integer.MIN_VALUE, -1)); // 2147483647
        System.out.println(quotient(Integer.MIN_VALUE, 1)); // -2147483648
        System.out.println(quotient(Integer.MIN_VALUE, 2)); // -1073741824
        System.out.println(quotient(Integer.MIN_VALUE, -3)); // 715827882

        System.out.println(quotient(Integer.MAX_VALUE, -1)); // -2147483647
        System.out.println(quotient(Integer.MAX_VALUE, 1)); // 2147483647

        System.out.println(quotient(1, -1)); // -1
        System.out.println(quotient(-1, 1)); // -1
        System.out.println(quotient(1, 1)); // 1
        System.out.println(quotient(1, 1)); // 1

        System.out.println("----------------");

        System.out.println(quotientEfficient(10, 3)); // 3
        System.out.println(quotientEfficient(7, -3)); // -2
        System.out.println(quotientEfficient(-11, -3)); // 3
        System.out.println(quotientEfficient(0, 9)); // 0

        System.out.println(quotientEfficient(-12, -2)); // 6
        System.out.println(quotientEfficient(-12, 6)); // -2

        System.out.println(quotientEfficient(Integer.MIN_VALUE, -1)); // 2147483647
        System.out.println(quotientEfficient(Integer.MIN_VALUE, 1)); // -2147483648
        System.out.println(quotientEfficient(Integer.MIN_VALUE, 2)); // -1073741824
        System.out.println(quotientEfficient(Integer.MIN_VALUE, -3)); // 715827882

        System.out.println(quotientEfficient(Integer.MAX_VALUE, -1)); // -2147483647
        System.out.println(quotientEfficient(Integer.MAX_VALUE, 1)); // 2147483647

        System.out.println(quotientEfficient(1, -1)); // -1
        System.out.println(quotientEfficient(-1, 1)); // -1
        System.out.println(quotientEfficient(1, 1)); // 1
        System.out.println(quotientEfficient(1, 1)); // 1

    }

    private static int quotient(final int dividend, final int divisor) {
        if(dividend == 0) return 0;
        if(dividend==Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long ldivisor = Math.abs((long)divisor);
        long ldividend = Math.abs((long)dividend);
        if(divisor == 1) return ((int) (sign * ldividend));
        int quotient = 0;
        while (ldividend >= ldivisor) {
            ldividend -= ldivisor;
            quotient++;
        }
        return sign * quotient;
    }

    // TODO
    private static int quotientEfficient(final int dividend, final int divisor) {
        if(dividend == 0) return 0;
        if(dividend==Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long ldivisor = Math.abs(Long.valueOf(divisor));
        long ldividend = Math.abs(Long.valueOf(dividend));
        int quotient = 0;
        while (ldividend >= ldivisor) {
            long temp = ldivisor, multiple = 1;
            while (ldividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            ldivisor -= temp;
            quotient += multiple;
        }
        return sign * quotient;
    }
}
