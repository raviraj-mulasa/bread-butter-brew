package net.geekscore.tree.binary.traversal;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *      1
 *   2      3
 * 4   5      6
 *
 * Pre Order: Rlr: 1 2 4 5 3 6
 */
public class PreOrderTraversal {

    public static void main(String[] args) {
        Node right = new Node(3, null, new Node(6, null, null));
        Node left = new Node(2, new Node(4, null, null), new Node(5, null, null));
        Node root = new Node(1, left, right);

        final List<Node> traversal = new LinkedList<>();
        preOrderRec(root, traversal);
        System.out.println("----------");
        System.out.println(traversal);

        System.out.println("----------");
        System.out.println(preOrder(root));
    }

    private static void preOrderRec(final Node node, final List<Node> traversal) {
        if(null == node) return;
        traversal.add(node);
        preOrderRec(node.left, traversal);
        preOrderRec(node.right, traversal);
    }


    private static List<Node> preOrder(final Node node) {
        if(null == node) return Collections.emptyList();
        final List<Node> traversal = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            final Node curr = stack.pop();
            if(curr != null) traversal.add(curr);
            if(curr != null && curr.right != null) stack.push(curr.right);
            if(curr != null && curr.left != null) stack.push(curr.left);
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
