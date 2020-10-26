package memos;

// See also: LC206

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


public class ReverseLinkedList {

    public ListNode reverse_iteration(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public ListNode reverse_recursion(ListNode head) {
        if (head.next == null)
            return head;

        ListNode last = reverse_recursion(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
