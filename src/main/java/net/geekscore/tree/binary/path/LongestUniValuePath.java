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


        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{5,4,5,1,1,null,5}); // 2
        TreeUtil.print(tree);
        System.out.println(longestUniValuePath(tree));

        tree = TreeUtil.treeOf(new Integer[]{5,4,5,1,1,5}); // 2
        TreeUtil.print(tree);
        System.out.println(longestUniValuePath(tree));

        tree = TreeUtil.treeOf(new Integer[]{1,4,5,4,4,null,5}); // 2
        TreeUtil.print(tree);
        System.out.println(longestUniValuePath(tree));
        tree = TreeUtil.treeOf(new Integer[]{1,2,3}); // 0
        TreeUtil.print(tree);
        System.out.println(longestUniValuePath(tree));
        tree = TreeUtil.treeOf(new Integer[]{5,null,5,null,5,null,5}); // 3
        TreeUtil.print(tree);
        System.out.println(longestUniValuePath(tree));
        tree = TreeUtil.treeOf(new Integer[]{5,5,null,5,null,null}); // 3
        TreeUtil.print(tree);
        System.out.println(longestUniValuePath(tree));
        tree = TreeUtil.treeOf(new Integer[]{5,5,5,5,5,5,5}); // 4
        TreeUtil.print(tree);
        System.out.println(longestUniValuePath(tree));

    }

    // TODO
    private static <T extends Comparable<T>> int longestUniValuePath(IBTree<T> tree) {
        if(tree == null || tree.root() == null) return 0;
        final Stack<BTreeNode<T>> stack = new Stack<>();
        stack.push(tree.root());
        int left= 0, right = left, longest = 0;
        while (!stack.isEmpty()) {
            BTreeNode<T> curr = stack.pop();
            if(curr.left != null && curr.left.data.compareTo(curr.data) == 0) left += 1; else left = 0;
            if(curr.right != null && curr.right.data.compareTo(curr.data) == 0) right += 1; else right = 0;
            longest = Math.max(longest, Math.max(left+right, Math.max(left, right)));
            System.out.println(longest+" "+left+" "+right);
            if(curr.right != null) stack.push(curr.right);
            if(curr.left != null) stack.push(curr.left);
        }

        return longest;
    }

}
