package net.geekscore.tree.binary.misc;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.*;

/**
 * You need to find the largest value in each row of a binary tree.
 *
 * Example:
 * Input:
 *          1
 *         / \
 *        3   2
 *       / \   \
 *     5   3   9
 *
 *  Output: [1, 3, 9]
 */
public class FindLargestValueEachTreeRow {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,3,2,5,3,null,9});
        TreeUtil.print(tree);

        System.out.println(largestValuesDFS(tree.root())); // [1,3,9]
        System.out.println(largestValuesBFS(tree.root())); // [1,3,9]
    }

    private static List<Integer> largestValuesDFS(BTreeNode<Integer> root) {
        final List<Integer> maxByLevel = new LinkedList<>();
        largestValuesDFS(root, maxByLevel, 0);
        return maxByLevel;
    }

    private static void largestValuesDFS(BTreeNode<Integer> root, List<Integer> maxByLevel, int level) {
        if(root == null) return;
        if(maxByLevel.size() == level) {
            maxByLevel.add(root.data);
        } else {
            maxByLevel.set(level , Math.max(maxByLevel.get(level), root.data));
        }
        largestValuesDFS(root.left, maxByLevel, level+1);
        largestValuesDFS(root.right, maxByLevel, level+1);
    }

    private static List<Integer> largestValuesBFS(BTreeNode<Integer> root) {
        if(root == null) return Collections.emptyList();
        final List<Integer> maxByLevel = new LinkedList<>();
        final Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(root);
        while (!queue.isEmpty()) {
            final int size = queue.size();
            Integer max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                final BTreeNode<Integer> top = queue.removeLast();
                max = Math.max(top.data, max);
                if(top.right != null) queue.offerFirst(top.right);
                if(top.left != null) queue.offerFirst(top.left);
            }
            maxByLevel.add(max);
        }
        return maxByLevel;
    }
}
