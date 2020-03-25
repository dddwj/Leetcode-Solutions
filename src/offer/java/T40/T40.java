package offer.java.T40;

import java.util.*;

public class T40 {

    // Reference: LC347, LC215
    //            剑指offer P209
    // 经典TopK问题

    public static void main(String[] args) {
        T40 t40 = new T40();
        System.out.println(Arrays.toString(t40.getLeastNumbers_solution3(new int[]{0, 1, 2, 1}, 2)));
        System.out.println(Arrays.toString(t40.getLeastNumbers_solution3(new int[]{0,0,0,2,0,5}, 0)));
    }



    // 解法一： 快排思想 P210
    // Time: O(N)
    // Space: O(1), 但在原地更改数组了
    public int[] getLeastNumbers_solution1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || arr.length < k || k == 0) {
            return new int[0];
        }

        int n = arr.length;
        int start = 0, end = n - 1;
        int index = partition(arr, n, start, end);
        while (index != k - 1) {
            if (index > k -1) {
                end = index - 1;
                index = partition(arr, n, start, end);
            } else if (index < k - 1) {
                start = index + 1;
                index = partition(arr, n, start, end);
            }
        }

        return Arrays.copyOf(arr, k);
    }

    private int partition(int[] arr, int n, int start, int end) {       // TODO: 还不太理解partition函数, refer to LC215
        Random random = new Random(20);
        int index = random.nextInt(end - start + 1) + start;
        swap(arr, index, end);

        int small = start - 1;
        for (index = start; index < end; index++) {
            if (arr[index] < arr[end]) {
                small++;
                if (small != index) {
                    swap(arr, index, small);
                }
            }
        }

        small++;
        swap(arr, small, end);

        return small;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }




    // 解法二：大根堆, 适合处理海量数据
    // Time: O(NlogK)
    // Space: O(K)
    // ref: https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/
    public int[] getLeastNumbers_solution2(int[] arr, int k) {
        if (arr == null || k == 0 || arr.length == 0) {
            return new int[0];
        }

        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()){
                pq.poll();
                pq.offer(num);
            }
        }

        int[] res = new int[pq.size()];
        int idx = 0;
        for (int num: pq) {
            res[idx++] = num;
        }
        return res;
    }





    // 解法三：BST
    // ref: https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/
    public int[] getLeastNumbers_solution3(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();   // key: 数字，value: 该数字的个数
        int cnt = 0;    // cnt: 当前map总存了多少数字
        for (int num : arr) {
            //  1. 遍历数组，若当前map中的数字个数小于k，则map中当前数字对应个数+1
            if (cnt < k) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                cnt++;
                continue;
            }

            // 2. 否则，取出map中最大的Key（即最大的数字), 判断当前数字与map中最大数字的大小关系：
            //    若当前数字比map中最大的数字还大，就直接忽略；
            //    若当前数字比map中最大的数字小，则将当前数字加入map中，并将map中的最大数字的个数-1。
            Map.Entry<Integer, Integer> entry = map.lastEntry();        // TreeMap: 默认按key的升序排列
            if (entry.getKey() > num) {
                // 加入更小者
                map.put(num, map.getOrDefault(num, 0) + 1);

                // 移除原来树中的更大者
                if (entry.getValue() == 1) {
                    map.pollLastEntry();
                } else {
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            }
        }

        // 最后返回map中的元素
        int[] res = new int[k];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            while (freq > 0) {
                res[idx++] = entry.getKey();
                freq--;
            }
        }
        return res;
    }
}
