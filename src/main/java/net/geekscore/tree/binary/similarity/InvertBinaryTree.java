package net.geekscore.tree.binary.similarity;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

/**
 * Invert a binary tree.
 *            4
 *          /   \
 *         2     7
 *        / \   / \
 *      1   3 6   9
 *
 *      to
 *            4
 *          /   \
 *         7     2
 *        / \   / \
 *       9   6 3   1
 *
 *  Trivia:
 This problem was inspired by this original tweet by Max Howell:
 Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
 */
public class InvertBinaryTree {

    public static void main(String[] args) {
        final IBTree<Integer> btree = TreeUtil.treeOf(new Integer[]{4,2,7,1,3,6,9,null,1,10});
        TreeUtil.print(btree);
        invert(btree.root());
        TreeUtil.print(btree);
    }

    private static void invert(BTreeNode<Integer> root) {
        if(root != null) invertHelper(root, root.left, root.right);
    }

    private static void invertHelper(BTreeNode<Integer> root, BTreeNode<Integer> left, BTreeNode<Integer> right) {
        if(left==right) return;
        root.left = right;
        root.right = left;
        if(left != null)  invertHelper(left, left.left, left.right);
        if(right != null)  invertHelper(right, right.left, right.right);
    }
}
