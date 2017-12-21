package net.geekscore.algo.dynamic;

/***
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction
 * (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 #
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {

    }
    private static final int maxProfit(int[] prices){
        int maxProfit = 0;
        if(null == prices || prices.length == 0) {
            return maxProfit;
        }
        int cheapest = prices[0];
        for (int i = 1; i < prices.length; i++) {
            cheapest = Math.min(cheapest, prices[i]); // when is the best day to buy
            maxProfit= Math.max(maxProfit, prices[i] - cheapest); // when is the best day to sell to maximize profit
        }
        return maxProfit;
    }
}
