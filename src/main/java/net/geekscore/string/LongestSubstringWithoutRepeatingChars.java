package net.geekscore.string;

/**
 *
 * SLIDING WINDOW
 *
 *
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 *
 *  Given "abcabcbb", the answer is "abc", which the length is 3.
 *
 *  Given "bbbbb", the answer is "b", with the length of 1.
 *
 *  Given "pwwkew", the answer is "wke", with the length of 3.
 *  Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 **/

public class LongestSubstringWithoutRepeatingChars {

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring(""));
        System.out.println(longestSubstringWithoutRepeatingChars(""));

        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(longestSubstringWithoutRepeatingChars("abcabcbb"));

        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(longestSubstringWithoutRepeatingChars("bbbbb"));

        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(longestSubstringWithoutRepeatingChars("pwwkew"));
    }

    private static final int lengthOfLongestSubstring(String s) {
        if(null == s || s.length() == 0) {
            return 0;
        }
        final int[] freq = new int[256];
        int left =0, right = left, counter = 0, subStrLen = Integer.MIN_VALUE;
        while (right < s.length()) {
            if(freq[s.charAt(right++)]++ >= 1) counter++; // saw it more than once , increment counter
            while (counter > 0) {
                if(freq[s.charAt(left++)]-- > 1) counter--; // decrement counter to 1 so we can count from one again
            }
            subStrLen = Math.max(right- left, subStrLen);
        }
        return subStrLen;

    }

    private static final String longestSubstringWithoutRepeatingChars(String s) {
        if(null == s || s.length() == 0) {
            return "";
        }
        final int[] freq = new int[256];
        int left =0, right = left, counter = 0, subStrLen = Integer.MIN_VALUE, subStrBegIdx = left;
        while (right < s.length()) {
            if(freq[s.charAt(right++)]++ >= 1) counter++; // saw it more than once , increment counter
            while (counter > 0) {
                if(freq[s.charAt(left++)]-- > 1) counter--; // decrement to 1 so we can count from one again
            }
            if(right - left > subStrLen) {
                subStrLen = right- left;
                subStrBegIdx = left;
//                System.out.println(s.substring(subStrBegIdx, subStrBegIdx+subStrLen));
            }

        }
        return s.substring(subStrBegIdx, subStrBegIdx+subStrLen);
    }
}
