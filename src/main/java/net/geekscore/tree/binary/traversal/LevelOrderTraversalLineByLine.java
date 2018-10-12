package net.geekscore.tree.binary.traversal;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;


import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 * For example:
 *      1
 *   2      3
 * 4   5      6
 *
 *  1
 *  2 3
 *  4 5 6
 */
public class LevelOrderTraversalLineByLine {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,6});
        TreeUtil.print(tree);
        printLevelOrder(tree.root());
    }


    private static <T extends Comparable<T>>  void printLevelOrder(final BTreeNode<T> root) {

        if(null == root) return;
        final Deque<BTreeNode<T>> queue = new LinkedList<>();
        queue.offerFirst(root);
        while (!queue.isEmpty()) {
            final int size = queue.size(); // queue size until this level
            final StringBuilder levelBuilder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                BTreeNode<T> top = queue.removeLast();
                levelBuilder.append(top.data).append(" ");
                if(null != top.left) queue.offerFirst(top.left);
                if(null != top.right) queue.offerFirst(top.right);
            }
            levelBuilder.deleteCharAt(levelBuilder.length()-1);
            System.out.println(levelBuilder.toString());
        }
    }
}