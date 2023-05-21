package net.geekscore.tree.binary.view;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.*;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 *
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as
 * the path from root to the right-most node. If the root doesn't have left subtree or right subtree,
 * then the root itself is left boundary or right boundary. Note this definition only applies to the
 * input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to
 * the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
 *
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * Example 1
 * Input:
 *      1
 *        \
 *         2
 *        / \
 *       3   4
 *
 * Output: [1, 3, 4, 2]
 *
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output
 * reversed right boundary. So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 *
 * Example 2
 * Input:
 *          ____1_____
 *         /          \
 *        2            3
 *       / \          /
 *      4   5        6
 *         / \      / \
 *         7   8   9  10
 *
 * Output:[1,2,4,7,8,9,10,6,3]
 *
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 */
public class BoundaryBinaryTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,null,2,null, null, 3,4});
        TreeUtil.print(tree);
        System.out.println(boundary(tree.root()));
//        System.out.println(bottomViewDFS(tree.root()));
        System.out.println("-----");
        IBTree<Integer> tree1 = TreeUtil.treeOf(new Integer[]{1,2,3,null,4,null,null,null,null,null,5,null,null,null,null,null,null,null,null,null,null,null,6});
        TreeUtil.print(tree1);
        System.out.println(boundary(tree1.root()));
//        System.out.println(bottomViewDFS(tree1.root()));
        System.out.println("-----");
        IBTree<Integer> tree2 = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,6,null,null,null,7,8,9,10});
        TreeUtil.print(tree2);
        System.out.println(boundary(tree2.root()));
//        System.out.println(bottomViewDFS(tree1.root()));
        System.out.println("-----");
    }

    private static Collection<Integer> boundary(BTreeNode<Integer> node) {
        if(null == node) return Collections.emptyList();
        List<Integer> boundaryValues = new LinkedList<>();
        boundaryValues.add(node.data);
        boundaryValues.addAll(leftBoundary(node));
        boundaryValues.addAll(inOrderLeaves(node));
        boundaryValues.addAll(rightBoundary(node));
        return boundaryValues;
    }

    private static Collection<Integer> leftBoundary(BTreeNode<Integer> node) {
        if(null == node) return Collections.emptyList();
        List<Integer> leftBoundaryLeaves = new LinkedList<>();
        BTreeNode<Integer> curr = node.left;
        while(null != curr) {
            if(null != curr.left || null != curr.right) {
                leftBoundaryLeaves.add(curr.data);
            }
            if(null != curr.left) curr = curr.left;
            else curr = curr.right;
        }
//        System.out.println("Left Non leaves" + leftBoundaryLeaves);
        return leftBoundaryLeaves;
    }

    private static Collection<Integer> inOrderLeaves(BTreeNode<Integer> node) {
        if(null == node) return Collections.emptyList();
        List<Integer> inOrderLeaves = new LinkedList<>(inOrderLeaves(node.left));
        if(null == node.left && null == node.right) {
            inOrderLeaves.add(node.data);
        }
        inOrderLeaves.addAll(inOrderLeaves(node.right));
//        System.out.println("inOrderLeaves "+inOrderLeaves);
        return inOrderLeaves;
    }

    private static Collection<Integer> rightBoundary(BTreeNode<Integer> node) {
        if(null == node) return Collections.emptyList();
        List<Integer> rightBoundaryLeaves = new LinkedList<>();
        BTreeNode<Integer> curr = node.right;
        while(null != curr) {
            if(null != curr.right || null != curr.left) {
                rightBoundaryLeaves.add(curr.data);
            }
            if(null != curr.right) curr = curr.right;
            else curr = curr.left;
        }
         Collections.reverse(rightBoundaryLeaves);
        //        System.out.println("Right Non leaves" + rightBoundaryLeaves);
        return rightBoundaryLeaves;
    }

}
