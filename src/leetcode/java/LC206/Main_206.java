package leetcode.java.LC206;

// See also: LC234, (Series) LC92

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Main_206 {

    public static ListNode reverseList(ListNode head) {  // Approach 1 (Iterative)
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static ListNode reverseList2(ListNode head) {   // Approach 2 (Recursive)
        if (head.next == null)
            return head;
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


    public static void printList(ListNode head) {
        ListNode node = head;
        while (node.next != null) {
            System.out.print(" " + node.val + "->");
            node = node.next;
        }
        System.out.print(" " + node.val + "\n");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(10);
        head.next = new ListNode(20);
        head.next.next = new ListNode(30);
        head.next.next.next = new ListNode(40);
        printList(head);
//        printList(reverseList(head));
        printList(reverseList2(head));
    }
}
