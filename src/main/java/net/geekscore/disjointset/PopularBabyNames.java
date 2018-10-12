package net.geekscore.disjointset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/*
 *  Every year, the government lists the most popular baby names
 *  (name and count). The problem is that some names have multiple
 *  spellings and are listed separately (like "Michael" and "Mike").
 *  Given a map of name and count and a list of equivalent name pairs,
 *  write an algorithm to print a new list of the unique names and
 *  their count.
 *
 */
public class PopularBabyNames {

    public static void main(String[] args) {

        final Map<String, Integer> nameCounts = new HashMap<>();
        nameCounts.put("Jen", 4);
        nameCounts.put("Jenny", 5);
        nameCounts.put("Jennifer", 3);
        nameCounts.put("Janet", 3);
        nameCounts.put("Michael", 2);
        nameCounts.put("Mike", 3);
        nameCounts.put("Sara", 4);
        nameCounts.put("Sarah", 2);
        nameCounts.put("David", 3);

        final String[][] synonyms = {
                {"Jen", "Jenny"},
                {"Jenny", "Jennifer"},
                {"Jennifer", "Janet"},
                {"Sara", "Sarah"},
                {"Michael", "Mike"}
        };

        final Map<String, Integer> uniqueCounts = getUniqueNameCounts(nameCounts, synonyms);

        System.out.println("---- Jen Name ----"); // 15
        Stream.of("Jen", "Jenny", "Jennifer", "Janet", "Je")
                .map(x -> x+" "+uniqueCounts.getOrDefault(x, 0)).forEach(System.out::println);

        System.out.println("---- Sara Names -----"); // 5
        Stream.of("Sara", "Sarah")
                .filter(uniqueCounts::containsKey).map(x -> x+" "+uniqueCounts.get(x)).forEach(System.out::println);

        System.out.println("----- Mike Names ------"); // 6
        Stream.of("Mike", "Michael")
                .filter(uniqueCounts::containsKey).map(x -> x+" "+uniqueCounts.get(x)).forEach(System.out::println);

        System.out.println("------- David Names -------"); // 3
        Stream.of("David")
                .filter(uniqueCounts::containsKey).map(x -> x+" "+uniqueCounts.get(x)).forEach(System.out::println);

    }

    private static Map<String, Integer> getUniqueNameCounts(final Map<String, Integer> nameCounts, final String[][] synonyms) {
        final Map<String, Integer> uniqueNameCounts = new HashMap<>();
        final Map<String, Set<String>> groupedSynonyms = groupSynonyms(nameCounts, synonyms);
        for (final Set<String> _synonyms: groupedSynonyms.values()) {
            int count = 0;
            for (final String word: _synonyms) {
                count += nameCounts.getOrDefault(word, 0);
            }
            for (final String word: _synonyms) {
                uniqueNameCounts.put(word, count);
            }
        }
        return uniqueNameCounts;
    }

    private static Map<String, Set<String>> groupSynonyms(final Map<String, Integer> nameCounts, final String[][] synonyms) {
        final Map<String, Set<String>> adjList = new HashMap<>();
        final Map<String, String> parent = new HashMap<>();
        for (final String word: nameCounts.keySet()) {
            makeSet(parent, word);
        }
        for (final String[] synonym: synonyms) {
            union(parent, synonym[0], synonym[1]);
        }
        for (final String word: nameCounts.keySet()) {
            final String root = findSet(parent, word);
            final Set<String> set = adjList.getOrDefault(root, new HashSet<>());
            set.add(word);
            adjList.put(root, set);
        }
        return adjList;
    }

    private static void makeSet(final Map<String, String> parent, final String word) {
        if(!parent.containsKey(word)) parent.put(word, word);
    }

    private static String findSet(final Map<String, String> parent, final String word) {
        final String root = parent.get(word);
        if(root.equals(word)) return word;
        parent.put(root, parent.get(root));
        return parent.get(root);
    }

    private static void union(final Map<String, String> parent, final String word1, final String word2) {
        final String root1 = parent.get(word1);
        final String root2 = parent.get(word2);
        if(!root1.equals(root2)) {
            parent.put(parent.get(root2), root1);
        }
    }
}
