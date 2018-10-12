package net.geekscore.tree.binary.bst;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 *
 * Note: If the given node has no in-order successor in the tree, return null.
 *
 *
 */
public class InOrderSuccessorBST {

    public static void main(String[] args) {

        final IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{8, 3, 10, 1, 6, 9, 14, null, null, 4, 7, null, null, 13, null});
        TreeUtil.print(tree);

        System.out.println("--- Traversal from root --");
        // 8
        System.out.println("Inorder Successor 7 " + inorderSuccessor(tree.root(), find(tree.root(), 7)));
        // 14
        System.out.println("Inorder Successor 13 " + inorderSuccessor(tree.root(), find(tree.root(), 13)));
        // 10
        System.out.println("Inorder Successor 8 " + inorderSuccessor(tree.root(), find(tree.root(), 8)));
        // 13
        System.out.println("Inorder Successor 10 " + inorderSuccessor(tree.root(), find(tree.root(), 10)));
        // nul
        System.out.println("Inorder Successor 14 " + inorderSuccessor(tree.root(), find(tree.root(), 14)));
        // null
        System.out.println("Inorder Successor 27 " + inorderSuccessor(tree.root(), find(tree.root(), 27)));

        System.out.println("--- Using stack --");

        // 8
        System.out.println("Inorder Successor 7 " + inorderSuccessorStack(tree.root(), find(tree.root(), 7)));
        // 14
        System.out.println("Inorder Successor 13 " + inorderSuccessorStack(tree.root(), find(tree.root(), 13)));
        // 10
        System.out.println("Inorder Successor 8 " + inorderSuccessorStack(tree.root(), find(tree.root(), 8)));
        // 13
        System.out.println("Inorder Successor 10 " + inorderSuccessorStack(tree.root(), find(tree.root(), 10)));
        // nul
        System.out.println("Inorder Successor 14 " + inorderSuccessorStack(tree.root(), find(tree.root(), 14)));
        // null
        System.out.println("Inorder Successor 27 " + inorderSuccessorStack(tree.root(), find(tree.root(), 27)));


        System.out.println("--- Parent pointer and comparison ---");

        // 8
        System.out.println("Inorder Successor 7 " + inorderSuccessorParent(find(tree.root(), 7)));
        // 14
        System.out.println("Inorder Successor 13 " + inorderSuccessorParent(find(tree.root(), 13)));
        // 10
        System.out.println("Inorder Successor 8 " + inorderSuccessorParent(find(tree.root(), 8)));
        // 13
        System.out.println("Inorder Successor 10 " + inorderSuccessorParent(find(tree.root(), 10)));
        // nul
        System.out.println("Inorder Successor 14 " + inorderSuccessorParent(find(tree.root(), 14)));
        // null
        System.out.println("Inorder Successor 27 " + inorderSuccessorParent(find(tree.root(), 27)));

        System.out.println("--- Parent pointer and NO comparison ---");

        // 8
        System.out.println("Inorder Successor 7 " + inorderSuccessorParentNoComparison(find(tree.root(), 7)));
        // 14
        System.out.println("Inorder Successor 13 " + inorderSuccessorParentNoComparison(find(tree.root(), 13)));
        // 10
        System.out.println("Inorder Successor 8 " + inorderSuccessorParentNoComparison(find(tree.root(), 8)));
        // 13
        System.out.println("Inorder Successor 10 " + inorderSuccessorParentNoComparison(find(tree.root(), 10)));
        // nul
        System.out.println("Inorder Successor 14 " + inorderSuccessorParentNoComparison(find(tree.root(), 14)));
        // null
        System.out.println("Inorder Successor 27 " + inorderSuccessorParentNoComparison(find(tree.root(), 27)));

    }

    private static BTreeNode<Integer> inorderSuccessor(BTreeNode<Integer> node, BTreeNode<Integer> p) {
        if(node == null || p == null) return null;
        BTreeNode<Integer> successor = null;
        while (node != null) {
            if(p.data.compareTo(node.data) < 0) {
                // I am smaller than current node, so current node can be a successor
                successor =  node;
                node = node.left; // move left to find the next smallest element greater than me
            } else {
                // I am greater than current node, then the successor will be in right sub tree of current node
                node = node.right;
            }
        }
        return successor;
    }


    /* Has a parent pointer */
    private static BTreeNode<Integer> inorderSuccessorParent(BTreeNode<Integer> p) {
        if(p == null) return null;
        BTreeNode<Integer> successor = null;
        /* Case 1: I have a right child, the in order successor will be the left most child of my right child */
        if(p.right != null) {
            successor = p.right;
            while (successor.left != null) successor = successor.left;
        }
        /* Case 2: I have no right child, move up the tree */
        else {
            BTreeNode<Integer> curr = p;
            successor = p.parent;
            while (successor != null) {
                if(curr.data.compareTo(successor.data) < 0) break;
                curr = successor;
                successor =  successor.parent;
            }
        }
        return successor;
    }


    /* Has a parent pointer - NO COMPARISON */
    private static BTreeNode<Integer> inorderSuccessorParentNoComparison(BTreeNode<Integer> p) {
        if(p == null) return null;
        BTreeNode<Integer> successor = null;
        /* Case 1: I have a right child, the in order successor will be the left most child of my right child */
        if(p.right != null) {
            successor = p.right;
            while (successor.left != null) successor = successor.left;
        }
        /* Case 2: I have no right child, move up the tree */
        else {
            BTreeNode<Integer> curr = p;
            successor = p.parent;
            while (successor != null) {
                if(curr == successor.left) break; /* If I am the left child parent is greater than me break*/
                curr = successor;
                successor =  successor.parent;
            }
        }
        return successor;
    }


    private static BTreeNode<Integer> inorderSuccessorStack(BTreeNode<Integer> node, BTreeNode<Integer> p) {

        if(node == null || p == null) return null;

        final Deque<BTreeNode<Integer>> stack = new LinkedList<>();
        boolean foundP = false;
        BTreeNode<Integer> successor = node;
        while (successor != null || !stack.isEmpty()) {
            while (successor != null) {
                stack.push(successor);
                successor = successor.left;
            }
            successor = stack.pop();
            if(foundP) return successor;
            if(successor == p) foundP = true;
            successor = successor.right;
        }
        return null;

    }

    private static BTreeNode<Integer> find(BTreeNode<Integer> node, Integer key) {
        while (node != null) {
            final int comparison = key.compareTo(node.data);
            if(0 == comparison) return node;
            if(comparison < 0) node=node.left;
            else node=node.right;
        }
        return null;
    }
}
