package net.geekscore.tree.binary;

import net.geekscore.tree.TreeUtil;

import java.util.*;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *      3
 *      / \
 *      9  20
 *          /  \
 *          15   7
 *          return its level order traversal as:
 *          [[3],[9,20],[15,7]]
 */
public class LevelOrderTraversal {

    public static void main(String[] args) {

        BTreeNode<Integer> root = new BTreeNode<>(3);
        root.left = new BTreeNode<>(9);
        root.right = new BTreeNode<>(20);
        root.right.left = new BTreeNode<>(15);
        root.right.right = new BTreeNode<>(7);

        IBTree<Integer> binaryTree = new BTreeImpl<>(root);

        TreeUtil.print(binaryTree);

        System.out.println(levelOrderTraversal(binaryTree)); // [[3],[9,20],[15,7]]
        System.out.println(levelOrderRec(binaryTree)); // [[3],[9,20],[15,7]]

        root = new BTreeNode<>(1);
        root.left = new BTreeNode<>(2);
        root.right = new BTreeNode<>(3);
        root.left.left = new BTreeNode<>(4);
        root.left.right = new BTreeNode<>(5);
        binaryTree = new BTreeImpl<>(root);
        TreeUtil.print(binaryTree);

        System.out.println(levelOrderTraversal(binaryTree)); // [[1], [2,3], [4,5]]
        System.out.println(levelOrderRec(binaryTree)); // [[1], [2,3], [4,5]]
    }

    // Uses DFS using 2 stacks
    private static <T extends Comparable<T>> List<List<T>> levelOrderTraversal(final IBTree<T> tree) {
        if(null == tree || tree.root() == null) return Collections.emptyList();
        final List<List<T>> levelOrderList = new LinkedList<>();
        final Stack<BTreeNode<T>> stack = new Stack<>();
        final BTreeNode<T> curr = tree.root();
        stack.push(curr);
        final Stack<Integer> levelStack = new Stack<>();
        levelStack.push(0);
        while (!stack.isEmpty() && !levelStack.isEmpty()) {
            final int level = levelStack.pop();
            if(level == levelOrderList.size()) levelOrderList.add(level, new LinkedList<>()); // new level
            final List<T> levelList = levelOrderList.get(level) ;
            final BTreeNode<T> ele = stack.pop();
            levelList.add(ele.data);
            if(ele.right != null) {stack.push(ele.right);levelStack.push(level+1);}
            if(ele.left != null) {stack.push(ele.left);levelStack.push(level+1);}
        }
        return levelOrderList;
    }

    private static <T extends Comparable<T>> List<List<T>> levelOrderRec(final IBTree<T> tree) {
        final List<List<T>> levelOrderList = new LinkedList<>();
        levelOrderRecHelper(tree.root(), 0, levelOrderList);
        return levelOrderList;

    }

    private static <T extends Comparable<T>> void levelOrderRecHelper(BTreeNode<T> node, int level, List<List<T>> levelOrderList) {
        if(null == node){
            return;
        }
        if(levelOrderList.size() == level) levelOrderList.add(level, new LinkedList<>()); // new level
        final List<T> levelList = levelOrderList.get(level);
        levelList.add(node.data);
        levelOrderRecHelper(node.left, level+1, levelOrderList);
        levelOrderRecHelper(node.right, level+1, levelOrderList);
    }
}
