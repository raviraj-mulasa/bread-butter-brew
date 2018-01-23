package net.geekscore.string;

import java.util.*;

/**
 * Given a string, we can "shift" each of its letter to its successive letter,
 * for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * "abc" -> "bcd" -> ... -> "xyz"
 *
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to
 * the same shifting sequence.
 *
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 *
 * A solution is:
 * [
 *  ["abc","bcd","xyz"],
 *  ["az","ba"],
 *  ["acef"],
 *  ["a","z"]
 * ]
 *
 */
public class GroupShiftedStrings {


    public static void main(String[] args) {
//        [["b"],["aa","bb"]]
        System.out.println(groupShiftedStrings(new String[]{"aa","bb","b"}));
//        [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
        System.out.println(groupShiftedStrings(new String[]{"abc","bcd","acef","xyz","az","ba","a","z"}));
//        [["b"],["aa","bb"],["bbb"]]
        System.out.println(groupShiftedStrings(new String[]{"aa","bbb","b", "bb"}));
    }

    private static List<List<String>> groupShiftedStrings(final String[] strings) {
        if(null == strings || strings.length == 0) {
            return Collections.emptyList();
        }
        final Map<String, List<String>> grpdStrs = new HashMap<>();
        for (final String string:strings) {
            final String key = key(string);
//            System.out.println(string+ " : "+key);
            final List<String> strs = grpdStrs.getOrDefault(key, new LinkedList<>());
            strs.add(string);
            grpdStrs.put(key, strs);
        }
        return new ArrayList<>(grpdStrs.values());
    }

    // To identify each group, compute the modulo 26 difference between each letter in a word with
    // the first letter in it.
    private static String key(final String string) {
        if(string == null || string.length() == 0) {
            return "";
        }
        final int firstChar = string.charAt(0);
        StringBuilder keyBuilder  = new StringBuilder();
        for (int i = 1; i < string.length(); i++) {
            int howFarFromFirstChar = string.charAt(i) - firstChar;
            keyBuilder.append((howFarFromFirstChar +26) % 26);
            keyBuilder.append(",");//char separation required
        }
        return keyBuilder.toString();
    }
}
