package leetcode.java.LC213;

// See also (Series): LC198, LC337
// Ref: https://leetcode.wang/leetcode-213-House-RobberII.html
//      https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw

import java.util.Arrays;

public class Main_213 {

    public static void main(String[] args) {
        Main_213 main_213 = new Main_213();
//        System.out.println(main_213.rob_solution_TopDown(new int[]{1}));
//        System.out.println(main_213.rob_solution_TopDown(new int[]{1, 2, 3, 1}));
//        System.out.println(main_213.rob_solution_TopDown(new int[]{2, 3, 2}));
//        System.out.println(main_213.rob_solution_TopDown(new int[]{1, 2, 1, 1}));
        System.out.println(main_213.rob_solution_BottomUp(new int[]{1}));
        System.out.println(main_213.rob_solution_BottomUp(new int[]{1, 2, 3, 1}));
        System.out.println(main_213.rob_solution_BottomUp(new int[]{2, 3, 2}));
        System.out.println(main_213.rob_solution_BottomUp(new int[]{1, 2, 1, 1}));
    }

    // Solution1: DP Recursion (Top-Down)
    public int rob_solution_TopDown(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        if (n == 1)
            return nums[0];
        memo = new int[n];
        Arrays.fill(memo, -1);
        int res1 = robHelper_opt2(nums, 0, n - 2);
        Arrays.fill(memo, -1);      // Note: Must clear the memo to prevent interference between two results!
        int res2 = robHelper_opt2(nums, 1, n - 1);
        return Math.max(res1, res2);
    }

    // Opt1: Recursion without optimization (Top-down)   Time: O(2^N)  Space: O(N) for recursion
    private int robHelper_opt1(int[] nums, int start, int end) {
        if (start > end)
            return 0;
        return Math.max(robHelper_opt1(nums, start + 1, end),
                robHelper_opt1(nums, start + 2, end) + nums[start]);
    }

    // Opt2: Recursion with optimization  (Top-down)   Time: O(N)  Space: O(N + N)
    private int[] memo;
    private int robHelper_opt2(int[] nums, int start, int end) {
        if (start > end)
            return 0;
        if (memo[start] != -1) {        // Note: Must clear the memo before calling helper function in main function!!
            return memo[start];
        }
        int res = Math.max(robHelper_opt2(nums, start + 1, end),
                robHelper_opt2(nums, start + 2, end) + nums[start]);
        memo[start] = res;
        return res;
    }




    // Solution2: DP Iteration (Bottom-Up)
    public int rob_solution_BottomUp(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int res1 = robHelper_opt4(nums, 0, n - 2);
        int res2 = robHelper_opt4(nums, 1, n - 1);
        return Math.max(res1, res2);
    }

    // Opt3: DP Iteration (Bottom-up)       Time: O(N)  Space: O(N)
    private int robHelper_opt3(int[] nums, int start, int end) {
        int n = nums.length;
        int[] dp = new int[n + 2];  Arrays.fill(dp, 0);
        for (int i = end; i >= start; i--) {
            int choice1 = dp[i + 2] + nums[i];
            int choice2 = dp[i + 1];
            dp[i] = Math.max(choice1, choice2);
        }
        return dp[start];
    }

    // Opt4: DP Iteration (Bottom-up)       Time: O(N)   Space: O(1)
    private int robHelper_opt4(int[] nums, int start, int end) {
        int prev = 0, cur = 0;
        for (int i = end; i >= start; i--) {
            int choice1 = prev + nums[i];
            int choice2 = cur;
            prev = cur;
            cur = Math.max(choice1, choice2);
        }
        return cur;
    }
}
