package leetcode.java.LC2;


import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


public class Main_2 {

    // Ref: LC415, LC43

    public static void main(String[] args) {
        Main_2 main_2 = new Main_2();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);
        ListNode n9 = new ListNode(9);
        ListNode n0 = new ListNode(0);

//        n2.next = n4;
//        n5.next = n6; n6.next = n1;

        main_2.addTwoNumbers(n5, n5);
    }



    // Solution: Elementary Math
    // Time: O(max(N, M))
    // Space: O(max(N, M))
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            if (l1 == null)
                l1 = new ListNode(0);
            if (l2 == null)
                l2 = new ListNode(0);

            int sum = l1.val + l2.val + carry;

            curr.next = new ListNode(sum % 10);
            carry = sum / 10;
            curr = curr.next;


            l1 = l1.next;
            l2 = l2.next;
        }

        if (carry > 0) {        // 求和运算最后可能出现额外的进位，这一点很容易被遗忘, Test Case: 5 + 5
            curr.next = new ListNode(carry);
        }

        return dummy.next;
    }



}
