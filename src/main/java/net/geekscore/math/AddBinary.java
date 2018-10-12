package net.geekscore.math;

/**
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 *
 * Return "100".
 */
public class AddBinary {

    public static void main(String[] args) {
        System.out.println(addBinary("0", "0")); // 0
        System.out.println(addBinary("010", "101")); // 111
        System.out.println(addBinary("11", "1")); // 100
    }

    private static String addBinary(String a, String b) {
        if(a==null || a.length() == 0) return b;
        if(b==null || b.length() == 0) return a;
        int carry = 0;
        int i = a.length() - 1 , j = b.length() - 1;
        final StringBuilder result = new StringBuilder();
        while (i > -1 || j > -1) {
            final int x = i < 0 ? 0: a.charAt(i--) - '0';
            final int y = j < 0 ? 0: b.charAt(j--) - '0';
            final int sum = x + y + carry;
            result.append(sum%2);
            carry = sum/2;
        }
        if(carry == 1) result.append(carry);
        result.reverse();
        return result.toString();
    }
}
