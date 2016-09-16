package edu.learn.me.sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by ravirajmulasa on 9/10/16.
 */
public final class MergeSort<T extends Comparable> implements IMergeSort<T> {

    @Override
    public Collection<T> sort(T[] elements2sort, boolean asc) {
        if(elements2sort == null || elements2sort.length <= 0) {
            Collections.emptyList();
        }
        mergeSort(elements2sort, asc);
        return Arrays.asList(elements2sort);
    }


    /**
     * Merge: merge two sorted arrays into a single sorted array
     */
    @Override
    public void merge(final T[] left, final T[] right, final T[] array, final boolean asc){

//        i - for left
//        j - for right
//        k - position in the array
        int i,j,k;
        i = j = k = 0;

        final int leftLength    = Math.max(0, left.length);
        final int rightLength   = Math.max(0, right.length);
        int leftComparedRight   = 0;

        while(i < leftLength && j < rightLength) {

            leftComparedRight = (asc ? right[j].compareTo(left[i]) :left[i].compareTo(right[j]));

            if(leftComparedRight <= 0) {
                array[k] = left[i];
                i +=1;
            } else {
                array[k] = right[j];
                j +=1;
            }
            k +=1;
        }

        while(i < leftLength) {
            array[k] = left[i];
            i += 1;
            k += 1;
        }

        while(j < rightLength) {
            array[k] = right[j];
            j += 1;
            k += 1;
        }
    }

    //TODO: PLEASE COMPLETE LATER
    @Override
    public void bottomUpMergeSort(final T[] elements2sort, final boolean asc) {

        final int length = elements2sort.length;

        for (int width = 1; width < length; width += width ) {

            for (int i = 0; i < length; i += (2*width)) {

                final T[] left  = (T[]) new Comparable[Math.min(width, length - i)];
                final T[] right = (T[]) new Comparable[width];

//                System.arraycopy(elements2sort, Math.min(i,length-width),        left, 0, width);
                System.arraycopy(elements2sort, i,        left, 0, Math.min(width, length - i));
//                System.arraycopy(elements2sort, Math.min(i+width, length-width), right, 0, width);

//                System.arraycopy(elements2sort, i+width,  right, 0, Math.min(width, length - i));
                System.arraycopy(elements2sort, Math.min(width, length - i) + 1,  right, 0, width);

                System.out.println(Arrays.asList(left));
                System.out.println(Arrays.asList(right));

//                System.out.println(Arrays.asList(Arrays.copyOfRange(elements2sort, i, i + width)));
//                System.out.println(Arrays.asList(Arrays.copyOfRange(elements2sort, i + width, i+(2*width))));
            }


        }
    }


    private void mergeSort(T[] elements2sort, final boolean asc) {

        final int numberOfElements = elements2sort.length;
        if(numberOfElements < 2) {
            return;
        }

        final int middle= numberOfElements / 2;
        final T[] left  = (T[]) new Comparable[middle];
        final T[] right = (T[]) new Comparable[numberOfElements - middle];

        System.arraycopy(elements2sort,      0,  left, 0, middle);
        System.arraycopy(elements2sort, middle, right, 0, numberOfElements - middle);

        mergeSort(left, asc);
        mergeSort(right, asc);
        merge(left, right, elements2sort, asc);

    }
}
