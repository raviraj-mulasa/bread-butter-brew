package net.geekscore.string;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has
 * exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 *
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line do not divide evenly between words, the empty slots on the left
 * will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 * Example 1:
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 *
 * Output:
 * [
 *  "This    is    an",
 *  "example  of text",
 *  "justification.  "
 *  ]
 *
 * Example 2:
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 *  "What   must   be",
 *  "acknowledgment  ",
 *  "shall be
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 * because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified becase it contains only one word.
 *
 *
 * Example 3:
 * Input:
 * words = ["Science","is","what","we","understand","well","enough","to","explain", "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 *  "Science  is  what we",
 *  "understand      well",
 *  "enough to explain to",
 *  "a  computer.  Art is",
 *  "everything  else  we",
 *  "do                  "
 * ]
 */
public class TextJustification {

    public static void main(String[] args) {
        System.out.println(fullJustify(
                new String[]{"This", "is", "an", "example", "of", "text", "justification."}
                ,16
        ));

        System.out.println(fullJustify(
                new String[]{"What","must","be","acknowledgment","shall","be"}
                ,16
        ));

        System.out.println(fullJustify(
                new String[]{"Science","is","what","we","understand","well","enough","to","explain",
                        "to","a","computer.","Art","is","everything","else","we","do"}
                ,20
        ));
    }

    private static List<String> fullJustify(String[] words, int maxWidth) {
        if(maxWidth < 1 || words == null || words.length == 0) return Collections.emptyList();
        final List<String> lines = new LinkedList<>();
        for (int i = 0; i < words.length;) {
            final List<String> wordsInLine = new LinkedList<>();
            wordsInLine.add(words[i]);
            int lineLength = words[i].length();
            int last = i + 1;
            while (last < words.length && lineLength + 1 + words[last].length() <= maxWidth) {
                wordsInLine.add(words[last]);
                lineLength += (1 + words[last].length()); // +1 for extra space
                last++;
            }
            if(wordsInLine.size() == 1 || last == words.length) {
                // One word or is this the last line with > 1 words
                // left justify
                lines.add(leftJustify(wordsInLine, maxWidth));
            } else {
                // full justify
                lines.add(fullJustify(wordsInLine, maxWidth, lineLength));
            }
            i = last;
        }
        return lines;

    }

    private static String leftJustify(List<String> wordsInLine, int maxWidth) {
        if(wordsInLine.size() > 0) {
            final StringBuilder lineBuilder = new StringBuilder(wordsInLine.get(0));
            int runningLineLength = wordsInLine.get(0).length();
            int spaces = wordsInLine.size() - 1;
            for (int i = 1; i < wordsInLine.size(); i++) {
                lineBuilder.append(' ');
                if(spaces > 0) spaces--;
                lineBuilder.append(wordsInLine.get(i));
                runningLineLength += (wordsInLine.get(i).length() + 1);
            }
            int spaceLeft2Fill = maxWidth - runningLineLength;
            while (spaceLeft2Fill > 0){
                lineBuilder.append(' ');
                spaceLeft2Fill--;
            }
            return lineBuilder.toString();
        }
        return "";
    }


    private static String fullJustify(List<String> wordsInLine, int maxWidth, int lineLength) {

        if(wordsInLine.size() > 0) {

            final StringBuilder lineBuilder = new StringBuilder(wordsInLine.get(0));

            int spaces = wordsInLine.size()  - 1;

            int extraSpaces = maxWidth - lineLength;
            if((spaces-1) < extraSpaces) extraSpaces = extraSpaces/Math.max((spaces-1),1);

            System.out.println(spaces+"  "+extraSpaces+" "+lineLength+"  "+maxWidth);

            for (int i = 1; i < wordsInLine.size(); i++) {
                if(spaces > 0) {
                    lineBuilder.append(' ');
                    spaces--;
                }
                for (int j = 0; j < extraSpaces; j++) {
                    lineBuilder.append(' ');
                }
                lineBuilder.append(wordsInLine.get(i));
            }
            return lineBuilder.toString();
        }
        return "";
    }
}
