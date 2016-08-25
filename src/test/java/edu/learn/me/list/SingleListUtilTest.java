package edu.learn.me.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ravirajmulasa on 8/23/16.
 */
public class SingleListUtilTest {

    private SingleListNode normalLinkedList = null;

    private SingleListNode loopyLinkedList = null;

    @Before
    public void setUp() {



        SingleListUtil.printSingleList(createLinkedList(23, true));
    }

    private final SingleListNode buildLinkedListWithLoop ( ) {

      return null;
    }

    @Test
    public void testLoopExistenceFloyds() {
        Assert.assertFalse(SingleListUtil.findIfLoopExistsUsingFloyds(null));
    }

    private final SingleListNode createLinkedList(final int nodeCount, boolean isCircular) {

        SingleListNode head    = null;
        SingleListNode prev    = null;

        for(int i=1 ; i <= nodeCount; i++) {
            final SingleListNode curr = new SingleListNode(i, null);
            if(i == 1) {
                head = prev = curr;
            }
            if(i == nodeCount) {
                curr.setNext(isCircular ? head : null);
            }
            prev.setNext(curr);
            prev = curr;
        }
        return head;
    }


    private final SingleListNode createCircularLinkedList(final int nodeCount) {
        return null;
    }
}
