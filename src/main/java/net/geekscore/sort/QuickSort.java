package net.geekscore.sort;

import java.util.*;

/**
 * Created by ravirajmulasa on 9/7/16.
 *
 * http://algs4.cs.princeton.edu/lectures/23Quicksort.pdf
 */
public final class QuickSort<T extends Comparable> implements IQuickSort<T> {

    @Override
    public Collection<T> sort(T[] elements2sort, boolean asc) {

        if(elements2sort == null) {
            Collections.emptyList();
        }

        quickSort(elements2sort, 0, elements2sort.length - 1);
        final List<T> sortedList = Arrays.asList(elements2sort);

        if(asc) {
            Collections.reverse(sortedList);
        }
        return sortedList;
    }

    /**
     *
     * 2-way partition
     *
     * @param array
     * @param low
     * @param high
     * @return pivot Index
     */
    @Override
    public int partition(T[] array, final int low, final int high) {

        if(null == array || array.length <=0){
            return -1;
        }

//        First element as the pivot element here
        final T pivotElem = array[low];

        int lessThan        = low + 1;
        int greaterThan     = high;

        while (true) {

            while (lessThan <= greaterThan && array[lessThan].compareTo(pivotElem) <= 0) {
                lessThan += 1;
            }

            while (array[greaterThan].compareTo(pivotElem) >= 0 && greaterThan >= lessThan) {
                greaterThan -= 1;
            }

            if(greaterThan < lessThan) {
                break;
            }
            swap(array, lessThan, greaterThan);
        }

//        greaterThan is final position for the pivot element
        swap(array, low, greaterThan);
        return greaterThan;

    }


    /**
     *
     * @param array
     * @param k
     * @return Given an array of N items, find the kth smallest item.
     */
    @Override
    public T select(final T[] array, final int k) {

        if(null == array || k > array.length || k < 1) {
            return null;
        }

        int low = 0;
        int high= array.length - 1;

        while (high > low) {

            // Partition and check the pivot Index against k
            final int pivotIndex = partition(array, low, high);

            if(pivotIndex == k-1 ) {
//                the pivot index is the kth smallest item
                return array[pivotIndex];
            } else if(pivotIndex < k-1) {
//                the pivot index < kth smallest item, discard all elements from low to pivot index
                low = pivotIndex + 1;
            } else {
//                the pivot index > kth smallest item, discard all elements from pivot index to high
                high = pivotIndex - 1;
            }
        }
        return array[k];

    }


    /**
     * Given an array of N buckets, each containing a red, white, or blue pebble, sort them by color.
     *
     * DUTCH NATIONAL FLAG PROBLEM
     */
    @Override
    public final void threeWayPartitionSort(final T[] array, final int low, final int high) {

        if(null == array || array.length <=0){
            return;
        }

//        Recursion termination
        if (high <= low) {
            return;
        }
        int lessThan    = low;
        int greaterThan = high;

//        Partitioning element (v)
        T v  = array[low];

//        Scan i from left to right
        int i = low;
        while (i <= greaterThan) {

            final int cmp = array[i].compareTo(v);
            if (cmp < 0) {
                swap(array, lessThan++, i++);
            }
            else if (cmp > 0) {
                swap(array, i, greaterThan--);
            }
            else i++;
        }
        threeWayPartitionSort(array, low, lessThan - 1);
        threeWayPartitionSort(array, greaterThan + 1, high);

    }


    /**
     *
     * @return  pivot value, such the it is always 'closer' to the actual median
     *
     * https://en.wikipedia.org/wiki/Median_of_medians
     */
    @Override
    public T medianOfMedians(T[] array, final int low, final int high) {

        //TODO: Need Clarification
        if(high - low + 1 <= 9) {
            Arrays.sort(array);
            return array[array.length / 2];
        }


//        Array to hold the medians of all '5-element sub arrays'
        final T medians[]   = (T[]) new Comparable[(int)Math.ceil((double)(high-low+1)/5)];
        int medianIndex     = 0;


//        Divide an array into '5-element sub arrays', sort each sub array and store its median
        for(int i = low; i <= high; i+=5) {
            final T[] _5ElemSubArray = (T[]) new Comparable[Math.min(5, high-i+1)];
            System.arraycopy(array, i, _5ElemSubArray, 0, Math.min(5, high-i+1));
            Arrays.sort(_5ElemSubArray);

            medians[medianIndex] = _5ElemSubArray[_5ElemSubArray.length/2];
            medianIndex++;
        }
        return medianOfMedians(medians,0, medians.length - 1);

    }


    private void quickSort(T[] elements2sort, final int low, final int high) {
        if(high <= low || null == elements2sort){
            return;
        }
        final int pivotIndex = partition(elements2sort, low, high);
        quickSort(elements2sort, low, pivotIndex - 1);
        quickSort(elements2sort, pivotIndex + 1, high);
    }


    private void swap(final T[] array, final int index1, final int index2) {
        if(null == array || Math.min(index1, index2) < 0 || Math.max(index1, index2) >= array.length) {
            return;
        }
        T temp          = array[index1];
        array[index1]   = array[index2];
        array[index2]   = temp;
    }
}
