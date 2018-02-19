package net.geekscore.algo.backtrack;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern
 * and a non-empty substring in str.
 * Examples:
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 *
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 *
 */
public class WordPattern2 {

    public static void main(String[] args) {
        System.out.println(followsPattern(null, null)); // true
        System.out.println(followsPattern("", null)); // false
        System.out.println(followsPattern(null, "")); // false
        System.out.println(followsPattern("", "")); // true
        System.out.println(followsPattern("abab", "redblueredblue")); // true
        System.out.println(followsPattern("aaaa", "asdasdasdasd")); // true
        System.out.println(followsPattern("aabb", "xyzabcxzyabc")); // false
        System.out.println(followsPattern("abab", "xyzabcxzyabc")); // true
        System.out.println(followsPattern("ccc", "airairair")); // true
    }

    private static boolean followsPattern(String pattern, String str) {
        if(pattern == null && str == null) return true;
        if(pattern == null || str == null) return false;
        return followsPatternHelper(pattern.toCharArray(), 0, str.toCharArray(), 0);
    }

    private static boolean followsPatternHelper(char[] pattern, int i, char[] str, int j) {
        if(pattern == null && str == null) return true;
        if(pattern == null || str == null) return false;

        return true;
    }
}
