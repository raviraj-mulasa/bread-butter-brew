package net.geekscore.tree.binary.path;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

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
            max = Math.max(max, root.data + left + right);
            return root.data + Math.max(left, right);
        }
    }


    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]
                {15,5,6,11,-8,1,3,9,2,6});
        TreeUtil.print(tree);

        System.out.println("----------");
        final BinaryTreeMaximumPathSum maxPathSum = new BinaryTreeMaximumPathSum();
        System.out.println(maxPathSum.maxPathSum(tree.root()));
    }
}
