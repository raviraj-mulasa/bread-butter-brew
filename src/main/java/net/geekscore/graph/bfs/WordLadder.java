package net.geekscore.graph.bfs;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 * For example,
 * Given:  beginWord = "hit", endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 */
public class WordLadder {

    public static void main(String[] args) {
        // 5
        System.out.println(ladderLength("hit", "cog", new LinkedList<>(Arrays.asList("hot","dot","dog","lot","log","cog")))); // 5
        // 7
//        System.out.println(ladderLength("toon", "plea", new LinkedList<>(Arrays.asList("poon","plee","same","poie","plea","plie","poin"))));
//
//        System.out.println("------------------");
//        // 5
//        System.out.println(ladderLength1("hit", "cog", new LinkedList<>(Arrays.asList("hot","dot","dog","lot","log","cog")))); // 5
//        // 7
//        System.out.println(ladderLength1("toon", "plea", new LinkedList<>(Arrays.asList("poon","plee","same","poie","plea","plie","poin"))));
//

    }

    private static int ladderLength(final String beginWord, final String endWord, List<String> words) {

        if(endWord == null || endWord.length() == 0 || words == null || words.size() == 0) return 0;

        final Set<String> wordSet = new HashSet<>(words);
        if(!wordSet.contains(endWord)) return 0;

        final Deque<WordNode> queue = new LinkedList<>();
        queue.offerFirst(new WordNode(beginWord, 1));

        while (!queue.isEmpty()) {

            final WordNode top = queue.removeLast();
            wordSet.remove(top.word);

            if(top.word.equals(endWord)) return top.steps;

            final char[] topWordChars = top.word.toCharArray();
            for (int i = 0; i < topWordChars.length; i++) {
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    final char temp = topWordChars[i]; // backup the letter at index i
                    if(topWordChars[i] != letter) {
                        topWordChars[i] = letter; // change the letter at index i
                    }
                    final String newWord = String.valueOf(topWordChars);
                    if(wordSet.contains(newWord)) {
                        queue.offerFirst(new WordNode(newWord, top.steps+1));
                    }
                    topWordChars[i] = temp; // revert the character
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static int ladderLength1(final String begin, final String end, List<String> words) {

        if(end == null || end.length() == 0 || null == words || words.size() == 0) return 0;
        final Set<String> wordSet = new HashSet<>(words);
        if(!wordSet.contains(end)) return 0;

        final Map<String, Integer> distance = new HashMap<>();
        distance.put(begin, 1);

        final Deque<String> queue = new LinkedList<>();
        queue.offerFirst(begin);

        while (!queue.isEmpty()) {

            final String top = queue.removeLast();
            final Integer topDistance = distance.get(top);
            wordSet.remove(top);

            if(top.equals(end)) return topDistance;

            final Set<String> topNeighbors = neighbors(top, wordSet);
            for (final String neighbor: topNeighbors) {
                if (!distance.containsKey(neighbor)) {// Check if visited
                    distance.put(neighbor, topDistance + 1);
                    queue.offerFirst(neighbor);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static Set<String> neighbors(String word, Set<String> wordSet) {
        Set<String> neighbors = new HashSet<>();
        final char[] topChars = word.toCharArray();
        for (int j = 0; j < topChars.length; j++) {
            for (char ch = 'z'; ch >= 'a'; ch--) {
                final char temp = topChars[j];
                if(temp != ch){
                    topChars[j] = ch;
                }
                final String newWord = String.valueOf(topChars);
                if(wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
                topChars[j] = temp;
            }
        }
        return neighbors;
    }



}


