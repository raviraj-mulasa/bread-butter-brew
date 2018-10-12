package net.geekscore.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Note:
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to
 * a result and there won't be any divide by zero operation.
 *
 * Example 1:
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 * Example 2:
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 *
 * Example 3:
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        // 9
        System.out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        // 6
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        // 22
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        // 0
        System.out.println(evalRPN(new String[]{}));
        // 0
        System.out.println(evalRPN(new String[]{"+"}));
        // 0
        System.out.println(evalRPN(new String[]{"-"}));
        // 0
        System.out.println(evalRPN(new String[]{"*"}));
        // 0
        System.out.println(evalRPN(new String[]{"/"}));
        // 10
        System.out.println(evalRPN(new String[]{"10"}));
        // 0
        System.out.println(evalRPN(new String[]{"+", "10", "11"}));
        // 0
        System.out.println(evalRPN(new String[]{"+", "-", "/", "*"}));
    }

    private static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        final Deque<String> stack = new LinkedList<>();
        for(final String token: tokens) {
            if(isOperator(token)){
                if(stack.isEmpty()) return 0;
                final String operand2 = stack.pop();
                final String operand1 = stack.pop();
                stack.push(String.valueOf(applyOperator(token, operand1, operand2)));
                continue;
            }
            stack.push(token);
        }
        return stack.isEmpty() ? 0 : Integer.valueOf(stack.pop());

    }

    private static boolean isOperator(String token) {
        if(token != null) {
            switch (token){
                case "+":
                case "-":
                case "*":
                case "/":
                    return true;
            }
        }
        return false;
    }

    private static int applyOperator(String operator, String operand1, String operand2) {
        final int op1 = Integer.valueOf(operand1);
        final int op2 = Integer.valueOf(operand2);
        switch (operator){
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                return op1 / op2;
        }
        return 0;
    }
}
