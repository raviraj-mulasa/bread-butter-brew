package net.geekscore.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 *
 * Example 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 *
 * Note:
 * -1e7 <= x <= 1e7
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 *
 *
 */
public class MaxStack {

    public static void main(String[] args) {

        final Stack maxStack = new Stack();

        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(5);


        System.out.println(maxStack.top()); // 5
        System.out.println(maxStack.popMax());  // 5
        System.out.println(maxStack.top()); // 1
        System.out.println(maxStack.peekMax()); // 5
        System.out.println(maxStack.pop()); // 1
        System.out.println(maxStack.top()); // 5
    }


    private static class Stack {

        Node top;

        /** initialize your data structure here. */
        public Stack() {
            top = null;
        }

        public void push(int x) {
            if(top == null) top = new Node(x);
            else top = new Node(x, Math.max(x, top.max), top);
        }

        public int pop() {
            if(top != null) {
                int val2Ret = top.val;
                top = top.next;
                return val2Ret;
            }
            return -1;
        }

        public int top() {
            if(top != null) return top.val;
            return -1;
        }

        public int peekMax() {
            if(top != null) return top.max;
            return -1;
        }

        public int popMax() {
            if(top != null) {
                final int max = peekMax();
                final List<Integer> temp = new LinkedList<>();
                while(top != null && top.val != max) temp.add(pop());
                pop();
                for (int i = temp.size()-1; i >= 0 ; i--) {
                    push(temp.get(i));
                }
                return max;
            }
            return -1;
        }
    }

    private static class Node {

        int val;
        int max;
        Node next;

        Node(int val) {
            this(val, val);
        }

        Node(int val, int max) {
            this(val, max, null);
        }

        Node(int val, int max, Node next) {
            this.val = val;
            this.max = max;
            this.next= next;
        }
    }


}
