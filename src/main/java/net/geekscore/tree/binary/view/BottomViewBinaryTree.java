package net.geekscore.tree.binary.view;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.*;

/**
 *
 *               20
 *            /     \
 *          8         22
 *         /  \         \
 *       5     3        25
 *           /    \
 *        10       14
 *
 * Output: 5, 10, 3, 14, 25.
 *
 *            1
 *          /   \
 *          2       3
 *          \
 *           4
 *            \
 *             5
 *              \
 *               6
 * Bottom view of the above binary tree is 2, 4, 5, 6.
 *
 */
public class BottomViewBinaryTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{20,8,22,5,3,null,25,null,null,10,14});
        TreeUtil.print(tree);
//        System.out.println(bottomViewBFS(tree.root()));
        System.out.println(bottomViewDFS(tree.root()));
        System.out.println("-----");
        IBTree<Integer> tree1 = TreeUtil.treeOf(new Integer[]{1,2,3,null,4,null,null,null,null,null,5,null,null,null,null,null,null,null,null,null,null,null,6});
        TreeUtil.print(tree1);
//        System.out.println(bottomViewBFS(tree.root()));
        System.out.println(bottomViewDFS(tree1.root()));
        System.out.println("-----");
    }
    private static Collection<BTreeNode<Integer>> bottomViewDFS(BTreeNode<Integer> node) {
        if(null == node) Collections.emptyList();
        SortedMap<Integer, BTreeNode<Integer>> verticalOrderMap = new TreeMap<>();
        bottomViewDFSHelper(node, 0, verticalOrderMap);
        return verticalOrderMap.values();
    }
    private static void bottomViewDFSHelper(
            BTreeNode<Integer> node
            , int verticalOrder
            ,  Map<Integer, BTreeNode<Integer>> verticalOrderMap
    ) {
        if(null != node) {
            verticalOrderMap.put(verticalOrder, node);
            if(null != node.right) {
                bottomViewDFSHelper(node.right, verticalOrder+1, verticalOrderMap);
            }
            if(null != node.left) {
                bottomViewDFSHelper(node.left, verticalOrder-1, verticalOrderMap);
            }
        }
    }

    private static Collection<Integer> bottomViewBFS(BTreeNode<Integer> node) {
        if(null == node) return Collections.emptyList();
        Collection<Integer> bottomView = new LinkedList<>();
        Deque<BTreeNode<Integer>> queue = new LinkedList<>();
        queue.offerFirst(node);
        int level = 0;
        while(!queue.isEmpty()) {
            final int size = queue.size();
            level++;
            System.out.println("XXXX  Queue:"+queue+" Level:"+level+" Size:"+queue.size());
            for (int i = 0; i < size; i++) {
                final BTreeNode<Integer> curr = queue.removeLast();
                if(null == curr.left && null == curr.right && (i == size-1 || i == 0)) {
                    bottomView.add(curr.data);
                }
                if(null != curr.left) {
                    queue.offerFirst(curr.left);
                }
                if(null != curr.right) {
                    queue.offerFirst(curr.right);
                }
            }
        }
        return bottomView;
    }

}
