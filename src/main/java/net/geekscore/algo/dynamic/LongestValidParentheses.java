package net.geekscore.algo.dynamic;

import net.geekscore.array.ArrayUtil;

import java.util.Arrays;

/**
 * TODO
 *
 *
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 *
 * Another example is ")()())", where the longest valid parentheses substring is "()()",
 * which has length = 4.
 *
 *  Another example is "()(()", where the longest valid parentheses substring is "()",
 * which has length = 2.
 */


public class LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println("-----------");
        System.out.println(longestValidParenthesesRec("(")); // 0
        System.out.println(longestValidParenthesesRec("()")); // 2
        System.out.println(longestValidParenthesesRec("(()")); // 2
        System.out.println(longestValidParenthesesRec("(())")); // 4
        System.out.println(longestValidParenthesesRec("()(()")); // 2
        System.out.println(longestValidParenthesesRec(")()())")); // 4
        System.out.println(longestValidParenthesesRec("()()")); // 4
        System.out.println(longestValidParenthesesRec("()(()))))")); // 6
        System.out.println(longestValidParenthesesRec("((())))()")); // 8
        System.out.println(longestValidParenthesesRec("()((()))()")); // 10

        System.out.println("-----------");
        System.out.println(longestValidParentheses("(")); // 0
        System.out.println(longestValidParentheses("()")); // 2
        System.out.println(longestValidParentheses("(()")); // 2
        System.out.println(longestValidParentheses("(())")); // 4
        System.out.println(longestValidParentheses("()(()")); // 2
        System.out.println(longestValidParentheses(")()())")); // 4
        System.out.println(longestValidParentheses("()()")); // 4
        System.out.println(longestValidParentheses("()(()))))")); // 6
        System.out.println(longestValidParentheses("((())))()")); // 8
        System.out.println(longestValidParentheses("()((()))()")); // 10

    }

    private static final int longestValidParenthesesRec(final String str) {
        if(str == null || str.length() <= 1) return 0;
        return longestValidParenthesesRecHelper(str.toCharArray(), 0, str.length()-1);
    }

    private static final int longestValidParenthesesRecHelper(final char[] chars, int i, int j) {
        if(i>=j) return 0;
        if(chars[i] == '(' && chars[i+1] == ')') return longestValidParenthesesRecHelper(chars, i+2, j) + 2;
        else if(chars[j-1] == '(' && chars[j] == ')') return longestValidParenthesesRecHelper(chars, i, j-2) + 2;
        else if(chars[i] == '(' && chars[j] == ')') return longestValidParenthesesRecHelper(chars, i+1, j-1) + 2;
        else return 0;
//        else if(chars[i] == ')') return longestValidParenthesesRecHelper(chars, i+1, j);
//        else return longestValidParenthesesRecHelper(chars, i, j-1);
//        else return Math.max(
//                longestValidParenthesesRecHelper(chars, i, j-1),
//
//        );
    }

    private static final int longestValidParentheses(final String str) {
        if(str == null || str.length() <= 1) return 0;
        final int[][] validParensLen = new int[str.length()][str.length()];
        for (int len = 1; len < str.length(); len++) {
            for (int i = 0; i < str.length()-len; i++) {
                final int j = i + len;
                if(str.charAt(i) == '(' && str.charAt(j) == ')') {
                    validParensLen[i][j] = 2 + validParensLen[i+1][j-1];
                } else {
                    validParensLen[i][j] = Math.max(validParensLen[i+1][j], validParensLen[i][j-1]);
                }
            }
        }
        ArrayUtil.print(validParensLen);
        return validParensLen[0][str.length()-1];
    }
}
