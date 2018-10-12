package net.geekscore.algo.dynamic;


/**
 *
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */
public class MaxProductSubarray {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4}));
        System.out.println(maxProduct(new int[]{-2,3,0,2,4}));
    }

    private static long maxProduct(int[] nums) {
        if(nums == null || 0 == nums.length) {
            return 0;
        }

        int min = nums[0], max = nums[0], maxProduct = nums[0];
        for(int i = 1; i < nums.length; i++) {
            final int temp = max;
            max = Math.max(Math.max(min * nums[i], max * nums[i]), nums[i]);
            min = Math.min(Math.min(min * nums[i], temp * nums[i]), nums[i]);
            maxProduct = Math.max(max, maxProduct);
        }
        return maxProduct;
    }
}

