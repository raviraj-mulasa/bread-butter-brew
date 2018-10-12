package net.geekscore.tree.binary.path;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree containing digits from 0-9 only,
 * each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Input: [1,2,3]
 *      1
 *     / \
 *    2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 *
 *
 * Example 2:
 * Input: [4,9,0,5,1]
 *          4
 *         / \
 *        9   0
 *       / \
 *      5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class SumRoot2Leaf {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{1,2,3});
        TreeUtil.print(tree);
        System.out.println("Sum root to leaves "+sumRoot2Leaf(tree.root()));
        System.out.println("Sum root to leaves Efficient "+sumRoot2LeafEfficient(tree.root()));
        System.out.println("Sum root to leaves Efficient 1 "+sumRoot2LeafEfficient1(tree.root()));

        System.out.println("-----------");
        tree = TreeUtil.treeOf(new Integer[]{4,9,0,5,1});
        TreeUtil.print(tree);
        System.out.println("Sum root to leaves "+sumRoot2Leaf(tree.root()));
        System.out.println("Sum root to leaves Efficient "+sumRoot2LeafEfficient(tree.root()));
        System.out.println("Sum root to leaves Efficient 1 "+sumRoot2LeafEfficient1(tree.root()));

        System.out.println("-----------");
        tree = TreeUtil.treeOf(new Integer[]{1,null,5});
        TreeUtil.print(tree);
        System.out.println("Sum root to leaves "+sumRoot2Leaf(tree.root()));
        System.out.println("Sum root to leaves Efficient "+sumRoot2LeafEfficient(tree.root()));
        System.out.println("Sum root to leaves Efficient 1 "+sumRoot2LeafEfficient1(tree.root()));


        System.out.println("-----------");
        tree = TreeUtil.treeOf(new Integer[]{1,5,null});
        TreeUtil.print(tree);
        System.out.println("Sum root to leaves "+sumRoot2Leaf(tree.root()));
        System.out.println("Sum root to leaves Efficient "+sumRoot2LeafEfficient(tree.root()));
        System.out.println("Sum root to leaves Efficient 1 "+sumRoot2LeafEfficient1(tree.root()));
    }

    private static int sumRoot2Leaf(BTreeNode<Integer> root) {
        if(root == null) return 0;
        final List<String> paths = new LinkedList<>();
        dfs(root, new StringBuilder(), paths);
        int sum = 0;
        for(final String path: paths) sum += Integer.valueOf(path);
        return sum;
    }

    private static void dfs(BTreeNode<Integer> root, StringBuilder pathSoFar, List<String> paths) {
        if(root == null) return;
        pathSoFar.append(root.data);
        if(root.right == null && root.left == null) paths.add(pathSoFar.toString());
        dfs(root.left,pathSoFar,paths);
        dfs(root.right,pathSoFar,paths);
        pathSoFar.deleteCharAt(pathSoFar.length()-1);
    }


    private static int sumRoot2LeafEfficient(BTreeNode<Integer> root) {
        return dfs(root, 0);
    }

    private static int dfs(BTreeNode<Integer> root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.data;
        if (root.right == null && root.left == null) {
            return sum;
        }
        final int temp = sum;
        sum =  dfs(root.left, temp);
        sum += dfs(root.right, temp);
        return sum;
    }

    private static int sumRoot2LeafEfficient1(BTreeNode<Integer> root) {
        return dfs1(root, 0);
    }

    private static int dfs1(BTreeNode<Integer> root, int sum) {
        if (root == null) return 0;
        if (root.right == null && root.left == null) return sum * 10 + root.data;
        return   dfs(root.left, sum * 10 + root.data) + dfs(root.right, sum * 10 + root.data);
    }

}
