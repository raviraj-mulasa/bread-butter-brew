package net.geekscore.algo.backtrack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        System.out.println(followsPattern("abab", "xyzabcxyzabc")); // true
        System.out.println(followsPattern("ccc", "airairair")); // true
        System.out.println(followsPattern("abcd", "wxyz")); // true
        System.out.println(followsPattern("xyzxy", "airbnbairbn")); // true
    }

    private static boolean followsPattern(String pattern, String str) {
        if (str == pattern) return true;
        if (null == pattern || null == str) return false;
        Map<Character, String> map = new HashMap<>();
        final boolean result = followsPatternHelper(pattern, 0, str, 0, map);
//        System.out.println("Patterns mapped "+map);
        return result;
    }

    private static boolean followsPatternHelper(String pattern, int pPos, String str, int sPos, Map<Character, String> patternStrMap) {

        if(pPos == pattern.length() && sPos == str.length()) return true;
        if(pPos == pattern.length() || sPos == str.length()) return false;

        final Character patternChar = pattern.charAt(pPos);
        if(patternStrMap.containsKey(patternChar)) {
            final String mappedStr = patternStrMap.get(patternChar);
            return str.startsWith(mappedStr, sPos) && followsPatternHelper(pattern, pPos+1, str, sPos+mappedStr.length(),patternStrMap);
        }
        for(int i = sPos ; i < str.length(); i++) {
            final String subStr = str.substring(sPos, i+1);
            if(patternStrMap.containsValue(subStr)) continue; // is this substring already mapped, continue
            // Choose
            patternStrMap.put(patternChar, subStr);
            // Explore
            if(followsPatternHelper(pattern, pPos+1, str, i+1, patternStrMap)) return true;
            // Un choose
            patternStrMap.remove(patternChar);
        }
        return false;
    }
}
