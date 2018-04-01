package net.geekscore.tree.binary.traversal;

import java.util.*;

/**
 *      1
 *   2      3
 * 4   5      6
 *
 * Post Order: lrR: 4 5 2 6 3 1
 */

public class PostOrderTraversal {

    public static void main(String args[]) {
        Node right = new Node(3, null, new Node(6, null, null));
        Node left = new Node(2, new Node(4, null, null), new Node(5, null, null));
        Node root = new Node(1, left, right);

        final List<Node> traversal = new LinkedList<>();
        postOrderRec(root, traversal);
        System.out.println("----------");
        System.out.println(traversal);

        System.out.println("----------");
        System.out.println(postOrder(root));

        System.out.println("----------");
        System.out.println(postOrderWith2Stacks(root));

        System.out.println("----------");
        System.out.println(postOrderChangesPointers(root));

    }

    private static final void postOrderRec(Node node, List<Node> traversal) {
        if (null == node) return;
        postOrderRec(node.left, traversal);
        postOrderRec(node.right, traversal);
        traversal.add(node);
    }

    // Extra Memory - Depth of the tree
    private static final Collection<Node> postOrder(Node root) {

        if (null == root) return Collections.emptyList();

        final Stack<Node> stack = new Stack<>();
        final List<Node> postOrder = new LinkedList<>();
        Node curr = root;

        while (curr !=null || !stack.isEmpty()) {
            while(curr != null) { // traverse deep to left sub tree
                stack.push(curr);
                curr = curr.left;
            }
            Node temp = stack.peek(); // Reached left most element
            //  check the left most element has a right child
            if(temp.right != null) {
                curr = temp.right; // YES, then do post order on the right child
                continue;
            }
            // NO, then pop
            temp = stack.pop();
            postOrder.add(temp);
            // Traverse up, check am I the right child of node, pop and print till root of right child
            while (!stack.isEmpty() && temp == stack.peek().right) {
                temp = stack.pop();
                postOrder.add(temp);
            }
        }
        return postOrder;
    }


    // Extra memory
    private static final Collection<Node> postOrderWith2Stacks(Node root) {
        if (null == root) return Collections.emptyList();
        final Stack<Node> stack = new Stack<>();
        final List<Node> postOrder = new LinkedList<>(); // treat as  another stack

        stack.push(root);

        while (!stack.isEmpty()) {
            final Node curr = stack.pop();
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
            postOrder.add(curr);
        }
        Collections.reverse(postOrder);
        return postOrder;
    }

    // Modifying the pointers of the original tree - NO GOOD
    private static final List<Integer> postOrderChangesPointers(Node root) {

        if (null == root) return Collections.emptyList();
        List<Integer> postOrder = new LinkedList<>();
        Node curr = root;
        final Stack<Node> stack = new Stack<Node>();
        stack.push(curr);

        while (!stack.isEmpty()) {

            curr = stack.peek();
            if (curr.left == null && curr.right == null) {
                postOrder.add(stack.pop().val);
            } else {
                if (curr.right != null) {
                    stack.push(curr.right); // Subtree right
                    curr.right = null;
                }
                if (curr.left != null) {
                    stack.push(curr.left); // Subtree left
                    curr.left = null;
                }
            }
        }
        return postOrder;
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
