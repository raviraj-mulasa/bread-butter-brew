package net.geekscore.algo.backtrack;


import java.util.*;

/**
 *
 * http://algosolver.blogspot.com/2011/09/backtracking-and-game-solvers.html
 *
 * Given n numbers: x1, x2, x3, .... xn
 * Find in how many ways we can use + and - to satisfy
 * x1 +/- x2 +/- x3 +/- ..... +/- xn-1 = xn
 *
 * eg: 1 _ 2 _ 3 _ 4 _ 5 = 10
 *
 * Created by ravirajmulasa on 9/16/16.
 */
public final class MakeValidEquation {



    public static void main(String[] args) {

        System.out.println(validEquations(new int[] {1, 2, 3, 4, 5, 10}, new char[]{'+', '-'})); // []
        System.out.println(validEquations(new int[] {1, 2, 3, 4, 5, 15}, new char[]{'+', '-'})); // [[+, +, +, +]]
        System.out.println(validEquations(new int[] {1, 2, 3, 4, 5, 5}, new char[]{'+', '-'})); // [[+, +, +, -], [-, -, +, +]]
        System.out.println(validEquations(new int[] {1, 2, 3, -4, -5, 7}, new char[]{'+', '-'})); // [[+, +, +, -]]

        System.out.println("---------------------");

        System.out.println(validEquations(new int[] {1, 2, 3, 4, 5, 120}, new char[]{'*', '/'})); // [[*, *, *, *]]
        System.out.println(validEquations(new int[] {1, 2, 3, 4, 5, 15}, new char[]{'*', '/'})); // []
        System.out.println(validEquations(new int[] {1, 2, 3, 4, 5, 5}, new char[]{'*', '/'})); // [[*, *, /, *]]
        System.out.println(validEquations(new int[] {1, 2, 3, -4, -5, 7}, new char[]{'*', '/'})); // []

    }

    private static List<List<Character>> validEquations(final int[] numbers, char[] operators) {
        if(numbers == null || numbers.length == 0) return Collections.emptyList();
        List<List<Character>> validEquations = new LinkedList<>();
        validEquationHelper(validEquations, new ArrayList<>(numbers.length-1), numbers, 1, numbers[0], operators);
        return validEquations;
    }

    private static void validEquationHelper(List<List<Character>> validEquations, final List<Character> validEquationSoFar, final int[] numbers, int k, int resultSoFar, char[] operators) {
//        System.out.println("validEquationHelper("+validEquations+","+validEquationSoFar+","+Arrays.toString(numbers)+","+k+","+resultSoFar+","+Arrays.toString(operators)+")");
        if(k == numbers.length - 1 && resultSoFar == numbers[k]) {
                validEquations.add(new ArrayList<>(validEquationSoFar));
                return;
        }
        if(k >= numbers.length) return;

        int temp = resultSoFar;

        for (char operator : operators) {
            // choose the operator
            validEquationSoFar.add(operator);
            // apply the operation
            switch (operator) {
                case '+':
                    resultSoFar += numbers[k];
                    break;
                case '-':
                    resultSoFar -= numbers[k];
                    break;
                case '*':
                    resultSoFar *= numbers[k];
                    break;
                case '/':
                    resultSoFar /= numbers[k];
                    break;
            }
            // Explore
            validEquationHelper(validEquations, validEquationSoFar, numbers, k + 1, resultSoFar, operators);
            // Un choose the operator
            validEquationSoFar.remove(validEquationSoFar.size() - 1);
            // revert the operation
            resultSoFar = temp;
        }
    }
}
