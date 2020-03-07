package leetcode.java.LC148;

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}


public class Main_148 {

    // GREAT Tutorial: https://www.bilibili.com/video/av31197206/

    public static void main(String[] args) {
        Main_148 main_148 = new Main_148();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l4.next = l2;
        l2.next = l1;
        l1.next = l3;
        main_148.sortList_solution1(l4);
    }


    // Solution2: MergeSort (Bottom-Up)  (Non-recursion)
    // Space: O(1)
    // Time: O(NlogN)  In each level, each node is visited by at maximum twice. And there are log(n) level.
    // Ref: https://leetcode.com/problems/sort-list/discuss/46767/Java-solution-with-strict-O(1)-auxiliary-space-complexity

    // CHECK OUT: ./Main_148_BestSolution.java



    // Solution1: Merge Sort (Top-Down)  (Recursion)
    // Time: O(N * logN)  [merge * recursion = O(N) * O(logN)]
    // Space: O(logN)   [recursion stack: O(logN)
    // Ref: https://leetcode.com/problems/sort-list/discuss/46714/Java-merge-sort-solution
    public ListNode sortList_solution1(ListNode head) {
        // 0 or 1 element
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;   // to cut off at the middle

        ListNode l1 = sortList_solution1(head);
        ListNode l2 = sortList_solution1(slow);

        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null)
            tail.next = l1;
        if (l2 != null)
            tail.next = l2;
        return dummy.next;
    }




    // My Solution: HeapSort
    // Time: O(NlogN)  Space: O(N)
    public ListNode sortList_mysolution(ListNode head) {
        if (head == null)
            return head;

        PriorityQueue<ListNode> heap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

        while (head != null) {
            heap.add(head);
            head = head.next;
        }

        ListNode first = heap.poll();
        ListNode curr = first;
        while (!heap.isEmpty()) {
            curr.next = heap.poll();
            curr = curr.next;
        }
        curr.next = null;

        return first;
    }

}
