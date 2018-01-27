package net.geekscore.tree.binary;

import net.geekscore.tree.TreeUtil;

import java.util.Comparator;
import java.util.Objects;

public class ValidBST {

    public static void main(String[] args) {

        final IBTree<Integer> bstTree = new BSTreeImpl<>();
        bstTree.insert(1);
        bstTree.insert(6);
        bstTree.insert(5);
        bstTree.insert(3);
        bstTree.insert(2);
        bstTree.insert(4);
        bstTree.insert(-1);
        bstTree.insert(-3);
        bstTree.insert(0);
        bstTree.insert(17);
        bstTree.insert(8);
        bstTree.insert(20);

//        TreeUtil.print(bstTree);

        System.out.println(isBST(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE)); //  true

        final BTreeNode<Integer> node5 = bstTree.find(5);
        node5.data = 18;
        System.out.println(isBST(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));  //false

        node5.data = 5;
        final BTreeNode<Integer> node6 = bstTree.find(6);
        System.out.println(isBST(node6, Integer.MIN_VALUE, Integer.MAX_VALUE));  // true
    }

    private static <T extends Comparable<T>>  boolean isBST(final IBTree<T> tree, T minValue, T maxValue) {
        return isBST(tree.root(), minValue, maxValue);
    }

    private static <T extends Comparable<T>> boolean isBST(final BTreeNode<T> node, T minValue, T maxValue) {

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
}
