package leetcode.java.LC1696;

import java.util.*;

public class Main_1696 {

    // Reference: https://leetcode.com/problems/jump-game-vi/discuss/1260737/Optimizations-from-Brute-Force-to-Dynamic-Programming-w-Explanation/1478399
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(0);
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            if (dq.peekFirst() < i - k) {
                dq.pollFirst();
            }

            dp[i] = nums[i] + dp[dq.peekFirst()];
            while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]) {
                dq.pollLast();
            }

            dq.addLast(i);
        }

        return dp[n-1];
    }

    // Dynamic Programming with Memoization [TLE!]
    public int maxResult_Solution2(int[] nums, int k) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, Integer.MIN_VALUE);

        memo[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= k && i - j >= 0; j++) {
                memo[i] = Math.max(memo[i], memo[i-j] + nums[i]);
            }
        }
        return memo[memo.length - 1];
    }

    public static void main(String[] args) {
        Main_1696 main_1696 = new Main_1696();
        System.out.println(main_1696.maxResult(new int[]{1, -1, -2, 4, -7, 3}, 2));
        System.out.println(main_1696.maxResult_Solution2(new int[]{1, -1, -2, 4, -7, 3}, 2));
    }

}
