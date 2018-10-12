package net.geekscore.algo.dynamic;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 *
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 */
public class DecodeWays {

    public static void main(String[] args) {
        System.out.println(ways2Decode("226")); // 3
        System.out.println(ways2Decode("1226"));// 5
        System.out.println(ways2Decode("1229")); // 3
        System.out.println(ways2Decode("12")); // 2
        System.out.println(ways2Decode("1")); // 1
        System.out.println(ways2Decode("10")); // 1
        System.out.println(ways2Decode("120")); // 1
        System.out.println(ways2Decode("02")); // 0
        System.out.println(ways2Decode("")); // 0
    }

    private static int ways2Decode(final String num2Decode) {
        if(null == num2Decode || num2Decode.length() <=0) return 0;
        final int[] ways = new int[num2Decode.length()+1];
        ways[num2Decode.length()]  = 1;
        ways[num2Decode.length()-1] = num2Decode.charAt(num2Decode.length()-1) != '0' ? 1 : 0;
        for (int i = num2Decode.length()-2; i >= 0; i--) {
            if(num2Decode.charAt(i) != '0')
                ways[i] = Integer.valueOf(num2Decode.substring(i, i+2)) <= 26 ? ways[i+2] + ways[i+1] : ways[i+1];
        }
        return ways[0];
    }
}
