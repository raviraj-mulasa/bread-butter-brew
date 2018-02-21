package net.geekscore.tree.binary;

import net.geekscore.tree.TreeUtil;

import java.time.Duration;
import java.time.Instant;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 *            _______3______
 *           /              \
 *       ___5__          ___1__
 *      /      \        /      \
 *     6      _2       0       8
 *   /  \
 *  7   4
 *
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3.
 * Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to
 * the LCA definition.
 *
 *          _______6______
 *         /              \
 *          ___2__          ___8__
 *         /      \        /      \
 *         0      _4       7       9
 *        /  \
 *        3   5
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6.
 * Another example is LCA of nodes 2 and 4 is 2
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        final  IBTree<Integer> bstTree = new BSTreeImpl<>();
        bstTree.insert(6);
        bstTree.insert(2);
        bstTree.insert(8);
        bstTree.insert(0);
        bstTree.insert(4);
        bstTree.insert(3);
        bstTree.insert(5);
        bstTree.insert(7);
        bstTree.insert(9);

        TreeUtil.print(bstTree);

        Instant start = Instant.now();
        System.out.println(lca(bstTree, bstTree.find(2), bstTree.find(8)).data); // 6
        System.out.println(lca(bstTree, bstTree.find(2), bstTree.find(4)).data); // 2
        System.out.println(lca(bstTree, bstTree.find(3), bstTree.find(9)).data); // 6
        System.out.println(lca(bstTree, bstTree.find(5), bstTree.find(0)).data); // 2
        System.out.println(Duration.between(start, Instant.now()).toNanos());

        System.out.println("------------------------------");
        start = Instant.now();
        System.out.println(lcaBST(bstTree, bstTree.find(2), bstTree.find(8)).data); // 6
        System.out.println(lcaBST(bstTree, bstTree.find(2), bstTree.find(4)).data); // 2
        System.out.println(lcaBST(bstTree, bstTree.find(3), bstTree.find(9)).data); // 6
        System.out.println(lcaBST(bstTree, bstTree.find(5), bstTree.find(0)).data); // 2
        System.out.println(Duration.between(start, Instant.now()).toNanos());



    }

    private static<T extends Comparable> BTreeNode<T> lca(IBTree<T> bstTree, BTreeNode<T> p, BTreeNode<T> q) {
        return lcaHelper(bstTree.root(), p, q);
    }

    private static<T extends Comparable> BTreeNode<T> lcaHelper(BTreeNode<T> node, BTreeNode<T> p, BTreeNode<T> q) {
        if(node == null || p == node || q == node)  return node;
        final BTreeNode<T> left = lcaHelper(node.left, p, q);
        final BTreeNode<T> right = lcaHelper(node.right, p, q);
        return left == null ? right : right == null ? left : node;
    }

    private static<T extends Comparable> BTreeNode<T> lcaBST(IBTree<T> bstTree, BTreeNode<T> p, BTreeNode<T> q) {
        return lcaBSTHelper(bstTree.root(), p, q);
    }

    private static<T extends Comparable> BTreeNode<T> lcaBSTHelper(BTreeNode<T> node, BTreeNode<T> p, BTreeNode<T> q) {
        final T max = p.data.compareTo(q.data) > 0 ? p.data : q.data;
        final T min = p.data.compareTo(q.data) < 0 ? p.data : q.data;
        if(node != null && node.data.compareTo(max) > 0) {
            return lcaBSTHelper(node.left, p, q);
        }
        else if(node != null && node.data.compareTo(min) < 0){
            return lcaBSTHelper(node.right, p, q);
        }
        return node;
    }

}
