package leetcode.java.LC141;

import java.util.HashSet;
import java.util.Set;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Main_141 {

    // Solution1: Hash Table
    public boolean hasCycle_S1(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        while (head != null) {
            if (visited.contains(head))
                return true;
            visited.add(head);
            head = head.next;
        }
        return false;
    }

    // Solution2: Slow Fast Pointers
    public boolean hasCycle_S2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (fast.next == null)
                return false;
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    // Solution3: Brute Force with Time Limits
    public boolean hasCycle(ListNode head) {
        long startTime = System.currentTimeMillis();
        while (head != null) {
            head = head.next;
            if (System.currentTimeMillis() - startTime > 500) {
                return true;
            }
        }
        return false;
    }

    public static void main (String[] args) {
        ListNode l1 = new ListNode(10);
        ListNode l2 = new ListNode(10);
        ListNode l3 = new ListNode(10);
        ListNode l4 = new ListNode(10);
        ListNode l5 = new ListNode(10);

        l1.next = l2; l2.next = l3; l3.next = l4; l4.next = l5; l5.next = null;


        Main_141 main_141 = new Main_141();
        System.out.println(main_141.hasCycle(l1));
    }
}
