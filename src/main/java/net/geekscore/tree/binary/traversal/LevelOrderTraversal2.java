package net.geekscore.tree.binary.traversal;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeImpl;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *      3
 *      / \
 *     9  20
 *        /  \
 *        15   7
 *        return its bottom-up level order traversal as:
 *        [[15,7],[9,20],[3]]
 */
public class LevelOrderTraversal2 {

    public static void main(String[] args) {

        BTreeNode<Integer> root = new BTreeNode<>(3);
        root.left = new BTreeNode<>(9);
        root.right = new BTreeNode<>(20);
        root.right.left = new BTreeNode<>(15);
        root.right.right = new BTreeNode<>(7);
        IBTree<Integer> binaryTree = new BTreeImpl<>(root);
        TreeUtil.print(binaryTree);

        System.out.println(levelOrderTraversalReverse(binaryTree)); // [[15,7],[9,20],[3]]

        root = new BTreeNode<>(1);
        root.left = new BTreeNode<>(2);
        root.right = new BTreeNode<>(3);
        root.left.left = new BTreeNode<>(4);
        root.left.right = new BTreeNode<>(5);
        binaryTree = new BTreeImpl<>(root);
        TreeUtil.print(binaryTree);

        System.out.println(levelOrderTraversalReverse(binaryTree)); // [[4,5],[2,3],[1]]
    }

    // Uses BFS
    private static <T extends Comparable<T>> List<List<T>> levelOrderTraversalReverse(final IBTree<T> tree) {

        if(null == tree || tree.root() == null) return Collections.emptyList();
        final List<List<T>> levelOrderList = new LinkedList<>();
        final Deque<BTreeNode<T>> queue = new LinkedList<>();
        final BTreeNode<T> curr = tree.root();
        queue.offerFirst(curr);
        while (!queue.isEmpty()) {
            final List<T> levelList = new LinkedList<>();
            final int size = queue.size(); // queue size until this level
            for (int i = 0; i < size; i++) {
                final BTreeNode<T> ele = queue.removeLast();
                levelList.add(ele.data);
                if(null != ele.left) queue.offerFirst(ele.left);
                if(null != ele.right) queue.offerFirst(ele.right);
            }
            levelOrderList.add(levelList);
        }
        Collections.reverse(levelOrderList);
        return levelOrderList;
    }
}
