package net.geekscore.bit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Single Number
 *
 *  There is an array of non-negative integers. A second array is formed by
 *  shuffling the elements of the first array and deleting a random element.
 *
 *  Given these two arrays,find which element is missing in the second array.
 *
 * Input:
 *  Array 1: [4,1,0,2,9,6,8,7,5,3]
 *  Array 2: [6,4,7,2,1,0,8,3,9]
 *
 *  Output:
 *  5
 */
public class MissingElement {

    public static void main(String[] args) {
        System.out.println(missingElement(new Integer[]{4,1,0,2,9,6,8,7,5,3},  new Integer[]{6,4,7,2,1,0,8,3,9}));
    }

    private static int missingElement(final Integer[] nums1, final Integer[] nums2) {

        final List<Integer> numsList = new ArrayList<>(nums1.length + nums2.length);
        Collections.addAll(numsList, nums1);
        Collections.addAll(numsList, nums2);

        final Integer[] nums = numsList.toArray(new Integer[numsList.size()]);
        int missingElement = 0;
        for (final Integer num: nums) missingElement ^= num;
        return missingElement;
    }

}
