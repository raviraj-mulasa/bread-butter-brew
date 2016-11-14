package net.geekscore.tree.binary;

import java.util.Comparator;

/**
 * Created by ravirajmulasa on 8/29/16.
 */
public class BTreeNodeComparator<T extends Comparable<T>> implements Comparator<T> {

    private boolean reverse = false;

    public BTreeNodeComparator() {
        this(false);
    }

    BTreeNodeComparator(final boolean reverse) {
        this.reverse = reverse;
    }

    public int compare(T o1, T o2) {
        return  (this.reverse ? o2.compareTo(o1) : o1.compareTo(o2));
    }
}
