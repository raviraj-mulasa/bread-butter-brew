package net.geekscore.tree.binary.view;

/**
 * Top view of a binary tree is the set of nodes visible when the tree is viewed from the top.
 * Given a binary tree, print the top view of it. The output nodes can be printed in any order.
 * Expected time complexity is O(n)
 *
 *
 *          1
 *       /   \
 *      2     3
 *    /  \   / \
 *   4    5 6   7
 *
 *  Top view of the above binary tree is 4 2 1 3 7
 *            1
 *          /   \
 *          2       3
 *          \
 *           4
 *            \
 *             5
 *              \
 *               6
 * Top view of the above binary tree is 2 1 3 6

 *
 * The idea is to do something similar to vertical Order Traversal.
 * Like vertical Order Traversal, we need to nodes of same horizontal distance together.
 * We do a level order traversal so that the topmost node at a horizontal node is visited before
 * any other node of same horizontal distance below it.
 * Hashing is used to check if a node at given horizontal distance is seen or not.
 */
public class TopViewBinaryTree {
}
