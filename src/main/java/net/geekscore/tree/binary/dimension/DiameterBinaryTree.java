package net.geekscore.tree.binary.dimension;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *            1
 *           / \
 *          2   3
 *         / \
 *        4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterBinaryTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,null,null});
        TreeUtil.print(tree);

        System.out.println("Height "+height(tree.root())); // 3
        System.out.println("Diameter "+diameter(tree.root())); // 3
    }

    private static int diameter(final BTreeNode<Integer> node) {
        if(node == null) return 0;
        final int leftHeight = height(node.left); // height of left subtree
        final int rightHeight = height(node.right); // height of right subtree
        final int leftDiameter = diameter(node.left); // diameter of left subtree
        final int rightDiameter = diameter(node.right); // diameter of right subtree
        // diameter is the
        // max diameter of left or right subtrees when root is not included
        // or
        // sum of the heights of left and right subtrees when root is included
        return Math.max(leftHeight+rightHeight, Math.max(leftDiameter, rightDiameter));
    }

    private static int height(final BTreeNode<Integer> node) {
        if(node == null) return 0;
        return Math.max(height(node.left), height(node.right))+1;
    }

    private static int diameterBFS(final BTreeNode<Integer> node) {
        return 0;
    }
}
