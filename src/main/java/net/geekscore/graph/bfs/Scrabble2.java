package net.geekscore.graph.bfs;


import java.util.*;

/**
 * Evolved Scrabble has the following rules:
 *     To set the game up, create different groups of words of the same size.
 *     To start their turn, each player will select a word group.
 *     They will choose an initial word and a final word from that same selected word group.
 *     Then, the player on the other side has to convert the initial word into the final word.
 *         In one move, only one letter of the word can be converted.
 *         The resulting new word should also be part of the same word group.
 *     The player gets full points if they figure out the minimum number of character conversions to reach the final word.
 *     In the end, when all the lists are exhausted by each player, the player who has converted all the words in the least time will win the game.
 *
 *  We will need to introduce the following features in order to implement the above-mentioned functionality:
 *     Feature #1: Find the minimum number of conversions to reach the final word from the initial word.
 *     Feature #2: For the minimum number of conversions from Feature #1, show all possible end results for users to choose from
 *
 *  Ex1:  Initial Word: "sail",  Final Word: "ruin",  word group: { "mail", "main", "rain", "ruin" }
 *  Ex2:  Initial Word: "Hit",  Final Word: "Cog",  word group: { "Hot", "Dot", "Dog", "Log", "Lot" }
 *
 */
public class Scrabble2 {

    public static void main(String[] args) {

        String initial = "sail";
        String finial = "ruin";
        Set<String> words = new HashSet<>(Arrays.asList("mail", "main", "rain", "ruin"));

        Map<String, Set<String>> allComboGraph = buildComboGraph(initial.length(), words);
        System.out.printf(
                "Initial:%s Final:%s Minimum moves:%d%n"
                , initial
                , finial
                , minimumMoves(initial, finial, allComboGraph)
        );

        initial = "Hit";
        finial = "Cog";
        words = new HashSet<>(Arrays.asList("Hot", "Dot", "Dog", "Log", "Lot", "Cog"));
        allComboGraph = buildComboGraph(initial.length(), words);
        System.out.printf(
                "Initial:%s Final:%s Minimum moves:%d%n"
                , initial
                , finial
                , minimumMoves(initial, finial, allComboGraph)
        );
    }


    private static Map<String, Set<String>> buildComboGraph(int wordLength, Set<String> words) {
        if (wordLength < 1) return Collections.emptyMap();
        Map<String, Set<String>> allComboDict = new HashMap<>();
        Set<String> wordSet = new HashSet<>(words);
        for (String word : wordSet) {
            for (int i = 0; i < wordLength; i++) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                Set<String> transformations = allComboDict.getOrDefault(newWord, new HashSet<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        }
        System.out.println(allComboDict);
        return allComboDict;
    }


    private static int minimumMoves(String initial, String finial, Map<String, Set<String>> allComboGraph) {
        // Do BFS
        Deque<WordNode> queue = new LinkedList<>();
        queue.offerFirst(new WordNode(initial, 1));

        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            System.out.println(queue);
            WordNode top = queue.removeLast();
            int steps = top.steps;
            String word = top.word;
            if (word.equals(finial)) {
                return steps;
            }
            for (int i = 0; i < word.length(); i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1);
//                System.out.println("New word:"+newWord);
                if(allComboGraph.containsKey(newWord)) {
                    Set<String> neighbors = allComboGraph.get(newWord);
                    for (String neighbor : neighbors) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.offerFirst(new WordNode(neighbor, steps + 1));
                        }
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
