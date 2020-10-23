package leetcode.java.LC23;

import java.util.Comparator;
import java.util.PriorityQueue;

// See also: (Series) LC21

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Main_23 {


    public static void main(String[] args) {
        Main_23 main_23 = new Main_23();

        ListNode l1_1 = new ListNode(1);
        ListNode l1_4 = new ListNode(4);
        ListNode l1_5 = new ListNode(5);
        l1_1.next = l1_4; l1_4.next = l1_5;

        ListNode l2_1 = new ListNode(1);
        ListNode l2_3 = new ListNode(3);
        ListNode l2_4 = new ListNode(4);
        l2_1.next = l2_3; l2_3.next = l2_4;

        ListNode l3_2 = new ListNode(2);
        ListNode l3_6 = new ListNode(6);
        l3_2.next = l3_6;

        ListNode res = main_23.mergeKLists_solution3(new ListNode[]{null, l1_1, l2_1, l3_2});

        while (res != null) {
            System.out.printf("%d->", res.val);
            res = res.next;
        }
    }


    // Solution1 on Leetcode-cn: Brute Force
    // Time: O(K^2 * N)
    // Space: O(1) or O(M), [O(M): solution2 of mergeTwoLists , where M = len( list )]
    public ListNode mergeKLists_solution1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode res = lists[0];
        for (int i = 1; i < lists.length; i++) {
            res = mergeTwoLists_solution2(res, lists[i]);
        }
        return res;
    }


    // Solution2 on Leetcode-cn (opt1):
    // Time: O(Nlogk)
    // Space: O(1) (use mergeTwoLists_solution1)
    // Ref: https://leetcode.wang/leetCode-23-Merge-k-Sorted-Lists.html
    public ListNode mergeKLists_solution21(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        int interval = 1;
        while(interval < lists.length){
            for (int i = 0; i + interval < lists.length; i = i + interval*2) {
                lists[i] = mergeTwoLists_solution1(lists[i], lists[i+interval]);
            }
            interval *= 2;
        }

        return lists[0];
    }


    // Solution2 on Leetcode-cn (opt2):
    // Time: O(K*N*logK)
    // Space:  O(logK) for recursion stack
    // Ref: https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
    public ListNode mergeKLists_solution22(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists_solution1(merge(lists, l, mid), merge(lists, mid + 1, r));
    }



    // Solution3 on Leetcode-cn: Divide and Conquer
    // Ref: https://leetcode.wang/leetCode-23-Merge-k-Sorted-Lists.html
    // Time: 假如总共有 N 个节点，每个节点入队出队都需要 log（k），所有时间复杂度是 O（N log（k））, where K = len(lists)
    // Space: 优先队列需要 O（k）的复杂度。
    public ListNode mergeKLists_solution3(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        // 建立队列
        PriorityQueue<ListNode> q = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;     // NOTE: Java默认是小根堆。这里重写compare方法，建立一个：根为最小val的ListNode堆。
            }
        });

        for (ListNode list : lists) {
            if (list != null)
                q.offer(list);
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!q.isEmpty()) {
            // 出队列
            ListNode node = q.poll();
            cur.next = node;
            cur = cur.next;

            // 判断当前链表是否为空，不为空就将新元素入队
            if (node.next != null) {
                q.offer(node.next);
            }
        }
        return dummy.next;
    }


    /*************/
    /* From LC21 */
    /*************/

    // Linear Merge, See also: LC21, Time: O(N + M), Space: O(1)
    private ListNode mergeTwoLists_solution1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }

    // Recursion Merge, See also: LC21, Time: O(N + M), Space: O(N + M)
    private ListNode mergeTwoLists_solution2(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        else if (l2 == null)
            return l1;
        else if (l1.val > l2.val) {
            l2.next = mergeTwoLists_solution2(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists_solution2(l1.next, l2);
            return l1;
        }
    }


}
