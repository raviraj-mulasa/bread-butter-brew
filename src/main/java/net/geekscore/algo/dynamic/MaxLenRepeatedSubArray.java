package net.geekscore.algo.dynamic;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears
 * in both arrays.
 *
 * Example 1:
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 *
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3, 2, 1].
 */
public class MaxLenRepeatedSubArray {

    public static void main(String[] args) {
        System.out.println(maxLenRepeatedSubArray(
                    new int[]{1,2,3,2,1}
                    ,new int[]{3,2,1,4,7}
                )
        );
        System.out.println("---------------");
        System.out.println(maxLenRepeatedSubArray(
                new int[]{1,2,3,2,1}
                ,new int[]{3,2,1,4,7}
                )
        );
    }

    private static int maxLenRepeatedSubArray(int[] a, int[] b) {
        int max = 0;
        if(null == a || a.length == 0 && null == b || b.length == 0) return max;
        final int min = Math.min(a.length, b.length);
        final int[][] len = new int[min+1][min+1];
        for (int i = 1; i <= min; i++) {
            for (int j = 1; j <= min; j++) {
                if(a[i-1] == b[j-1]) {
                    System.out.println(a[i-1]+" == "+b[j-1]);
                    len[i][j] = 1 + len[i-1][j-1];
                    max = Math.max(max, len[i][j]);
                }
            }
        }
        return max;
    }


    private static int maxLenRepeatedSubArrayEfficient(int[] a, int[] b) {
        int max = 0;
//        if(null == a || a.length == 0 && null == b || b.length == 0) return max;
//        final int min = Math.min(a.length, b.length);
//        final int[] len = new int[min+1];
//        for (int i = 1; i <= a.length; i++) {
//            for (int j = 1; j <= b.length; j++) {
//                if(a[i-1] == b[j-1]) {
//                    System.out.println(a[i-1]+" == "+b[j-1]);
//                    len[i][j] = 1 + len[i-1][j-1];
//                    max = Math.max(max, len[i][j]);
//                }
//            }
//        }
        return max;
    }
}
