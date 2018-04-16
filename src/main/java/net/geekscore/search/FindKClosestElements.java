package net.geekscore.search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 *
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 104
 * Absolute value of elements in the array and x will not exceed 104
 */
public class FindKClosestElements {

    public static void main(String[] args) {
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3)); // [1,2,3,4]
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 2, 3)); // [2,3]
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1)); // [1,2,3,4]
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 1, -1)); // [1]
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 1, 4)); // [4]
        System.out.println(findClosestElements(new int[]{0, 0, 1, 2, 3, 3, 4, 7, 7, 8}, 3, 5)); // [3,3,4]
        System.out.println(findClosestElements(new int[]{0, 1, 1, 1, 2, 3, 6, 7, 8, 9}, 9, 4)); // [0,1,1,1,2,3,6,7,8]
        System.out.println(findClosestElements(new int[]{0,0,0,1,3,5,6,7,8,8}, 2, 2)); // [1,3]

    }

    private static List<Integer> findClosestElements(int[] arr, int k, int x) {
        if(arr == null || arr.length == 0) return Collections.emptyList();
        int low = 0;
        int high = arr.length-1;
        while (low <= high) {
            final int mid = low+(high-low)/2;
            if(arr[mid] < x){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        final List<Integer> result = new LinkedList<>();
        int i = low < 0 ? 0 : arr[low] == x ? low : low-1;
        int j = i+1;
        while (k > 0) {
            int diffLow = i > -1 ? Math.abs(x - arr[i]) : Integer.MAX_VALUE;
            int diffHig = j < arr.length ? Math.abs(x - arr[j]) : Integer.MAX_VALUE;
            if(diffLow <= diffHig) result.add(arr[i--]);
            else result.add(arr[j++]);
            k--;
        }
        Collections.sort(result);
        return result;
    }
}
