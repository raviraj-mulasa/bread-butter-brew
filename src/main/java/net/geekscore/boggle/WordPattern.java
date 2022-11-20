package net.geekscore.boggle;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and
 * a non-empty word in str.
 *
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 *
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated
 * by a single space.


 *
 */
public class WordPattern {

    public static void main(String[] args) {
        System.out.println(followsPattern(null, null)); // true
        System.out.println(followsPattern("", null)); // false
        System.out.println(followsPattern(null, "")); // false
        System.out.println(followsPattern("", " ")); // true
        System.out.println(followsPattern("abba", "dog cat cat dog")); // true
        System.out.println(followsPattern("abba", "dog cat cat fish")); // false
        System.out.println(followsPattern("aaaa", "dog cat cat dog")); // false
        System.out.println(followsPattern("abba", "dog dog dog dog")); // false
    }

    private static boolean followsPattern(String pattern, String str) {
        if(pattern == null && str == null) return true;
        if(pattern == null || str == null) return false;

        final String[] strings = str.split(" ");

        if(pattern.length() != strings.length) return false;
        final Map<Character, String> patternStrMap = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            final Character patternChar = pattern.charAt(i);
            final String str2MatchPattern = strings[i];
            // I mapped the sub string to my pattern
            if(patternStrMap.containsKey(patternChar) && !patternStrMap.get(patternChar).equals(str2MatchPattern)) return false;
            // someone mapped the sub string to their pattern
            if(!patternStrMap.containsKey(patternChar) && patternStrMap.containsValue(str2MatchPattern)) return false;
            patternStrMap.put(patternChar, str2MatchPattern);
        }
        return true;
    }
}
