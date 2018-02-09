package net.geekscore.problems;

/**
 *
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases.
 *
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).
 * You are responsible to gather all the input requirements up front.
 *
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your function signature
 * accepts a const char * argument, please click the reload button  to reset your code definition.
 *
 */
public class String2Int {

    public static void main(String[] args) {
        System.out.println(str2Int("1234")); // 1234
        System.out.println(str2Int("1234 ")); // 1234
        System.out.println(str2Int(" 1234 ")); // 1234
        System.out.println(str2Int("")); // 0
        System.out.println(str2Int(" -1234 ")); // -1234
        System.out.println(str2Int(" +1")); // 1
        System.out.println(str2Int(String.valueOf(Integer.MAX_VALUE))); // 2147483647
        System.out.println(str2Int(String.valueOf(Integer.MIN_VALUE))); // -2147483648
        System.out.println(str2Int("2147483648")); // 2147483647
        System.out.println(str2Int("-2147483649")); // -2147483648
        System.out.println(str2Int("      -11919730356x")); // -2147483648
        System.out.println(str2Int("1 234")); // 1
        System.out.println(str2Int("-2147483647")); // -2147483647
        System.out.println(str2Int(null));
    }

    private static int str2Int(final String str) {
        if(str == null) throw  new IllegalArgumentException("Invalid Input");
        if(str.length() == 0) return 0;
        long number = 0;
        final char[] chars  = str.trim().toCharArray();
        final boolean negative = (chars[0] == '-');
        final boolean sign = chars[0] == '+' || negative;
        for (int i = (sign ? 1 : 0); i < chars.length; i++) {
            final char ch = chars[i];
            if(!isDigit(ch)) break;
            number *= 10;
            number += (ch - '0');
            if(number >= Integer.MAX_VALUE)
                return (int) (negative ? -number <= Integer.MIN_VALUE ? Integer.MIN_VALUE: -number : Integer.MAX_VALUE);
        }
        return (int)(negative ? -number : number);
    }

    private static boolean isDigit(final char ch) {
        return (ch-'0')>= 0 && (ch-'0')<= 9;
    }
}
