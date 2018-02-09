package net.geekscore.algo.divideNconquer;

import java.util.*;

/**
 *
 * Given a file of strings in some mystery lexicographic order, come up with a way to determine the order the
 * file is in.
 *
 * I always make sure the candidate understands what lexicographic order means.
 * If they're confused I usually motivate it by writing out a sample input file, e.g.:
 *
 * apricot
 * baby
 * bath
 * cab
 * cat
 * frog
 * game
 * zoo
 *
 * A sorted order of characters for the above file could be: a, b, c, t, f, g, z
 * because we only have enough information to have relative ordering between characters.
 *
 * Note that this is a partial ordering (there are several possible partial orderings here).
 * When there is only one possible ordering, we call this total ordering (eg the ordering of the alphabet in our universe).
 */

public class PartialOrdering {

    public static void main(String[] args) {
        System.out.println(orderingNWords(new String[] {"abc", "ace" ,"mbc" ,"mmc"})); // [b, c, m]
        System.out.println(orderingNWords(new String[] {"apricot", "baby" ,"bath" ,"cab", "cat", "frog", "game", "zoo"})); // [a, b, c, t, f, g, z]
        System.out.println(orderingNWords(new String[] {"baby" , "bath"})); // [b, t]
        System.out.println(orderingNWords(new String[] {"baby" , null}));
        System.out.println(orderingNWords(new String[] {null , "baby"}));
        System.out.println(orderingNWords(new String[] {"" , null}));
        System.out.println(orderingNWords(new String[] {null , ""}));
    }


    private static final Set<Character> orderingNWords(final String[]  words) {
        Set<Character> ordering = new HashSet<>();
        if(words == null || words.length < 2) return ordering;
        if(words.length == 2) {
            ordering.addAll(ordering2Words(words[0], words[1]));
        } else {
            final String[] left = Arrays.copyOfRange(words, 0, words.length/2 + 1);
            final String[] right = Arrays.copyOfRange(words, (words.length+1)/2, words.length);
            ordering.addAll(orderingNWords(left));
            ordering.addAll(orderingNWords(right));
        }
        return ordering;

    }

    private static final List<Character> ordering2Words(final String str1, String str2) {
        if(str1 == null || 0 == str1.length()) return Collections.emptyList();
        List<Character> ordering = new LinkedList<>();
        if(str2 != null) {
            int i = 0, j = 0;
            while (i < str1.length() && j < str2.length()) {
                if(str1.charAt(i) == str2.charAt(j)) {
                    i++;
                    j++;
                    continue;
                }
                ordering.add(str1.charAt(i));
                ordering.add(str2.charAt(j));
                break;
            }
        }
        return ordering;
    }
}
