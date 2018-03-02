package net.geekscore.tree.binary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *      1
 *   2      3
 * 4   5      6
 *
 * In Order: lRr: 4 2 5 1 3 6
 */
public class InOrderTraversal {

    public static void main(String[] args) {
        Node right = new Node(3, null, new Node(6, null, null));
        Node left = new Node(2, new Node(4, null, null), new Node(5, null, null));
        Node root = new Node(1, left, right);

        final List<Node> traversal = new LinkedList<>();
        inOrderRec(root, traversal);
        System.out.println("----------");
        System.out.println(traversal);

        System.out.println("----------");
        System.out.println(inOrder(root));
    }

    private static void inOrderRec(final Node node, final List<Node> traversal) {
        if(null == node) return;
        inOrderRec(node.left,traversal);
        traversal.add(node);
        inOrderRec(node.right,traversal);
    }

    private static List<Node> inOrder(final Node node) {
        if(null == node) return Collections.emptyList();
        final List<Node> traversal = new LinkedList<>();

        Stack<Node> stack = new Stack<>();
        Node curr = node;

        while(!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr); // Push curr
                curr = curr.left;
            }
            curr = stack.pop(); // Either curr or root
            traversal.add(curr);
            curr = curr.right; // Move to right of curr and do inorder on right child
        }
        return traversal;
    }

    static final class Node {
        int val;
        Node left;
        Node right;

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }
    }
}
