package net.geekscore.tree.binary;

import net.geekscore.tree.TreeUtil;

import java.util.*;

/**
 * Created by ravirajmulasa on 8/28/16.
 */
public final class BSTreeImpl<T extends Comparable<T>> implements IBTree<T> {

    private BTreeNode<T> root = null;

    public BSTreeImpl(){ this(null); }

    public BSTreeImpl(final BTreeNode<T> root){ this.root = root; }


    public boolean insert(T item) {

        final BTreeNode<T> elem = new BTreeNode<T>(item);
//        Empty Tree
        if(null == this.root){
            this.root = elem;
            return true;
        }

        final Comparator<T> bTreeNodeComparator = new BSTreeNodeComparator<T>();
        BTreeNode<T> curr   = this.root;
        BTreeNode<T> parent = null;
        while (curr != null) {
            parent = curr;
            if (Objects.equals(item, curr.data)) return false; //Current equals item, already present
            curr = (Objects.compare(item, parent.data, bTreeNodeComparator) < 0)  ? curr.left : curr.right;

        }
        if(parent != null) {
            if(Objects.compare(item, parent.data, bTreeNodeComparator) < 0) {
                parent.left=elem;
            }else {
                parent.right=elem;
            }
        }
        return true;
    }


    public BTreeNode<T> delete(T item) {

        final BTreeNode<T> elemFound = find(item);

        if(null != elemFound) {

            final BTreeNode<T> parent = elemFound.parent;
//            Node to be deleted has no children a.k.a Leaf Node
            if(elemFound.isLeaf()) {
                if(parent.left == elemFound) {
                    parent.left=null;
                } else {
                    parent.right=null;
                }
            }

//            Node to be deleted has only right child
            if(elemFound.right != null && elemFound.left == null) {
                parent.right=elemFound.right;
            }

//            Node to be deleted has only left child
            if(elemFound.left != null && elemFound.right == null) {
                parent.left=elemFound.left;
            }

//            Node to be deleted has 2 children
            if(elemFound.right != null && elemFound.left != null) {
                final BTreeNode<T> max = TreeUtil.successorBST(elemFound);

            }


        }
        if(null != elemFound) {
            elemFound.parent = null;
        }
        return elemFound;
    }


    public BTreeNode<T> find(T item) {
        final Comparator<T> bTreeNodeComparator = new BSTreeNodeComparator<T>();
        BTreeNode<T> curr   = this.root;
        BTreeNode<T> parent = null;
        while (curr != null && !Objects.equals(item, curr.data)) {
            parent  = curr;
            curr    = (Objects.compare(item, parent.data, bTreeNodeComparator) < 0) ? curr.left : curr.right;
        }
        return curr;
    }

    @Override
    public BTreeNode<T> root() {
        return this.root;
    }

}
