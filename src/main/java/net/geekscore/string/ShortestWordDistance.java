package net.geekscore.string;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance
 * between these two words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance {

    private final Map<String, List<Integer>> wordIndices = new HashMap<>();

    public static void main(String[] args) {

        final ShortestWordDistance shortestWordDistance = new ShortestWordDistance();

        Instant start = Instant.now();
        // 3
        System.out.println(shortestWordDistance.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"));
        // 1
        System.out.println(shortestWordDistance.shortestDistance(new String[]{"a","c","b","a"}, "a", "b"));
        // 7
        System.out.println(shortestWordDistance.shortestDistance(new String[]{"this","is","a","long","sentence","is","fun","day","today","sunny","weather","is","a","day","tuesday","this","sentence","run","running","rainy"}, "weather", "long"));
        System.out.println("Duration: "+Duration.between(start, Instant.now()).toNanos());

        System.out.println("----------------");

        start = Instant.now();
        // 3
        System.out.println(shortestWordDistance.shortestDistanceEfficient(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"));
        // 1
        System.out.println(shortestWordDistance.shortestDistanceEfficient(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "makes"));
        // 1
        System.out.println(shortestWordDistance.shortestDistanceEfficient(new String[]{"a","c","b","a"}, "a", "b"));
        // 7
        System.out.println(shortestWordDistance.shortestDistanceEfficient(new String[]{"this","is","a","long","sentence","is","fun","day","today","sunny","weather","is","a","day","tuesday","this","sentence","run","running","rainy"}, "weather", "long"));
        System.out.println("Duration: "+Duration.between(start, Instant.now()).toNanos());
    }

    private int shortestDistance(String[] words, String word1, String word2) {
        if(words == null || words.length == 0) return 0;
        for(int i = 0; i < words.length; i++) {
            final String word = words[i];
            final List<Integer> indices = wordIndices.getOrDefault(word, new LinkedList<>());
            indices.add(i);
            wordIndices.put(word, indices);
        }

        final List<Integer> word1Indices = wordIndices.get(word1);
        final List<Integer> word2Indices = wordIndices.get(word2);
        int min = Integer.MAX_VALUE;
        for(int i = 0, j = 0; i < word1Indices.size() && j < word2Indices.size();) {
            min = Math.min(min, Math.abs(word1Indices.get(i) - word2Indices.get(j)));
            if(word1Indices.get(i) < word2Indices.get(j)) i++;
            else j++;
        }
        return min;
    }

    private int shortestDistanceEfficient(String[] words, String word1, String word2) {
        if(words == null || words.length == 0) return 0;
        int idx1 = -1, idx2 = idx1, min = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            final String word = words[i];
            if(word.equals(word1)) idx1 = i;
            if(word.equals(word2)) idx2 = i;
            if(idx1 == -1 || idx2 == -1) continue;
            if(idx1 != idx2) min = Math.min(min, Math.abs(idx1-idx2));
        }
        return min;
    }

}

