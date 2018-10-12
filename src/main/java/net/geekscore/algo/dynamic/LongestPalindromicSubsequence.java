package net.geekscore.algo.dynamic;

import net.geekscore.array.ArrayUtil;

import java.time.Duration;
import java.time.Instant;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input: "bbbab"
 * Output: 4
 * One possible longest palindromic subsequence is "bbbb".
 *
 * Example 2:
 * Input: "cbbd"
 * Output: 2
 *
 * One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        System.out.println("--------------");
        Instant start = Instant.now();
        System.out.println(longestPalindromicSubsequenceRec("adbscbag")); // 5 - abcba
        System.out.println(longestPalindromicSubsequenceRec("aaa")); // 3  - aaa
        System.out.println(longestPalindromicSubsequenceRec("bbbab")); // 4 - bbbb
        System.out.println(longestPalindromicSubsequenceRec("cbbd")); // 2 - bb
        System.out.println("Duration "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("--------------");
        start = Instant.now();
        System.out.println(longestPalindromicSubsequence("adbscbag")); // 5 - abcba
        System.out.println(longestPalindromicSubsequence("aaa")); // 3  - aaa
        System.out.println(longestPalindromicSubsequence("bbbab")); // 4 - bbbb
        System.out.println(longestPalindromicSubsequence("cbbd")); // 2 - bb
        System.out.println("Duration "+ Duration.between(start, Instant.now()).toNanos());
    }

    private static final int longestPalindromicSubsequenceRec(final String str) {
        if(str == null || str.length() == 0) return 0;
        return longestPalindromicSubsequenceHelper(str.toCharArray(), 0, str.length()-1);
    }

    private static final int longestPalindromicSubsequenceHelper(final char[] chars, int i, int j) {
        if(j < i) return 0;
        if(i==j) return 1;
        if(chars[i] == chars[j]) return longestPalindromicSubsequenceHelper(chars, i+1, j-1) + 2;
        else return Math.max(
                longestPalindromicSubsequenceHelper(chars, i+1, j)
                , longestPalindromicSubsequenceHelper(chars, i, j-1)
        );
    }

    private static final int longestPalindromicSubsequence(final String str) {
        if(str == null || str.length() == 0) return 0;
        final int[][] palindromeLength = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            palindromeLength[i][i] = 1;
        }
        for (int len = 1; len < str.length(); len++) {
            for (int i = 0; i < str.length()-len; i++) {
                final int j = i + len;
                if(str.charAt(i) == str.charAt(j)) {
                    palindromeLength[i][j] = palindromeLength[i+1][j-1] + 2;
                } else {
                    palindromeLength[i][j] =  Math.max(palindromeLength[i+1][j], palindromeLength[i][j-1]);
                }
            }
        }
        ArrayUtil.print(palindromeLength);
        return palindromeLength[0][str.length()-1];
    }

}
