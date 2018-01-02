package net.geekscore.string;


/**
 *
 *
 * SLIDING WINDOW
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T
 * in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 *
 * Windows
 * -------
 * ADOBEC
 * DOBECODEBA
 * OBECODEBA
 * BECODEBA
 * ECODEBA
 * CODEBA
 * ODEBANC
 * DEBANC
 * EBANC
 * BANC
 *
 * Minimum window is "BANC".
 *
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 *
 *
 *
 **/

/*int findSubstring(string s){
        vector<int> map(128,0);
        int counter; // check whether the substring is valid
        int begin=0, end=0; //two pointers, one point to tail and one  head
        int d; //the length of substring

        for() { *//* initialize the hash map here *//* }

        while(end<s.size()){

        if(map[s[end++]]-- ?){  *//* modify counter here *//* }

        while(*//* counter condition *//*){

                 *//* update d here if finding minimum*//*

        //increase begin to make it invalid/valid again

        if(map[s[begin++]]++ ?){ *//*modify counter here*//* }
        }

            *//* update d here if finding maximum*//*
        }
        return d;
        }*/
/**
 * One thing needs to be mentioned is that when asked to find maximum substring, we should update maximum after the inner while loop to guarantee that the substring is valid. On the other hand, when asked to find minimum substring, we should update minimum inside the inner while loop.
 */

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow("", ""));
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
        System.out.println(minWindow("cabwefgewcwaefgcf", "cae")); //cwae
    }

    private static final String minWindow(String s, String t) {
        if(s == null || t == null || s.length() < t.length()) {
            return "";
        }
        final int[] freq = new int[256];
        for(final char tChar: t.toCharArray()){
            freq[tChar]++;
        }
        int left = 0, right = left, counter = t.length(), subStrLength = Integer.MAX_VALUE, subStrBeginIdx = left;
        while (right < s.length()){
            if(freq[s.charAt(right++)]-- > 0){
                // char present in s also present in t will have values > 0 in freq for first time, then 0 or more
                // for chars that are present in s but not in t will have values < 0
                counter--;
            }
            while (counter == 0) {
                // we found all characters in t in s - subString found
                if(right - left < subStrLength) {
                    subStrLength = right - left; // track the smallest window
                    subStrBeginIdx = left;
                }
//                System.out.println(left+":"+right+"="+s.substring(subStrBeginIdx, subStrBeginIdx+subStrLength));
                if(freq[s.charAt(left++)]++ == 0) counter++;
            }
        }
        return subStrLength == Integer.MAX_VALUE ? "" : s.substring(subStrBeginIdx, subStrBeginIdx+subStrLength);
    }
}

