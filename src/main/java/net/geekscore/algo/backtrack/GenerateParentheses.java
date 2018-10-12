package net.geekscore.algo.backtrack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed
 * parentheses.
 * For example, given n = 3, a solution set is:
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(parentheses(3));
        System.out.println(generateParentheses(3));
    }

    private static List<String> parentheses(final int n) {
        if(n <= 0) return Collections.emptyList();
        List<String> parentheses = new LinkedList<>();
        parenthesesHelper(n, n, parentheses, new StringBuilder());
        return parentheses;
    }

    private static void parenthesesHelper(final int right, final int left, List<String> parentheses , StringBuilder  parenthesesSoFar) {
        if(left > right) return;
        if(0 == left && 0 == right) parentheses.add(parenthesesSoFar.toString().trim());
        // Used  during backtrack step for right sub tree
        final StringBuilder temp = new StringBuilder(parenthesesSoFar);
        // Choose and Explore
        if(left > 0) parenthesesHelper(right, left-1, parentheses, parenthesesSoFar.append("("));
        // Backtrack, choose and explore
        if(right > 0) parenthesesHelper(right-1, left, parentheses, temp.append(")"));
    }

    private static List<String> generateParentheses(final int n) {
        if(n <= 0) return Collections.emptyList();
        List<String> parentheses = new LinkedList<>();
        generateParenthesesHelper(n, n, parentheses, "");
        return parentheses;
    }

    private static void generateParenthesesHelper(final int begin, final int end, final List<String> parentheses , final String  parenthesesSoFar) {
        if(end < begin ) return;
        if(begin == 0 && end == 0) {
            parentheses.add(parenthesesSoFar);
            return;
        }
        if(begin > 0)generateParenthesesHelper(begin-1, end, parentheses, parenthesesSoFar+'(');
        if(end > 0)generateParenthesesHelper(begin, end-1, parentheses, parenthesesSoFar+')');
    }


}
