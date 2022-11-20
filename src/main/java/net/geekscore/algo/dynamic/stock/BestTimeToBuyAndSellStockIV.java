package net.geekscore.algo.dynamic.stock;


/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most 'K' transactions.
 * Note:
 *  You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 *
 *  ith transaction,
 *  jth day
 *
 *  max_profit[i][j] = max {
 *      max_profit[i][j-1]  // No transaction on day j
 *      ,profit[j] - price[m] + max_profit[i-1][m] // transaction on day j extending the max_profit from some day m < j
 *  }
 **/

public class BestTimeToBuyAndSellStockIV {

    public static void main(String[] args) {

    }
    private static final int maxProfilt(int[] prices, int k) {

        final int[][] profit  = new int[k+1][prices.length];
        for (int i = 1; i < k+1; i++) {
            for (int j = 1; j < prices.length; j++) {
                int maxValue = 0;
                for (int m = 0; m < j; m++) {
                    maxValue = Math.max(maxValue, prices[j] - prices[m] + profit[i-1][m]);
                }
                profit[i][j] = Math.max(maxValue, profit[i][j-1]);
            }
            
        }
        return profit[k][prices.length - 1];
    }
}
