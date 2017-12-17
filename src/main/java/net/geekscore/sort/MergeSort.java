package net.geekscore.sort;

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

        while(i < leftLength && j < rightLength) {
            final int leftComparedRight = (asc ? right[j].compareTo(left[i]) :left[i].compareTo(right[j]));
            if(leftComparedRight <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while(i < leftLength) {
            array[k++] = left[i++];
        }

        while(j < rightLength) {
            array[k++] = right[j++];
        }
    }

    @Override
    public void bottomUpMergeSort(final T[] elements2sort, final boolean asc) {

        final int length = elements2sort.length;
        final T[] tmp = Arrays.copyOf(elements2sort, elements2sort.length);

        for (int width = 1; width < length; width *= 2 ) {

            for (int left = 0; left < length; left += (2*width)) {

                final int middle = Math.min(left + width, length);
                final int right  = Math.min(left + 2 * width , length);

                this.merge(elements2sort, left, middle, right, tmp, asc);

            }
            for ( int i = 0; i < elements2sort.length; i++ )
                elements2sort[i] = tmp[i];

        }
    }

    private  void merge(final T[] elements2sort, final int left, final int middle, final int right, final T[]  tmp, final boolean asc)
    {
        int i = left, j = middle, k = i;

        while ( i < middle && j < right ) {
            final int leftComparedRight = (asc ? elements2sort[j].compareTo(elements2sort[i]) : elements2sort[i].compareTo(elements2sort[j]));
            if (leftComparedRight <= 0)
                tmp[k++] = elements2sort[i++];
            else
                tmp[k++] = elements2sort[j++];
        }
        while(i < middle) {
            tmp[k++] = elements2sort[i++];
        }
        while(j < right) {
            tmp[k++] = elements2sort[j++];
        }
    }





}
