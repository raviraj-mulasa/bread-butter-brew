package net.geekscore.stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * Example:
 * Stack minStack = new Stack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
public class MinStack {

    public static void main(String[] args) {

        final Stack minStack = new Stack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // -3.
        minStack.pop();
        System.out.println(minStack.top()); // 0.
        System.out.println(minStack.getMin()); // -2.
    }


    private static class Stack {

        Node top = null;

        /** initialize your data structure here. */
        public Stack() {
            top = null;
        }

        public void push(int x) {
            if(null == top) top = new Node(x);
            else top = new Node(x, Math.min(x, top.min), top);

        }

        public void pop() {
            if(top != null) top = top.next;
        }

        public int top() {
            if(top != null) return top.val;
            return -1;
        }

        public int getMin() {
            if(top != null) return top.min;
            return -1;
        }
    }

    private static class Node {

        int val;
        int min;
        Node next;

        Node(int val) {
            this(val, val, null);
        }

        Node(int val, int min) {
            this(val, min, null);
        }

        Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next= next;
        }
    }
}
