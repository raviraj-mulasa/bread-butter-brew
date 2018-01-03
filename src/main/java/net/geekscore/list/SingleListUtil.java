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

    public static final void reverse(final SingleLinkedList list) {
        final ListNode head  = list.getHead();
        if(head == null) {
             return;
         }
         ListNode prev = null, current = head, next = null;
         while (current != null) {
             /**
              * pointer modification
              */
             next = current.getNext();
             current.setNext(prev);
             /**
              * then move forward
              */
             prev = current;
             current = next;
         }
         list.setHead(prev);
    }


    public static void printSingleList(final SingleLinkedList list) {
        ListNode<Integer> curr = list.getHead();
        while (curr != null) {
            System.out.print(String.format("%d-->", curr.getData()));
            final ListNode<Integer> next = curr.getNext();
            if(next == list.getHead()) {
                System.out.print(String.format("%d", next.getData()));
                break;
            }
            curr = next;
        }
    }



}
