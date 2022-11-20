package net.geekscore.parenthesis;


/*
 *
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 *  "" - 0
 * "()(((())))()" - 12
 * "((((" - 0
 * "(" - 0
 * "))" - 0
 * ")(" - 0
 * ")((((" - 0
 * ")()(" - 1
 * "(())()" - 6
 * * "(()()" - 4 The longest valid parentheses substring is "()()".
 * "()(()" - 2 The longest valid parentheses substring is "()".
 *  ")()())" -4 The longest valid parentheses substring is "()()".
 * "(()" - 2 The longest valid parentheses substring is "()".
 *
 * SOLUTION:
 * One of the key things to realize about valid parentheses strings is that they're entirely self-satisfied,
 * meaning that while you can have one substring that is entirely inside another, you can't have two substrings that only partially overlap.
 *
 * This means that we can use a greedy O(N) time complexity solution to this problem without the need for any kind of backtracking.
 * In fact, we should be able to use a very standard stack-based valid parentheses string algorithm with just three very minor modifications.
 *
 * In a standard valid parentheses string algorithm, we iterate through the string (S) and push the index (i) of any '('
 * to our stack. Whenever we find a ')', we match it with the last entry on the stack and pop said entry off.
 *  We know the string is not valid if we find a ')' while there are no '(' indexes in the stack with which to match it,
 * and also if we have leftover '(' in the stack when we reach the end of S.

*/

// TODO:

import java.util.List;
import java.util.Stack;

public class LongestValidParenthesis {

    public static void main(String[] args) {
        System.out.println("---------------\"\"---------------");
        System.out.println(longestValidParentheses( ""));



        System.out.println("-----------------\"()(((())))()\"-------------");
        System.out.println(longestValidParentheses( "()(((())))()"));

        System.out.println("----------------\"((((\"--------------");
        System.out.println(longestValidParentheses( "(((("));

        System.out.println("-----------------\"(\"-------------");
        System.out.println(longestValidParentheses( "("));

        System.out.println("---------------\"))\"---------------");
        System.out.println(longestValidParentheses( "))"));

        System.out.println("-----------------\")(\"-------------");
        System.out.println(longestValidParentheses( "("));

        System.out.println("---------------\")((((\"---------------");
        System.out.println(longestValidParentheses( ")(((("));

        System.out.println("----------------\"(())()\"--------------");
        System.out.println(longestValidParentheses( "(())()"));

        System.out.println("---------------\"(()()\"---------------");
        System.out.println(longestValidParentheses( "(()()"));

        System.out.println("------------------\"()(()\"------------");
        System.out.println(longestValidParentheses( "()(()"));

        System.out.println("-----------------\")()())\"-------------");
        System.out.println(longestValidParentheses( ")()())"));

        System.out.println("-----------------\"(()\"-------------");
        System.out.println(longestValidParentheses( "(()"));
    }

    public static int longestValidParentheses(String s) {
        if(s == null || s.length() <= 1) return 0;
        return longestValidParenthesesRecHelper(s.toCharArray());
    }

    private static int longestValidParenthesesRecHelper(final char[] chars) {
        Stack<Integer>  stack = new Stack<>();
        stack.push(-1);
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }
}
