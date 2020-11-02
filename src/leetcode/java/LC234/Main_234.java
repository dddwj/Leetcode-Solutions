package leetcode.java.LC234;

// See also: LC206, LC92
// Met in Apple Interview @2020.10.26

// Great Tutorial: https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/2.2-shou-ba-shou-shua-lian-biao-ti-mu-xun-lian-di-gui-si-wei/pan-duan-hui-wen-lian-biao

import leetcode.java.Utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Main_234 {
    public static void main(String[] args) {
        Main_234 main_234 = new Main_234();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(1);

        n1.next = n2; n2.next = n4; n4.next = n5; n5.next = null;
        System.out.println(main_234.isPalindrome_solution1(n1));  // true
        // System.out.println(main_234.isPalindrome_solution2(n1));  // true

        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;  n5.next = null;
        System.out.println(main_234.isPalindrome_solution1(n1));  // true
        // System.out.println(main_234.isPalindrome_solution2(n1));  // true

        n3.next = n4; n4.next = n5; n5.next = null;
        System.out.println(main_234.isPalindrome_solution1(n3));  // false
        // System.out.println(main_234.isPalindrome_solution2(n3));  // false
    }


    // Solution1: Copy and validate
    // Time: O(N)
    // Space: O(1)
    public boolean isPalindrome_solution1(ListNode head) {
        if (head == null)
            return true;

        // store linked list to array
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        // isPanlindrome
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right)))
                return false;
            left++;
            right--;
        }

        return true;
    }


    // Solution2: Two Pointers + ReverseList
    // Time: O(N)
    // Space: O(1)
    public boolean isPalindrome_solution2(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode newHead = reverseList(slow);

        while (newHead != null) {
            if (newHead.val != head.val)
                return false;
            newHead = newHead.next;
            head = head.next;
        }

        return true;
    }

    ListNode reverseList(ListNode head) {
        if (head.next == null)
            return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


    // Solution3: Recursion
    // Time: O(N)
    // Space: O(N)
    // Ref: https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/2.2-shou-ba-shou-shua-lian-biao-ti-mu-xun-lian-di-gui-si-wei/pan-duan-hui-wen-lian-biao
    ListNode left;
    boolean isPalindrome_solution3(ListNode head) {
        if (head == null || head.next == null)
            return true;
        left = head;
        return traverse(head);
    }

    boolean traverse(ListNode right) {
        if (right == null)
            return true;
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }
}
