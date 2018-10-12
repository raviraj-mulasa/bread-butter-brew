package net.geekscore.string;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be
 * the same as word2.
 *
 * Given a list of words and two words word1 and word2, return the shortest distance between these
 * two words in the list.
 *
 * word1 and word2 may be the same and they represent two individual words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.

 Note:
 You may assume word1 and word2 are both in the list.
 */
public class ShortestWordDistance3 {


    public static void main(String[] args) {
        final ShortestWordDistance3.WordDistance wordDistance = new ShortestWordDistance3.WordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        // 3
        System.out.println(wordDistance.shortest("coding", "practice"));
        // 1
        System.out.println(wordDistance.shortest("makes", "coding"));
        // 3
        System.out.println(wordDistance.shortest("makes", "makes"));

        System.out.println("--------");

        // 3
        System.out.println(shortestEfficient(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"));
        // 1
        System.out.println(shortestEfficient(new String[]{"practice", "makes", "perfect", "coding", "makes"},"makes", "coding"));
        // 3
        System.out.println(shortestEfficient(new String[]{"practice", "makes", "perfect", "coding", "makes"},"makes", "makes"));

    }


    private static int shortestEfficient(String[] words, String word1, String word2) {
        int i = -1 , j = -1,  min = Integer.MAX_VALUE;
        for (int k = 0; k < words.length; k++) {
            if(words[k].equals(word1)) {
                i = k;
                break;
            }
        }
        for (int k = words.length-1; k > i; k--) {
            int _i = i;
            if(words[k].equals(word1)) _i = k;
            if(words[k].equals(word2)) j = k;
            min = Math.min(min, Math.min(Math.abs(i - j), Math.abs(_i - j)));
        }
        return min;
    }


    private static class WordDistance {

        private final Map<String, List<Integer>> wordIndices = new HashMap<>();

        private WordDistance(String[] words) {
            for (int i = 0; i < words.length; i++) {
                final String word = words[i];
                final List<Integer> indices = wordIndices.getOrDefault(word, new LinkedList<>());
                indices.add(i);
                wordIndices.put(word, indices);
            }
        }

        private int shortest(String word1, String word2) {
            int min = Integer.MAX_VALUE;
            final List<Integer> word1Indices = wordIndices.get(word1);
            if(word1.equals(word2)) {
                for (int i = 0; i < word1Indices.size()-1; i++) {
                    min = Math.min(min, Math.abs(word1Indices.get(i) - word1Indices.get(i+1)));
                }
            }
            else {
                final List<Integer> word2Indices = wordIndices.get(word2);
                int i = 0, j = i;
                while (i < word1Indices.size() && j < word2Indices.size()) {
                    min = Math.min(min, Math.abs(word1Indices.get(i) - word2Indices.get(j)));
                    if (word1Indices.get(i) < word2Indices.get(j)) i++;
                    else j++;
                }
            }
            return min;
        }


    }
}


