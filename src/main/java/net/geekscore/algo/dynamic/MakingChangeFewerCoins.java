package net.geekscore.algo.dynamic;

import java.util.Arrays;
import java.util.List;

/**
 *
 * https://people.cs.clemson.edu/~bcdean/dp_practice/
 *
 * Created by ravirajmulasa on 9/12/16.
 *
 * You are given n types of coin denominations of values v(1) < v(2) < ... < v(n) (all integers).
 * Give an algorithm which makes change for an amount of money C with as few coins as possible.
 *
 * M(C) - Min # of coins required to make change for amount - a.
 * M(C) = Min over all denominations(i) { M(C - V[i]) } + 1
 *
 * O(nC)
 *
 */
public class MakingChangeFewerCoins {



    /**
     * Dynamic Programing - 0(nC)
     */
    public static int makeChangeWithMinCoins(final Long amount, final List<Integer> denominations) {
        if(amount <= 0L) {
            return 0;
        }
        Integer minCoins = Integer.MAX_VALUE;
        for (int i = 0; i < denominations.size(); i++) {
            final Integer denominationValue  = denominations.get(i);
            if(denominationValue <= amount){
                final Integer coinsWithThisDenomination = makeChangeWithMinCoins(amount - denominations.get(i), denominations);
                // With out this check, (coinsWithThisDenomination + 1) will wrap around
                // when Integer.MAX_VALUE is returned and hence, results in -ve value.
                if(coinsWithThisDenomination != Integer.MAX_VALUE) {
                    minCoins = Math.min(minCoins, coinsWithThisDenomination + 1);
                }
            }
        }
        return minCoins;
    }

    /**
     * Dynamic Programing - 0(nC)
     */
    public static int makeChangeWithMinCoinsBottomUp(final Long amount, final List<Integer> denominations) {
        if(amount <= 0L) {
            return 0;
        }

        Integer minCoins = Integer.MAX_VALUE;
        for (int i = 0; i < denominations.size(); i++) {
            if(denominations.get(i) <= amount){
                final Integer coinsWithThisDenomination = makeChangeWithMinCoins(amount - denominations.get(i), denominations);
                // With out this check, if coinsWithThisDenomination + 1 will wrap around and result in -ve value.
                if(coinsWithThisDenomination != Integer.MAX_VALUE) {
                    minCoins = Math.min(minCoins, coinsWithThisDenomination + 1);
                }
            }
        }
        return minCoins;
    }

    public static void main(String[] args) {
        final List<Integer> denomiations = Arrays.asList(new Integer[]{1, 2, 3});
        final List<Integer> denomiations1 = Arrays.asList(new Integer[]{1, 2, 3, 6});
        final List<Integer> denomiations2 = Arrays.asList(new Integer[]{1, 5, 6, 11});
        final List<Integer> denomiations3 = Arrays.asList(new Integer[]{2, 3, 4});
        System.out.println(makeChangeWithMinCoins(5L, denomiations));
        System.out.println(makeChangeWithMinCoins(10l, denomiations1));
        System.out.println(makeChangeWithMinCoins(11L, denomiations2));
        System.out.println(makeChangeWithMinCoins(6l, denomiations3));
    }
}
