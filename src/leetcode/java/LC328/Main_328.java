package leetcode.java.LC328;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Main_328 {

    public static void main(String[] args) {
        Main_328 main_328 = new Main_328();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        main_328.oddEvenList_mysolution(n1);
        ListNode n8 = new ListNode(8);
        main_328.oddEvenList_mysolution(n1);
    }


    // My Solution: Naive
    // Time: O(N)
    // Space: O(1)
    public ListNode oddEvenList_mysolution(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // To mark the head and tail of `odd list` & `even list`
        ListNode oddTail = head,
                evenHead = head.next,
                evenTail = head.next;

        // To act as an iterator.
        ListNode oddPtr = head.next.next,
                evenPtr;

        while (oddPtr != null) {  // while not reaching the end of the list
            // store the evenPtr
            evenPtr = oddPtr.next;

            // add a node to the `odd list`, and update the oddTail
            oddTail.next = oddPtr;
            oddTail = oddTail.next;

            // connect the `odd list` with `even list`
            oddTail.next = evenHead;

            // add a node to the `even list`, and update the evenTail
            evenTail.next = evenPtr;
            evenTail = evenPtr;

            // use the evenPtr that stored before
            if (evenPtr == null)   // reach the end of the list
                return head;
            else
                oddPtr = evenPtr.next;  // keep iterating
        }

        return head;
    }


    // Solution1 on leetcode: Naive (Concise Version)  (Similar to my solution)
    // Time: O(N)
    // Space: O(1)
    // Ref: https://leetcode.com/problems/odd-even-linked-list/solution/
    public ListNode oddEvenList_solution1(ListNode head) {
        if (head == null)
            return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
