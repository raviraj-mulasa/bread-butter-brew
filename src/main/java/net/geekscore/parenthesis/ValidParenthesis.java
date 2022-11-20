package net.geekscore.parenthesis;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by ravirajmulasa on 8/12/17.
 *
 * A.k.a BalancedBraces
 */
public class  ValidParenthesis {

    public static void main(String[] args) {
        String values[] ={
            "{}[]()", "{[}]}" ,"({[]})", "{[}]", "[{)]", "{", "{}", "[{}()]", "]{}[", "{(["
        };
        Arrays.stream(braces(values)).forEach(System.out::println);
    }

    private static String[] braces(String [] values) {
        if(null == values || values.length == 0){
            return new String[0];
        }
        final String[] balancedValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            if(isBalanced(values[i])) {
                balancedValues[i] = values[i]+" YES";
            } else {
                balancedValues[i] = values[i]+" NO";
            }
        }
        return balancedValues;
    }

    private static boolean isBalanced(String value) {

        if(null == value || value.length() == 0) return Boolean.TRUE;
        if(value.length() == 1) return Boolean.FALSE;

        Boolean balanced = Boolean.FALSE;
        Stack<Character> braceStack = new Stack<Character>();
        for(final char brace: value.trim().toCharArray()){
            if(braceStack.isEmpty() && (brace == '}' || brace == ']' || brace == ')')){
                return false;
            }
            if(brace == '{' || brace == '[' || brace == '(') {
                // opening brace push
                braceStack.push(brace);
                continue;
            }
            // closing brace pop
            final Character topBrace = braceStack.pop();
            if (topBrace != null &&
                    ((brace == '}' && topBrace == '{')
                            || (brace == ']' && topBrace == '[')
                            || (brace == ')' && topBrace == '('))) {
                balanced = Boolean.TRUE;
            }
            if(!balanced) break;
        }
        return balanced && braceStack.isEmpty();
    }
}
