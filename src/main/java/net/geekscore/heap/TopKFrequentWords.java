package net.geekscore.heap;

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest.
 * If two words have the same frequency, then the word with the lower alphabetical order comes first
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 * with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 *
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 */
public class TopKFrequentWords {

    public static void main(String[] args) {

        System.out.println("------------------");

        // ["i", "love"]
        System.out.println(topKFreqWords(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        // ["the","is","sunny","day"]
        System.out.println(topKFreqWords(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
        // ["i"]
        System.out.println(topKFreqWords(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 1));
        // ["i","love","coding"]
        System.out.println(topKFreqWords(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 3));


        System.out.println("------------------");

        // ["i", "love"]
        System.out.println(topKFreqWords1(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        // ["the","is","sunny","day"]
        System.out.println(topKFreqWords1(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
        // ["i"]
        System.out.println(topKFreqWords1(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 1));
        // ["i","love","coding"]
        System.out.println(topKFreqWords1(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 3));


        System.out.println("------------------");

        // ["i", "love"]
        System.out.println(topKFreqWords2(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        // ["the","is","sunny","day"]
        System.out.println(topKFreqWords2(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
        // ["i"]
        System.out.println(topKFreqWords2(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 1));
        // ["i","love","coding"]
        System.out.println(topKFreqWords2(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 3));


    }

    // O(n+ nlogk + k) time
    private static final List<String> topKFreqWords(final String[] words, int k) {
        if(words == null || words.length == 0 || k<0) return Collections.emptyList();
        final Map<String, Integer> frequencyMap = new HashMap<>();
        for(final String word: words) frequencyMap.put(word, frequencyMap.getOrDefault(word,0)+1);
        final PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue()) == 0 ?
                        o2.getKey().compareTo(o1.getKey()) :
                        o1.getValue().compareTo(o2.getValue());
            }
        });
        for (final Map.Entry<String, Integer> entry: frequencyMap.entrySet()) {
            if(minHeap.size() < k ) {
                minHeap.offer(entry);
                continue;
            }
            final int comparison = minHeap.peek().getValue().compareTo(entry.getValue());
            if(comparison < 0 || (0 == comparison && minHeap.peek().getKey().compareTo(entry.getKey()) > 0)) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        final List<String>  kWords = new LinkedList<>();
        while(minHeap.size() > 0) {
            kWords.add(minHeap.poll().getKey());
        }
        Collections.reverse(kWords);
        return kWords;
    }

    // O(n+ nlogn + klogk) time
    private static final List<String> topKFreqWords1(final String[] words, int k) {
        if(words == null || words.length == 0 || k<0) return Collections.emptyList();
        final Map<String, Integer> frequencyMap = new HashMap<>();
        for(final String word: words) frequencyMap.put(word, frequencyMap.getOrDefault(word,0)+1);
        final SortedMap<Integer, List<String>>  kWords = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (final Map.Entry<String, Integer> entry: frequencyMap.entrySet()) {
            final List<String> wordList = kWords.getOrDefault(entry.getValue(), new LinkedList<>());
            wordList.add(entry.getKey());
            kWords.put(entry.getValue(), wordList);
        }
        final List<String> result = new ArrayList<>();
        final Iterator<Integer> keySetIterator = kWords.keySet().iterator();
        while (k > 0 && keySetIterator.hasNext()){
            final List<String> wordList = kWords.getOrDefault(keySetIterator.next(), Collections.emptyList());
            Collections.sort(wordList);
            result.addAll(wordList.subList(0, Math.min(k, wordList.size())));
            k -= wordList.size();
        }
        return result;
    }

    // O(n + klogn) time
    private static final List<String> topKFreqWords2(final String[] words, int k) {
        if(words == null || words.length == 0 || k<0) return Collections.emptyList();
        final Map<String, Integer> frequencyMap = new HashMap<>();
        int highFreq = Integer.MIN_VALUE;
        for(final String word: words) {
            final int freq = frequencyMap.getOrDefault(word,0)+1;
            frequencyMap.put(word, freq);
            highFreq = Math.max(highFreq, freq);
        }
        final List<String>[] bucket = new List[highFreq+1];
        for (final Map.Entry<String, Integer> entry: frequencyMap.entrySet()) {
            final int freq   = entry.getValue();
            if (bucket[freq] == null) bucket[freq] = new LinkedList<>();
            bucket[freq].add(entry.getKey());
        }
        final List<String> result = new ArrayList<>();
        while (k > 0){
            final List<String> wordList = bucket[highFreq--];
            if(null != wordList) {
                Collections.sort(wordList);
                result.addAll(wordList.subList(0, Math.min(k, wordList.size())));
                k -= wordList.size();
            }
        }
        return result;
    }
}
