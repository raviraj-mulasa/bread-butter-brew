package net.geekscore.heap;

import java.util.*;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 * Input: "tree"
 * Output: "eert"
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * Example 2:
 * Input: "cccaaa"
 * Output: "cccaaa"
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 *
 * Example 3:
 * Input: "Aabb"
 * Output: "bbAa"
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 *
 */
public class SortCharsByFreq {

    public static void main(String[] args) {
        System.out.println(sortCharsByFreq("tree")); // eert
        System.out.println(sortCharsByFreq("cccaaa")); // cccaaa or aaaccc
        System.out.println(sortCharsByFreq("Aabb")); // Aabb or bbaA
    }

    private static final String sortCharsByFreq(final String word) {
        if(word == null || word.length() ==0) return word;
        final Map<Character, Integer> charFreqMap = new HashMap<>();
        for (final char ch: word.toCharArray())  charFreqMap.put(ch, charFreqMap.getOrDefault(ch, 0)+1);
        final PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (final Map.Entry<Character, Integer> entry: charFreqMap.entrySet()) maxHeap.offer(entry);
        final StringBuilder stringBuilder = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            final Map.Entry<Character, Integer> top = maxHeap.poll();
            final char[] chars = new char[top.getValue()];
            Arrays.fill(chars, top.getKey());
            stringBuilder.append(chars);
        }
        return stringBuilder.toString();
    }
}
