package net.geekscore.algo.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * For example, given s = "aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        System.out.println(palindromePartitions("aab"));
    }

    private static List<List<String>> palindromePartitions(final String str) {
        if(null == str || str.length() == 0) return Collections.emptyList();
        List<List<String>> partitions = new LinkedList<>();
        palindromePartitionsHelper(str, 0, partitions, new LinkedList<>());
        return partitions;
    }

    private static void palindromePartitionsHelper(final String str, int begin, List<List<String>> partitions, final List<String> currList) {
        if(begin == str.length()) partitions.add(new ArrayList<>(currList));
        for (int i = begin; i < str.length(); i++) {
            if (isPalindrome(str, begin, i)){
                currList.add(str.substring(begin, i+1));
                palindromePartitionsHelper(str, i+1, partitions, currList);
                currList.remove(currList.size()-1);
            }
        }
    }

    private static boolean isPalindrome(String partition, int i, int j) {
        while (i < j) {
            if(partition.charAt(i) != partition.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}
