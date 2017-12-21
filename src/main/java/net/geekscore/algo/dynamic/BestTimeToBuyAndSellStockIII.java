package net.geekscore.algo.dynamic;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most '2' transactions.
 * Note:
 *  You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * max_profit[j] = max {
 *      max_profit[j-1]  // No transaction on day j
 *      ,price[j] - price[m] + max_profit[m] // Second transaction extending the max_profit from some day m < j
 *  }
 **/

public class BestTimeToBuyAndSellStockIII {

    public static void main(String[] args) {

    }

    private static final int maxProfit(int[] prices) {
        if(prices == null || prices.length ==0) {
            return 0;
        }
        int[] profit = new int[prices.length+1];
        profit[0] = 0;
        for(int i=1; i <= prices.length; i++) {
            profit[i] = Math.max(profit[i-1], prices[i] - prices[i-1] + profit[i-1]);
        }
        return profit[prices.length];
    }
}