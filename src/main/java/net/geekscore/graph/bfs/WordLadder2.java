package net.geekscore.graph.bfs;

import java.util.*;

/**
 *
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 * For example,
 * Given:
 * beginWord = "hit", endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Return
 * [
 *  ["hit","hot","dot","dog","cog"],
 *  ["hit","hot","lot","log","cog"]
 *  ]
 *
 *  Note:
 *  Return an empty list if there is no such transformation sequence.
 *  All words have the same length.
 *  All words contain only lowercase alphabetic characters.
 *  You may assume no duplicates in the word list.
 *  You may assume beginWord and endWord are non-empty and are not the same.
 *
 *  UPDATE (2017/1/20):
 *  The wordList parameter had been changed to a list of strings (instead of a set of strings).
 *  Please reload the code definition to get the latest changes.
 */
public class WordLadder2 {

    public static void main(String[] args) {
        // [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ]
        System.out.println(ladders("hit", "cog", new LinkedList<>(Arrays.asList("hot","dot","dog","lot","log","cog")))); // 5
        // 7
        System.out.println(ladders("toon", "plea", new LinkedList<>(Arrays.asList("poon","plee","same","poie","plea","plie","poin"))));

        System.out.println("-----------");

        // [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ]
        System.out.println(ladders1("hit", "cog", new LinkedList<>(Arrays.asList("hot","dot","dog","lot","log","cog")))); // 5
        // [[toon, poon, poin, poie, plie, plee, plea]]
        System.out.println(ladders1("toon", "plea", new LinkedList<>(Arrays.asList("poon","plee","same","poie","plea","plie","poin"))));
    }

    private static List<List<String>> ladders(final String begin, final String end, List<String> words) {

        if(end == null || end.length() == 0 || null == words || words.size() == 0) return Collections.emptyList();

        final Set<String> wordSet = new HashSet<>(words);
        if(!wordSet.contains(end)) return Collections.emptyList();

        final Map<String, Integer> distance = new HashMap<>();
        distance.put(begin, 1);
        final Map<String, Set<String>> adjList =  new HashMap<>();

        final Deque<String> queue = new LinkedList<>();
        queue.offerFirst(begin);

        while (!queue.isEmpty()) {

            final String top = queue.removeLast();
            final Integer topDistance = distance.get(top);
            wordSet.remove(top);

            final Set<String> topNeighbors = neighbors(top, wordSet);
            adjList.put(top, topNeighbors);
            for (final String neighbor: topNeighbors) {
                if (!distance.containsKey(neighbor)) {// Check if visited
                    distance.put(neighbor, topDistance + 1);
                    queue.offerFirst(neighbor);
                }
            }
        }
//        System.out.println(adjList);
        final List<List<String>> ladders = new LinkedList<>();
        dfs(begin, end,  ladders, new LinkedList<>(), adjList, distance);
        return ladders;
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

    private static void dfs(final String begin, final String end,  final List<List<String>> ladders, List<String> ladderSoFar, final Map<String, Set<String>> adjList, final Map<String, Integer> distance) {
        ladderSoFar.add(begin);
        if(begin.equals(end)) {
            ladders.add(new ArrayList<>(ladderSoFar));
        } else  {
            for (final String neighbor: adjList.get(begin)) {
                if(distance.get(neighbor) == distance.get(begin) + 1) {
                    dfs(neighbor, end,  ladders, ladderSoFar, adjList, distance);
                }
            }
        }
        ladderSoFar.remove(ladderSoFar.size()-1);
    }

    private static class WordLadderNode{
        String word;
        int steps;
        Set<WordLadderNode> adjList = Collections.emptySet();
        WordLadderNode(String word, int steps) {
          this(word, steps,  Collections.emptySet());
        }

        WordLadderNode(String word, int steps, Set<WordLadderNode> adjList) {
            this.word = word;
            this.steps = steps;
            this.adjList = adjList;
        }

        @Override
        public String toString() {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.word);
            stringBuilder.append(" ");
            stringBuilder.append(this.steps);
            if(!this.adjList.isEmpty()) {
                stringBuilder.append(" ->{");
                for (final WordLadderNode neighbor: this.adjList) {
                    stringBuilder.append(neighbor.toString());
                    stringBuilder.append(",");
                }
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
                stringBuilder.append("}");
            }
            return stringBuilder.toString().trim();
        }
    }

    private static List<List<String>> ladders1(String begin, String end, List<String> words) {
        if(words == null || words.isEmpty() || begin == null || begin.length() == 0|| end == null || end.length() == 0) return Collections.emptyList();

        final Set<String> wordSet = new HashSet<>(words);

        final Deque<WordLadderNode> queue = new LinkedList<>();
        final WordLadderNode beginNode = new WordLadderNode(begin, 1);
        queue.offerFirst(beginNode);

        int minSteps = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            final WordLadderNode top = queue.removeLast();
            wordSet.remove(top.word);
            if(end.equals(top.word)) minSteps = Math.min(top.steps, minSteps);
            top.adjList = neighbors(top, wordSet);
            for (final WordLadderNode neighbor: top.adjList) {
                queue.offerFirst(neighbor);
            }
        }
//        System.out.println(beginNode);
        List<List<String>> ladders = new LinkedList<>();
        dfs(end, beginNode, minSteps, ladders, new LinkedList<>());
        return ladders;
    }

    private static Set<WordLadderNode> neighbors(WordLadderNode top, Set<String> wordSet) {
        Set<WordLadderNode> neighbors = new HashSet<>();
        final char[] topChars = top.word.toCharArray();
        for (int j = 0; j < topChars.length; j++) {
            for (char ch = 'z'; ch >= 'a'; ch--) {
                final char temp = topChars[j];
                if(temp != ch){
                    topChars[j] = ch;
                }
                final String newWord = String.valueOf(topChars);
                if(wordSet.contains(newWord)) {
                    neighbors.add(new WordLadderNode(newWord, top.steps+1));
                }
                topChars[j] = temp;
            }
        }
        return neighbors;
    }

    private static void dfs(String end, WordLadderNode begin, int minSteps, List<List<String>> ladders, List<String> ladderSoFar) {
        ladderSoFar.add(begin.word);
        if(begin.word.equals(end) && begin.steps == minSteps) {
            ladders.add(new ArrayList<>(ladderSoFar));
        }
        else {
            for (final WordLadderNode neighbor: begin.adjList) {
                dfs(end, neighbor, minSteps, ladders, ladderSoFar);
            }
        }
        ladderSoFar.remove(ladderSoFar.size()-1);
    }


}
