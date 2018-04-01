package net.geekscore.tree.binary.similarity;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *           1
 *          / \
 *         2   2
 *        / \ / \
 *       3  4 4  3
 *
 *       But the following [1,2,2,null,3,null,3] is not:
 *          1
 *         / \
 *        2   2
 *        \   \
 *        3    3
 *
 */

public class VerifySymmetricTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,2,2,3,4,4,3});
        TreeUtil.print(tree);
        System.out.println(isSymmetric(tree)); // true
        System.out.println(isSymmetricBFS(tree.root())); // true;

        tree = TreeUtil.treeOf(new Integer[]{1,2,2,null,3,null,3});
        TreeUtil.print(tree);
        System.out.println(isSymmetric(tree)); // false
        System.out.println(isSymmetricBFS(tree.root())); // false

        tree = TreeUtil.treeOf(new Integer[]{1,2,2,null,3,3,null});
        TreeUtil.print(tree);
        System.out.println(isSymmetric(tree)); // true
        System.out.println(isSymmetricBFS(tree.root())); // true

        tree = TreeUtil.treeOf(new Integer[]{1,2,3});
        TreeUtil.print(tree);
        System.out.println(isSymmetric(tree)); // false
        System.out.println(isSymmetricBFS(tree.root())); // false

        tree = TreeUtil.treeOf(new Integer[]{1});
        TreeUtil.print(tree);
        System.out.println(isSymmetric(tree)); // true
        System.out.println(isSymmetricBFS(tree.root())); // true



    }

    private static boolean isSymmetric(IBTree<Integer> tree) {
        return null == tree || null == tree.root() || isSymmetricHelper(tree.root().left, tree.root().right);
    }

    private static boolean isSymmetricHelper(BTreeNode<Integer> left, BTreeNode<Integer> right) {
        return     left == right ||
                right != null
                && left != null
                && left.data.compareTo(right.data) == 0
                && isSymmetricHelper(left.left, right.right)
                && isSymmetricHelper(left.right, right.left);
    }

    private static boolean isSymmetricBFS(BTreeNode<Integer> root) {
        if(null == root) return true;
        final Deque<BTreeNode<Integer>> leftQueue = new LinkedList<>();
        leftQueue.offerFirst(root);
        final Deque<BTreeNode<Integer>> rightQueue = new LinkedList<>();
        rightQueue.offerFirst(root);
        boolean isSymmetric = Boolean.FALSE;
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            final BTreeNode<Integer> left = leftQueue.removeLast();
            final BTreeNode<Integer> right = rightQueue.removeLast();
            if(null == left && null != right) return Boolean.FALSE;
            if(null != left && null == right) return Boolean.FALSE;
            if(null != left && null != right) {
                isSymmetric = (left.data.compareTo(right.data) == 0);
                leftQueue.offerFirst(left.left);
                leftQueue.offerFirst(left.right);
                rightQueue.offerFirst(right.right);
                rightQueue.offerFirst(right.left);
            }
            if(!isSymmetric) break;
        }
        return isSymmetric;

    }
}
