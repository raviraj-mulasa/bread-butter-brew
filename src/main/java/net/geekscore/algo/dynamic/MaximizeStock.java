package net.geekscore.algo.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ravirajmulasa on 8/15/17.
 *
 *
 * Your algorithms have become so good at predicting the market that you now know what the share price of
 * Wooden Orange Toothpicks Inc. (WOT) will be for the next N days.
 * Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own,
 * or not make any transaction at all. What is the maximum profit you can obtain with an optimum trading strategy?
 *
 * Input Format
 * The first line contains the number of test cases T. T test cases follow:
 *  The first line of each test case contains a number N. The next line contains N integers,
 *  denoting the predicted price of WOT shares for the next N days.
 *
 *  Sample Input
 *  3
 *  3
 *  5 3 2
 *  3
 *  1 2 100
 *  4
 *  1 3 1 2
 *  Sample Output
 *  0
 *  197
 *  3
 *
 *  Explanation
 *  For the first case, you cannot obtain any profit because the share price never rises.
 *  For the second case, you can buy one share on the first two days, and sell both of them on the third day.
 *  For the third case, you can buy one share on day 1, sell one on day 2, buy one share on day 3, and sell one share on day 4.
 *
 **/


public class MaximizeStock {

    public static void main(String[] args) throws IOException {
        final String[] testCases = readFromStdIn();
        for (int i = 0; i < testCases.length; i+=2) {
            final Integer days      = Integer.valueOf(testCases[i]);
            final Integer[] prices  = new Integer[days];
            int k = 0;
            for (final String price : testCases[i+1].split(" ", days)) {
                prices[k++] = Integer.valueOf(price);
            }
            System.out.println(maxProfit(prices));
        }
    }

    private static int maxProfit(final Integer[] prices) {
        return maxProfitHelper(prices, prices.length - 1, 0);
    }

    private static int maxProfitHelper(final Integer[] prices, final int today, int shareCount) {
        int maxProfit = 0;
        if(today == 0){
            return maxProfit;
        }
        final int yesterday = today - 1;
        final int profit    = prices[today] - prices[yesterday];
        if(profit > 0) {
            maxProfit = maxProfit + (shareCount * profit);
//            maxProfit = Math.max(maxProfit, maxProfitHelper(prices, today - 1, shareCount));
        }
        if(profit < 0 && today < prices.length - 1) {
            maxProfit  =  maxProfit + profit; // Buy a share.
            shareCount += 1;
        }
        maxProfit = Math.max(maxProfit, maxProfitHelper(prices, today - 1, shareCount));
        return maxProfit;

    }

    private static final String[] readFromStdIn() throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final int noOfTestCases = Integer.valueOf(in.readLine());
        final int noOfLinesPerTestCase = 2;
        return in.lines().limit(noOfTestCases * noOfLinesPerTestCase).toArray(String[]::new);
    }
}
