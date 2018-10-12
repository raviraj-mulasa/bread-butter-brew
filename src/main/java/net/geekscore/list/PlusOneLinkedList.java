package net.geekscore.list;

/**
 * Given a non-negative integer represented as non-empty a singly linked list of digits,
 * plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example:
 * Input: 1->2->3
 * Output: 1->2->4
 */
public class PlusOneLinkedList {

    public static void main(String[] args) {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);
        SingleListUtil.printSingleList(singleLinkedList);

        SingleLinkedList<Integer> singleLinkedList1 = new SingleLinkedList<Integer>();
        singleLinkedList.add(9);
        singleLinkedList.add(9);
        singleLinkedList.add(9);
        singleLinkedList.add(9);

    }

    private static ListNode plusOne(ListNode<Integer> head) {
        return null;
    }
}
