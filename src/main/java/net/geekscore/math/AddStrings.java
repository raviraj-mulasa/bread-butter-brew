package net.geekscore.math;

/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of
 * num1 and num2.
 *
 * Note:
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {

    public static void main(String[] args) {
        System.out.println(addStrings("999","1")); // 1000
        System.out.println(addStrings("100","1")); // 101
        System.out.println(addStrings("0","0")); // 0
    }

    private static String addStrings(String num1, String num2) {
        if(num1 == null || num1.length() == 0) return num2;
        if(num2 == null || num2.length() == 0) return num1;
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        final StringBuilder result = new StringBuilder();
        while (i > -1 || j > -1) {
            final int x = i <= -1 ? 0 : num1.charAt(i--) - '0';
            final int y = j <= -1 ? 0 : num2.charAt(j--) - '0';
            final int sum = x + y + carry;
            result.append(sum%10);
            carry = sum / 10;
        }
        if(carry > 0) result.append(carry);
        result.reverse();
        return result.toString();
    }
}
