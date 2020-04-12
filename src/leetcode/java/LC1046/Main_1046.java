package leetcode.java.LC1046;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_1046 {
    public static void main(String[] args) {
        Main_1046 main_1046 = new Main_1046();
        System.out.println(main_1046.lastStoneWeight_mysolution(new int[]{2, 7, 4, 1, 8, 1}));
    }

    // My Solution: Max Heap
    // Time: O(N)建堆,    O(logN)调堆
    // Space: O(N)
    public int lastStoneWeight_mysolution(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(stones.length, (l1, l2) -> l2 - l1);
        for (int weight : stones) {
            pq.offer(weight);
        }

        while (pq.size() >= 2) {
            int stone1 = pq.remove();
            int stone2 = pq.remove();
            if (stone1 != stone2) {
                pq.offer(Math.abs(stone1 - stone2));
            }
        }

        if (pq.size() == 1) {
            return pq.remove();
        } else if (pq.size() == 0) {
            return 0;
        } else {
            return -1;
        }
    }



    // Solution: Sort
    // Ref: https://leetcode-cn.com/problems/last-stone-weight/solution/java-0ms-6xing-yi-li-jie-by-rabbitzhao/
    // Time: O(N*NlogN)
    // Space: O(NlogN) for QuickSort
    public int lastStoneWeight(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }

        int index = stones.length - 1;
        Arrays.sort(stones);
        while(stones[index - 1] != 0) {
            int x = stones[index - 1];
            int y = stones[index];
            if (x == y) {
                stones[index] = stones[index - 1] = 0;
            } else {
                stones[index - 1] = 0;
                stones[index] = y - x;
            }
            Arrays.sort(stones);
        }
        return stones[stones.length - 1];
    }
}
