package net.geekscore.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given an encoded string, return it's decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is
 * being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are
 * well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits
 * are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

 */
public class DecodeString {

    public static void main(String[] args) {
        System.out.println(decode("3[a2[c]]")); // accaccacc
        System.out.println(decode("3[a]2[bc]")); // aaabcbc
        System.out.println(decode("2[abc]3[cd]ef")); // abcabccdcdcdef

        System.out.println("--------------------");

        System.out.println(decodeRec("3[a2[c]]")); // accaccacc
        System.out.println(decodeRec("3[a]2[bc]")); // aaabcbc
        System.out.println(decodeRec("2[abc]3[cd]ef")); // abcabccdcdcdef
        
    }

    private static String decodeRec(final String encoded) {
        if(encoded == null || encoded.length() == 0) return encoded;
        final List<String> decodedStrs = new LinkedList<>();
        decodeRecHelper(encoded, 0, "", new LinkedList<>(), "");
//        System.out.println(decodedStrs);
        return "";

    }

    private static void decodeRecHelper(final String encoded, int i, final String decoded, final List<Integer> counts, final String curr) {
        if(i == encoded.length()) {
            System.out.println(curr);
            return;
        }
        final char ch = encoded.charAt(i);
        System.out.println("Curr "+curr+" Char "+ch+" Decoded  "+decoded+" Counts "+counts);
        if(Character.isDigit(ch)) {
            final int start = i;
            while (Character.isDigit(encoded.charAt(i+1))) i++;
            counts.add(Integer.valueOf(encoded.substring(start, i+1)));
            decodeRecHelper(encoded, i+1, decoded, counts, curr);
        }
        else if(ch == '[') {
            decodeRecHelper(encoded, i+1, decoded+curr, counts, "");
        }
        else if(ch == ']') {
            final Integer count = counts.isEmpty() ? 0 : counts.remove(counts.size()-1);
            final StringBuilder builder = new StringBuilder();
            for (int j = 0; j < count; j++) {
                builder.append(curr);
            }
            final String neeDecoded = decoded+builder.toString();
            decodeRecHelper(encoded, i+1, decoded+builder.toString(), counts, builder.toString());
        } else {
            decodeRecHelper(encoded, i+1, decoded, counts, curr+ch);
        }
    }
    
    private static String decode(final String encoded) {
        if(encoded == null || encoded.length() == 0) return encoded;
        final Stack<String> result = new Stack<>();
        final Stack<Integer> counts = new Stack<>();
        result.push("");
        int i = 0;
        while (i < encoded.length()) {
//            System.out.println(result+" "+counts);
            final char ch = encoded.charAt(i);
            if(Character.isDigit(ch)) { // digit
                final int start = i;
                while (Character.isDigit(encoded.charAt(i+1))) i++;
                // Move to the next char after digits  a.k.a increment i
                final Integer count = Integer.valueOf(encoded.substring(start, ++i));
                counts.push(count);
                continue;
            }
            switch (ch) {
                case '[':
                    result.push("");
                    break;
                case ']':
                    final String str2Repeat = result.pop();
                    final StringBuilder builder = new StringBuilder();
                    final int k = counts.pop();
                    for (int j = 0; j < k; j++) {
                        builder.append(str2Repeat);
                    }
                    result.push(result.pop()+builder.toString());
                    break;
                default:
                    result.push(result.pop()+ ch);
            }
            i++;
        }
        return result.pop();
    }
}
