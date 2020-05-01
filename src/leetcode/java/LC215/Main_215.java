package leetcode.java.LC215;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Main_215 {

    // Ref: LC215, LC347, 剑指offer P209

    public static void main(String[] args) {
        Main_215 main_215 = new Main_215();
        System.out.println(main_215.findKthLargest_solution2(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(main_215.findKthLargest_solution2(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }


    // Solution1: Min-Heap (of size K)
    // Time: O( NlogK )
    // Space: O(K)
    // Ref: https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/
    public int findKthLargest_solution1(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>((v1, v2) -> v1 - v2);

        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.peek();
    }






    // Solution2: Quick Select
    // Time:    worst: O(N*N)  avg: O(N)
    // Space:   O(1)
    public int findKthLargest_solution2(int[] nums, int k) {
        this.nums = nums;
        int size = nums.length;
        // kth largest is the (N-k)th smallest
        return quickselect(0, size - 1, size - k);
    }

    int[] nums;

    private int quickselect(int left, int right, int k_smallest) {
        if (left == right) {
            return this.nums[left];
        }

        Random random = new Random(666);
        int pivot_index = left + random.nextInt(right - left);

        pivot_index = partition(left, right, pivot_index);

        if (k_smallest == pivot_index) {
            return this.nums[k_smallest];
        } else if (k_smallest < pivot_index) {
            return quickselect(left, pivot_index - 1, k_smallest);
        } else  // (k_smallest > pivot_index)
            return quickselect(pivot_index + 1, right, k_smallest);
    }

    private int partition(int left, int right, int pivot_index) {
        int pivot = this.nums[pivot_index];

        swap(pivot_index, right);
        int store_index = left;

        for (int i = left; i <= right; i++) {
            if (this.nums[i] < pivot) {
                swap(store_index, i);
                store_index++;
            }
        }

        swap(store_index, right);

        return store_index;
    }

    private void swap(int a, int b) {
        int temp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = temp;
    }







    // My Solution: Max-heap (of size N)
    // Time: O((K+N)logN)
    // Space: O(N)
    public int findKthLargest_mysolution(int[] nums, int k) {
        if (nums == null) {
            return -1;
        }

        Queue<Integer> queue = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
        }

        System.out.println(queue);

        int res = -1;
        for (int i = k; i > 0; i--) {
            res = queue.poll();
        }

        return res;
    }


}