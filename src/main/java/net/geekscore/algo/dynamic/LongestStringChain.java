package net.geekscore.algo.dynamic;

import java.util.*;

/**
 * Created by ravirajmulasa on 8/13/17.
 *
 * Given an array, words, of n word strings (words[0], words[1],..., words[n-1]),choose a word from it and, in each step,
 * remove a single letter from the chosen word if and only if doing so yields another word that is already in the library.
 * Each successive character removal should be performed on the result of the previous removal,
 * and you cannot remove a character if the resulting string is not an element in words(see Explanation below for detail).
 *
 * The length of a string chain is the maximum number of strings in a chain of successive character removals.
 *
 * Complete the longestChainLength function in your editor.
 * It has 1 parameter: an array of n strings, words, where the value of each element words;
 * (where 0 <= i < n) is a word.
 * It must return single integer denoting the length of the longest possible string chain in words.
 *
 * Sample Case 1: words = {"a", "b", "ba", "bca", "bda", "bdca"}
 * Because "a" and "b" are single-character words,
 * we cannot remove any characters from them as that would result in the empty string (which is not an element in words), so the length for both of these string chains is 1.
 *
 * The word "ba" can create two different string chains of length 2 ("ba" -> "a" and "ba" -> "b").
 * This means our current longest string chain is 2.
 *
 * The word "bca" can create two different string chains of length 3 ("bca" -> "ba" -> "a" and "bca" -> "ba" -> "b").
 * This means our current longest string chain is now 3.
 *
 * The word "bda" can create two different string chains of length 3 ("bda" -> "ba" -> "a" and "bda" -> "ba" -> "b").
 * This means our current longest string chain is now 3.
 *
 * The word "bdca" can create four different string chains of length 4
 * ("bdca" -> "bda" -> "ba" -> "a"
 * , "bdca" -> "bda" -> "ba" -> "b"
 * , "bdca" -> "bca" -> "ba" -> "a"
 * , "bdca" -> "bca" -> "ba" -> "b").
 * This means our current longest string chain is now 4.
 *
 */
public class LongestStringChain {


    public static void main(String[] args) {
        final String[] words3 = {""}; // 0
        final String[] words4 = {}; // 0
        final String[] words = {"a", "b", "ba", "bca", "bda", "bdca"}; // 4
        final String[] words1 = {"a", "ee"}; // 1
        final String[] words2 = {"\b", "\b\b\b", "\b\b", "\b\b\b\b", "\b\b\b\b\b"};  // 5
        final String[] words5 = {"a", "b", "c", "d", "bd", "bddb"}; // 2
        final String[] words6 = {"a", "b", "c", "d", "bd", "bddb", "ddb"}; // 2
        final String[] words7 = {"a", "b", "c", "d", "db", "bddb", "ddb"}; // 4
        final String[] words8 = {"ddb", "eee", "efd"}; // 1
        final String[] words9 = {"ddb", "dd", "eee", "ee", "efd"}; // 2
        final String[] words10 = {"a", "b", "c", "d", "db", "bdebb", "dd"}; // 2

        System.out.println(longestChainLength(null));
        System.out.println(longestChainLength(words3));
        System.out.println(longestChainLength(words4));
        System.out.println(longestChainLength(words));
        System.out.println(longestChainLength(words1));
        System.out.println(longestChainLength(words2));
        System.out.println(longestChainLength(words5));
        System.out.println(longestChainLength(words6));
        System.out.println(longestChainLength(words7));
        System.out.println(longestChainLength(words8));
        System.out.println(longestChainLength(words9));
        System.out.println(longestChainLength(words10));
    }

    private static final int longestChainLength(String words[]) {
        int longestChainLength = 0;
        if(null == words || words.length == 0){
            return longestChainLength;
        }
        final Map<String, Integer> chainLengthMap = new HashMap<>();
        Arrays.stream(words).forEach(word -> chainLengthMap.put(word, 1)); // Every word itself can be one chain of size 1
        for (final String word: words) {
            longestChainLength = Math.max(longestChainLengthAtWord(word, chainLengthMap), longestChainLength);
        }
        return longestChainLength;
    }

    private static final int longestChainLengthAtWord(String word, final Map<String, Integer> chainLengthMap) {
        if(null == word || word.length() == 0){
            return 0;
        }
        Integer longestChainLength = chainLengthMap.putIfAbsent(word, 1);
        if(null != longestChainLength) {
            final Set<String> wordsSet  = chainLengthMap.keySet();
//            Find longest chain length among  sub words
            for (int i = 0; i < word.length(); i++) {
                final String subWord      = removeSingleLetter(i, word);
                final int lengthSubWord  = subWord.length();
                if (lengthSubWord > 0 && wordsSet.contains(subWord)) {
                    Integer longestChainLengthSubWord = chainLengthMap.get(subWord);
                    // longest chain length can be at most length of the word.
                    if(longestChainLengthSubWord != null && longestChainLengthSubWord < lengthSubWord){
                        longestChainLengthSubWord = longestChainLengthAtWord(subWord, chainLengthMap);
                        chainLengthMap.put(subWord, longestChainLengthSubWord);
                    }
                    // longest chain length at sub word is extended by 1 for word.
                    longestChainLength = Math.max(chainLengthMap.get(subWord) + 1, longestChainLength);
                    chainLengthMap.put(word, longestChainLength);
                }
            }
        }
        return chainLengthMap.get(word);
    }

    private static final String removeSingleLetter(int index, String word) throws StringIndexOutOfBoundsException{
        if(null == word || word.length() == 0){
            return "";
        }
        if(index < 0 || index > word.length() - 1) {
            throw new StringIndexOutOfBoundsException("Invalid Index");
        }
        return word.substring(0, index) + word.substring(index+1);
    }



}
