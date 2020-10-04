package leetcode.java.LC725;


import java.util.Arrays;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


public class Main_725 {

    public static void main(String[] args) {
        Main_725 main_725 = new Main_725();
        ListNode n0 = new ListNode(0);
        ListNode cur = n0;
        for (int i = 0; i < 9; i++) {
            cur.next = new ListNode(i + 1);
            cur = cur.next;
        }


        // ListNode[] res = main_725.splitListToParts_solution2(n0, 4);
        ListNode[] res = main_725.splitListToParts_solution2(n0, 3);


        Arrays.stream(res).forEach(listNode -> {
            System.out.print("[");
            while (listNode != null) {
                System.out.print(listNode.val + ",");
                listNode = listNode.next;
            }
            System.out.print("]\n");
        });
    }


    // Solution1: Create New Lists
    // Ref: https://leetcode.com/problems/split-linked-list-in-parts/solution/
    // Time: O(N) + O(N)
    // Space: O(max(N, K)).  Extra space are needed for creating ListNode objects.
    public ListNode[] splitListToParts_solution1(ListNode root, int k) {
        /* Find list length: O(N) */
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            N++;
            cur = cur.next;
        }

        /* Create List: O(N) */
        ListNode[] ans = new ListNode[k];
        int width = N / k, rem = N % k;     // i.e: N%k parts have an extra item
        cur = root;
        for (int i = 0; i < k; i++) {
            ListNode head = new ListNode(0);
            ListNode write = head;
            for (int j = 0; j < width + (i < rem ? 1 : 0); j++) {
                write.next = new ListNode(cur.val);
                write = write.next;
                cur = cur.next;
            }
            ans[i] = head.next;
        }

        return ans;
    }



    // Solution2: Split Input List
    // Ref: https://leetcode.com/problems/split-linked-list-in-parts/solution/
    // Time: O(N) + O(N)
    // Space: O(k).   No extra space are needed.
    public ListNode[] splitListToParts_solution2(ListNode root, int k) {
        /* Find list length: O(N) */
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            N++;
            cur = cur.next;
        }

        /* Create Answer list: O(N) */
        ListNode[] ans = new ListNode[k];
        int width = N / k, rem = N % k;     // i.e: N%k parts have an extra item
        cur = root;
        for (int i = 0; i < k; i++) {
            ListNode head = cur;
            for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; j++) {
                cur = cur.next;
            }
            if (cur != null) {
                ListNode prev = cur;
                cur = cur.next;
                prev.next = null;
            }
            ans[i] = head;
        }

        return ans;
    }
}
