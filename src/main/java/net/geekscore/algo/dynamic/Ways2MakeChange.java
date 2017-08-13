package net.geekscore.algo.dynamic;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravirajmulasa on 7/16/17.
 *
 * You are given n types of coin denominations of values v(1) < v(2) < ... < v(n) (all integers).
 * We have infinite supply of coin denominations.
 * Assume v(1) = 1, so you can always make change for any amount of money C.
 */
public class Ways2MakeChange {

    /**
     * Overlapping Subproblems
     */
    public static int makeChange(final long amount, final List<Integer> denominations, final int denominationSelected) {
        if(amount < 0L) {
            return 0;
        }
        if(denominationSelected < 0 && amount > 0L){
            return 0;
        }
        if(amount == 0L) {
            return 1;
        }
        return makeChange(amount - denominations.get(denominationSelected), denominations, denominationSelected) // Denomination Selected
                + makeChange(amount, denominations, denominationSelected - 1); // Denomination NOT Selected
    }

    /**
     * Dynamic Programming
     */
    public static int makeChangeDP(final Integer amount, final List<Integer> denominations) {

        final Integer[][] ways2MakeChange = new Integer[denominations.size()][amount + 1];

        for (int j = 0; j < denominations.size(); j++) {
            ways2MakeChange[j] = new Integer[amount + 1];
            Arrays.fill(ways2MakeChange[j], 0);
            // Zero amount, there is 1 way to make change
            ways2MakeChange[j][0] = 1;
        }

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < denominations.size(); j++) {
                Integer ways2MakeChangeUsingDenomination = 0;
                if(denominations.get(j) <= i) {
                    ways2MakeChangeUsingDenomination =  ways2MakeChange[j][i - denominations.get(j)]; // Select the denomination
                }
                Integer ways2MakeChangeNotUsingDenomination = ways2MakeChange[j - 1][i]; // Don't select the denomination
                ways2MakeChange[j][i] = ways2MakeChangeUsingDenomination + ways2MakeChangeNotUsingDenomination;
            }
        }
        return ways2MakeChange[denominations.size()][amount];
    }

    public static void main(String[] args) {
        final List<Integer> denomiations = Arrays.asList(new Integer[]{1, 2, 3});
//        System.out.println(makeChange(5, denomiations, denomiations.size() - 1));
        final List<Integer> denomiations1 = Arrays.asList(new Integer[]{2, 3, 6});
//        System.out.println(makeChange(10, denomiations1, denomiations.size() - 1));
        System.out.println(makeChangeDP(5, denomiations));
        System.out.println(makeChangeDP(10, denomiations1));
    }
}
