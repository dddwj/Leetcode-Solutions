package leetcode.java.LC703;

// Ref: https://leetcode.com/problems/kth-largest-element-in-a-stream/discuss/298641/Java-solution-with-complete-explanation

import java.util.PriorityQueue;
import java.util.Queue;

public class Main_703 {

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[] {4,5,8,2});
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));

    }

}

class KthLargest {

    Queue<Integer> minHeap;
    int limit;

    public KthLargest(int k, int[] nums) {
        limit = k;
        minHeap = new PriorityQueue<Integer>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (limit > 0) {
            limit--;
            minHeap.offer(val);
        } else if (val > minHeap.peek()){
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }
}
