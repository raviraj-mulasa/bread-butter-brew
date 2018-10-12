package net.geekscore.algo.dynamic;

import java.util.Arrays;

/**
 *
 * TODO
 *
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different
 * substrings even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: "aaa"
 * Output: 6
 *
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

 *
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {
        System.out.println(palindromicSubstrings("abc")); // 3
        System.out.println(palindromicSubstrings("aa")); // 3
        System.out.println(palindromicSubstrings("aaa")); // 6
        System.out.println(palindromicSubstrings("aaaa")); // 10
        System.out.println(palindromicSubstrings("aab")); // 4
        System.out.println(palindromicSubstrings("aba")); // 4
        System.out.println(palindromicSubstrings("abaa")); // 6
    }

    private static final int palindromicSubstrings(final String str) {
        if (null == str || str.length() == 0) return 0;
        final int[] palindromes = new int[str.length()+1];
        Arrays.fill(palindromes, 1);
        palindromes[0] = 0;
        for (int i = 2; i <= str.length() ; i++) {
            if(str.charAt(i-2) == str.charAt(i-1)) {
                palindromes[i] = palindromes[i] * i + palindromes[i-1];
//                palindromes[i] = palindromes[i] * i + palindromes[i-1];
            }
            else {
                palindromes[i] +=palindromes[i-1];
            }
        }
        System.out.println(Arrays.toString(palindromes));
        return palindromes[str.length()];
    }
}
