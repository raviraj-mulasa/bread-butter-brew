package net.geekscore.tree.binary.bst;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the
 * root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height
 * of the tree.
 */
public class BSTIterator {

    public static void main(String[] args) {
        final IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{8, 3, 10, 1, 6, 9, 14, null, null, 4, 7, null, null, 13, null});
        TreeUtil.print(tree);

        final Iterator<Integer> iterator = new InorderIterator<>(tree.root());
        while (iterator.hasNext()) {
            System.out.println(""+iterator.next());
        }
    }

    private static class InorderIterator<T extends Comparable> implements Iterator<T> {

        private final Deque<BTreeNode<T>> stack = new LinkedList<>();

        private BTreeNode<T> current = null;

        private InorderIterator(BTreeNode<T> root) {
            if(root != null) {
                this.current = root;
            }
        }

        /** @return whether we have a next smallest number */
        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty() || this.current != null;
        }

        /** @return the next smallest number */
        @Override
        public T next() {
            BTreeNode<T> result = null;
            if (hasNext()) {
                while (this.current != null) {
                    this.stack.push(this.current);
                    this.current = this.current.left;
                }
                result = this.stack.pop();
                this.current = result;
                this.current = this.current.right;
            }
            return result == null ? null : result.data;
        }
    }
}

