package net.geekscore.string;

import java.util.*;

/**
 * SLIDING WINDOW
 *
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and
 * without any intervening characters.
 *
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 *
 * You should return the indices: [0,9].(order does not matter).
 *
 **/

public class SubstringConcatOfAllWords {

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"})); // [0,9]
//        System.out.println(findSubstring("foothethefoo", new String[]{"the", "foo"})); // [0,6]
        System.out.println(findSubstring("foothefoo", new String[]{"the", "foo"})); // [0,3]
//        System.out.println(findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"})); // [6,9,12]
    }

    private static final List<Integer> findSubstring(String s, String[] words) {
        if(s == null || s.length() == 0 || null == words || words.length == 0) {
            return Collections.EMPTY_LIST;
        }
        final Map<String, Integer> freq = new HashMap<>(words.length);
        final List<Integer> indices = new LinkedList<>();
        final int lengthOfEachWord = words[0].length();
        for (String word: words) {
            Integer wordFreq = freq.get(word);
            if(null == wordFreq) {
                wordFreq = 0;
            }
            freq.put(word, wordFreq + 1);
        }
        int left = 0 , right = left, counter = 0, subStrBegIdx = left;
        while (right < s.length()) {
            String word = s.substring(right, right+lengthOfEachWord);
            System.out.println("left:"+left+" right:"+right);
            Integer wordFreq = freq.get(word);
            if(null != wordFreq && wordFreq >= 1) {
                counter++;
                freq.put(word, wordFreq - 1);
                right += lengthOfEachWord;
            }
            if(counter == words.length){
                indices.add(left);
                counter = 0;
            }
            if(null == wordFreq) {
                wordFreq = 0;
                freq.put(word, wordFreq - 1);
                left += lengthOfEachWord;
            }


        }

        return indices;
    }
}
