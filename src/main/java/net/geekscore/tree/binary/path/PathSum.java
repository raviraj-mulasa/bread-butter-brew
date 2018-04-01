package net.geekscore.tree.binary.path;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that
 * adding up all the values along the path equals the given sum.
 *
 * For example:
 * Given the below binary tree and sum = 22,
 *              5
 *             / \
 *            4   8
 *           /   / \
 *          11  13  4
 *        /  \      \
 *      7    2      1
 *  return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,null,null,1});
        TreeUtil.print(tree);
        System.out.println(hasPathSum(tree.root(), 22)); // true
        System.out.println(hasPathSum(tree.root(), 12)); // false
        System.out.println(hasPathSum(tree.root(), 21)); // false
        System.out.println(hasPathSum(tree.root(), 26)); // true

        tree = TreeUtil.treeOf(new Integer[]{5,4,8,11,null,13,4,6,1,null,null,null,null,null,0});
        TreeUtil.print(tree);
        System.out.println(hasPathSum(tree.root(), 22)); // false
        System.out.println(hasPathSum(tree.root(), 12)); // false
        System.out.println(hasPathSum(tree.root(), 21)); // true
        System.out.println(hasPathSum(tree.root(), 26)); // true
    }

    private static  boolean hasPathSum(final BTreeNode<Integer> node, int sum) {
        if (node == null) return false;
        sum -= node.data;
        return (node.left == null && node.right == null && sum == 0) || hasPathSum(node.left, sum) || hasPathSum(node.right, sum);
    }
}
