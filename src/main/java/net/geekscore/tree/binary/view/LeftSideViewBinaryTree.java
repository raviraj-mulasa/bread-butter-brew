package net.geekscore.tree.binary.view;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Given a Binary Tree, the task is to print the left view of the Binary Tree.
 * The left view of a Binary Tree is a set of leftmost nodes for every level.
 *                   4
 *                 /
 *               5
 *                \
 *                 2
 *                 \
 *                 7
 *
 * Output: 4 5 3 7
 *
 *          1
 *       /   \
 *     2       3
 *       \
 *        4
 *          \
 *           5
 *             \
 *              6
 *
 *
 * Output: 1 2 10
 *
 * For example:
 * Given the following binary tree,
 *          1            <---
 *        /   \
 *       2     3         <---
 *       \     \
 *       5     4         <---
 *
 *  You should return [1, 2, 5].
 *
 *
 *                  1
 *                /  \
 *                2  3
 *             / \   / \
 *           4   5   6  7
 *              /       /
 *             8        9
 *
 * You should return [1, 2, 4, 8].
 *
 *          0
 *         / \
 *        1  2
 *       / \
 *      3  4
 *         /
 *        5
 *
 * You should return [0, 1, 3, 5].
 */
public class LeftSideViewBinaryTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,2,3,null,5,null,4});
        TreeUtil.print(tree);
        System.out.println("-----");
        System.out.println(leftSideViewBFS(tree.root()));
        System.out.println("-----");
        System.out.println(leftSideViewBFS1(tree.root()));
        System.out.println("-----");
        IBTree<Integer> tree1 = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,6,7,null,null,8,null,null,null,9,null});
        TreeUtil.print(tree1);
        System.out.println("-----");
        System.out.println(leftSideViewBFS(tree1.root()));
        System.out.println("-----");
        System.out.println(leftSideViewBFS1(tree1.root()));
        System.out.println("-----");
        IBTree<Integer> tree2 = TreeUtil.treeOf(new Integer[]{0,1,2,3,4,null,null,null,null,5});
        TreeUtil.print(tree2);
        System.out.println("-----");
        System.out.println(leftSideViewBFS(tree2.root()));
        System.out.println("-----");
        System.out.println(leftSideViewBFS1(tree2.root()));
        System.out.println("-----");
        IBTree<Integer> tree3 = TreeUtil.treeOf(new Integer[]{4,5,null,null,2,null,null,3,1,null,7});
        TreeUtil.print(tree3);
        System.out.println("-----");
        System.out.println(leftSideViewBFS(tree3.root()));
        System.out.println("-----");
        System.out.println(leftSideViewBFS1(tree3.root()));
        System.out.println("-----");
    }

    private static Collection<Integer> leftSideViewBFS(BTreeNode<Integer> node) {
        if(null == node) return Collections.emptyList();
        Collection<Integer> rightView = new LinkedList<>();
        Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
//            System.out.println("XXXX  Queue "+queue);
//            System.out.println("yyyy  Size "+size+" Queue First "+queue.peekFirst()+ " Queue Last "+queue.peekLast());
            for (int i = 0; i < size; i++) {
                BTreeNode<Integer> curr = queue.removeLast();
                if(i == size-1){
                    rightView.add(curr.data);
                }
                if(curr.right != null) {
                    queue.offerFirst(curr.right);
                }
                if(curr.left != null) {
                    queue.offerFirst(curr.left);
                }
            }
        }
        return rightView;
    }


    private static Collection<Integer> leftSideViewBFS1(BTreeNode<Integer> node) {
        if(null == node) return Collections.emptyList();
        Collection<Integer> rightView = new LinkedList<>();
        Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
//            System.out.println("XXXX  Queue "+queue);
//            System.out.println("yyyy  Size "+size+" Queue First "+queue.peekFirst()+ " Queue Last "+queue.peekLast());
            for (int i = 0; i < size; i++) {
                BTreeNode<Integer> curr = queue.removeLast();
                if(0 == i){
                    rightView.add(curr.data);
                }
                if(curr.left != null) {
                    queue.offerFirst(curr.left);
                }
                if(curr.right != null) {
                    queue.offerFirst(curr.right);
                }
            }
        }
        return rightView;
    }
}
