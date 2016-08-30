package edu.learn.me.tree.binary;

import java.util.Objects;

/**
 * Created by ravirajmulasa on 8/28/16.
 */
public final class BSTreeImpl<T extends Comparable<T>> implements IBTree<T> {

    private BTreeNode<T> root = null;

    public BSTreeImpl(){ this.root = null; }

    public BSTreeImpl(final BTreeNode<T> root){ this.root = root; }


    public void insert(T item) {

//        Empty Tree
        if(null == this.root){
            this.root = new BTreeNode<T>(item);
            return;
        }

//        Item equals root
        if(Objects.equals(item, this.root.getData())) {
            return;
        }

        BTreeNode<T> curr   = this.root;
        BTreeNode<T> parent = null;

        while (true) {

            parent = curr;
            if(Objects.compare(item, parent.getData() , new BTreeNodeComparator<T>()) < 0) {

            }
        }



    }

    public BTreeNode<T> delete(T item) {
        return null;
    }

    public BTreeNode<T> find(T item) {
        return null;
    }

    public String preOrder(BTreeNode<T> node) {
        return null;
    }

    public String postOrder(BTreeNode<T> node) {
        return null;
    }

    public String inOrder(BTreeNode<T> node) {
        return null;
    }

    public int height(BTreeNode<T> node) {
        return 0;
    }
}
