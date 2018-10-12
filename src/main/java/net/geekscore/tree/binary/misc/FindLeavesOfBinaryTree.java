package net.geekscore.tree.binary.misc;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, collect a tree's nodes as if you were doing this:
 * Collect and remove all leaves, repeat until the tree is empty.
 *
 * Example:
 * Given binary tree
 *      1
 *     / \
 *    2   3
 *  / \
 * 4   5
 * Returns [4, 5, 3], [2], [1].
 *
 * Explanation:
 *  1. Removing the leaves [4, 5, 3] would result in this tree:
 *      1
 *      /
 *      2
 *  2. Now removing the leaf [2] would result in this tree:
 *      1
 *  3. Now removing the leaf [1] would result in the empty tree:
 *  []
 * Returns [4, 5, 3], [2], [1].


 */
public class FindLeavesOfBinaryTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,3,2,5,3,null,9});
        TreeUtil.print(tree);
        System.out.println(removeLeaves(tree.root())); //        [[5,3,9], [3,2] [1]]

        System.out.println("-------");

        tree = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,null,null});
        TreeUtil.print(tree);
        System.out.println(removeLeaves(tree.root())); //        [[4, 5, 3], [2], [1]]

    }

    /* O(n^2) time */
    private static List<List<Integer>> removeLeaves(BTreeNode<Integer> root) {
        List<List<Integer>> result = new LinkedList<>();
        while (root != null) {
            final List<Integer> leaves = new LinkedList<>();
            if(isLeaf(root, leaves)) root = null; // Is sub-tree at this root has all leaves ?
            result.add(leaves);
        }
        return result;
    }

    private static boolean isLeaf(BTreeNode<Integer> node, List<Integer> leaves) {
        if(node == null) return false; // no tree return false
        if(node.left == null && node.right == null) { // AmI  a leaf ?
            leaves.add(node.data);
            return true;
        }
        if(isLeaf(node.left, leaves)) node.left = null; // Is my left child a leaf ?
        if(isLeaf(node.right,leaves)) node.right = null; // Is my right child a leaf ?
        return false;
    }



}
