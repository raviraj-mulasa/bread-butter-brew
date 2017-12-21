package net.geekscore.algo.dynamic;

import java.util.Arrays;

/**
 *There are n coins in a line. Two players take turns to take one or two coins from right side until
 * there are no more coins left. The player who take the last coin wins.
 *
 * Could you please decide the first player will win or lose?\
 *
 * n = 1, return true.
 * n = 2, return true.
 * n = 3, return false.
 * n = 4, return true.
 * n = 5, return true.
 *
 **/
public class CoinsInALine {

    public static void main(String[] args) {

        System.out.println(firstWillWin(3)); // False
        System.out.println(firstWillWin(4));
        System.out.println(firstWillWin(20));
        System.out.println(firstWillWin(21)); // False

        System.out.println(firstWillWinII(3)); // False
        System.out.println(firstWillWinII(4));
        System.out.println(firstWillWinII(20));
        System.out.println(firstWillWinII(21)); // False

    }
    private static final boolean firstWillWin(int n) {

        if(n <= 1) {
            return false;
        }
        if(n <= 2) {
            return true;
        }
        final boolean[] firstWins = new boolean[n+1];
        Arrays.fill(firstWins, Boolean.TRUE);
        firstWins[3] = Boolean.FALSE;
        for (int i = 4; i <= n; i++) {
            firstWins[i] = firstWins[i - 3];
        }
       return firstWins[n];
    }

    private static final boolean firstWillWinII(int n) {
        if(n <= 1) {
            return false;
        }
        if(n <= 2) {
            return true;
        }
        return n % 3 != 0;
    }
}
