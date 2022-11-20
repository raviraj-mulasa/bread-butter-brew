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
public class Scrabble {

    public static void main(String[] args) {

        String initial = "sail";
        String finial = "ruin";
        Map<String, Set<String>> graph = Collections.emptyMap();
        Set<String> words = Collections.emptySet();

        words = new HashSet<>(Arrays.asList("mail", "main", "rain", "ruin"));
        graph = buildComboGraph(words);
        System.out.printf(
                "Initial:%s Final:%s Minimum moves:%d%n"
                , initial
                , finial
                , minimumMoves(initial, finial, graph)
        );
//
//        initial = "Hit";
//        finial = "Cog";
//        words = new HashSet<>(Arrays.asList("Hot", "Dot", "Dog", "Log", "Lot", "Cog"));
//        graph = buildComboGraph(words);
//        System.out.printf(
//                "Initial:%s Final:%s Minimum moves:%d%n"
//                , initial
//                , finial
//                , minimumMoves(initial, finial, graph)
//        );

        initial = "sail";
        finial = "ruin";
        words = new HashSet<>(Arrays.asList("mail", "main", "rain", "ruin"));
        graph = buildComboGraphOptimized(words);
        System.out.printf(
                "Initial:%s Final:%s Minimum moves:%d%n"
                , initial
                , finial
                , minimumMovesOptimized(initial, finial, graph)
        );

//        initial = "Hit";
//        finial = "Cog";
//        words = new HashSet<>(Arrays.asList("Hot", "Dot", "Dog", "Log", "Lot", "Cog"));
//        graph = buildComboGraphOptimized(words);
//        System.out.printf(
//                "Initial:%s Final:%s Minimum moves:%d%n"
//                , initial
//                , finial
//                , minimumMovesOptimized(initial, finial, graph)
//        );
    }


    private static Map<String, Set<String>> buildComboGraph(Set<String> words) {
        Map<String, Set<String>> allComboDict = new HashMap<>();
        for (String word : words) {
            int wordLength = word.length();
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

    private static Map<String, Set<String>> buildComboGraphOptimized(Set<String> words) {
        Map<String, Set<String>> allComboDict = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for (String word : words) {
            char[] wordCharArray = word.toCharArray();
            for (int i = 0; i < wordCharArray.length; i++) {
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    final char temp = wordCharArray[i]; // backup the letter at index i
                    if(wordCharArray[i] != letter) {
                        wordCharArray[i] = letter; // change the letter at index i
                    }
                    final String newWord = String.valueOf(wordCharArray);
                    if(words.contains(newWord)) {
                        Set<String> neighbors = allComboDict.getOrDefault(word, new HashSet<>());
                        neighbors.add(newWord);
                        allComboDict.put(word, neighbors);
                    }
                    wordCharArray[i] = temp;
                }
            }
        }
        System.out.println(allComboDict);
        return allComboDict;
    }

    private static int minimumMovesOptimized(String initial, String finial, Map<String, Set<String>> graph) {

        Set<String> visited = new HashSet<>();

        Deque<WordNode> queue = new LinkedList<>();
        queue.offerFirst(new WordNode(initial, 1));
        while (!queue.isEmpty()) {
            System.out.println(queue);
            WordNode top = queue.removeLast();
            String word = top.word;
            int steps = top.steps;
            if(word.equals(finial)) {
                return steps;
            }
            char[] wordArray = word.toCharArray();
            for(int i = 0; i < wordArray.length; i++) {
             for (char letter = 'a'; letter <= 'z'; letter++) {
                 char temp = wordArray[i];
                 if(letter != temp) {
                     wordArray[i] = letter;
                 }
                 String newWord = String.valueOf(wordArray);
                 if(graph.containsKey(newWord)) {
                     System.out.println("New word: "+ newWord);
                     Set<String> neighbors = graph.get(newWord);
                     for (String neighbor: neighbors) {
                         if(!visited.contains(neighbor)) {
                             queue.offerFirst(new WordNode(neighbor, steps+1));
                             visited.add(neighbor);
                         }
                     }
                 }
                 wordArray[i] = temp;
             }
            }
        }
        return Integer.MAX_VALUE;
    }

}
