package net.geekscore.problems;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by ravirajmulasa on 8/12/17.
 */
public class BalancedBraces {

    public static void main(String[] args) {
        String values[] ={
            "{}[]()", "{[}]}" ,"({[]})", "{[}]", "[{)]", "{", "{}", "[{}()]", "]{}[", "{(["
        };
        Arrays.stream(braces(values)).forEach(System.out::println);
    }

    public static final String[] braces(String [] values) {
        if(null == values || values.length == 0){
            return new String[0];
        }
        final String[] balancedValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            if(braceBalanced(values[i])) {
                balancedValues[i] = values[i]+" YES";
            } else {
                balancedValues[i] = values[i]+" NO";
            }
        }
        return balancedValues;
    }

    public static final boolean braceBalanced(String value) {
        if(null == value || value.length() == 0){
            return Boolean.TRUE;
        }
        if(null != value && value.length() == 1){
            return Boolean.FALSE;
        }
        Boolean balanced = Boolean.TRUE;
        Stack<Character> braceStack = new Stack<Character>();
        for(final char brace: value.trim().toCharArray()){
            if(braceStack.isEmpty() && (brace == '}' || brace == ']' || brace == ')')){
                // First character is a closing brace
                balanced &= Boolean.FALSE;
                break;
            }
            if(brace == '{' || brace == '[' || brace == '('){
                // opening brace push
                braceStack.push(brace);
                continue;
            }
            // closing brace pop
            Character topBrace = braceStack.pop();
            if (topBrace != null &&
                    ((brace == '}' && topBrace == '{')
                            || (brace == ']' && topBrace == '[')
                            || (brace == ')' && topBrace == '('))) {
                balanced &= Boolean.TRUE;
            }else {
                balanced &= Boolean.FALSE;
            }
            if(!balanced)
                break;
        }
        return balanced && braceStack.isEmpty();
    }
}
