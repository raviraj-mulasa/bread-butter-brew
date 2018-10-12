package net.geekscore.tree.binary.dimension;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 *Given a binary tree, write a function to get the maximum width of the given tree.
 * Width of a tree is maximum of widths of all levels.
 *
 * Let us consider the below example tree.
 *          1
 *        /  \
 *       2    3
 *     /  \     \
 *    4    5     8
 *  /  \
 * 6    7
 *
 * For the above tree,
 * width of level 1 is 1,
 * width of level 2 is 2,
 * width of level 3 is 3
 * width of level 4 is 2.

 So the maximum width of the tree is 3.
 */
public class MaxWidthBinaryTree2 {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,null,8,6,7,null,null,null,null,null,null});
        TreeUtil.print(tree);
        System.out.println("Max Width "+maxWidth(tree.root()));

        System.out.println("--------");
        final IBTree<Integer> tree2 = TreeUtil.treeOf(new Integer[]{1,2,null,15,null,null,null,16});
        TreeUtil.print(tree2);
        System.out.println("Max Width "+maxWidth(tree2.root()));

        System.out.println("--------");
        final IBTree<Integer> tree3 = TreeUtil.treeOf(new Integer[]{1,2,null,15,null,null,null,16,17});
        TreeUtil.print(tree3);
        System.out.println("Max Width "+maxWidth(tree3.root()));
    }

    private static final int maxWidth(BTreeNode<Integer> root) {
        if(root == null) return 0;
        final Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            final int size = queue.size();
            maxWidth = Math.max(size, maxWidth);
            for (int i = 0; i < size; i++) {
                final BTreeNode<Integer> top = queue.removeLast();
                if(top.left != null) queue.offerFirst(top.left);
                if(top.right != null) queue.offerFirst(top.right);
            }
        }
        return maxWidth;
    }

}
