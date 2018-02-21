package net.geekscore.algo.divideNconquer;

import java.util.Arrays;

public class Median2SortedArrays {

    public static void main(String[] args) {
        System.out.println(median(new int[]{2,3,5,7,9,10,11})); // 7.0
        System.out.println(median(new int[]{1,3,7,10,13,16,17})); // 10.0
        System.out.println(median(new int[]{1,2,3,3,5,7,7,9,10,10,11,13,16,17})); // 8.0
        System.out.println(median(new int[]{1,2,3,4,5,6,7,8,9,10,10,11,13})); // 7.0

        System.out.println("------------------------");

        System.out.println(median(new int[]{1,2}, new int[]{3})); // 2.0
        System.out.println(median(new int[]{1,2}, new int[]{3,4})); // 2.5
        System.out.println(median(new int[]{2,3,5,7,9,10,11}, new int[]{1,3,7,10,13,16,17})); // 8.0
        System.out.println(median(new int[]{1,3,7,10,13,16,17}, new int[]{2,3,5,7,9,10,11})); // 8.0
        System.out.println(median(new int[]{1,2,7,8}, new int[]{3, 4, 5, 6, 9, 10, 11, 12, 13})); // 7.0
        System.out.println(median(new int[]{3, 4, 5, 6, 9, 10, 11, 12, 13}, new int[]{1,2,7,8})); // 7.0


    }

    public static final double median(final int[] a1, final int[] a2) {
        if(a1 == null && a2 != null) return median(a2);
        if(a2 == null && a1 != null) return median(a1);
        if(a1 == a2) return median(a1);
        int n = a1.length + a2.length;
        if(n % 2 ==0){
            return  (findKth(n/2+1, a1, 0, a2, 0) + findKth(n/2, a1, 0, a2, 0))/2.0;
        }
        return findKth(n/2+1, a1, 0, a2, 0)*1.0;
    }

    private static final double median(int[] nums) {
        if(nums == null || nums.length == 0) throw new IllegalArgumentException("Invalid Input!");
        int mid = nums.length/2 ;
        return nums.length % 2 == 0 ? (nums[mid] + nums[mid-1])/2.0 : nums[mid]*1.0;
    }

    private static int findKth(int k, int[] a1, int beginA, int[] a2, int beginB) {
        System.out.println( "findKth("+k+","+ Arrays.toString(a1)+","+beginA+","+Arrays.toString(a2)+","+beginB+")");
        if(beginA >= a1.length) return a2[beginB+k-1];
        if(beginB >= a2.length) return a1[beginA+k-1];
        if(k==1) return Math.min(a1[beginA], a2[beginB]);

        int m1IdX = beginA+k/2-1;
        int m2IdX = beginB+k/2-1;
        int m1 = m1IdX<a1.length ? a1[m1IdX] : Integer.MAX_VALUE;
        int m2 = m2IdX<a2.length ? a2[m2IdX] : Integer.MAX_VALUE;

        if(m1 < m2) return findKth(k-k/2, a1, m1IdX+1, a2, beginB);
        else return findKth(k-k/2, a1, beginA, a2, m2IdX+1);

    }



}
