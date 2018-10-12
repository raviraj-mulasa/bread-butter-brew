package net.geekscore.array;

import java.util.Arrays;

/**
 *
 * Given an array arr[] of integers, find out the difference between any two elements such that
 * larger element appears after the smaller number in arr[].
 *
 * Examples:
 * If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be 8 (Diff between 10 and 2).
 * If array is [ 7, 9, 5, 6, 3, 2 ] then returned value should be 2 (Diff between 7 and 9).
 *
 * Created by ravirajmulasa on 8/12/17.
 *
 */
public class MaxDifferenceInArray {

    public static void main(String[] args) {
        int values[] ={2,3,10,2,4,8,1}; // 8 a[2] - a[0]
        int values1[]={7,9,5,6,3,2}; // 2 a[1] - a[0]
        int values2[]={7}; // -1
        int values3[]={7,9}; // 2 a[1] - a[0]
        int values4[]={7, 2}; // -1
        int values5[]={7,9,5,1,9,2}; // 8 a[4] - a[3]
        int values6[]={9,9,9,9,9,2}; // 8 a[4] - a[3]
        int values7[]={0,-1}; // 8 a[4] - a[3]
        int values8[]={9,8,7,6,5,4}; // 8 a[4] - a[3]
        int values9[]={4,5,6,7,8,9}; // 8 a[4] - a[3]
        int values10[]={}; // -1
        int values11[]={9,1,1,9,9,1}; // -1
        int values12[]={1,1,1,9,9,9}; // 8 a[5] - a[0]
        int values13[]={0,0}; // -1

        System.out.println(maxDifference(values));
        System.out.println(maxDifference(values1));
        System.out.println(maxDifference(values2));
        System.out.println(maxDifference(values3));
        System.out.println(maxDifference(values4));
        System.out.println(maxDifference(values5));
        System.out.println(maxDifference(values6));
        System.out.println(maxDifference(values7));
        System.out.println(maxDifference(values8));
        System.out.println(maxDifference(values9));
        System.out.println(maxDifference(values10));
        System.out.println(maxDifference(values11));
        System.out.println(maxDifference(values12));
        System.out.println(maxDifference(values13));
        System.out.println("---------------------");
        System.out.println(maxDifferenceEfficient(values));
        System.out.println(maxDifferenceEfficient(values1));
        System.out.println(maxDifferenceEfficient(values2));
        System.out.println(maxDifferenceEfficient(values3));
        System.out.println(maxDifferenceEfficient(values4));
        System.out.println(maxDifferenceEfficient(values5));
        System.out.println(maxDifferenceEfficient(values6));
        System.out.println(maxDifferenceEfficient(values7));
        System.out.println(maxDifferenceEfficient(values8));
        System.out.println(maxDifferenceEfficient(values9));
        System.out.println(maxDifferenceEfficient(values10));
        System.out.println(maxDifferenceEfficient(values11));
        System.out.println(maxDifferenceEfficient(values12));
        System.out.println(maxDifferenceEfficient(values13));
    }


    private static int maxDifference(int[] a) {
        final int maxValueIdx = maxValueIndex(a);
        if(maxValueIdx <= 0){
            return -1;
        }
        final int minValueIdx = minValueIndex(Arrays.copyOfRange(a, 0, maxValueIdx));
        return   a[maxValueIdx] - a[minValueIdx];
    }
    private static final int maxValueIndex(int[] a) {
       if(null == a || a.length == 0){
           return -1;
       }
        if(a.length == 1){
            return 0;
        }
       int maxValueIdx = 0;
       int maxValue = a[0];
        for (int i = 1; i < a.length; i++) {
            if(maxValue <= a[i]){
                maxValue = a[i];
                maxValueIdx = i;
            }

        }
        return maxValueIdx;
    }
    private static final int minValueIndex(int[] a) {
        if(null == a || a.length == 0){
            return -1;
        }
        if(a.length == 1){
            return 0;
        }
        int minValueIdx = 0;
        int minValue = a[0];
        for (int i = 1; i < a.length; i++) {
            if(minValue > a[i]){
                minValue = a[i];
                minValueIdx = i;
            }
        }
        return minValueIdx;
    }



    private static final int maxDifferenceEfficient(int[] a) {
        if(null == a || a.length == 0){
            return -1;
        }
        if(a.length == 1){
            return -1;
        }
        int minValue = a[0];
        int maxDiff = a[1] - minValue;
        for (int i = 1; i < a.length; i++) {
            if(a[i] - minValue > maxDiff){
                maxDiff = a[i] - minValue;
            }
            if(a[i] < minValue){
                minValue = a[i];
            }
        }
        return maxDiff < 0 ? -1 : maxDiff;
    }
}
