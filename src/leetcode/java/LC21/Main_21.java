package leetcode.java.LC21;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


public class Main_21 {
    public static void main(String[] args) {
        Main_21 main_21 = new Main_21();

    }

    // Solution2: Recursive Solution
    // Time: O(N+M)  Space: O(N+M), for recursion stack
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // Solution1: Iterative Solution
    // Time: O(N+M)  Space: O(1)
    public ListNode mergeTwoLists_Solution1(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        ListNode dummy = new ListNode(0);
        ListNode ans = dummy;
        while (p != null && q != null) {
            if (p.val < q.val) {
                ans.next = p;
                p = p.next;
            }
            else {
                ans.next = q;
                q = q.next;
            }
            ans = ans.next;
        }

        // Option 1: Two 'IF' Statement
        // if (p == null) {
        //     ans.next = q;
        // }
        // if (q == null) {
        //     ans.next = p;
        // }

        // Option 2: Ternary Expression
        ans.next = p == null ? q : p;

        return dummy.next;
    }
}
