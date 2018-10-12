package net.geekscore.tree.binary.bst;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 */
public class InOrderPredecessorBST {

    public static void main(String[] args) {
        final IBTree<Integer> tree = TreeUtil.treeOf(
                new Integer[]{
                        15
                        ,6,18
                        ,3,7,17,20
                        ,2,4,null,13,null,null,null,null
                        ,null,null,null,null,null,null,9,null,null,null,null,null});
        TreeUtil.print(tree);
        System.out.println(predecessor(tree.root(), 15)); // 13
        System.out.println(predecessor(tree.root(), 17)); // 15
        System.out.println(predecessor(tree.root(), 6)); // 4
        System.out.println(predecessor(tree.root(), 13)); // 9

        System.out.println("------------------------");

        System.out.println(predecessorParent(find(tree.root(), 15))); // 13
        System.out.println(predecessorParent(find(tree.root(), 17))); // 15
        System.out.println(predecessorParent(find(tree.root(), 6))); // 4
        System.out.println(predecessorParent(find(tree.root(), 13))); // 9


        System.out.println("------------------------");

        System.out.println(predecessorParentNoComparison(find(tree.root(), 15))); // 13
        System.out.println(predecessorParentNoComparison(find(tree.root(), 17))); // 15
        System.out.println(predecessorParentNoComparison(find(tree.root(), 6))); // 4
        System.out.println(predecessorParentNoComparison(find(tree.root(), 13))); // 9


        System.out.println("------------------------");

        System.out.println(inorderPredecessorStack(tree.root(), find(tree.root(), 15))); // 13
        System.out.println(inorderPredecessorStack(tree.root(), find(tree.root(), 17))); // 15
        System.out.println(inorderPredecessorStack(tree.root(), find(tree.root(), 6))); // 4
        System.out.println(inorderPredecessorStack(tree.root(), find(tree.root(), 13))); // 9


    }

    private static BTreeNode<Integer> predecessor(final BTreeNode<Integer> root, final Integer key) {
        BTreeNode<Integer> curr = root, predecessor = null;
        while (curr != null) {
            if(key.compareTo(curr.data) > 0) {
                predecessor = curr;
                curr = curr.right;
                continue;
            }
            curr = curr.left;
        }
        return predecessor;
    }

    /* Has a parent pointer */
    private static BTreeNode<Integer> predecessorParent(BTreeNode<Integer> p) {
        if(p == null) return null;
        BTreeNode<Integer> predecessor = null;
        /* Case 1: I have a left child, the in order predecessor will be the right most child of my left child */
        if(p.left != null) {
            predecessor = p.left;
            while (predecessor.right != null) predecessor = predecessor.right;
        }
        /* Case 2: I have no left child, move up the tree */
        else {
            BTreeNode<Integer> curr = p;
            predecessor = p.parent;
            while (predecessor != null) {
                if(curr.data.compareTo(predecessor.data) > 0) break;
                curr = predecessor;
                predecessor =  predecessor.parent;
            }
        }
        return predecessor;
    }

    /* Has a parent pointer - NO COMPARISON */
    private static BTreeNode<Integer> predecessorParentNoComparison(BTreeNode<Integer> p) {
        if(p == null) return null;
        BTreeNode<Integer> predecessor = null;
        /* Case 1: I have a left child, the in order predecessor will be the right most child of my left child */
        if(p.left != null) {
            predecessor = p.left;
            while (predecessor.right != null) predecessor = predecessor.right;
        }
        /* Case 2: I have no left child, move up the tree */
        else {
            BTreeNode<Integer> curr = p;
            predecessor = p.parent;
            while (predecessor != null) {
                if(curr == predecessor.right) break; /* If I am the right child parent is less than me break*/
                curr = predecessor;
                predecessor =  predecessor.parent;
            }
        }
        return predecessor;
    }

    private static BTreeNode<Integer> inorderPredecessorStack(BTreeNode<Integer> node, BTreeNode<Integer> p) {

        if(node == null || p == null) return null;

        final Deque<BTreeNode<Integer>> stack = new LinkedList<>();
        BTreeNode<Integer> curr = node, predecessor = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if(curr.data.compareTo(p.data) == 0) break;
            predecessor = curr;
            curr = curr.right;
        }
        return predecessor;

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
