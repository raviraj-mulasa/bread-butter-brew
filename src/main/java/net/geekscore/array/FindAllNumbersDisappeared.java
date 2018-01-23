package net.geekscore.array;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 * Example:
 * Input: [4,3,2,7,8,2,3,1]  Output: [5,6]
 */
public class FindAllNumbersDisappeared {

    public static void main(String[] args) {
        System.out.println(numbersDisappeared(new int[]{4,3,2,7,8,2,3,1})); //  [5, 6]
        System.out.println(numbersDisappeared(new int[]{4,3,2,7,8,2,3,7})); // [1, 5, 6]
        System.out.println(numbersDisappeared(new int[]{1})); // []
        System.out.println(numbersDisappeared(new int[0])); // []
        System.out.println(numbersDisappeared(new int[]{1,1})); // [2]
    }

    private static List<Integer> numbersDisappeared(final int[] nums) {
        final int n = nums.length;
        for (int i = 0; i < n; i++) nums[(nums[i]-1) % n] += n;
        final List<Integer> disappeared = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(nums[i] <= n) disappeared.add(i+1);
        }
        return disappeared;
    }

}
