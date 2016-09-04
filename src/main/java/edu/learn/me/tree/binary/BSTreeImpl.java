package edu.learn.me.tree.binary;

import java.util.*;

/**
 * Created by ravirajmulasa on 8/28/16.
 */
public final class BSTreeImpl<T extends Comparable<T>> implements IBTree<T> {

    private BTreeNode<T> root = null;

    public BSTreeImpl(){ this.root = null; }

    public BSTreeImpl(final BTreeNode<T> root){ this.root = root; }

    /**
     *
     * @param item
     * @return true or false
     */
    public boolean insert(T item) {

        final BTreeNode<T> elem = new BTreeNode<T>(item);

//        Empty Tree
        if(null == this.root){
            this.root = elem;
            return true;
        }

        final Comparator<T> bTreeNodeComparator = new BTreeNodeComparator<T>();

        BTreeNode<T> curr   = this.root;
        BTreeNode<T> parent = null;


        while (curr != null) {

            parent = curr;
//            Current equals item, already present
            if (Objects.equals(item, curr.getData())) {
                return false;
            }
//            Current less than item, move to left
            else if (Objects.compare(item, parent.getData(), bTreeNodeComparator) < 0) {
                curr = curr.getLeft();
            }
//            Current greater than item, move to right
            else {
                curr = curr.getRight();
            }
        }

        if(parent != null) {
            if(Objects.compare(item, parent.getData(), bTreeNodeComparator) < 0) {
                parent.setLeft(elem);
            }else {
                parent.setRight(elem);
            }
        }
        return true;
    }


    /**
     *
     * @param item
     * @return BTreeNode or null
     */
    public BTreeNode<T> delete(T item) {

        final BTreeNode<T> elemFound = find(item);

        if(null != elemFound) {

//            Node to be deleted has no children a.k.a Leaf Node
            if(elemFound.isLeaf()) {
                final BTreeNode<T> parent = elemFound.getParent();
                if(parent.getLeft() == elemFound) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }

//            Node to be deleted has only right child
            if(elemFound.getRight() != null && elemFound.getLeft() == null) {
                final BTreeNode<T> parent = elemFound.getParent();
                parent.setRight(elemFound.getRight());
            }

//            Node to be deleted has only left child
            if(elemFound.getLeft() != null && elemFound.getRight() == null) {
                final BTreeNode<T> parent = elemFound.getParent();
                parent.setLeft(elemFound.getLeft());
            }

//            Node to be deleted has 2 children
            if(elemFound.getRight() != null && elemFound.getLeft() != null) {
                final BTreeNode<T> parent = elemFound.getParent();

            }


        }

        if(null != elemFound) {
            elemFound.setParent(null);
        }
        return elemFound;
    }

    /**
     *
     * @param item
     * @return BTreeNode or null
     */
    public BTreeNode<T> find(T item) {

        final Comparator<T> bTreeNodeComparator = new BTreeNodeComparator<T>();

        BTreeNode<T> curr   = this.root;
        BTreeNode<T> parent = null;

        while (curr != null && !Objects.equals(item, curr.getData())) {
            parent  = curr;
            curr    = (Objects.compare(item, parent.getData(), bTreeNodeComparator) < 0) ? curr.getLeft() : curr.getRight();
        }
        return curr;
    }

    @Override
    public BTreeNode<T> root() {
        return this.root;
    }


    public int height() {
        return 0;
    }
}
