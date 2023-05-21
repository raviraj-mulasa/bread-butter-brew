package net.geekscore.tree.binary.view;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.*;

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

    public static void main(String[] args) {

        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{20,8,22,5,3,null,25,null,null,10,14});
        TreeUtil.print(tree);
//        System.out.println(bottomViewBFS(tree.root()));
        System.out.println(topViewDFS(tree.root()));
        System.out.println("-----");

        IBTree<Integer> tree1 = TreeUtil.treeOf(new Integer[]{1,2,3,null,4,null,null,null,null,null,5,null,null,null,null,null,null,null,null,null,null,null,6});
        TreeUtil.print(tree1);
//        System.out.println(bottomViewBFS(tree.root()));
        System.out.println(topViewDFS(tree1.root()));
        System.out.println("-----");
    }

    private static Collection<BTreeNode<Integer>> topViewDFS(BTreeNode<Integer> node) {
        if(null == node) Collections.emptyList();
        SortedMap<Integer, BTreeNode<Integer>> verticalOrderMap = new TreeMap<>();
        topViewDFSHelper(node, 0, verticalOrderMap);
        return verticalOrderMap.values();
    }
    private static void topViewDFSHelper(
            BTreeNode<Integer> node
            , int verticalOrder
            ,  Map<Integer, BTreeNode<Integer>> verticalOrderMap
    ) {
        if(null != node) {
            if(!verticalOrderMap.containsKey(verticalOrder)) {
                verticalOrderMap.put(verticalOrder, node);
            }
            if(null != node.right) {
                topViewDFSHelper(node.right, verticalOrder+1, verticalOrderMap);
            }
            if(null != node.left) {
                topViewDFSHelper(node.left, verticalOrder-1, verticalOrderMap);
            }
        }
    }

    private static Collection<Integer> topViewBFS(BTreeNode<Integer> node) {
        if(null == node) return Collections.emptyList();
        Collection<Integer> bottomView = new LinkedList<>();
        Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(node);
        int level = 0;
        while(!queue.isEmpty()) {
            final int size = queue.size();
            level++;
            System.out.println("XXXX  Queue:"+queue+" Level:"+level+" Size:"+queue.size());
            for (int i = 0; i < size; i++) {
                final BTreeNode<Integer> curr = queue.removeLast();
                if(null == curr.left && null == curr.right && (i == size-1 || i == 0)) {
                    bottomView.add(curr.data);
                }
                if(null != curr.left) {
                    queue.offerFirst(curr.left);
                }
                if(null != curr.right) {
                    queue.offerFirst(curr.right);
                }
            }
        }
        return bottomView;
    }
}
