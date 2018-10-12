package net.geekscore.algo.dynamic;

import java.time.Duration;
import java.time.Instant;

/**
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Example:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example:
 * Input: "cbbd"
 * Output: "bb"
 *
 * Example:
 * Inout: "dabcba"
 * Output: "abcba"
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println("-------------");
        Instant start = Instant.now();
        System.out.println(longestPalindromicSubstringRec("babad")); // 3 - "bab"
        System.out.println(longestPalindromicSubstringRec("cbbd")); // 2 -"bb"
        System.out.println(longestPalindromicSubstringRec("dabcba")); // 5- "abcba"
        System.out.println(longestPalindromicSubstringRec("aaaaaaaaaaaaaaaaabcbaaaaaaaaaaaaaaaaa")); // 37 - itself
        System.out.println(longestPalindromicSubstringRec("aaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaa")); // 36 - itself
        System.out.println("Duration "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("-------------");
        start = Instant.now();
        System.out.println(longestPalindromicSubstringDP("babad")); // 3 - "bab"
        System.out.println(longestPalindromicSubstringDP("cbbd")); // 2 -"bb"
        System.out.println(longestPalindromicSubstringDP("dabcba")); // 5- "abcba"
        System.out.println(longestPalindromicSubstringDP("aaaaaaaaaaaaaaaaabcbaaaaaaaaaaaaaaaaa")); // 37 - itself
        System.out.println(longestPalindromicSubstringDP("aaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaa")); // 36 - itself
        System.out.println("Duration "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("-------------");
        start = Instant.now();
        System.out.println(longestPalindromicSubstringSimple("babad")); // 3 - "bab"
        System.out.println(longestPalindromicSubstringSimple("cbbd")); // 2 -"bb"
        System.out.println(longestPalindromicSubstringSimple("dabcba")); // 5- "abcba"
        System.out.println(longestPalindromicSubstringSimple("aaaaaaaaaaaaaaaaabcbaaaaaaaaaaaaaaaaa")); // 37 - itself
        System.out.println(longestPalindromicSubstringSimple("aaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaa")); // 36 - itself
        System.out.println("Duration "+ Duration.between(start, Instant.now()).toNanos());


        System.out.println("-------------");
        start = Instant.now();
        System.out.println(manacher("babad")); // 3 - "bab"
        System.out.println(manacher("cbbd")); // 2 -"bb"
        System.out.println(manacher("dabcba")); // 5- "abcba"
        System.out.println("Duration "+ Duration.between(start, Instant.now()).toNanos());
    }

    private static final int longestPalindromicSubstringRec(String str) {
        if(str == null || str.length() == 0) return 0;
        return longestPalindromicSubstringRecHelper(str.toCharArray(), 0, str.length()-1);
    }

    private static final int longestPalindromicSubstringRecHelper(char[] chars, int i, int j) {
        if (j < i) return 0;
        if(i==j) return 1;
        if(chars[i] == chars[j]) return longestPalindromicSubstringRecHelper(chars, i+1, j-1) + 2;
        else return Math.max(
                longestPalindromicSubstringRecHelper(chars, i+1, j)
                ,longestPalindromicSubstringRecHelper(chars, i, j-1)
        );
    }

    // Space - O(n2) , Runtime - O(n2)
    private static final String longestPalindromicSubstringDP(final String str) {
        if(str == null || str.length() == 0) return "";
        final boolean[][] palindrome = new boolean[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            palindrome[i][i] = true; // Each char is a palindrome on its own
        }
        String subStr = str.substring(0,1); // Sub string of length 1
        int lenSubStr = subStr.length();
        // the palindrome sub string can be at most the length of the string
        for (int len = 1; len < str.length(); len++) {
            for (int i = 0; i < str.length()-len; i++) {
                final int j = i + len; // j will have last char, i will have first char
                if(str.charAt(i) == str.charAt(j) && (len == 1 || palindrome[i+1][j-1])) {
                    palindrome[i][j] = true;
                    subStr = str.substring(i, j+1);
                    lenSubStr = Math.max(lenSubStr, subStr.length());
                }
            }
        }
        return subStr;
    }

    // Space - O(1) , Runtime - O(n2)
    private static final String longestPalindromicSubstringSimple(final String str) {
        if(str == null || str.length() == 0) return "";
        String subStr = str.substring(0,1); // Each char is a palindrome on its own
        for (int i = 0; i < str.length(); i++) {
            // Find longest palindrome with center of i - odd length
            String palindromeAtCenterOfI = palindromicSubstringCenterOfI(str, i, i);
            if(palindromeAtCenterOfI.length() >= subStr.length()) {
                subStr = palindromeAtCenterOfI;
            }
            // Find longest palindrome with center of i, i+1 - even length
            palindromeAtCenterOfI = palindromicSubstringCenterOfI(str, i, i+1);
            if(palindromeAtCenterOfI.length() >= subStr.length()) {
                subStr = palindromeAtCenterOfI;
            }
        }
        return subStr;
    }

    private static final String palindromicSubstringCenterOfI(final String str, int begin, int end) {
        while (begin >= 0 && end < str.length() && str.charAt(begin) == str.charAt(end)) {
            begin--;
            end++;
        }
        return str.substring(begin+1, end);
    }

    private static boolean manacher(final String str) {
        // todo
        return false;
    }



}
