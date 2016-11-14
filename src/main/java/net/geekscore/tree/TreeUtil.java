package net.geekscore.tree;

import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.BTreeNodeComparator;
import net.geekscore.tree.binary.IBTree;

import java.util.*;

/**
 * Created by ravirajmulasa on 9/3/16.
 */
public final class TreeUtil {

    private TreeUtil(){}

    /**
     *
     * @return preOrder : Rlr
     */
    public static <T> List<T> preOrder(final IBTree<T> tree) {
//        start with root
        return preOrder(tree.root());
    }


    /**
     *
     * @return preOrder : Rlr
     */
    public static <T> List<T> preOrder(final BTreeNode<T> node) {

        BTreeNode curr = node;
        if(curr==null) {
            return Collections.emptyList();
        }

        final Stack<BTreeNode> stack      = new Stack<>();
//        start with node in stack
        stack.push(curr);
        final List<T> preOrderList  = new LinkedList<>();

        while(!stack.isEmpty()) {

            curr = stack.pop();
            preOrderList.add((T) curr.getData());

            if(null != curr.getRight()){
                stack.push(curr.getRight());
            }

            if(null != curr.getLeft()){
                stack.push(curr.getLeft());
            }
        }
        return preOrderList;
    }


    /**
     *
     * @return preOrder : lRr
     */
    public static <T> List<T> postOrder(final IBTree<T> tree) {
//        start with root
        return postOrder(tree.root());
    }


    /**
     *
     * @return preOrder : lrR
     */
    public static <T> List<T> postOrder(final BTreeNode<T> node) {

        BTreeNode curr = node;
        if(curr==null) {
            return Collections.emptyList();
        }

        BTreeNode prev              = null;
        final List<T> postOrderList = new LinkedList<>();
        Stack<BTreeNode> stack      = new Stack<>();

//        start with node in stack
        stack.push(curr);

        while(!stack.isEmpty()) {

            curr = stack.peek();
//            Moving Down
            if(prev == null || prev.getLeft() == curr || prev.getRight() == curr){

                if(null != curr.getLeft()) {
                    stack.push(curr.getLeft());
                } else if(null != curr.getRight()) {
                    stack.push(curr.getRight());
                } else  {
                    postOrderList.add((T) curr.getData());
//                    pop
                }

            }

//            Moving up from left
            if(prev == curr.getLeft()) {
                if(null != curr.getRight()) {
                    stack.push(curr.getRight());
                }
            } else {
                postOrderList.add((T) curr.getData());
//                pop
            }

//            Moving up from right
            if(prev == curr.getRight()) {
                postOrderList.add((T) curr.getData());
//                pop
            }
            prev = curr;
        }

        return postOrderList;
    }


    /**
     *
     * @return preOrder : lRr
     */
    public static <T> List<T> inOrder(final IBTree<T> tree) {
//        start with root
        return inOrder(tree.root());
    }


    /**
     *
     * @return preOrder : lRr
     */
    public static <T> List<T> inOrder(final BTreeNode<T> node) {

//        start with node in stack
        BTreeNode curr = node;

        if(curr==null) {
            return Collections.emptyList();
        }

        final List<T> inOrderList   = new LinkedList<>();
        Stack<BTreeNode> stack      = new Stack<>();

        while(!stack.isEmpty() || curr != null) {

            if(curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            } else {
                curr    = stack.pop();
                inOrderList.add((T) curr.getData());
                curr    = curr.getRight();
            }
        }
        return inOrderList;
    }

    /**
     *
     * @param node
     * @param <T>
     * @return inorder Successor:
     */
    public static <T extends Comparable<T>> BTreeNode<T> inOrderSuccessor(final BTreeNode<T> node) {

        if(null == node){
            return null;
        }

        final Comparator<T> bTreeNodeComparator = new BTreeNodeComparator<T>();
        BTreeNode<T> curr   = node;
//        Node has right sub tree, go deep to leftmost node in the right subtree i.e: Min in right subtree.
        if(curr.getRight() != null) {
            curr = curr.getRight();
            while (curr.getLeft() != null) curr = curr.getLeft();
            return curr;
        }
//        Node has NO right sub tree, go to nearest greater ancestor in which the node is part of the left subtree.
        else {
            BTreeNode<T> parent = curr.getParent();
            while(parent != null) {
                if(Objects.compare(curr.getData(), parent.getData(), bTreeNodeComparator) < 0) {
                    break;
                }
                curr    = parent;
                parent  = parent.getParent();
            }
            return parent;
        }
    }


    /**
     *
     * @param tree
     * @param minValue of generic
     * @param maxValue of generic
     * @param <T> Generic
     * @return true if tree is a Binary Search Tree or false
     */
    public static <T extends Comparable<T>>  boolean isBST(final IBTree<T> tree, T minValue, T maxValue) {
        return isBST(tree.root(), minValue, maxValue);
    }


    /**
     *
     * @param node
     * @param minValue of generic type
     * @param maxValue of generic type
     * @param <T> Generic type
     * @return true if tree at node is a Binary Search Tree or false
     */
    public static <T extends Comparable<T>> boolean isBST(final BTreeNode<T> node, T minValue, T maxValue) {

        if(null == node) {
            return true;
        }

        final Comparator<T> bTreeNodeComparator = new BTreeNodeComparator<T>();
        return
//                node value is greater than min value
                   Objects.compare(minValue, node.getData(), bTreeNodeComparator) < 0
//                node value is less than max value
                && Objects.compare(node.getData(), maxValue, bTreeNodeComparator) < 0
//                left subtree values are less than node value
                && isBST(node.getLeft(), minValue, node.getData())
//                right subtree values are greater than node value
                && isBST(node.getRight(), node.getData(), maxValue);
    }


    public static <T extends Comparable<T>> BTreeNode<T> findMinInBST(final BTreeNode<T> node) {

        if(node == null) {
            return null;
        }
        BTreeNode<T> curr = node;
        for(;curr.getLeft() != null; curr = curr.getLeft());
        return curr;
    }

    public static <T extends Comparable<T>> BTreeNode<T> findMaxInBST(final BTreeNode<T> node) {
        if(node == null) {
            return null;
        }
        BTreeNode<T> curr = node;
        for(;curr.getRight() != null; curr = curr.getRight());
        return curr;
    }


    public static <T> Integer heightBinaryTree(final IBTree<T> tree) {
        return heightBinaryTree(tree.root());
    }


    /**
     *
     * @return height: Max depth of a Binary tree from node
     */
    public static <T> Integer heightBinaryTree(final BTreeNode<T> node) {
        if(node == null) {
            return -1;
        }
        return Math.max(heightBinaryTree(node.getLeft()), heightBinaryTree(node.getRight())) + 1;
    }


}
