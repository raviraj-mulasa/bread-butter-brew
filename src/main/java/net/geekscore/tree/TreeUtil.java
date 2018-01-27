package net.geekscore.tree;

import net.geekscore.tree.binary.BTreeImpl;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.BSTreeNodeComparator;
import net.geekscore.tree.binary.IBTree;

import java.util.*;

/**
 * Created by ravirajmulasa on 9/3/16.
 */
public final class TreeUtil {

    private TreeUtil(){}

    /**
     *
     * @return preOrder : Rlr
     */
    public static <T> List<T> preOrder(final IBTree<T> tree) {
//        start with root
        return preOrder(tree.root());
    }


    /**
     *
     * @return preOrder : Rlr
     */
    public static <T> List<T> preOrder(final BTreeNode<T> node) {

        BTreeNode curr = node;
        if(curr==null) {
            return Collections.emptyList();
        }

        final Stack<BTreeNode> stack      = new Stack<>();
//        start with node in stack
        stack.push(curr);
        final List<T> preOrderList  = new LinkedList<>();

        while(!stack.isEmpty()) {

            curr = stack.pop();
            preOrderList.add((T) curr.data);

            if(null != curr.right){
                stack.push(curr.right);
            }

            if(null != curr.left){
                stack.push(curr.left);
            }
        }
        return preOrderList;
    }


    /**
     *
     * @return preOrder : lRr
     */
    public static <T> List<T> postOrder(final IBTree<T> tree) {
//        start with root
        return postOrder(tree.root());
    }


    /**
     *
     * @return preOrder : lrR
     */
    public static <T> List<T> postOrder(final BTreeNode<T> node) {

        BTreeNode curr = node;
        if(curr==null) {
            return Collections.emptyList();
        }

        BTreeNode prev              = null;
        final List<T> postOrderList = new LinkedList<>();
        Stack<BTreeNode> stack      = new Stack<>();

//        start with node in stack
        stack.push(curr);

        while(!stack.isEmpty()) {

            curr = stack.peek();
//            Moving Down
            if(prev == null || prev.left == curr || prev.right == curr){

                if(null != curr.left) {
                    stack.push(curr.left);
                } else if(null != curr.right) {
                    stack.push(curr.right);
                } else  {
                    postOrderList.add((T) curr.data);
//                    pop
                }

            }

//            Moving up from left
            if(prev == curr.left) {
                if(null != curr.right) {
                    stack.push(curr.right);
                }
            } else {
                postOrderList.add((T) curr.data);
//                pop
            }

//            Moving up from right
            if(prev == curr.right) {
                postOrderList.add((T) curr.data);
//                pop
            }
            prev = curr;
        }

        return postOrderList;
    }



    /**
     *
     * @param node
     * @param <T>
     * @return successorBST:
     */
    public static <T extends Comparable<T>> BTreeNode<T> successorBST(final BTreeNode<T> node) {

        if(null == node){
            return null;
        }

        final Comparator<T> bTreeNodeComparator = new BSTreeNodeComparator<T>();
        BTreeNode<T> curr   = node;
//        Node has right sub tree, go deep to leftmost node in the right subtree i.e: Min in right subtree.
        if(curr.right != null) {
            curr = curr.right;
            while (curr.left != null) curr = curr.left;
            return curr;
        }
//        Node has NO right sub tree, go to nearest greater ancestor in which the node is part of the left subtree.
        else {
            BTreeNode<T> parent = curr.parent;
            while(parent != null) {
                if(Objects.compare(curr.data, parent.data, bTreeNodeComparator) < 0) {
                    break;
                }
                curr    = parent;
                parent  = parent.parent;
            }
            return parent;
        }
    }


    /**
     *
     * @param node
     * @param <T>
     * @return predecessorBST:
     */
    public static <T extends Comparable<T>> BTreeNode<T> predecessorBST(final BTreeNode<T> node) {

        return null;
    }

    /**
     *
     * @param tree
     * @param minValue of generic
     * @param maxValue of generic
     * @param <T> Generic
     * @return true if tree is a Binary Search Tree or false
     */
    public static <T extends Comparable<T>>  boolean isBST(final IBTree<T> tree, T minValue, T maxValue) {
        return isBST(tree.root(), minValue, maxValue);
    }


    /**
     *
     * @param node
     * @param minValue of generic type
     * @param maxValue of generic type
     * @param <T> Generic type
     * @return true if tree at node is a Binary Search Tree or false
     */
    public static <T extends Comparable<T>> boolean isBST(final BTreeNode<T> node, T minValue, T maxValue) {

        if(null == node) {
            return true;
        }

        final Comparator<T> bTreeNodeComparator = new BSTreeNodeComparator<T>();
        return
//                node value is greater than min value
                   Objects.compare(minValue, node.data, bTreeNodeComparator) < 0
//                node value is less than max value
                && Objects.compare(node.data, maxValue, bTreeNodeComparator) < 0
//                left subtree values are less than node value
                && isBST(node.left, minValue, node.data)
//                right subtree values are greater than node value
                && isBST(node.right, node.data, maxValue);
    }


    public static <T extends Comparable<T>> BTreeNode<T> findMinInBST(final BTreeNode<T> node) {

        if(node == null) {
            return null;
        }
        BTreeNode<T> curr = node;
        for(;curr.left != null; curr = curr.left);
        return curr;
    }

    public static <T extends Comparable<T>> BTreeNode<T> findMaxInBST(final BTreeNode<T> node) {
        if(node == null) {
            return null;
        }
        BTreeNode<T> curr = node;
        for(;curr.right != null; curr = curr.right);
        return curr;
    }


    public static <T> Integer heightBinaryTree(final IBTree<T> tree) {
        return heightBinaryTree(tree.root());
    }

    public static <T extends Comparable<T>> IBTree<T> treeOf(final T[] values) {

        if(values == null || values.length == 0) return new BTreeImpl<>(null);
        BTreeNode<T> root = treeOfHelper(null, null, values, 0);
        return new BTreeImpl<>(root);
    }

    private static <T extends Comparable<T>> BTreeNode<T> treeOfHelper(BTreeNode<T> node, BTreeNode<T> parent, final T[] values, int i) {
        if (i < values.length && values[i] != null) {
            node = new BTreeNode<>(values[i]);
            node.parent = parent;
            node.left = treeOfHelper(node.left, node, values, 2 * i + 1);
            node.right = treeOfHelper(node.right, node, values, 2 * i + 2);
        }
        return node;
    }


    /**
     *
     * @return height: Max depth of a Binary tree from node
     */
    public static <T> Integer heightBinaryTree(final BTreeNode<T> node) {
        if(node == null) {
            return -1;
        }
        return Math.max(heightBinaryTree(node.left), heightBinaryTree(node.right)) + 1;
    }



    public static <T> void print(IBTree<T> tree) {
        final int height = heightBinaryTree(tree.root());
        printNodeInternal(Collections.singletonList(tree.root()), 0, height);
    }

    private static <T> void printNodeInternal(List<BTreeNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = floor;
//        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<BTreeNode<T>> newNodes = new ArrayList<>();
        for (BTreeNode<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }

}
