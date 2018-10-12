package net.geekscore.disjointset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar
 * word pairs pairs, determine if two sentences are similar.
 *
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar,
 * if the similar word pairs are
 * pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 *
 * Note that the similarity relation is transitive.
 * For example, if "great" and "good" are similar, and "fine" and "good" are similar,
 * then "great" and "fine" are similar.
 *
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as
 * "fine" and "great" being similar.
 *
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"],
 * pairs = [] are similar, even though there are no specified similar word pairs.
 *
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like
 * words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 *
 * Note:
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class SentenceSimilarity2 {

    public static void main(String[] args) {
        System.out.println(areSentencesSimilarTwo(
                new String[]{"great", "acting", "skills"}
                ,new String[]{"fine", "drama", "talent"}
                ,new String[][]{{"great", "good"}, {"fine", "good"}, {"acting","drama"}, {"skills","talent"}}
        )); // true

        System.out.println(areSentencesSimilarTwo(
                new String[]{"great", "acting", "skills"}
                ,new String[]{"fine", "painting", "talent"}
                ,new String[][]{{"great", "fine"}, {"acting","drama"}, {"skills","talent"}}
        )); // false


        System.out.println(areSentencesSimilarTwo(
                new String[]{"this","summer","thomas","get","really","very","rich","and","have","any","actually","wonderful","and","good","truck","every","morning","he","drives","an","extraordinary","truck","around","the","nice","city","to","eat","some","extremely","extraordinary","food","as","his","meal","but","he","only","entertain","an","few","well","fruits","as","single","lunch","he","wants","to","eat","single","single","and","really","healthy","life"}
                ,new String[]{"this","summer","thomas","get","very","extremely","rich","and","possess","the","actually","great","and","wonderful","vehicle","every","morning","he","drives","unique","extraordinary","automobile","around","unique","fine","city","to","drink","single","extremely","nice","meal","as","his","super","but","he","only","entertain","a","few","extraordinary","food","as","some","brunch","he","wants","to","take","any","some","and","really","healthy","life"}
                ,new String[][]{{"good","wonderful"},{"nice","well"},{"fine","extraordinary"},{"excellent","good"},{"wonderful","nice"},{"well","fine"},{"extraordinary","excellent"},{"great","wonderful"},{"one","the"},{"a","unique"},{"single","some"},{"an","one"},{"the","a"},{"unique","single"},{"some","an"},{"any","the"},{"car","wagon"},{"vehicle","car"},{"auto","vehicle"},{"automobile","auto"},{"wagon","automobile"},{"truck","wagon"},{"have","have"},{"take","take"},{"eat","eat"},{"drink","drink"},{"entertain","entertain"},{"meal","food"},{"lunch","breakfast"},{"super","brunch"},{"dinner","meal"},{"food","lunch"},{"breakfast","super"},{"brunch","dinner"},{"fruits","food"},{"own","own"},{"have","have"},{"keep","keep"},{"possess","own"},{"very","very"},{"super","super"},{"really","really"},{"actually","actually"},{"extremely","extremely"}}
        )); // false

        System.out.println(areSentencesSimilarTwo(
                new String[]{"great"}
                ,new String[]{"great"}
                ,new String[0][0]
        )); // true

        System.out.println(areSentencesSimilarTwo(
                new String[]{"great"}
                ,new String[]{"doubleplus","good"}
                ,new String[][]{{"great", "fine"}, {"acting","drama"}, {"skills","talent"}}
        )); // false
    }

    private static boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if(words1.length != words2.length) return false;

        final Map<String, String> rootMap = new HashMap<>();
        for (final String[] pair: pairs) {
            union(rootMap, pair[0], pair[1]);
        }

        for (int i = 0; i < words1.length; i++) {
            final boolean similar = words1[i].equals(words2[i])
                    || findSet(rootMap, words1[i]).equals(findSet(rootMap, words2[i]));
            if(!similar) return false;
        }
        return true;
    }

    private static void makeSet(final Map<String, String> rootMap, final String word) {
        if(!rootMap.containsKey(word)) {
            rootMap.put(word, word);
        }
    }

    private static void union(final Map<String, String> rootMap, final String word1, String word2) {
        final String root1 = findSet(rootMap, word1);
        final String root2 = findSet(rootMap, word2);
        if(!root1.equals(root2)) {
            rootMap.put(rootMap.get(root1), root2);
        }
    }

    private static String findSet(final Map<String, String> rootMap, final String word) {
        makeSet(rootMap, word);
        String root = rootMap.get(word);
        if(root.equals(word)) return word;
        rootMap.put(root, findSet(rootMap, root)); // path compression
        return rootMap.get(root);
    }
}
