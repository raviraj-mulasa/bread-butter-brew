package net.geekscore.algo.dynamic;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 * Input: 2
 * Output:  2
 * Explanation:  There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 * Input: 3
 * Output:  3
 * Explanation:  There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Example 2:
 * Input: 4
 * Output: 5 = steps[3] + steps[2]
 * Explanation:  There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps + 1 step
 * 3. 2 steps + 1 step + 1 step
 * 4. 1 step + 1 step + 2 steps
 * 5. 2 steps + 2 steps
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(ways2Climb(5)); // 8
        System.out.println(ways2Climb(3)); // 3
        System.out.println(ways2Climb(4)); // 5
    }

    private static int ways2Climb(final int stepsInStairCase) {
        if(stepsInStairCase <= 2) return stepsInStairCase;
        final int[] ways = new int[stepsInStairCase+1];
        ways[0] = 0;
        ways[1] = 1;
        ways[2] = 2;
        for (int i = 3; i <= stepsInStairCase; i++) {
            ways[i] = ways[i-2] + ways[i-1];
        }
        return ways[stepsInStairCase];
    }
}
