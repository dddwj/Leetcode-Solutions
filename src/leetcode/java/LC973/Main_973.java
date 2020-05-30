package leetcode.java.LC973;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Main_973 {

    public static void main(String[] args) {
        Main_973 main_973 = new Main_973();

        // TestCase1
        main_973.kClosest_solution2(new int[][]{
                {3,3},
                {5,-1},
                {-2,4}
        }, 2);

        // TestCase2, 将会导致原题解死循环。
        main_973.kClosest_solution2(new int[][]{
                {0,1},
                {1,0}
        }, 2);
    }



    // My Solution:  Heap
    // Time: O(NlogN + (N-K)logN)
    // Space: O(N)
    public int[][] kClosest_mysolution(int[][] points, int K) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((p1, p2) -> distance(p2) - distance(p1));
        for (int[] point : points)
            heap.offer(point);
        for (int i = 0; i < points.length - K; i++)
            heap.poll();
        int[][] res = new int[heap.size()][2];
        heap.toArray(res);
        return res;
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }



    // Solution1:   Array Sort
    // Time: O(N + NlogN)
    // Space: O(N)
    public int[][] kClosest_solution1(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];
        for (int i = 0; i < N; ++i)
            dists[i] = distance(points[i]);

        Arrays.sort(dists);
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i)
            if (distance(points[i]) <= distK)
                ans[t++] = points[i];
        return ans;
    }




    // Solution2:  QuickSort (Divide & Conquer)
    // Time: O(N)
    // Space: O(lgN) ?  for recursion stack
    public int[][] kClosest_solution2(int[][] points, int K) {
        this.points = points;
        work(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void work(int i, int j, int K) {
        if (i >= j) return;
        int oi = i, oj = j;
        int pivot = dist(ThreadLocalRandom.current().nextInt(i, j));

        while (i < j) {
            while (i < j && dist(i) < pivot) i++;
            while (i < j && dist(j) > pivot) j--;
            // 原题解里没有设置以下断开条件，对TestCase2产生死循环。   但如果设置以下断开条件，可能会导致i和j中间的点(如果有的话)没有得到排序。因此尽管可以过LC测试，但事实上结果可能出错。
            if (dist(i) == pivot && dist(j) == pivot)
                break;
            swap(i, j);
        }

        if (K <= i - oi + 1)
            work(oi, i, K);
        else
            work(i+1, oj, K - (i - oi + 1));
    }

    public int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }

    int[][] points;

}
