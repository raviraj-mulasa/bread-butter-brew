package net.geekscore.tree.binary.traversal;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 *
 * Example 1:
 *      1
 *   2      3
 * 4   5      6
 *
 *  1
 *  3 2
 *  4 5 6
 *
 * Example 2:
 *      3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 *  3
 *  9 20
 *  4 5 6
 */
public class ZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,6});
        TreeUtil.print(tree);
        System.out.println(printZigzagLevelOrder(tree.root()));

        tree = TreeUtil.treeOf(new Integer[]{3,9,20,null,null,15,7});
        TreeUtil.print(tree);
        System.out.println(printZigzagLevelOrder(tree.root()));
    }

    private static <T extends Comparable<T>>  List<List<BTreeNode<T>>> printZigzagLevelOrder(final BTreeNode<T> root) {
        if(root == null) return Collections.emptyList();
        final Deque<BTreeNode<T>> queue = new LinkedList<>();
        queue.offerFirst(root);
        List<List<BTreeNode<T>>> list = new LinkedList<>();
        int level = 0;
        while (!queue.isEmpty()) {
            final int size = queue.size();
            List<BTreeNode<T>> levelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                final BTreeNode<T> top = queue.removeLast();
                levelList.add(top);
                if(top.right != null) queue.offerFirst(top.right);
                if(top.left != null) queue.offerFirst(top.left);
            }
            if(level++ % 2 == 0 && levelList.size() > 1) Collections.reverse(levelList);
            list.add(levelList);
        }
        return list;

    }
}
