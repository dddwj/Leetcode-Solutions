package leetcode.java.LC986;

import java.util.*;

public class Main_986 {

    public static void main(String[] args) {
        Main_986 main_986 = new Main_986();
        main_986.intervalIntersection(new int[][]{
                {0,2},
                {5,10},
                {13,23},
                {24,25}
        }, new int[][] {
                {1,5},
                {8,12},
                {15,24},
                {25,26}
        });
    }



    // Solution:   Two Pointers
    // Time: O(M + N)
    // Space:  O(min(M, N)) ? , where M = A.length, N = B.length
    // Ref: https://leetcode-cn.com/problems/interval-list-intersections/solution/jiu-pa-ni-bu-dong-shuang-zhi-zhen-by-hyj8/
    //      https://leetcode.com/problems/interval-list-intersections/solution/
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);

            if (lo <= hi)
                ans.add(new int[] {lo, hi});

            if (A[i][1] == hi)
                i++;
            if (B[j][1] == hi)
                j++;
        }

        return ans.toArray(new int[ans.size()][2]);
    }




    // My Solution: Brute Force
    // Time: O(M*N), where M = A.length, N = B.length
    // Space: O(min(M, N))
    public int[][] intervalIntersection_mysolution(int[][] A, int[][] B) {
        int la = A.length, lb = B.length;
        if (la == 0 || lb == 0)
            return new int[0][2];

        List<int[]> ans = new ArrayList<>();        // Space: O(min(M, N)) ?

        for (int a = 0; a < la; a++) {
            for (int b = 0; b < lb; b++) {
                intersect(A[a], B[b], ans);
            }
        }

        int[][] ret = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ret[i] = ans.get(i);
            System.out.println(Arrays.toString(ret[i]));
        }
        return ret;
    }

    private void intersect(int[] intervalA, int[] intervalB, List<int[]> ans) {
        int aStart = intervalA[0], aEnd = intervalA[1];
        int bStart = intervalB[0], bEnd = intervalB[1];

        if (aEnd < bStart || bEnd < aStart)
            return;

        ans.add(new int[] {aStart > bStart ? aStart : bStart, aEnd > bEnd ? bEnd : aEnd});
    }




    // My Solution2:  Enumerate    (Not work,  Reason: Cannot differentiate some specific set of size = 1,  for example, [24, 24] in this case)
    // Time: O(1000) = O(1)
    // Space: O(1000) + O(min(M, N))
    public int[][] intervalIntersection_mysolution2(int[][] A, int[][] B) {
        int[] board = new int[1000];
        for (int[] interval : A) {
            for (int i = interval[0]; i <= interval[1]; i++) {
                board[i] = 1;
            }
        }

        for (int[] interval : B) {
            for (int i = interval[0]; i <= interval[1]; i++) {
                board[i] *= -1;
            }
        }

        System.out.println(Arrays.toString(board));

        List<int[]> ans = new ArrayList<>();        // Space: O(min(M, N)) ?
        int start = -1, end = -1, index = 0;
        for (; index < 1000; index++) {     // Drawback: Cannot differentiate some specific set of size = 1
            if (board[index] == -1) {
                start = index;
                index++;
                while (index < 1000
                        && board[index] == -1) {
                    index++;
                }
                end = index - 1;
                ans.add(new int[] {start, end});
            }
        }

        int[][] ret = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ret[i] = ans.get(i);
            System.out.println(Arrays.toString(ret[i]));
        }
        return ret;
    }

}
