package net.geekscore.array;

import java.util.*;

public class Intersection2SortedArrays {

    public static void main(String[] args) {

        System.out.println(
                Arrays.toString(
                        intersection(
                                new int[]{1, 3, 4, 5, 7}
                                , new int[]{2, 3, 5, 6}
                        )
                )
        ); // [3, 5]

    }

    private static final Integer[] intersection(final int nums1[], final int nums2[]) {

        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new Integer[0];
        List<Integer> intersection = new LinkedList<>();
        int i = 0, j = i;
        while (i < nums1.length && j < nums2.length) {
            if(nums1[i] < nums2[j]) i++;
            else if(nums2[j] < nums1[i]) j++;
            else  {
                intersection.add(nums1[i]);
                i++;
                j++;
            }
        }
        return intersection.toArray(new Integer[intersection.size()]);
    }


}
