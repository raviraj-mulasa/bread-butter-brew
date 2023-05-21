package net.geekscore.tree.binary.view;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.*;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * For example:
 * Given the following binary tree,
 *          1            <---
 *        /   \
 *       2     3         <---
 *       \     \
 *       5     4         <---
 *
 *  You should return [1, 3, 4].
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
 * You should return [1, 3, 7, 9].
 *
 *          0
 *         / \
 *        1  2
 *       / \
 *      3  4
 *         /
 *        5
 *
 * You should return [0, 2, 4, 9].
 *
 * We can use a depth-first search (DFS) to solve this problem.
 * The intuition here is to traverse the tree level by level recursively, starting from the rightmost node for each recursive call.
 *
 */
public class RightSideViewBinaryTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,2,3,null,5,null,4});
        TreeUtil.print(tree);
        System.out.println("-----");
        System.out.println(rightSideViewBFS(tree.root()));
        System.out.println("-----");
        System.out.println(rightSideViewBFS1(tree.root()));
        System.out.println("-----");
        IBTree<Integer> tree1 = TreeUtil.treeOf(new Integer[]{1,2,3,4,5,6,7,null,null,8,null,null,null,9,null});
        TreeUtil.print(tree1);
        System.out.println("-----");
        System.out.println(rightSideViewBFS(tree1.root()));
        System.out.println("-----");
        System.out.println(rightSideViewBFS1(tree1.root()));
        System.out.println("-----");
        IBTree<Integer> tree2 = TreeUtil.treeOf(new Integer[]{0,1,2,3,4,null,null,null,null,5});
        TreeUtil.print(tree2);
        System.out.println("-----");
        System.out.println(rightSideViewBFS(tree2.root()));
        System.out.println("-----");
        System.out.println(rightSideViewBFS1(tree2.root()));
        System.out.println("-----");
    }

    private static Collection<Integer> rightSideViewBFS(BTreeNode<Integer> node) {
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
                if(i == 0){
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


    private static Collection<Integer> rightSideViewBFS1(BTreeNode<Integer> node) {
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
                if(size - 1 == i){
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

    private static Collection<Integer> rightSideViewDFS(BTreeNode<Integer> node) {
        if(null == node) return Collections.emptyList();
        List<Integer> rightSideElems = new LinkedList<>();
        if(null != node.right) {
            rightSideElems.addAll(rightSideViewDFS(node.right));
        }
        if(null != node.right && null == node.left) {
            rightSideElems.add(node.data);
//            rightSideElems.addAll(rightSideView(node.left));
        }
//        rightSideElems.add(node.data);
        if(null != node.left) {
            rightSideElems.addAll(rightSideViewDFS(node.left));
        }
//        if(null != node.right && null == node.left) {
//            rightSideElems.add(node.data);
//        }

//        rightSideElems.add(node.data);
        return rightSideElems;
    }
}
