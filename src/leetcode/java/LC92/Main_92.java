package leetcode.java.LC92;

// See also: LC234, (Series) LC206

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Main_92 {

    public static void main(String[] args) {
        Main_92 main_92 = new Main_92();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;

//        ListNode res = main_92.reverseBetween(n1, 5, 5);
        ListNode res = main_92.reverseBetween_solution1(n1, 3, 5);
        while (res != null) {
            System.out.printf("%d->", res.val);
            res = res.next;
        }
    }


    // Solution1: Recursion
    // Time: O(N)
    // Space: O(N) for recursion stack
    // Ref: https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/di-gui-fan-zhuan-lian-biao-de-yi-bu-fen
    public ListNode reverseBetween_solution1(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }

        // 如果我们把 head 的索引视为 1，那么我们是想从第 m 个元素开始反转对吧；如果把 head.next 的索引视为 1 呢？那么相对于 head.next，反转的区间应该是从第 m - 1 个元素开始的；那么对于 head.next.next 呢……
        // 所以，接下去，前进到反转的起点触发 base case：
        head.next = reverseBetween_solution1(head.next, m - 1, n - 1);

        return head;
    }

    ListNode successor = null; // 后驱节点

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }



    // My Solution (Solution2 on leetcode.com):  Iteration
    // Time: O(N)
    // Space: O(1)
    public ListNode reverseBetween_mysolution(ListNode head, int m, int n) {
        // The whole list after reverse: head -> other nodes -> connectHead -> reversedHead -> reversedTail -> connectTail -> other nodes -> null
        if (head == null || head.next == null)
            return head;
        m--; n--;
        int m_copy = m;

        ListNode connectHead = null;
        ListNode curr = head;
        while (m > 0) {
            connectHead = curr;
            curr = curr.next;
            m--; n--;
        }
        ListNode reversedTail = curr;
        ListNode prev = null;
        while (n >= 0 && curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            n--;
        }
        ListNode reversedHead = prev;
        ListNode connectTail = curr;


        reversedTail.next = connectTail;
        if (m_copy > 0) {
            connectHead.next = reversedHead;
            return head;
        }

        return reversedHead;
    }
}
