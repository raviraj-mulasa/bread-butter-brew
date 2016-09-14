package edu.learn.me.heap;

import java.util.*;

/**
 * Created by ravirajmulasa on 9/12/16.
 */
public abstract class AbstractBinaryHeap<T extends Comparable> implements IBinaryHeap<T> {

    protected T[] items                 = (T[]) new Comparable[0];
    protected Comparator<T> comparator  = Comparator.<T>naturalOrder();


    @Override
    public int parent(int position) {
        if(position == 0){
            return -1;
        }
        return (int)Math.floor((position - 1)/2);
    }

    @Override
    public int left(int position) {
        if (2*position + 1 < this.items.length) {
            return 2*position + 1;
        }
        return -1;
    }

    @Override
    public int right(int position) {
        if (2*position + 2 < this.items.length) {
            return 2*position + 2;
        }
        return -1;
    }

    @Override
    public boolean isEmpty(){
        return (null == this.items || this.items.length <= 0);
    }


    /**
     *
     * Observation:
     *  Elements A[ Math.floor)(n-1)/2)  … n-1] are leaves of the tree.
     *  because 2i > n, for all i >= Math.floor(n/2) - 1
     *  so heap property may only be violated at nodes 0… n/2 - 1 of the tree
     *
     *
     */
    @Override
    public void buildHeap() {
        for (int i = (this.items.length / 2 - 1); i >= 0; i--) {
            heapify(i);
        }
    }


    @Override
    public void heapify(int root) {

        final int left  = this.left(root);
        final int right = this.right(root);

//        we assume root as heap root index
        int heapRoot     = root;

        if(left != -1  && Objects.compare(this.items[left], this.items[root], this.comparator) > 0) {
//            left is heap root index
            heapRoot     = left;
        }

        if(right != -1 && Objects.compare(this.items[right], this.items[heapRoot], this.comparator) > 0) {
//            right is heap root index
            heapRoot     = right;
        }

        if(root != heapRoot) {
//            our assumption is wrong, exchange items at root and heapRoot
            swap(root, heapRoot);
//            restart heapify at the new heap root
            heapify(heapRoot);
        }
    }

    @Override
    public void updateKey(int position, T item) {

        if(position < 0 || position >= this.size()){
            return;
        }
        this.items[position]= item;
        int parentIndex     = this.parent(position);

        while (parentIndex != -1 && Objects.compare(this.items[position], this.items[parentIndex], this.comparator) > 0) {
            this.swap(position, parentIndex);
            position  = parentIndex;
            parentIndex = this.parent(position);
        }

    }


    @Override
    public void insert(T item) {

        if(this.isEmpty()){
            this.items      = (T[]) new Comparable[1];
            this.items[0]   = item;
            return;
        }

        append(item);

        int childIndex  = this.items.length - 1;
        int parentIndex = this.parent(childIndex);

        while (parentIndex != -1 && Objects.compare(this.items[childIndex], this.items[parentIndex], this.comparator) > 0) {

            this.swap(childIndex, parentIndex);
            childIndex  = parentIndex;
            parentIndex = this.parent(childIndex);
        }


    }

    @Override
    public T extract() {
        if(this.isEmpty()){
            return null;
        }
        final T rootItem= this.items[0];
        this.items[0]   = this.items[this.items.length - 1];
        this.deleteFromEnd(1);
        heapify(0);
        return rootItem;
    }


    @Override
    public T[] items() {
        return this.items;
    }

    @Override
    public int size() {
        return (this.isEmpty() ? 0 : this.items.length);
    }



    private void swap(final int index1, final int index2) {
        if(Math.min(index1, index2) < 0 || Math.max(index1, index2) >= this.items.length) {
            return;
        }
        T temp              = this.items[index1];
        this.items[index1]  = this.items[index2];
        this.items[index2]  = temp;
    }


    private void deleteFromEnd(final int noItems2Delete) {
        this.items = Arrays.copyOf(this.items, this.items.length - noItems2Delete);
    }


    private void append(T item) {
        this.items                          = Arrays.copyOf(this.items, this.items.length + 1);
        this.items[this.items.length - 1]   = item;
    }


}
