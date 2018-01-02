package net.geekscore.string;


/**
 * SLIDING WINDOW
 *
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
 *
 * For example, Given s = “eceba”,
 * T is "ece" which its length is 3.
 *
 */
public class LongestSubstringWithAtMost2DistinctChars {


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringAtMost2Distinct(""));
        System.out.println(longestSubstringAtMost2Distinct(""));

        System.out.println(lengthOfLongestSubstringAtMost2Distinct("eceba"));
        System.out.println(longestSubstringAtMost2Distinct("eceba"));

        System.out.println(lengthOfLongestSubstringAtMost2Distinct("eecba"));
        System.out.println(longestSubstringAtMost2Distinct("eecba"));

        System.out.println(lengthOfLongestSubstringAtMost2Distinct("eecbb"));
        System.out.println(longestSubstringAtMost2Distinct("eecbb"));

        System.out.println(lengthOfLongestSubstringAtMost2Distinct("eecbbc"));
        System.out.println(longestSubstringAtMost2Distinct("eecbbc"));
    }

    private static final int lengthOfLongestSubstringAtMost2Distinct(String s) {

        if(null == s || s.length() == 0) {
            return 0;
        }
        final int[] freq = new int[256];
        int left =0, right = left, counter = 0, subStrLen = Integer.MIN_VALUE;
        while (right < s.length()) {
            if(freq[s.charAt(right++)]++ == 0) counter++; // saw it, increment counter
            while (counter > 2) {
                if(freq[s.charAt(left++)]-- == 1) counter--; // decrement to position, there is one non repeating char
            }
            subStrLen = Math.max(right - left, subStrLen);
        }
        return subStrLen;

    }


    private static final String longestSubstringAtMost2Distinct(String s) {
        if(null == s || s.length() == 0) {
            return "";
        }
        final int[] freq = new int[256];
        int left =0, right = left, counter = 0, subStrLen = Integer.MIN_VALUE, subStrBegIdx = left;
        while (right < s.length()) {
            if(freq[s.charAt(right++)]++ == 0) counter++; // saw it, increment counter
            while (counter > 2) {
                if(freq[s.charAt(left++)]-- == 1) counter--; // decrement to position, there is one non repeating char
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
