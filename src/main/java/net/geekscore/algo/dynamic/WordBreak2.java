package net.geekscore.algo.dynamic;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 *
 * Return all such possible sentences.
 *
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *  "cats and dog",
 *  "cat sand dog"
 * ]
 *
 * Example 2:
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *  "pine apple pen apple",
 *  "pineapple pen apple",
 *  "pine applepen apple"
 * ]
 *
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: []
 *
 *
 */
public class WordBreak2 {


    public static void main(String[] args) {
//        [ "cats and dog", "cat sand dog" ]
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
//        [ "leet co de", "leet code"]
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "co", "de", "code")));
//        ["1234"]
        System.out.println(wordBreak("1234", Arrays.asList("1234")));
//        ["a b cd"]
        System.out.println(wordBreak("abcd", Arrays.asList("a","abc","b","cd")));
//        ["a"]
        System.out.println(wordBreak("a", Collections.singletonList("a")));
//        ["pine apple pen apple",  "pineapple pen apple", "pine applepen apple"]
        System.out.println(wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
//        []
        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));


    }

    private static List<String> wordBreak(String str, List<String> words) {

        if(str == null || str.length() == 0 || words == null || words.isEmpty()) return Collections.emptyList();

        final Map<Integer, List<String>> indexWordsMap = new LinkedHashMap<>();
        final BitSet segmented = new BitSet(str.length()+1);
        segmented.set(0);
        int longestWordLength = Integer.MIN_VALUE;
        for (final String word: words) {
            longestWordLength = Math.max(longestWordLength, word.length());
        }
        for (int i = 0; i < str.length(); i++) {
            for (int j = i+1; j <= Math.min(i + longestWordLength, str.length()); j++) {
                final String subString = str.substring(i, j);
                if(segmented.get(i) && words.contains(subString)) {
                    segmented.set(j);
                    final List<String> list = indexWordsMap.getOrDefault(i,new LinkedList<>());
                    list.add(subString);
                    indexWordsMap.put(i, list);
                }
            }
        }
        if(!segmented.get(str.length())) return Collections.emptyList();
        final List<String> result = new LinkedList<>();
        dfs(str, 0, "", result, indexWordsMap);
        return result;
    }

    private static void dfs(String str, int index, String soFar, List<String> result, Map<Integer, List<String>> indexWordsMap) {
        if(str.length() == index) {
            result.add(soFar.trim());
            return;
        }
        if(indexWordsMap.containsKey(index)) {
            for (String word : indexWordsMap.get(index)) {
                dfs(str, index + word.length(), soFar + word + " ", result, indexWordsMap);
            }
        }
    }

}
