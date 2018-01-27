package net.geekscore.tree.binary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * preOrder : lRr
 * For example:
 * Given binary tree [1,null,2,3],
 * 1
 *  \
 *  2
 * /
 * 3
 * return [1,3,2].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 *
 */
public class InOrder {

    public static void main(String[] args) {

        final BTreeNode<Integer> root = new BTreeNode<>(1);
        root.left = null;
        root.right = new BTreeNode<>(2);
        root.right.left = new BTreeNode<>(3);
        final IBTree<Integer> binaryTree = new BTreeImpl<>(root);

//        TreeUtil.print(binaryTree);
        System.out.println(inOrder(binaryTree)); // [1,3,2]

        final IBTree<Integer> bst = new BSTreeImpl<>();
        bst.insert(1);
        bst.insert(6);
        bst.insert(5);
        bst.insert(3);
        bst.insert(2);
        bst.insert(4);
        bst.insert(-1);
        bst.insert(-3);
        bst.insert(0);
        bst.insert(17);
        bst.insert(8);
        bst.insert(20);

//        TreeUtil.print(bst);
        System.out.println(inOrder(bst)); // [-3, -1, 0, 1, 2, 3, 4, 5, 6, 8, 17, 20]



    }

    public static <T> List<T> inOrder(final IBTree<T> tree) {
        return inOrder(tree.root());
    }

    private static <T> List<T> inOrder(final BTreeNode<T> node) {
        BTreeNode curr = node;
        if(curr==null) {
            return Collections.emptyList();
        }
        final List<T> inOrderList   = new LinkedList<>();

        Stack<BTreeNode> stack      = new Stack<>();
        while(!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr); // Push curr
                curr = curr.left; // Move to left or curr
            }
            curr    = stack.pop(); // Either curr or left
            inOrderList.add((T)curr.data);
            curr    = curr.right; // Move to right of curr
        }
        return inOrderList;
    }
}
