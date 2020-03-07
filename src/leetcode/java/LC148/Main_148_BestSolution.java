package leetcode.java.LC148;

public class Main_148_BestSolution {

    public static void main(String[] args) {
        Main_148_BestSolution main_148_bestSolution = new Main_148_BestSolution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l4.next = l2;
        l2.next = l1;
        l1.next = l3;
        main_148_bestSolution.sortList_solution2(l4);
    }

    // Solution2: MergeSort (Bottom-Up)  (Non-recursion)
    // Space: O(1)
    // Time: O(NlogN)  In each level, each node is visited by at maximum twice. And there are log(n) level.
    // Ref: https://leetcode.com/problems/sort-list/discuss/46767/Java-solution-with-strict-O(1)-auxiliary-space-complexity
    private class MergeHelper {
        public ListNode newHead;
        public ListNode newTail;
    }

    public ListNode sortList_solution2(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode dummyHeadOne = new ListNode(0);
        ListNode dummyHeadTwo = new ListNode(0);
        ListNode dummySortedHead = new ListNode(0);
        ListNode dummySortedLast = dummySortedHead;
        ListNode unvisitedNode = head;
        MergeHelper mergeRst = new MergeHelper();

        int listLength = 0;
        int level = 0;
        while (unvisitedNode != null && unvisitedNode.next != null) {       // The First(0-th) Level
            unvisitedNode = addNode(dummyHeadOne, unvisitedNode, 1<<level);
            unvisitedNode = addNode(dummyHeadTwo, unvisitedNode, 1<<level);
            merge(dummyHeadOne.next, dummyHeadTwo.next, mergeRst);
            dummySortedLast.next = mergeRst.newHead;
            dummySortedLast = mergeRst.newTail;
            listLength += 2;
        }
        if (unvisitedNode != null) {
            dummySortedLast.next = unvisitedNode;
            listLength++;
        }
        level++;

        while (listLength > 1 << level) {   // The remaining levels
            dummySortedLast = dummySortedHead;
            unvisitedNode = dummySortedHead.next;
            while (unvisitedNode != null) {
                unvisitedNode = addNode(dummyHeadOne, unvisitedNode, 1<<level);
                unvisitedNode = addNode(dummyHeadTwo, unvisitedNode, 1<<level);
                merge(dummyHeadOne.next, dummyHeadTwo.next, mergeRst);
                dummySortedLast.next = mergeRst.newHead;
                dummySortedLast = mergeRst.newTail;
            }
            level++;
        }

        return dummySortedHead.next;
    }

    /*
     add at max #"count" nodes into "head" from "source"
     return the new position of source after adding.
    */
    private ListNode addNode ( ListNode head, ListNode source, int count ) {
        while (count > 0 && source != null) {
            head.next = source;
            head = head.next;
            source = source.next;
            count --;
        }
        head.next = null;
        return source;
    }

    /* merge listOne and listTwo.
       Save the sorted list head into rst.newHead
       Save the last node of the sorted list into rst.newTail
    */
    private void merge(ListNode listOne, ListNode listTwo, MergeHelper rst) {
        ListNode dummyHead = new ListNode(0);
        ListNode lastNode = dummyHead;
        while (listOne != null && listTwo != null) {
            if (listOne.val < listTwo.val) {
                lastNode.next = listOne;
                listOne = listOne.next;
            } else {
                lastNode.next = listTwo;
                listTwo = listTwo.next;
            }
            lastNode = lastNode.next;
        }
        while (listOne != null) {
            lastNode.next = listOne;
            listOne = listOne.next;
            lastNode = lastNode.next;
        }
        while (listTwo != null) {
            lastNode.next = listTwo;
            listTwo = listTwo.next;
            lastNode = lastNode.next;
        }
        rst.newHead = dummyHead.next;
        rst.newTail = lastNode;
    }


}
