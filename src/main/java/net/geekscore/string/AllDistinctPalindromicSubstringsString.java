package net.geekscore.string;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string of lowercase ASCII characters, find all distinct continuous palindromic sub-strings of it.
 *
 * Examples:
 * Input: str = "abaaa"
 * Output:  Below are 5 palindrome sub-strings
 * a
 * aa
 * aaa
 * aba
 * b
 *
 * Input: str = "geek"
 * Output:  Below are 4 palindrome sub-strings
 * e
 * ee
 * g
 * k
 */
public class AllDistinctPalindromicSubstringsString {

    public static void main(String[] args) {

    }

    private static Collection<String> distinctPalindromicSubstrings(final String str) {
        if(null == str || str.length() == 0) return Collections.emptySet();
        Set<String> allPalindromicSubstrings  = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
        }
        return allPalindromicSubstrings;

    }

    private static void expand(final String str, Set<String> allPalindromicSubstrings, int low, int high) {

        for (int i = 0; i < str.length(); i++) {

        }

    }


}
