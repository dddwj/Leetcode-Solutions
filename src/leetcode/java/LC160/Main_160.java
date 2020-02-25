package leetcode.java.LC160;


import java.util.HashSet;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Main_160 {
    public static void main(String[] args) {
        Main_160 main_160 = new Main_160();
        ListNode headA = new ListNode(1000);
        ListNode c = new ListNode(3);
        ListNode headB = new ListNode(1111);
        headA.next = c;
        headB.next = c;
//        System.out.println(main_160.getIntersectionNode_solution2(c, c).val);
        System.out.println(main_160.getIntersectionNode_solution2(headA, headB).val);
    }


    // Key point to this question:
    //
    // The Address of Intersection Nodes are the same!


    // Solution1: Brute Force
    // Time: O(M*N)  Space: O(1)
    public ListNode getIntersectionNode_solution1(ListNode headA, ListNode headB) {
        // Corner Case
        if (headA == null || headB == null)
            return null;

        // Init data
        ListNode p1 = headA;

        // Brute Force
        while (p1 != null) {
            ListNode p2 = headB;
            while (p2 != null) {
                if (p1.equals(p2))
                    return p1;
                p2 = p2.next;
            }
            p1 = p1.next;
        }
        return null;
    }


    // Solution2: Brute Force -- Hash Table
    // Time: O(M+N)
    // Space: O(M) or O(N)
    public ListNode getIntersectionNode_solution2(ListNode headA, ListNode headB) {
        // Corner Case
        if (headA == null || headB == null)
            return null;

        HashSet<ListNode> setA = new HashSet<>();
        while (headA != null) {
            setA.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (setA.contains(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }



    // Solution3: Two Pointers, 追及问题
    // Time: O(N+M)
    // Space: O(1)
    public ListNode getIntersectionNode_solution3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {      // 如果有intersection，总有机会相遇
            pA = pA.next;
            pB = pB.next;
            if (pA == null && pB == null) {
                return null;
            }
            if (pA == null) {    // 当 pA 到达链表的尾部时，将它重定位到链表 B 的头结点
                pA = headB;
            }
            if (pB == null) {    // 当 pB 到达链表的尾部时，将它重定位到链表 A 的头结点
                pB = headA;
            }
        }
        return pA;
    }
}
