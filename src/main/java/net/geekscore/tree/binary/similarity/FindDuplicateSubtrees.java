package net.geekscore.tree.binary.similarity;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.IBTree;

/**
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees,
 * you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with same node values.
 * Example 1:
 *          1
 *         / \
 *        2   3
 *       /   / \
 *      4   2   4
 *          /
 *          4
 * The following are two duplicate subtrees:
 *  2
 * /
 * 4
 * and
 * 4
 */
public class FindDuplicateSubtrees {

    public static void main(String[] args) {
        final IBTree<Integer> btree = TreeUtil.treeOf(new Integer[]{1,2,3,4,null,2,4,null,null,null,null,4,null,null,null});
        TreeUtil.print(btree);
    }

}
