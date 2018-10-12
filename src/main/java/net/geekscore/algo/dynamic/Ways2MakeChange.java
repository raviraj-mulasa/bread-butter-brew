package net.geekscore.algo.dynamic;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravirajmulasa on 7/16/17.
 *
 * You are given n types of coin denominations of values v(1) < v(2) < ... < v(n) (all integers).
 * We have infinite supply of coin denominations.
 * Assume v(1) = 1, so you can always make change for any price of money C.
 */
public class Ways2MakeChange {

    public static void main(String[] args) {
        final List<Integer> denominations = Arrays.asList(new Integer[]{1, 2, 3});
        System.out.println(makeChange(5, denominations, denominations.size() - 1));
        System.out.println(makeChangeDP(5, denominations));

        System.out.println("--------------------------");

        final List<Integer> denominations1 = Arrays.asList(new Integer[]{2, 3, 6});
        System.out.println(makeChange(10, denominations1, denominations1.size() - 1));
        System.out.println(makeChangeDP(10, denominations1));
    }

    /**
     * Overlapping Subproblems
     */
    private static int makeChange(final long amount, final List<Integer> denominations, final int denominationSelected) {
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
    private static int makeChangeDP(final Integer amount, final List<Integer> denominations) {

        final Integer[][] ways2MakeChange = new Integer[denominations.size() + 1][amount + 1];

        for (int j = 0; j < denominations.size(); j++) {
            ways2MakeChange[j] = new Integer[amount + 1];
            Arrays.fill(ways2MakeChange[j], 0);
            // Zero price, there is 1 way to make change
            ways2MakeChange[j][0] = 1;
        }

        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j < denominations.size(); j++) {
                Integer ways2MakeChangeUsingDenomination = 0;
                if(denominations.get(j) <= i) {
                    ways2MakeChangeUsingDenomination =  ways2MakeChange[j][i - denominations.get(j)]; // Select the denomination
                }
                Integer ways2MakeChangeNotUsingDenomination = ways2MakeChange[j - 1][i]; // Don't select the denomination
                ways2MakeChange[j][i] = ways2MakeChangeUsingDenomination + ways2MakeChangeNotUsingDenomination;
            }
        }
        return ways2MakeChange[denominations.size() - 1][amount];
    }


}
