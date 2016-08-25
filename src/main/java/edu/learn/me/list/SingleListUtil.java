package edu.learn.me.list;

/**
 * Created by ravirajmulasa on 8/23/16.
 *
 * https://en.wikipedia.org/wiki/Cycle_detection
 */
public final class SingleListUtil {

    private SingleListUtil() {
    }

    public static final boolean findIfLoopExistsUsingFloyds(final SingleListNode head) {
        return false;
    }

    public static final boolean findIfLoopExistsUsing(final SingleListNode head) {
        return false;
    }

    public static void printSingleList(final SingleListNode head) {
        SingleListNode curr = head;
        while (curr != null) {
            System.out.print(String.format("%d-->", curr.getData()));
            final SingleListNode next = curr.getNext();
            if(next == head) {
                System.out.print(String.format("%d", next.getData()));
                break;
            }
            curr = next;
        }
    }

}
