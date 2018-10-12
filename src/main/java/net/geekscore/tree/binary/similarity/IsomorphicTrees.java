package net.geekscore.tree.binary.similarity;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeImpl;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

/**
 * Write a function to detect if two trees are isomorphic.
 * Two trees are called isomorphic if one of them can be obtained from other by a series of flips,
 * i.e. by swapping left and right children of a number of nodes.
 *
 * Any number of nodes at any level can have their children swapped. Two empty trees are isomorphic.
 *
 *        1
 *       / \
 *      2   3
 *     / \  /
 *    4   5 6
 *       / \
 *       7 8
 *
 *        1
 *       / \
 *      3   2
 *      \  / \
 *       6 4  5
 *           / \
 *           8  7
 *
 */
public class IsomorphicTrees {

    public static void main(String[] args) {
        IBTree<Integer> tree1 = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,6,null,null,null,7,8,null,null,null,null});
        TreeUtil.print(tree1);

        IBTree<Integer> tree2 = TreeUtil.treeOf(new Integer[]{1,3,2,null,6,4,5,null,null,null,null,null,null,8,7});
        TreeUtil.print(tree2);

        System.out.println(isomorphic(tree1, tree2));

        IBTree<Integer> tree3 = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,6,null,null,null,7,8,-1,-2,null,null,});
        TreeUtil.print(tree3);

        System.out.println(isomorphic(tree1, tree3));

        System.out.println(isomorphic(new BTreeImpl<>(null), new BTreeImpl<>(null)));
    }


    private static final boolean isomorphic(final IBTree<Integer> tree1, final IBTree<Integer> tree2) {
        return isomorphic(tree1.root(), tree2.root());
    }

    private static boolean isomorphic(final BTreeNode<Integer> node1, final BTreeNode<Integer> node2) {
        if(node1 == node2) return true;
        if(node1 == null || node2 == null) return false;
        // There are two possible cases for n1 and n2 to be isomorphic
        // Case 1: The subtrees rooted at these nodes have NOT been "Flipped".
        // Both of these subtrees have to be isomorphic, hence the &&
        // Case 2: The subtrees rooted at these nodes have been "Flipped"
        return ((isomorphic(node1.left, node2.left) && isomorphic(node1.right, node2.right))
                || (isomorphic(node1.left, node2.right) && isomorphic(node1.right, node2.left)))
                && node1.data.compareTo(node2.data) == 0;

    }



}
