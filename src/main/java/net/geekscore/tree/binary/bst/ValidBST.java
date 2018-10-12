package net.geekscore.tree.binary.bst;


import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BSTreeNodeComparator;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 * Input:
 *      2
 *     / \
 *    1   3
 * Output: true
 *
 * Example 2:
 *      5
 *     / \
 *    1   4
 *       / \
 *      3   6
 * Output: false
 * Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 * is 5 but its right child's value is 4.

 */
public class ValidBST {

    public static void main(String[] args) {

        IBTree<Integer> bstTree = TreeUtil.treeOf(new Integer[]{8,3,10,1,6,null,14,null,null,4,7,null,null,13,null});
        TreeUtil.print(bstTree);
        //  true
        System.out.println("Is valid BST Rec? "+ isBSTRec(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Is valid BST ? "+ isBST(bstTree));


        System.out.println("-------------------");
        bstTree = TreeUtil.treeOf(new Integer[]{2,1,3});
        TreeUtil.print(bstTree);
        // false
        System.out.println("Is valid BST Rec? "+ isBSTRec(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Is valid BST ? "+ isBST(bstTree));

        System.out.println("-------------------");
        bstTree = TreeUtil.treeOf(new Integer[]{8,3,10,1,9,null,14,null,null,4,7,null,null,13,null});
        TreeUtil.print(bstTree);
        // false
        System.out.println("Is valid BST Rec? "+ isBSTRec(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Is valid BST ? "+ isBST(bstTree));

        System.out.println("-------------------");
        bstTree = TreeUtil.treeOf(new Integer[]{1,1});
        TreeUtil.print(bstTree);
        // false
        System.out.println("Is valid BST Rec? "+ isBSTRec(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Is valid BST ? "+ isBST(bstTree));


        System.out.println("-------------------");
        bstTree = TreeUtil.treeOf(new Integer[]{Integer.MAX_VALUE});
        TreeUtil.print(bstTree);
        // true
        System.out.println("Is valid BST Rec? "+ isBSTRec(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Is valid BST ? "+ isBST(bstTree));

        System.out.println("-------------------");
        bstTree = TreeUtil.treeOf(new Integer[]{Integer.MIN_VALUE});
        TreeUtil.print(bstTree);
        // true
        System.out.println("Is valid BST Rec? "+ isBSTRec(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Is valid BST ? "+ isBST(bstTree));


        System.out.println("-------------------");
        bstTree = TreeUtil.treeOf(new Integer[]{Integer.MIN_VALUE, null, Integer.MAX_VALUE});
        TreeUtil.print(bstTree);
        // true
        System.out.println("Is valid BST Rec? "+ isBSTRec(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Is valid BST ? "+ isBST(bstTree));


        System.out.println("-------------------");
        bstTree = TreeUtil.treeOf(new Integer[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
        TreeUtil.print(bstTree);
        // true
        System.out.println("Is valid BST Rec? "+ isBSTRec(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Is valid BST ? "+ isBST(bstTree));
    }



    private static <T extends Comparable<T>>  boolean isBSTRec(final IBTree<T> tree, T minValue, T maxValue) {
        return isBSTRec(tree.root(), minValue, maxValue);
    }


    private static <T extends Comparable<T>> boolean isBSTRec(final BTreeNode<T> node, T minValue, T maxValue) {
        if(null == node) return true;
        final Comparator<T> bTreeNodeComparator = new BSTreeNodeComparator<>();
        return
//                node value is greater than min value
                Objects.compare(minValue, node.data, bTreeNodeComparator) < 0
//                node value is less than max value
                        && Objects.compare(node.data, maxValue, bTreeNodeComparator) < 0
//                left subtree values are less than node value
                        && isBSTRec(node.left, minValue, node.data)
//                right subtree values are greater than node value
                        && isBSTRec(node.right, node.data, maxValue);
    }

    // Based on in order traversal - Preferred way
    private static <T extends Comparable<T>>  boolean isBST(final IBTree<T> tree) {
        if(tree == null || tree.root() == null) return true;
        BTreeNode<T> root = tree.root();
        BTreeNode<T> prev = null;
        final Deque<BTreeNode<T>> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(prev != null && prev.data.compareTo(root.data) >= 0) return false;
            prev = root;
            root = root.right;
        }
        return true;
    }
}
