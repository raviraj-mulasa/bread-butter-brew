package net.geekscore.stack;

import java.time.Duration;
import java.time.Instant;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an input string, reverse the string word by word.
 *
 * Example:
 * Input: "the sky is blue",
 * Output: "blue is sky the".
 *
 * Notes:
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * Follow up: For C programmers, try to solve it in-place in O(1) space.
 */
public class ReverseWordsInSentence {

    public static void main(String[] args) {

        Instant start = Instant.now();
        System.out.println(reverseSentence("Alice likes Bob"));
        System.out.println(reverseSentence("Bob likes Alice"));
        System.out.println(reverseSentence("Bob   Alice"));
        System.out.println(reverseSentence("Bob "));
        System.out.println(reverseSentence(" "));
        System.out.println(reverseSentence(""));
        System.out.println(reverseSentence(null));
        System.out.println("Time taken in nanos: "+ Duration.between(start, Instant.now()).toNanos());
    }

    private static String reverseSentence(String s) {
        if(s == null || s.length() == 0) return s;
        final Deque<String> stack = new LinkedList<>();
        for(String word : s.trim().split(" ")) {
            word = word.trim();
            if(word.length() > 0) stack.push(word);
        }
        final StringBuilder strBuilder = new StringBuilder();
        while(!stack.isEmpty()) {
            strBuilder.append(stack.pop()).append(' ');
        }
        if(strBuilder.length() > 0) strBuilder.deleteCharAt(strBuilder.length()-1);
        return strBuilder.toString();
    }


}
