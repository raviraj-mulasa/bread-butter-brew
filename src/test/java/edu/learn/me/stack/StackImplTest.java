package edu.learn.me.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ravirajmulasa on 8/27/16.
 */
public class StackImplTest {


    @Test
    public void testPush() {
        final IStack<Integer> stack = new StackImpl<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        Assert.assertEquals(new Integer(9), stack.peek());
    }

    @Test
    public void testPop() {
        final IStack<Integer> stack = new StackImpl<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        Integer ele = null;
        for (int i = 0; i < 5; i++) {
            ele = stack.pop();
        }
        Assert.assertEquals(new Integer(ele - 1), stack.peek());
    }

    @Test
    public void testEmpty() {
        final IStack<Integer> stack = new StackImpl<>();
        Assert.assertTrue(stack.isEmpty());
    }


    @Test
    public void testClear() {
        final IStack<Integer> stack = new StackImpl<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        Assert.assertFalse(stack.isEmpty());
        stack.clear();
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void testPrint() {
        final IStack<Integer> stack = new StackImpl<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        stack.print();
    }

    @Test
    public void testDoubleCapacity() {
        final IStack<Integer> stack = new StackImpl<Integer>();
        Assert.assertEquals(256, stack.capacity());
        for (int i = 0; i < 220; i++) {
            stack.push(i);
        }
        Assert.assertEquals(512, stack.capacity());
    }


}
