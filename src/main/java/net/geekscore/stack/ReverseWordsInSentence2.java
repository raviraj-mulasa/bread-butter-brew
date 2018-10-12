package net.geekscore.stack;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * Given an input string, reverse the string word by word.
 * A word is defined as a sequence of non-space characters.
 *
 * The input string does not contain leading or trailing spaces and the words are always separated by a
 * single space.
 *
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * Could you do it IN-PLACE without allocating extra space?
 * Related problem: Rotate Array
 */
public class ReverseWordsInSentence2 {

    public static void main(String[] args) {
        Instant start = Instant.now();
        System.out.println(Arrays.toString(reverseSentence("Alice likes Bob")));
        System.out.println(Arrays.toString(reverseSentence("Bob likes Alice")));
        System.out.println(Arrays.toString(reverseSentence("Bob   Alice")));
        System.out.println(Arrays.toString(reverseSentence("Bob ")));
        System.out.println(Arrays.toString(reverseSentence(" ")));
        System.out.println(Arrays.toString(reverseSentence("")));
        System.out.println(Arrays.toString(reverseSentence(null)));
        System.out.println("Time taken in nanos: "+ Duration.between(start, Instant.now()).toNanos());
    }


    private static char[] reverseSentence(final String sentence) {
        if(sentence == null || sentence.length() == 0) return new char[0];
        final char[] chars  = sentence.trim().toCharArray();
        reverseSentenceInPlace(chars);
        return chars;
    }

    private static void reverseSentenceInPlace(final char[] chars) {
        reverse(chars, 0, chars.length-1);
        int i=0;
        for(int j=i; j<chars.length; j++){
            if(chars[j]==' '){
                reverse(chars, i, j-1);
                i=j+1;
            }
        }
        reverse(chars, i, chars.length-1); // swap last word
    }

    private static void reverse(char[] chars, int begin, int end) {
        while(begin<end) {
            if(chars[begin] != chars[end]) {
                final char temp = chars[begin];
                chars[begin] = chars[end];
                chars[end] = temp;
            }
            begin++;
            end--;
        }
    }
}
