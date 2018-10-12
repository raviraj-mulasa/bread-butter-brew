package net.geekscore.list;

/**
 * Created by ravirajmulasa on 8/23/16.
 *
 * https://en.wikipedia.org/wiki/Cycle_detection
 */
public final class SingleListUtil {

    private SingleListUtil() {
    }

    public static final boolean findIfLoopExistsUsingFloyds() {
        return false;
    }

    public static final boolean findIfLoopExistsUsing() {
        return false;
    }

    public static<T extends Comparable> void printSingleList(final SingleLinkedList<T> list) {
        if(null == list) return;
        ListNode<T> curr = list.getHead();
        while (curr != null) {
            System.out.print(String.format("%d-->", curr.getData()));
            final ListNode<T> next = curr.getNext();
            if(next == list.getHead()) {
                System.out.print(String.format("%d", next.getData()));
                break;
            }
            curr = next;
        }
        System.out.println();
    }



}
