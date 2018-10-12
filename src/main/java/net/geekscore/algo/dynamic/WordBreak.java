package net.geekscore.algo.dynamic;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * You may assume the dictionary does not contain duplicate words.
 *
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 *
 * Return true because "leetcode" can be segmented as "leet code".
 *
 * UPDATE (2017/1/4):
 * The wordDict parameter had been changed to a list of strings (instead of a set of strings).
 * Please reload the code definition to get the latest changes.


 */
public class WordBreak {

    public static void main(String[] args) {
        System.out.println(isSegmented("leetcode", Arrays.asList("leet", "code"))); // true
        System.out.println(isSegmented("leetcode", Arrays.asList("leet", "co", "de"))); // true
        System.out.println(isSegmented("abcdef", Arrays.asList("a", "bc", "de", "f"))); // true
        System.out.println(isSegmented("abcdef", Arrays.asList("a", "bc", "dg", "f"))); // false
        System.out.println(isSegmented("12345", Arrays.asList("1", "23", "4", "56"))); // false
        System.out.println(isSegmented("1234", Arrays.asList("1234"))); // true
        System.out.println(isSegmented("aaaaaaa", Arrays.asList("aaaa","aaa"))); // true
        System.out.println(isSegmented("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"))); // true

    }


    private static final boolean isSegmented(String str, List<String> words) {
        final BitSet segmented = new BitSet(str.length()+1);
        segmented.set(0);
        int longestWordLength = Integer.MIN_VALUE;
        for (final String word: words) {
            longestWordLength = Math.max(longestWordLength, word.length());
        }
        for (int i = 0; i < str.length(); i++) {
            for (int j = i+1; j <= Math.min(i + longestWordLength, str.length()); j++) {
                if(segmented.get(i) && words.contains(str.substring(i, j))) {
                    segmented.set(j);
                }
            }
        }
        return segmented.get(str.length());
    }
}
