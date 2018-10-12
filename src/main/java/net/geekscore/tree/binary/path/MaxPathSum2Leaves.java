package net.geekscore.tree.binary.path;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

/**
 * Given a binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any
 node in the tree along the parent-child connections.
 The path must contain at least one node and does not need to go through the root.
 */
public class MaxPathSum2Leaves {

    private static class BinaryTreeMaximumPathSum {
        int max = 0;

        private int maxPathSum(BTreeNode<Integer> root) {
            max = Integer.MIN_VALUE;
            maxPathSumUtil(root);
            return max;
        }

        private int maxPathSumUtil(BTreeNode<Integer> root) {
            if (root == null) return 0;
            int left = Math.max(maxPathSumUtil(root.left), 0);
            int right = Math.max(maxPathSumUtil(root.right), 0);
            max = Math.max(max, root.data+left+right);
            final int maxChild = Math.max(left, right);
            return Math.max(root.data + maxChild, root.data); // root is in the path or not
        }
    }


    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]
                {15,5,6,11,-8,1,3,9,2,6});
        TreeUtil.print(tree);
        BinaryTreeMaximumPathSum maxPathSum = new BinaryTreeMaximumPathSum();
        System.out.println("Max: "+maxPathSum.maxPathSum(tree.root()));

        System.out.println("----------");
        tree = TreeUtil.treeOf(new Integer[]
                {1,-2,-3,1,3,-2,null,-1});
        TreeUtil.print(tree);
        maxPathSum = new BinaryTreeMaximumPathSum();
        System.out.println("Max: "+maxPathSum.maxPathSum(tree.root())); // 3


        System.out.println("----------");
        tree = TreeUtil.treeOf(new Integer[]
                {-2,-1});
        TreeUtil.print(tree);
        maxPathSum = new BinaryTreeMaximumPathSum();
        System.out.println("Max: "+maxPathSum.maxPathSum(tree.root())); // -1

        System.out.println("----------");
        tree = TreeUtil.treeOf(new Integer[]
                {-3});
        TreeUtil.print(tree);
        maxPathSum = new BinaryTreeMaximumPathSum();
        System.out.println("Max: "+maxPathSum.maxPathSum(tree.root())); // -1

        System.out.println("----------");
        tree = TreeUtil.treeOf(new Integer[]
                {2,-1});
        TreeUtil.print(tree);
        maxPathSum = new BinaryTreeMaximumPathSum();
        System.out.println("Max: "+maxPathSum.maxPathSum(tree.root())); // 2

        System.out.println("----------");
        tree = TreeUtil.treeOf(new Integer[]
                {-1,2});
        TreeUtil.print(tree);
        maxPathSum = new BinaryTreeMaximumPathSum();
        System.out.println("Max: "+maxPathSum.maxPathSum(tree.root())); // 2

        System.out.println("----------");
        tree = TreeUtil.treeOf(new Integer[]
                {1,-2,-3,1,3,-2,null,-1});
        TreeUtil.print(tree);
        maxPathSum = new BinaryTreeMaximumPathSum();
        System.out.println("Max: "+maxPathSum.maxPathSum(tree.root())); // 3
    }
}
