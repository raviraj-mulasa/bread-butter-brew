package net.geekscore.tree.binary.dimension;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * HEIGTH of the TREE
 *
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *              3
 *             / \
 *            9  20
 *              /  \
 *             15   7
 * return its depth = 3.
 */
public class MaxDepthBinaryTree {

    public static void main(String[] args) {
        final IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{8,3,10,1,6,null,14,null,null,4,7,null,null,13,null});
        TreeUtil.print(tree);
        System.out.println("Height "+ maxDepth(tree.root())); // 4
        System.out.println("Height "+ maxDepthBFS(tree.root())); // 4

        System.out.println("-----");

        final IBTree<Integer> tree1 = TreeUtil.treeOf(new Integer[]{1,2,3,null,null,15,7});
        TreeUtil.print(tree1);
        System.out.println("Height "+ maxDepth(tree1.root())); // 3
        System.out.println("Height "+ maxDepthBFS(tree1.root())); // 3

        System.out.println("-----");

        final IBTree<Integer> tree2 = TreeUtil.treeOf(new Integer[]{1,2,3,15,7,null,null});
        TreeUtil.print(tree2);
        System.out.println("Height "+ maxDepth(tree2.root())); // 3
        System.out.println("Height "+ maxDepthBFS(tree2.root())); // 3

        System.out.println("-----");

        final IBTree<Integer> tree3 = TreeUtil.treeOf(new Integer[]{1,2,null,15,null,null,null,16});
        TreeUtil.print(tree3);
        System.out.println("Height "+ maxDepth(tree3.root())); // 3
        System.out.println("Height "+ maxDepthBFS(tree3.root())); // 3
    }

    private static int maxDepth(final BTreeNode<Integer> node) {
        if(node == null) return 0;
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    private static int maxDepthBFS(final BTreeNode<Integer> node) {
        if(node == null) return 0;
        int level = 0;
        final Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(node);
        queue.offerFirst(null); // level delimiter
        while (!queue.isEmpty()) {
            final BTreeNode<Integer> top = queue.removeLast();
            if(null == top) {
                if(!queue.isEmpty()) {
                    // there are still nodes at previous level to process
                    queue.offerFirst(null); // level delimiter
                }
                level++;
                continue;
            }
            if(top.left != null) queue.offerFirst(top.left);
            if(top.right != null) queue.offerFirst(top.right);
        }
        return level;
    }
}
