package edu.learn.me.tree.binary;

import edu.learn.me.tree.TreeUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ravirajmulasa on 8/28/16.
 */
public class BSTreeImplTest {

    @Test
    public void testInsert() {
        final IBTree<Integer> bstTree = new BSTreeImpl<>();
        bstTree.insert(1);
        bstTree.insert(-2);
        bstTree.insert(3);
        System.out.println();
    }

    @Test
    public void testInOrder() {
        final IBTree<Integer> bstTree = new BSTreeImpl<>();
        bstTree.insert(1);
        bstTree.insert(6);
        bstTree.insert(5);
        bstTree.insert(3);
        bstTree.insert(2);
        bstTree.insert(4);
        System.out.println(TreeUtil.inOrder(bstTree));
    }

    @Test
    public void testPreOrder() {
        final IBTree<Integer> bstTree = new BSTreeImpl<>();
        bstTree.insert(1);
        bstTree.insert(6);
        bstTree.insert(5);
        bstTree.insert(3);
        bstTree.insert(2);
        bstTree.insert(4);
        System.out.println(TreeUtil.preOrder(bstTree));
    }

//    @Test
//    public void testPostOrder() {
//        final IBTree<Integer> bstTree = new BSTreeImpl<>();
//        bstTree.insert(1);
//        bstTree.insert(6);
//        bstTree.insert(5);
//        bstTree.insert(3);
//        bstTree.insert(2);
//        bstTree.insert(4);
//        System.out.println(bstTree.postOrder());
//    }

    @Test
    public void testFind() {
        final IBTree<Integer> bstTree = new BSTreeImpl<>();
        bstTree.insert(1);
        bstTree.insert(6);
        bstTree.insert(5);
        bstTree.insert(3);
        bstTree.insert(2);
        bstTree.insert(4);
        Assert.assertNotNull(bstTree.find(1));
        Assert.assertNull(bstTree.find(7));
    }
}
