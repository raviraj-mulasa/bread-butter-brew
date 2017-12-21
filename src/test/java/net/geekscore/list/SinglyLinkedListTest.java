package net.geekscore.list;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ravirajmulasa on 8/23/16.
 */
public class SinglyLinkedListTest {

    @Test
    public void testPrint() {
        final SinglyLinkedList singlyLinkedList = createSinglyLinkedList(11);
        singlyLinkedList.print();
    }

    @Test
    public void testReverse() {
        final SinglyLinkedList singlyLinkedList = createSinglyLinkedList(11);
        singlyLinkedList.print();
        SingleListUtil.reverse(singlyLinkedList);
        singlyLinkedList.print();
    }

    @Test
    public void testSize() {
        final SinglyLinkedList singlyLinkedList = createSinglyLinkedList(0);
        Assert.assertEquals(0, singlyLinkedList.size());
    }

    @Test
    public void testGet() {
        final SinglyLinkedList singlyLinkedList = createSinglyLinkedList(100);
        Assert.assertEquals(100, singlyLinkedList.get(99));
    }

    @Test
    public void testDelete() {
        final SinglyLinkedList singlyLinkedList = createSinglyLinkedList(100);
        Assert.assertEquals(100, singlyLinkedList.get(99));
        Assert.assertEquals(100, singlyLinkedList.delete(99));
        Assert.assertEquals(null, singlyLinkedList.get(99));
        Assert.assertEquals(99, singlyLinkedList.get(98));
        Assert.assertEquals(47, singlyLinkedList.get(46));
    }

    @Test
    public void testFind() {
        final SinglyLinkedList singlyLinkedList = createSinglyLinkedList(100);
        Assert.assertEquals(99, singlyLinkedList.find(new Integer(100)));
    }


    @Test
    public void testSet() {
        final SinglyLinkedList singlyLinkedList = createSinglyLinkedList(100);
        Assert.assertEquals(100, singlyLinkedList.get(99));
        singlyLinkedList.set(99, 1234);
        Assert.assertEquals(1234, singlyLinkedList.get(99));
    }


    private final SinglyLinkedList createSinglyLinkedList(final int nodeCount) {
        SinglyLinkedList<Integer> singlyLinkedList    = new SinglyLinkedList<Integer>();
        for(int i=1 ; i <= nodeCount; i++) {
            singlyLinkedList.add(i);
        }
        return  singlyLinkedList;
    }
}
