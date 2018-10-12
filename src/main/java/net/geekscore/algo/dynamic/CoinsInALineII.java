package net.geekscore.algo.dynamic;

/**
 * Consider a row of n coins of values v1 . . . vn, where n is even.
 * We play a game against an opponent by alternating turns.
 *
 * In each turn, a player selects either the first or last coin from the row,
 * removes it from the row permanently, and receives the value of the coin.
 *
 * Determine the maximum possible amount of money we can definitely win if YOU move first.
 *
 *
 *
 * Case 1 - If you pick coin v[i] :
 * Then the opponent has the choice between v(i+1) and v(j).
 *
 *  If the opponent takes v(i+1), the remaining coins are { v(i+2) … v(j)},
 *  for which our maximum is denoted by A(i+2, j).
 *
 *  On the other hand, if the opponent takes v[j] our maximum is A(i+1, j-1).
 * 
 * Since the opponent is as smart as you, he would have chosen the choice that yields the minimum amount to you.
 * Therefore, the maximum amount you can get when you choose v[i] is:
 *  C1 = v[i] + min { A(i+2, j), A(i+1, j-1) }
 * 
 * Case 2 - If you pick coin v[j] :
 * Similar to case 1, the maximum amount you can get when you choose v[j] is:
 *  C2 = v[j] + min { A(i+1, j-1), A(i, j-2) }
 *  
 *  Therefore
 *  A(i, j)  = max { C1, C2 }= max { v[i] + min { A(i+2, j),   A(i+1, j-1) }
 *                                   ,v[j] + min { A(i+1, j-1), A(i,   j-2) } }
 **/
public class CoinsInALineII {

    public static void main(String[] args) {
        System.out.println(maxAmount(new int[]{3,9,1,2})); // 11
        System.out.println(maxAmount(new int[]{ 6, 9, 1, 2, 16, 8})); // 23
    }

    private static final int maxAmount(final int[] values){

        final int[][] amount = new int[values.length][values.length];
        for (int i = values.length - 1; i >= 0; i--) {
            for (int j = i; j < values.length; j++) {
                if(j == i){ // Base case - one coin
                    amount[i][j] = values[i];
                } else if(j  == i + 1) { // Base case - 2 coins, pick the max
                    amount[i][j] = Math.max(values[i], values[j]);
                } else {
//                case 1: You picked the ith coin
                    final int c1 = values[i] + Math.min(amount[i+2][j], amount[i+1][j-1]);
//                case 2: You picked the jth coin
                    final int c2 = values[j] + Math.min(amount[i+1][j-1], amount[i][j-2]);
                    amount[i][j] = Math.max(c1, c2);
                }
            }
        }
        return amount[0][values.length - 1];
    }
}
