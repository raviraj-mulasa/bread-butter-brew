package net.geekscore.tree.binary.bst;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest
 * frequently? How would you optimize the kthSmallest routine?
 *
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 */
public class KthSmallestElementBST {

    public static void main(String[] args) {
        final IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{8, 3, 10, 1, 6, 9, 14, null, null, 4, 7, null, null, 13, null});
        TreeUtil.print(tree);

        System.out.println(kthSmallest(tree.root(), 10)); // 14

        System.out.println(kthSmallest(tree.root(), 7)); // 9

        System.out.println(kthSmallest(tree.root(), 2)); // 3

        System.out.println(kthSmallest(tree.root(), 1)); // 1

        System.out.println(kthSmallest(tree.root(), 13)); // null
    }

    private static Integer kthSmallest(BTreeNode<Integer> root, int k) {
        if(root == null || k < 1) return null;
        final Deque<BTreeNode<Integer>> stack = new LinkedList<>();
        BTreeNode<Integer> kthSmallest = root;
        while (kthSmallest != null || !stack.isEmpty()) {
            while (kthSmallest != null) {
                stack.push(kthSmallest);
                kthSmallest = kthSmallest.left;
            }
            kthSmallest = stack.pop();
            if(k==1) return kthSmallest.data;
            k--;
            kthSmallest = kthSmallest.right;
        }
        return null;
    }
}
