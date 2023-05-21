package net.geekscore.tree.binary.path;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

/**
 * A path in a binary tree is defined as:
 *
 * A sequence of nodes such that each pair of adjacent nodes must have an edge connecting them.
 * A node can only be included in a path at most once. Moreover, including the root in the path is not compulsory.
 *
 * The path sum can be calculated by adding up all nodes’ values in the path.
 * To solve this problem, we will calculate the maximum path sum given the root of a binary tree so that there won’t be any greater path than it in the tree.
 *
 *              -8
 *             /  \
 *            2   17
 *          / \  / \
 *         1  4 19 5
 *  Maximum path: 19 -> 17 -> 5
 *  Max Total Sum : 41
 *
 *
 *               7
 *             /  \
 *            3   4
 *              / \
 *            -1 -1
 *  Maximum path: 3 -> 7 -> 4
 *  Max Total Sum : 14
 *
 */
public class BinaryTreeMaxPathSum {

    private static int maxPathSum = Integer.MIN_VALUE;
    public static void main(String[] args) {
        final IBTree<Integer> btree1 = TreeUtil.treeOf(new Integer[]{-8,2,17,1,4,19,5});
        TreeUtil.print(btree1);
        System.out.println("MaxPathSum "+maxPathSum(btree1.root()));
        System.out.println("----");
        final IBTree<Integer> btree2 = TreeUtil.treeOf(new Integer[]{7,3,4,null,null,-1,-1});
        TreeUtil.print(btree2);
        System.out.println("----");
        System.out.println("MaxPathSum "+maxPathSum(btree2.root()));
        IBTree<Integer> btree3 = TreeUtil.treeOf(new Integer[]{15,5,6,11,-8,1,3,9,2,6});
        TreeUtil.print(btree3);
        System.out.println("MaxPathSum "+maxPathSum(btree3.root()));}

    private static int maxPathSum(BTreeNode<Integer> root) {
        maxContrib(root);
        int temp = maxPathSum;
        maxPathSum = Integer.MIN_VALUE;
        return temp;
    }

    private static int maxContrib(BTreeNode<Integer> node) {
        if(null == node) {
            return 0;
        }
        int leftContribution = Math.max(maxContrib(node.left), 0);
        int rightContribution = Math.max(maxContrib(node.right), 0);

        int nodeContribution = node.data + leftContribution + rightContribution;
        System.out.println("Node value "+node.data+" Node "+nodeContribution+" Max "+maxPathSum);
        maxPathSum = Math.max(maxPathSum, nodeContribution);

        return node.data + Math.max(leftContribution, rightContribution);
    }
}