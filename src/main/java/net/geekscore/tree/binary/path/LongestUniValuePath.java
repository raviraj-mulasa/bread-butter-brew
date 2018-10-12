package net.geekscore.tree.binary.path;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Stack;

/**
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root.
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input:
 *          5
 *         / \
 *        4   5
 *       / \   \
 *      1   1   5
 *  Output: 2
 *
 *  Example 2:
 *  Input:
 *          1
 *         / \
 *        4   5
 *       / \   \
 *      4   4   5
 *  Output:2
 */
public class LongestUniValuePath {

    public static void main(String[] args) {

        System.out.println("---------------");
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{5,4,5,1,1,null,5}); // 2
        TreeUtil.print(tree);
        System.out.println("Longest: "+longestUniValuePathRec(tree));

        System.out.println("---------------");
        tree = TreeUtil.treeOf(new Integer[]{5,4,5,1,1,5}); // 2
        TreeUtil.print(tree);
        System.out.println("Longest: "+longestUniValuePathRec(tree));

        System.out.println("---------------");
        tree = TreeUtil.treeOf(new Integer[]{1,4,5,4,4,null,5}); // 2
        TreeUtil.print(tree);
        System.out.println("Longest: "+longestUniValuePathRec(tree));

        System.out.println("---------------");
        tree = TreeUtil.treeOf(new Integer[]{1,2,3}); // 0
        TreeUtil.print(tree);
        System.out.println("Longest: "+longestUniValuePathRec(tree));

        System.out.println("---------------");
        tree = TreeUtil.treeOf(new Integer[]{5,null,5,null,5,null,5}); // 3
        TreeUtil.print(tree);
        System.out.println("Longest: "+longestUniValuePathRec(tree));

        System.out.println("---------------");
        tree = TreeUtil.treeOf(new Integer[]{5,5,null,5,null,null}); // 3
        TreeUtil.print(tree);
        System.out.println("Longest: "+longestUniValuePathRec(tree));

        System.out.println("---------------");
        tree = TreeUtil.treeOf(new Integer[]{5,5,5,5,5,5,5}); // 4
        TreeUtil.print(tree);
        System.out.println("Longest: "+longestUniValuePathRec(tree));

    }

    private static Integer longest = 0;

    private static <T extends Comparable<T>> int longestUniValuePathRec(IBTree<T> tree) {
        if (tree == null) return 0;
        LongestUniValuePath.longest = 0;
        longestUniValuePathRecHelper(tree.root());
        return longest;
    }

    private static <T extends Comparable<T>> int longestUniValuePathRecHelper(BTreeNode<T> node) {
        if(node == null) return 0;
        int left = node.left != null ? longestUniValuePathRecHelper(node.left) : 0;
        int right = node.right != null ? longestUniValuePathRecHelper(node.right) : 0;
        left = node.left != null && node.left.data.compareTo(node.data) == 0 ? left + 1: 0;
        right = node.right != null && node.right.data.compareTo(node.data) == 0 ? right + 1: 0;
        LongestUniValuePath.longest = Math.max(LongestUniValuePath.longest, left+right);
        return Math.max(left, right);

    }

}
