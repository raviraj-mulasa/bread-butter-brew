package edu.learn.me.tree;

import edu.learn.me.tree.binary.BTreeNode;
import edu.learn.me.tree.binary.IBTree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ravirajmulasa on 9/3/16.
 */
public final class TreeUtil {

    /**
     *
     * @return preOrder : Rlr
     */
    public static <T> List<T> preOrder(final IBTree<T> tree) {

        BTreeNode curr = tree.root();
        if(curr==null) {
            return Collections.emptyList();
        }

        final Stack<BTreeNode> stack      = new Stack<>();
//        start with root in stack
        stack.push(curr);
        final List<T> preOrderList  = new LinkedList<>();

        while(!stack.isEmpty()) {

            curr = stack.pop();
            preOrderList.add((T) curr.getData());

            if(null != curr.getRight()){
                stack.push(curr.getRight());
            }

            if(null != curr.getLeft()){
                stack.push(curr.getLeft());
            }
        }
        return preOrderList;
    }


    /**
     *
     * @return preOrder : lrR
     */
    public static <T> List<T> postOrder(final IBTree<T> tree) {

        BTreeNode curr = tree.root();
        if(curr==null) {
            return Collections.emptyList();
        }

        BTreeNode prev              = null;
        final List<T> postOrderList = new LinkedList<>();
        Stack<BTreeNode> stack      = new Stack<>();

//        start with root in stack
        stack.push(curr);

        while(!stack.isEmpty()) {

            curr = stack.peek();
//            Moving Down
            if(prev == null || prev.getLeft() == curr || prev.getRight() == curr){

                if(null != curr.getLeft()) {
                    stack.push(curr.getLeft());
                } else if(null != curr.getRight()) {
                    stack.push(curr.getRight());
                } else  {
                    postOrderList.add((T) curr.getData());
//                    pop
                }

            }

//            Moving up from left
            if(prev == curr.getLeft()) {
                if(null != curr.getRight()) {
                    stack.push(curr.getRight());
                }
            } else {
                postOrderList.add((T) curr.getData());
//                pop
            }

//            Moving up from right
            if(prev == curr.getRight()) {
                postOrderList.add((T) curr.getData());
//                pop
            }
            prev = curr;
        }

        return postOrderList;
    }


    /**
     *
     * @return preOrder : lRr
     */
    public static <T> List<T> inOrder(final IBTree<T> tree) {

//        start with root in stack
        BTreeNode curr = tree.root();

        if(curr==null) {
            return Collections.emptyList();
        }

        final List<T> inOrderList   = new LinkedList<>();
        Stack<BTreeNode> stack      = new Stack<>();

        while(!stack.isEmpty() || curr != null) {

            if(curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            } else {
                curr    = stack.pop();
                inOrderList.add((T) curr.getData());
                curr    = curr.getRight();
            }
        }
        return inOrderList;
    }
}
