package net.geekscore.array;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class IntersectionOf2Arrays {

    public static void main(String[] args) {

        Instant start = Instant.now();
        System.out.println(
                Arrays.toString(
                        intersectionMap(
                                new int[]{7, 1, 5, 2, 3, 6}
                                , new int[]{3, 8, 6, 20, 7}
                                )
                )
        ); // [3, 6, 7]

        System.out.println(
                Arrays.toString(
                        intersectionMap(
                                new int[]{9,1}
                                , new int[]{7,8,3,9,0,0,9,1,5}
                        )
                )
        ); // [1,9]

        System.out.println(
                Arrays.toString(
                        intersectionMap(
                                new int[]{5,0,0,6,1,6,2,2,4}
                                , new int[]{4,7,9,7,6,7}
                        )
                )
        ); // [4,6]

        System.out.println(
                Arrays.toString(
                        intersectionMap(
                                new int[]{3,1,2}
                                , new int[]{1,1}
                        )
                )
        ); // [1]

        System.out.println(
                Arrays.toString(
                        intersectionMap(
                                new int[]{1, 2, 2, 1}
                                , new int[]{2,2}
                        )
                )
        ); // [2,2]

        System.out.println(
                Arrays.toString(
                        intersectionMap(
                                new int[]{1, 2, 2,  2,1}
                                , new int[]{2,2}
                        )
                )
        ); // [2,2]

        System.out.println(
                Arrays.toString(
                        intersectionMap(
                                new int[]{1, 2, 2 ,1}
                                , new int[]{2, 2, 2}
                        )
                )
        ); // [2,2]

        System.out.println(
                Arrays.toString(
                        intersectionMap(
                                new int[]{1, 2, 2 ,1}
                                , new int[]{1, 1}
                        )
                )
        ); // [1, 1]

        System.out.println(
                Arrays.toString(
                        intersectionMap(
                                new int[]{1, 2, 2 ,1}
                                , new int[]{2, 2, 1}
                        )
                )
        ); // [1,2,2]
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("------------------");

        start = Instant.now();
        System.out.println(
                Arrays.toString(
                        intersectionBinarySearch(
                                new int[]{7, 1, 5, 2, 3, 6}
                                , new int[]{3, 8, 6, 20, 7}
                        )
                )
        ); // [3, 6, 7]

        System.out.println(
                Arrays.toString(
                        intersectionBinarySearch(
                                new int[]{9,1}
                                , new int[]{7,8,3,9,0,0,9,1,5}
                        )
                )
        ); // [1,9]

        System.out.println(
                Arrays.toString(
                        intersectionBinarySearch(
                                new int[]{5,0,0,6,1,6,2,2,4}
                                , new int[]{4,7,9,7,6,7}
                        )
                )
        ); // [4,6]

        System.out.println(
                Arrays.toString(
                        intersectionBinarySearch(
                                new int[]{3,1,2}
                                , new int[]{1,1}
                        )
                )
        ); // [1]

        System.out.println(
                Arrays.toString(
                        intersectionBinarySearch(
                                new int[]{1, 2, 2, 1}
                                , new int[]{2,2}
                        )
                )
        ); // [2,2]

        System.out.println(
                Arrays.toString(
                        intersectionBinarySearch(
                                new int[]{1, 2, 2,  2,1}
                                , new int[]{2,2}
                        )
                )
        ); // [2,2]

        System.out.println(
                Arrays.toString(
                        intersectionBinarySearch(
                                new int[]{1, 2, 2 ,1}
                                , new int[]{2, 2, 2}
                        )
                )
        ); // [2,2]

        System.out.println(
                Arrays.toString(
                        intersectionBinarySearch(
                                new int[]{1, 2, 2 ,1}
                                , new int[]{1, 1}
                        )
                )
        ); // [1, 1]

        System.out.println(
                Arrays.toString(
                        intersectionBinarySearch(
                                new int[]{1, 2, 2 ,1}
                                , new int[]{2, 2, 1}
                        )
                )
        ); // [1,2,2]
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("------------------");
        start = Instant.now();
        System.out.println(
                Arrays.toString(
                        intersectionEfficient(
                                new int[]{7, 1, 5, 2, 3, 6}
                                , new int[]{3, 8, 6, 20, 7}
                        )
                )
        ); // [3, 6, 7]

        System.out.println(
                Arrays.toString(
                        intersectionEfficient(
                                new int[]{9,1}
                                , new int[]{7,8,3,9,0,0,9,1,5}
                        )
                )
        ); // [1,9]

        System.out.println(
                Arrays.toString(
                        intersectionEfficient(
                                new int[]{5,0,0,6,1,6,2,2,4}
                                , new int[]{4,7,9,7,6,7}
                        )
                )
        ); // [4,6]

        System.out.println(
                Arrays.toString(
                        intersectionEfficient(
                                new int[]{3,1,2}
                                , new int[]{1,1}
                        )
                )
        ); // [1]

        System.out.println(
                Arrays.toString(
                        intersectionEfficient(
                                new int[]{1, 2, 2, 1}
                                , new int[]{2,2}
                        )
                )
        ); // [2,2]

        System.out.println(
                Arrays.toString(
                        intersectionEfficient(
                                new int[]{1, 2, 2,  2,1}
                                , new int[]{2,2}
                        )
                )
        ); // [2,2]

        System.out.println(
                Arrays.toString(
                        intersectionEfficient(
                                new int[]{1, 2, 2 ,1}
                                , new int[]{2, 2, 2}
                        )
                )
        ); // [2,2]

        System.out.println(
                Arrays.toString(
                        intersectionEfficient(
                                new int[]{1, 2, 2 ,1}
                                , new int[]{1, 1}
                        )
                )
        ); // [1, 1]

        System.out.println(
                Arrays.toString(
                        intersectionEfficient(
                                new int[]{1, 2, 2 ,1}
                                , new int[]{2, 2, 1}
                        )
                )
        ); // [1,2,2]
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toNanos());
    }

    private static final Integer[] intersectionMap(final int nums1[], final int nums2[]) {

        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new Integer[0];
        int[] smaller  = nums1;
        int[] larger  = nums2;
        if(nums1.length > nums2.length) {
            larger = nums1;
            smaller = nums2;
        }
        final Map<Integer, Integer> mapSmaller = new HashMap<>();
        for (final int num: smaller) {
            mapSmaller.put(num, mapSmaller.getOrDefault(num, 0)+1);
        }

        final List<Integer> intersection = new LinkedList<>();
        for (final int num: larger) {
            if(mapSmaller.containsKey(num)) {
                final int count = mapSmaller.get(num);
                if(count > 0) {
                    intersection.add(num);
                    mapSmaller.put(num, count - 1);
                }
            }
        }
        return intersection.toArray(new Integer[intersection.size()]);
    }


    private static final Integer[] intersectionBinarySearch(final int nums1[], final int nums2[]) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new Integer[0];
        int[] smaller  = nums1;
        int[] larger  = nums2;
        if(nums1.length > nums2.length) {
            larger = nums1;
            smaller = nums2;
        }
        List<Integer> intersection = new LinkedList<>();
        Arrays.sort(larger);
        Arrays.sort(smaller);
        int prev = -1;
        for (final int num: larger) {
            final int index = search(smaller, prev + 1, smaller.length, num);
            if (index < 0) continue;
            prev = index;
            intersection.add(num);
        }
        return intersection.toArray(new Integer[intersection.size()]);
    }

    private static int search(final int[] nums, int start, int end, int target) {
        if(start == end || nums == null || nums.length == 0) return -1;
        int left = start, right = end-1;
        while(left <= right) {
            final int mid = left + (right - left) / 2;
            if(target <= nums[mid]) right = mid-1;
            else left = mid+1;
        }
        return left >= nums.length || nums[left] != target ? -1 : left;
    }


    private static final Integer[] intersectionEfficient(final int nums1[], final int nums2[]) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new Integer[0];
        List<Integer> intersection = new LinkedList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j= 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                intersection.add(nums1[i++]);
                j++;
                continue;
            }
            if(nums1[i] < nums2[j]) i++;
            else j++;
        }
        return intersection.toArray(new Integer[intersection.size()]);
    }



}
