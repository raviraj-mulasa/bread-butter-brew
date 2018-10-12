package net.geekscore.tree.binary.dimension;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the
 * nearest leaf node.
 *
 * Example 1:
 * Input:
 *
 *          1
 *        /   \
 *       3     2
 *      / \
 *     5   3
 *
 * Output: 2
 */
public class MinDepthBinaryTree {

    public static void main(String[] args) {

        final IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{8,3,10,1,6,null,14,null,null,4,7,null,null,13,null});
        TreeUtil.print(tree);
        System.out.println("Min Depth "+ minDepth(tree.root())); // 3
        System.out.println("Min Depth "+ minDepthBFS(tree.root())); // 3

        System.out.println("-----");

        final IBTree<Integer> tree1 = TreeUtil.treeOf(new Integer[]{1,3,2,5,3,null,null});
        TreeUtil.print(tree1);
        System.out.println("Min Depth "+ minDepth(tree1.root())); // 2
        System.out.println("Min Depth "+ minDepthBFS(tree1.root())); // 2

        System.out.println("-----");

        final IBTree<Integer> tree2 = TreeUtil.treeOf(new Integer[]{1});
        TreeUtil.print(tree2);
        System.out.println("Min Depth "+ minDepth(tree2.root())); // 1
        System.out.println("Min Depth "+ minDepthBFS(tree2.root())); // 1
    }

    private static int minDepth(final BTreeNode<Integer> root){
        if(null == root) return 0;
        if(root.right == root.left) return 1;
        if(null == root.left) return minDepth(root.right) + 1;
        if(null == root.right) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    private static int minDepthBFS(final BTreeNode<Integer> root){
        if(null == root) return 0;
        int level = 1;
        final Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(root);
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final BTreeNode<Integer> top = queue.removeLast();
                if(top.left == null && top.right == null) return level;
                if(top.left != null) queue.offerFirst(top.left);
                if(top.right != null) queue.offerFirst(top.right);
            }
            level++;
        }
        return level;
    }
}
