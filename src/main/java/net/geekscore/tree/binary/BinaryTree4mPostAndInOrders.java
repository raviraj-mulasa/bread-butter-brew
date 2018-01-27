package net.geekscore.tree.binary;

import net.geekscore.tree.TreeUtil;

import java.util.Arrays;

public class BinaryTree4mPostAndInOrders {

    public static void main(String[] args) {
        TreeUtil.print(new BTreeImpl<>(construct(new Integer[]{4,5,2,6,7,8,3,1}, new Integer[]{4,2,5,1,6,7,3,8})));
        TreeUtil.print(new BTreeImpl<>(construct(new Integer[]{10,40,30,60,90,70,50}, new Integer[]{10,30,40,50,60,70,90})));
    }

    private static BTreeNode<Integer> construct(final Integer[] postorder, final Integer[] inorder) {
        if(postorder == null || inorder == null || postorder.length == 0 || inorder.length == 0) return null;
        final Integer  rootVal = postorder[postorder.length-1];
        final BTreeNode<Integer> root = new BTreeNode<>(rootVal);
        int i = 0;
        while (i < inorder.length && Integer.compare(inorder[i++],rootVal) !=0 );
        root.left = construct(Arrays.copyOfRange(postorder, 0, i-1), Arrays.copyOfRange(inorder, 0, i-1));
        root.right = construct(Arrays.copyOfRange(postorder, i-1, postorder.length - 1), Arrays.copyOfRange(inorder, i, inorder.length));
        return root;
    }
}
