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
        List<List<String>> grpByAnagrams = groupByAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
//        [[eat, tea, ate], [bat], [tan, nat]]
        System.out.println(grpByAnagrams);

        grpByAnagrams = groupByAnagrams(new String[]{"eat","tea","ate", "eta", "aet", "tae", "nat","bat", "tan", "tab", "madam", "Madam"});
//        [[madam, Madam], [eat, tea, ate, eta, aet, tae], [bat, tab], [nat, tan]]
        System.out.println(grpByAnagrams);

        grpByAnagrams = groupByAnagrams(new String[]{"cab","tin","pew","duh","may","ill","buy","bar","max","doc"});
//        [[duh], [bar], [cab], [buy], [ill], [may], [pew], [max], [tin], [doc]]
        System.out.println(grpByAnagrams);

        grpByAnagrams = groupByAnagrams1(new String[]{"hos","boo","nay","deb","wow","bop","bob","brr","hey","rye","eve","elf"});
//       [[eve], [elf], [deb], [wow], [bop], [bob], [brr], [hey], [hos], [boo], [nay], [rye]]
        System.out.println(grpByAnagrams);

        System.out.println("------------");

        grpByAnagrams = groupByAnagrams1(new String[]{"eat","tea","tan","ate","nat","bat"});
//        [[eat, tea, ate], [bat], [tan, nat]]
        System.out.println(grpByAnagrams);

        grpByAnagrams = groupByAnagrams1(new String[]{"eat","tea","ate", "eta", "aet", "tae", "nat","bat", "tan", "tab", "madam", "Madam"});
//        [[madam, Madam], [eat, tea, ate, eta, aet, tae], [bat, tab], [nat, tan]]
        System.out.println(grpByAnagrams);

        grpByAnagrams = groupByAnagrams1(new String[]{"cab","tin","pew","duh","may","ill","buy","bar","max","doc"});
//        [["doc"],["bar"],["buy"],["ill"],["may"],["tin"],["cab"],["pew"],["max"],["duh"]]
        System.out.println(grpByAnagrams);


        grpByAnagrams = groupByAnagrams1(new String[]{"hos","boo","nay","deb","wow","bop","bob","brr","hey","rye","eve","elf"});
//       [[eve], [elf], [deb], [wow], [bop], [bob], [brr], [hey], [hos], [boo], [nay], [rye]]
        System.out.println(grpByAnagrams);

    }

    private static List<List<String>> groupByAnagrams(final String[] strings) {
        if(null == strings || strings.length == 0) {
            return Collections.emptyList();
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

    private static List<List<String>> groupByAnagrams1(final String[] strs) {
        if(null == strs || strs.length == 0) {
            return Collections.emptyList();
        }
        final Map<String,List<String>> anagramsMap = new HashMap<>();
        for (final String str:strs) {
            final String encoding = encode(str);
            final List<String> anagrams = anagramsMap.getOrDefault(encoding, new LinkedList<>());
            anagrams.add(str);
            anagramsMap.put(encoding, anagrams);

        }
        return new LinkedList<>(anagramsMap.values());
    }

    private static String encode(String str) {
        final int[] counter = new int[26];
        for(final char ch: str.toLowerCase().toCharArray()) {
            counter[ch - 'a']++; // Increment
        }
        final StringBuilder lexRunLenEncodingBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if(counter[i] > 0) {
                lexRunLenEncodingBuilder.append(counter[i]);
                lexRunLenEncodingBuilder.append((char)(i+'a'));
            }
        }
        return lexRunLenEncodingBuilder.toString();
    }
}
