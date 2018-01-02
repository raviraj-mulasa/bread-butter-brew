package net.geekscore.string;

import java.util.*;

/**
 * Given an array of string, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 *
 * Return:
 * [
 *  ["ate", "eat","tea"],
 *  ["nat","tan"],
 *  ["bat"]
 * ]
 * Note: All inputs will be in lower-case.
 **/

public class GroupAnagrams {

    public static void main(String[] args) {
        final List<List<String>> grpByAnagrams = groupByAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
        System.out.println(grpByAnagrams);
    }

    private static List<List<String>> groupByAnagrams(final String[] strings) {
        if(null == strings || strings.length == 0) {
            return Collections.EMPTY_LIST;
        }
        final Map<String, List<String>> grpByAnagrams = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            final char[] strChars = strings[i].toLowerCase().toCharArray();
            Arrays.sort(strChars);
            final String str = new String(strChars);
            List<String> anagrams = grpByAnagrams.get(str);
            if(null == anagrams) {
                anagrams = new LinkedList<>();
            }
            anagrams.add(strings[i]);
            grpByAnagrams.put(str, anagrams);
        }
        final List<List<String>> anagramsList = new ArrayList<>(grpByAnagrams.size());
        for (List<String> anagrams: grpByAnagrams.values()) {
            anagramsList.add(anagrams);
        }
        return anagramsList;
    }
}
