package net.geekscore.tree.binary.bst;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Given a Binary Search Tree (BST) with the root node root,
 * return the minimum difference between the values of any two different nodes in the tree.
 *
 * Example :
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *          4
 *        /   \
 *      2      6
 *     / \
 *    1   3
 *
 * while the minimum difference in this tree is 1,
 * it occurs between node 1 and node 2,
 * also between node 3 and node 2.
 */
public class MiniDistanceBetweenBSTNodes {

    public static void main(String[] args) {
        IBTree<Integer> bst = TreeUtil.treeOf(new Integer[]{4,2,6,1,3,null,null});
        TreeUtil.print(bst);
        System.out.println(minDiffInBST(bst.root())); // 1
    }

    private static int minDiffInBST(BTreeNode<Integer> root) {
        if(root == null) return 0;
        int minDiff = Integer.MAX_VALUE;

        BTreeNode<Integer> curr = root, prev = null;
        final Deque<BTreeNode<Integer>> stack = new LinkedList<>();
        stack.push(curr);

        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if(prev != null && curr != prev) {
                minDiff = Math.min(minDiff, Math.abs(prev.data - curr.data));
            }
            prev = curr;
            curr = curr.right;
        }
        return minDiff;
    }
}
