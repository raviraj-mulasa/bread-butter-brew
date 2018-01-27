package net.geekscore.tree.binary;

import net.geekscore.tree.TreeUtil;

import java.util.Arrays;

public class BinaryTree4mPreAndInOrders {

    public static void main(String[] args) {
        TreeUtil.print(new BTreeImpl<>(construct(new Integer[]{1,2,4,5,3,7,6,8}, new Integer[]{4,2,5,1,6,7,3,8})));
        TreeUtil.print(new BTreeImpl<>(construct(new Integer[]{50,30,10,40,70,60,90}, new Integer[]{10,30,40,50,60,70,90})));
    }

    private static BTreeNode<Integer> construct(final Integer[] preOrder, final Integer[] inOrder) {
        if(preOrder == null || inOrder == null || preOrder.length == 0 || inOrder.length == 0) return null;
        final Integer  rootVal = preOrder[0];
        final BTreeNode<Integer> root = new BTreeNode<>(rootVal);
        int i = 0;
        while (i < inOrder.length && Integer.compare(inOrder[i++],rootVal) !=0 );
        root.left = construct(Arrays.copyOfRange(preOrder, 1, i), Arrays.copyOfRange(inOrder, 0, i-1));
        root.right = construct(Arrays.copyOfRange(preOrder, i, preOrder.length), Arrays.copyOfRange(inOrder, i, inOrder.length));
        return root;
    }


}
