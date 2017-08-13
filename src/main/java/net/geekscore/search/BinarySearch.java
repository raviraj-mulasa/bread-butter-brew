package net.geekscore.search;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ravirajmulasa on 12/27/16.
 */
public final class BinarySearch {

    private static <T extends Comparable<T>> Integer binarySearch(final T[] items, T item2Search) {

        if(null == items || 0 >= items.length){
            return -1;
        }
        int low     = 0;
        int high    = items.length - 1;

        while (low <= high){
            final int mid               = low + (high - low) / 2;
            final int compareItem2Mid   = item2Search.compareTo(items[mid]);
            if(compareItem2Mid == 0){
                return mid;
            }
            if(compareItem2Mid < 0) {
//                search in left sub array
                high = mid - 1;
            } else {
//                search in right sub array
                low = mid + 1;
            }
        }
        return -1;
    }


    private static <T extends Comparable<T>> Integer binarySearchRec(final T[] items, T item2Search, int low, int high) {

        final int mid               = low + (high - low) / 2;
        final int compareItem2Mid   = item2Search.compareTo(items[mid]);
        if(compareItem2Mid == 0){
            return mid;
        }
        else if(compareItem2Mid < 0) {
//                search in left sub array
            return binarySearchRec(items, item2Search, low, mid - 1);
        } else {
//                search in right sub array
            return binarySearchRec(items, item2Search, mid+1, high);
        }

    }


    public static void main(String[] args) {
        final int lowerLimitInclusive = 10;
        final int upperLimitExclusive = 20;
        final Integer items[] = IntStream.range(lowerLimitInclusive, upperLimitExclusive).boxed().collect(Collectors.toList()).toArray(new Integer[0]);
        System.out.println(Arrays.asList(items));
        System.out.println(binarySearch(items, 17)); // 7
        System.out.println(binarySearch(items, 15)); // 5
        System.out.println(binarySearchRec(items, 17, 0, upperLimitExclusive - lowerLimitInclusive - 1)); // 7
        System.out.println(binarySearchRec(items, 15, 0, upperLimitExclusive - lowerLimitInclusive - 1)); // 5

    }
}
