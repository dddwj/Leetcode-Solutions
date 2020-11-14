package leetcode.java.LC849;

import java.util.Arrays;

public class Main_849 {

    public static void main(String[] args) {
        Main_849 main_849 = new Main_849();
        System.out.println(main_849.maxDistToClosest_solution2(new int[]{1, 0, 0, 0}));
        System.out.println(main_849.maxDistToClosest_solution2(new int[]{1, 0}));
        System.out.println(main_849.maxDistToClosest_solution2(new int[]{1, 0, 0, 0, 1}));
    }

    // Solution1: Two assistance arrays
    // Time: O(N)
    // Space: O(N)
    // Ref: https://leetcode-cn.com/problems/maximize-distance-to-closest-person/solution/dao-zui-jin-de-ren-de-zui-da-ju-chi-by-leetcode/
    public int maxDistToClosest_solution1(int[] seats) {
        int N = seats.length;
        int[] left = new int[N], right = new int[N];
        Arrays.fill(left, N);
        Arrays.fill(right, N);

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) left[i] = 0;
            else if (i > 0) left[i] = left[i-1] + 1;
        }

        for (int i = N-1; i >= 0; --i) {
            if (seats[i] == 1) right[i] = 0;
            else if (i < N-1) right[i] = right[i+1] + 1;
        }

        int ans = 0;
        for (int i = 0; i < N; ++i)
            if (seats[i] == 0)
                ans = Math.max(ans, Math.min(left[i], right[i]));
        return ans;
    }


    // Solution2: Two pointers
    // Time: O(N)
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/maximize-distance-to-closest-person/solution/dao-zui-jin-de-ren-de-zui-da-ju-chi-by-leetcode/
    public int maxDistToClosest_solution2(int[] seats) {
        int N = seats.length;
        int prev = -1, future = 0;
        int res = -1;

        for (int i = 0; i < N; i++) {
            if (seats[i] == 1)
                prev = i;
            else {
                while (future < N && seats[future] == 0 || future < i) {
                    future++;
                }

                int left = prev == -1 ? N : i - prev;
                int right = future == N ? N : future - i;
                res = Math.max(res, Math.min(left, right));
            }
        }

        return res;
    }


    // My Solution (Solution3 on leetcode-cn): Check continuous zeros and zeros in both sides
    // Time: O(N)
    // Space: O(1)
    public int maxDistToClosest_solution3(int[] seats) {
        if (seats == null || seats.length < 2)
            return -1;

        int maxLen = Integer.MIN_VALUE;

        for (int i = 0; i < seats.length; i++) {
            int len = 0;
            while (i < seats.length) {
                if (seats[i] == 0) {
                    len++;
                    i++;
                } else
                    break;
            }
            maxLen = Math.max(len, maxLen);
        }

        int startLen = -1, endLen = -1, sideMaxLen = -1;
        if (seats[0] == 0) {
            startLen = 1;
            int i = 1;
            while (i < seats.length && seats[i] == 0) {
                startLen++;
                i++;
            }
        }
        if (seats[seats.length-1] == 0) {
            endLen = 1;
            int i = seats.length - 2;
            while (i >= 0 && seats[i] == 0) {
                endLen++;
                i--;
            }
        }
        if (startLen != -1 || endLen != -1) {
            sideMaxLen = Math.max(startLen, endLen);
        }

        return Math.max((maxLen + 1) / 2, sideMaxLen);
    }
}
