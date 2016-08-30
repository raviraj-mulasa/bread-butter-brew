package edu.learn.me.tree.binary;

import java.util.Comparator;

/**
 * Created by ravirajmulasa on 8/29/16.
 */
public class BTreeNodeComparator<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
