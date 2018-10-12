package net.geekscore.stack;

import java.util.*;

/**
 * Created by ravirajmulasa on 8/17/17.
 */
public class Infix2Postfix {

    private static final Map<Character, Integer> OPERATOR_PRECEDENCE = new HashMap<Character, Integer>() {
        {
            put(Character.valueOf('('),0);
            put(Character.valueOf(')'),0);
            put(Character.valueOf('*'), 1);
            put(Character.valueOf('/'), 1);
            put(Character.valueOf('%'), 1);
            put(Character.valueOf('*'), 1);
            put(Character.valueOf('+'), 2);
            put(Character.valueOf('-'), 2);

        }
    };
    private static final Set<Character> OPERATORS = OPERATOR_PRECEDENCE.keySet();

    public static void main(String[] args) {
        final String[] infixExpressions={
                "a*b+c*d"
                ,"(a+b)*(c+d)"
                ,"(a+(b*c))"
                ,"(a+b)*c"
                ,"(a+1)*c+2"
                ,"(a+1)*c%d"
                ,"(a+b*c"
        };
        for (final String infixExpression:infixExpressions) {
            System.out.println(infix2Postfix(infixExpression));
        }
    }

    private static final String infix2Postfix(final String infix) {
        if(infix == null || infix.length() == 0){
            return "";
        }
        final Stack<Character> operatorStack    = new Stack<>();
        final StringBuffer stringBuffer         = new StringBuffer();
        for (final Character token: infix.toCharArray()) {
            final boolean isOperator = OPERATORS.contains(token);
            if(!isOperator){
                stringBuffer.append(token);
                continue;
            }
            switch (token) {
                case '(':
                    operatorStack.push(token);
                    break;
                case ')':
                    stringBuffer.append(popStackTillOpenBrace(operatorStack));
                    break;
                default:
                    stringBuffer.append(operatorsEqlOrHghPrec(operatorStack, token));
                    operatorStack.push(token);
                    break;
            }
        }
        if(operatorStack.isEmpty()) {
            return stringBuffer.toString().trim();
        }
        for (int i = 0; i <= operatorStack.size(); i++) {
            final char operator = operatorStack.pop();
            if (operator == '(') {
                return "Invalid Infix expression";
            }
            stringBuffer.append(operator);
        }
        return stringBuffer.toString().trim();
    }

    private static final char[] popStackTillOpenBrace(final Stack<Character> stack) {
        final StringBuffer operators = new StringBuffer();
        for (int i = 0; i < stack.size(); i++) {
            final char operator = stack.pop();
            if(operator == '('){
                break;
            }
            operators.append(operator);
        }
        return operators.toString().trim().toCharArray();
    }

    private static final char[] operatorsEqlOrHghPrec(final Stack<Character> stack, Character token) {
        final StringBuffer operators = new StringBuffer();
        for (int i = 0; i < stack.size(); i++) {
            final char operator = stack.peek();
//            if(operator == '(' || operator == ')'){
//                stack.pop();
//                continue;
//            }
            if(OPERATOR_PRECEDENCE.get(operator) > OPERATOR_PRECEDENCE.get(token)){
                break;
            }
            operators.append(stack.pop());
        }
        return operators.toString().trim().toCharArray();
    }
}
