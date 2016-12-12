package net.geekscore.algo.dynamic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * https://people.cs.clemson.edu/~bcdean/dp_practice/
 *
 * Created by ravirajmulasa on 9/12/16.
 *
 * You are given n types of coin denominations of values v(1) < v(2) < ... < v(n) (all integers).
 * Assume v(1) = 1, so you can always make change for any amount of money C.
 *
 * Give an algorithm which makes change for an amount of money C with as few coins as possible.
 */
public class MakingChange {

    public static int makeChange(final long amount, final List<Integer> denominations, final int denominationSelected) {
        if(amount < 0L) {
            return 0;
        }
        if(amount == 0L) {
            return 1;
        }
        if(denominationSelected < 0 && amount > 0L){
            return 0;
        }
        return makeChange(amount - denominations.get(denominationSelected), denominations, denominationSelected)
                + makeChange(amount, denominations, denominationSelected - 1);
    }

    public static void main(String[] args) {
        final List<Integer> denomiations = Arrays.asList(new Integer[]{1, 2, 3});
        System.out.println(makeChange(5, denomiations, denomiations.size() - 1));
    }
}
