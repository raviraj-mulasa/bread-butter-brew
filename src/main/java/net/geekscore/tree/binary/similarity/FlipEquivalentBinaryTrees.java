package net.geekscore.tree.binary.similarity;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

/**
 * Flip operation for a binary tree:
 * “Choosing any node and swapping the right and left child subtrees.”
 * A binary tree, T, is flip equivalent to another binary tree, S, if we can make T equal to S after some number of flip operations.
 *
 * Given the roots of two binary trees, root1 and root2,
 * you have to find out whether the trees are flip equivalent to each other or not.
 * The flipEquiv function should return True if the binary trees are equivalent. Otherwise, it will return False.
 *
 *
 *              1
 *            /   \
 *           2     3
 *          / \   / \
 *        4   5 6   9
 *           / \
 *          7  8
 *
 *        to
 *              1
 *            /   \
 *           3     2
 *          / \   / \
 *        9   6  4  5
 *                 / \
 *                8  7
 *
 * Flipping the tree1 nodes with the values 1, 3, and 5 generates tree2.
 * Both the trees are binary equivalent because we generated tree2 from tree1 by applying three flip operations.
 * Observe the node with the value 2 since it has not flipped
 */
public class FlipEquivalentBinaryTrees {

    public static void main(String[] args) {
        final IBTree<Integer> btree1 = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,6,9,null, null, 7,8});
        TreeUtil.print(btree1);
        System.out.println("----");
        final IBTree<Integer> btree2 = TreeUtil.treeOf(new Integer[]{1,3,2,9,6,4,5, null, null,null, null,null, null, 8, 7});
        TreeUtil.print(btree2);
        System.out.println(flipEquiv(btree1.root(), btree2.root()));
    }

    public static boolean flipEquiv(BTreeNode<Integer> root1, BTreeNode<Integer> root2) {
        if(root1 == root2) {
            return true;
        }
        if(root1 == null || root2 == null || root1.data.compareTo(root2.data)!=0) {
            return false;
        }
            return (flipEquiv(root1.left, root2.left) &&
                    flipEquiv(root1.right, root2.right)) ||
                    (flipEquiv(root1.right, root2.left) &&
                            flipEquiv(root1.left, root2.right));

        }
}
