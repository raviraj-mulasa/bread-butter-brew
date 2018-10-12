package net.geekscore.tree.binary.dimension;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 *  Given a binary tree, write a function to get the maximum width of the given tree.
 *  The width of a tree is the maximum width among all levels.
 *  The binary tree has the same structure as a full binary tree, but some nodes are null.
 *
 *  The width of one level is defined as the length between the end-nodes
 *  (the leftmost and right most non-null nodes in the level,
 *  where the null nodes between the end-nodes are also counted into the length calculation).
 *
 * Example 1:
 * Input:
 *
 *          1
 *        /   \
 *       3     2
 *      / \     \
 *     5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 *
 * Example 2:
 * Input:
 *      1
 *     /
 *    3
 *   / \
 *  5   3
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 *
 * Example 3:
 * Input:
 *      1
 *     / \
 *    3   2
 *   /
 *   5
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 *
 * Example 4:
 * Input:
 *              1
 *             / \
 *            3   2
 *           /     \
 *          5       9
 *         /         \
 *        6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length
 * 8 (6,null,null,null,null,null,null,7).
 */
public class MaxWidthBinaryTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,3,2,5,null,null,null});
        TreeUtil.print(tree);
        System.out.println("Max Width "+maxWidth(tree.root()));

        System.out.println("---------");

        IBTree<Integer> tree1 = TreeUtil.treeOf(new Integer[]{1,3,2,5,null,null,9,6,null,null,null,null,null,null,7});
        TreeUtil.print(tree1);
        System.out.println("Max Width "+maxWidth(tree1.root()));


        System.out.println("---------");

        IBTree<Integer> tree2 = TreeUtil.treeOf(new Integer[]{1,3,2,null,null,null,5});
        TreeUtil.print(tree2);
        System.out.println("Max Width "+maxWidth(tree2.root()));

        System.out.println("--------");
        final IBTree<Integer> tree4 = TreeUtil.treeOf(new Integer[]{1,2,null,15,null,null,null,16});
        TreeUtil.print(tree4);
        System.out.println("Max Width "+maxWidth(tree4.root()));

        System.out.println("--------");
        final IBTree<Integer> tree3 = TreeUtil.treeOf(new Integer[]{1,2,null,15,null,null,null,16,17});
        TreeUtil.print(tree3);
        System.out.println("Max Width "+maxWidth(tree3.root()));
    }

    private static int maxWidth(BTreeNode<Integer> root) {
        if(root == null) return 0;
        final Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            System.out.println(queue);
            final int size = queue.size();
            boolean hasLeft = false;
            int width = 0;
            for (int i = 0; i < size; i++) {
                final BTreeNode<Integer> top = queue.removeLast();
                if(top == null) {
                    if(hasLeft) {
                        queue.offerFirst(null);
                        queue.offerFirst(null);
                        width+=2;
                    }
                    continue;
                }
                if(top.left == null && top.right == null) continue;
                if(top.left != null) {
                    hasLeft = true;
                    width+=1;
                }
                if(top.right != null) {
                    width+=1;
                    queue.offerFirst(top.right);
                }
                queue.offerFirst(top.left);
                if(!hasLeft)queue.offerFirst(null);
            }
            if(queue.peek() != null) {
                maxWidth = Math.max(maxWidth, queue.size());
            } else {
                maxWidth = Math.max(maxWidth, width);
            }
        }
        return maxWidth;
    }
}
