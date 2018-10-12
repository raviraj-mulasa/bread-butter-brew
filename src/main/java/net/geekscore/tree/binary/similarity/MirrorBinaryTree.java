package net.geekscore.tree.binary.similarity;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeImpl;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *          1
 *         / \
 *        2   2
 *       / \ / \
 *      3  4 4  3
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *          1
 *         / \
 *        2   2
 *        \   \
 *        3    3
 */
public class MirrorBinaryTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,null,5,1});
        TreeUtil.print(tree);
        IBTree<Integer> mirrorTree = new BTreeImpl<Integer>(mirror(tree.root()));
        TreeUtil.print(mirrorTree);

        tree = TreeUtil.treeOf(new Integer[]{1,2,3,4,5});
        TreeUtil.print(tree);
        mirrorTree = new BTreeImpl<Integer>(mirror(tree.root()));
        TreeUtil.print(mirrorTree);

        tree = TreeUtil.treeOf(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,null,5,1});
        TreeUtil.print(tree);
        mirrorTree  = new BTreeImpl<Integer>(mirrorBFS(tree.root()));
        TreeUtil.print(mirrorTree);

        tree = TreeUtil.treeOf(new Integer[]{1,2,3,4,5});
        TreeUtil.print(tree);
        mirrorTree = new BTreeImpl<Integer>(mirrorBFS(tree.root()));
        TreeUtil.print(mirrorTree);

        tree = TreeUtil.treeOf(new Integer[]{1,2,3,4,5});
        TreeUtil.print(tree);
        mirrorTree = new BTreeImpl<Integer>(mirrorBFS(tree.root()));
        TreeUtil.print(mirrorTree);

        tree = TreeUtil.treeOf(new Integer[]{1,2,2,3,4,4,3});
        TreeUtil.print(tree);
        System.out.println(isMirror(tree.root())); // true

        tree = TreeUtil.treeOf(new Integer[]{1,2,2,null,3,null,3});
        TreeUtil.print(tree);
        System.out.println(isMirror(tree.root())); // false
    }

    private static final BTreeNode<Integer> mirror(BTreeNode<Integer> root) {
        if(null == root) return null;
        mirror(root.right);
        mirror(root.left);
        BTreeNode<Integer> temp  = root.right;
        root.right = root.left;
        root.left = temp;
        return root;
    }

    private static final BTreeNode<Integer> mirrorBFS(BTreeNode<Integer> root) {
        if(null == root) return null;
        final Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(root);
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final BTreeNode<Integer> node = queue.removeLast();
                if(null != node) {
                    BTreeNode<Integer> temp = node.left;
                    node.left = node.right;
                    node.right = temp;
                    queue.offerFirst(node.left);
                    queue.offerFirst(node.right);
                }
            }
        }
        return root;
    }

    private static final boolean isMirror(BTreeNode<Integer> root) {
        return root == null || isMirror(root.left, root.right);
    }

    private static final boolean isMirror(BTreeNode<Integer> left, BTreeNode<Integer> right) {
        return (left != null || right == null) && (left == null || right != null) && (left == right || (isMirror(left.left, right.right) && isMirror(right.left, left.right)));

    }

}
