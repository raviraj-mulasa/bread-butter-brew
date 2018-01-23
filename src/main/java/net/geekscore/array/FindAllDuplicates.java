package net.geekscore.array;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others
 * appear once.
 *
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 *
 * Example:
 * Input: [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 */
public class FindAllDuplicates {

    public static void main(String[] args) {
        System.out.println(duplicates(new int[]{4,3,2,7,8,2,3,1})); //  [2, 3]
        System.out.println(duplicates(new int[]{4,3,2,7,8,2,3,7})); // [2, 3, 7]
        System.out.println(duplicates(new int[]{1})); // []
        System.out.println(duplicates(new int[0])); // []
        System.out.println(duplicates(new int[]{1,1})); // [1]
        System.out.println(duplicates(new int[]{2,1})); // []
    }
    
    private static List<Integer> duplicates(final int[] nums) {
        if(nums == null || nums.length <= 1) return Collections.emptyList();
        final int n = nums.length;
        for (int i = 0; i < n; i++) nums[(nums[i]-1) % n] += n;
        final List<Integer> duplicates = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(nums[i] > 2*n) duplicates.add(i+1);
        }
        return duplicates;
    }
}
