package net.geekscore.backtrack;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    private MakeValidEquation() {}

    private static final Character[] OPERATORS = new Character[6];

    private static final List<Character> OPERATORS_ALLOWED = new LinkedList<>();

    public static void main(String[] args) {
        final int[] numbers = {1, 2, 3, 4, 5, 10};
        result(numbers, 1, numbers[0]);
    }

    private static void result(final int[] numbers, int k, int resultSoFar) {

        int temp = resultSoFar;

        if(k == numbers.length - 1) {
            if (resultSoFar == numbers[numbers.length - 1]) {
                System.out.println(Arrays.toString(OPERATORS));
            } else {
                return;
            }
        }else  {
            //        try +  operator
            resultSoFar += numbers[k];
            OPERATORS[k]= '+';
            result(numbers, k+1, resultSoFar);

//        try - operator
            temp -= numbers[k];
            OPERATORS[k]= '-';
            result(numbers, k+1, temp);
        }


    }
}
