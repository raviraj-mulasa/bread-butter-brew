package net.geekscore.string;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 *
 * Given a list of words and two words word1 and word2, return the shortest distance between
 * these two words in the list.
 *
 * word1 and word2 may be the same and they represent two individual words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 *
 * Note: You may assume word1 and word2 are both in the list.
 */
public class ShortestWordDistance2 {

    public static void main(String[] args) {

        Instant start = Instant.now();
        // 3
        WordDistance wordDistance = new WordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        System.out.println(wordDistance.shortest("coding", "practice"));
        // 1
        wordDistance = new WordDistance(new String[]{"a","c","b","a"});
        System.out.println(wordDistance.shortest("a", "b"));
        // 7
        wordDistance = new WordDistance(new String[]{"this","is","a","long","sentence","is","fun","day","today","sunny","weather","is","a","day","tuesday","this","sentence","run","running","rainy"});
        System.out.println(wordDistance.shortest("weather", "long"));
        System.out.println("Duration: "+ Duration.between(start, Instant.now()).toNanos());
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
            final List<Integer> word1Indices = wordIndices.get(word1);
            final List<Integer> word2Indices = wordIndices.get(word2);
            int i = 0, j = i , min = Integer.MAX_VALUE;
            while (i < word1Indices.size() && j < word2Indices.size()) {
                min = Math.min(min, Math.abs(word1Indices.get(i) - word2Indices.get(j)));
                if(word1Indices.get(i) <  word2Indices.get(j)) i++;
                else j++;
            }
            return min;
        }
    }

}


