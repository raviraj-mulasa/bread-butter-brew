package net.geekscore.tree.binary.traversal;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeImpl;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.*;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples:
 * Given binary tree [3,9,20,null,null,15,7],
 *          3
 *          /\
 *         /  \
 *         9  20
 *            /\
 *           /  \
 *          15   7
 *  Vertical order traversal as: [[9],[3,15],[20],[7]]
 *
 *  Given binary tree [3,9,8,4,0,1,7],
 *      3
 *      /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 * 4  01   7
 *
 * Vertical order traversal as: [[4],[9],[3,0,1],[8],[7]]
 *
 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 *              3
 *             /\
 *            /  \
 *           9   8
 *          /\  /\
 *         /  \/  \
 *        4  01   7
 *           /\
 *          /  \
 *         5   2
 *
 * Vertical order traversal as:[[4],[9,5],[3,0,1],[8,2],[7]]
 *
 *
 */
public class VerticalOrderTraversal {

    public static void main(String[] args) {

        BTreeNode<Integer> root = new BTreeNode<>(3);
        root.left = new BTreeNode<>(9);
        root.right = new BTreeNode<>(20);
        root.right.left = new BTreeNode<>(1);
        root.right.right = new BTreeNode<>(7);
        IBTree<Integer> binaryTree = new BTreeImpl<>(root);
        TreeUtil.print(binaryTree);

        System.out.println(verticalOrder(binaryTree)); // [[9],[3,15],[20],[7]]
        System.out.println(verticalOrderRec(binaryTree)); // [[9],[3,15],[20],[7]]

        root = new BTreeNode<>(3);
        root.left = new BTreeNode<>(9);
        root.right = new BTreeNode<>(8);
        root.left.left = new BTreeNode<>(4);
        root.left.right = new BTreeNode<>(0);
        root.right.left = new BTreeNode<>(15);
        root.right.right = new BTreeNode<>(7);
        binaryTree = new BTreeImpl<>(root);
        TreeUtil.print(binaryTree);

        System.out.println(verticalOrder(binaryTree)); // [[4],[9],[3,0,1],[8],[7]]
        System.out.println(verticalOrderRec(binaryTree)); // [[4],[9],[3,0,1],[8],[7]]

        root.right.left.left = new BTreeNode<>(5);
        root.right.left.right = new BTreeNode<>(2);

        TreeUtil.print(binaryTree);

        System.out.println(verticalOrder(binaryTree)); // [[4],[9,5],[3,0,1],[8,2],[7]]
        System.out.println(verticalOrderRec(binaryTree)); // [[4],[9,5],[3,0,1],[8,2],[7]]

    }

    private static <T extends Comparable<T>> List<List<T>> verticalOrder(final IBTree<T> tree) {
        if(null == tree || null == tree.root()) return Collections.emptyList();
        final Map<Integer, List<T>> verticalLevelOrder = new TreeMap<>();
        final Stack<BTreeNode<T>> stack = new Stack<BTreeNode<T>>();
        stack.push(tree.root());
        final Stack<Integer> levelStack = new Stack<Integer>();
        levelStack.push(0);
        while (!stack.isEmpty() && !levelStack.isEmpty()) {
            final int level = levelStack.pop();
            BTreeNode<T> curr = stack.pop();
            final List<T> verticalList = verticalLevelOrder.getOrDefault(level, new LinkedList<>());
            verticalList.add(curr.data);
            verticalLevelOrder.put(level, verticalList);
            if (curr.right != null) {
                stack.push(curr.right);
                levelStack.push(level + 1);
            }
            if(curr.left != null) {
                stack.push(curr.left);
                levelStack.push(level - 1);
            }
        }
//        System.out.println(verticalLevelOrder);
        return new LinkedList<>(verticalLevelOrder.values());

    }

    // todo
    private static <T extends Comparable<T>> List<List<T>> verticalOrderRec(final IBTree<T> tree) {
        if(null == tree || null == tree.root()) return Collections.emptyList();
        final SortedMap<Integer, List<T>> verticalLevelOrderMap = new TreeMap<>();
        verticalOrderRecHelper(
                tree.root()
                , verticalLevelOrderMap
                , new HashMap<BTreeNode, Integer>(){{put(tree.root(),0);}}
                );
        return new ArrayList<>(verticalLevelOrderMap.values());

    }

    private static <T extends Comparable<T>> void verticalOrderRecHelper(
            final BTreeNode<T> node
            , SortedMap<Integer, List<T>> verticalLevelOrderMap
            ,Map<BTreeNode, Integer> colMap
            ) {
        if(null == node) return;
        final int col = colMap.get(node);
        final List<T> verticalList = verticalLevelOrderMap.getOrDefault(col, new LinkedList<>());
        verticalList.add(node.data);
        verticalLevelOrderMap.put(col, verticalList);
        if(null != node.left) colMap.put(node.left, col - 1);
        if(null != node.right) colMap.put(node.right, col + 1);
        verticalOrderRecHelper(node.left, verticalLevelOrderMap, colMap);
        verticalOrderRecHelper(node.right, verticalLevelOrderMap, colMap);
    }


}
