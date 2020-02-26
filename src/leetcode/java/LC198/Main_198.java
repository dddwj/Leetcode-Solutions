package leetcode.java.LC198;

public class Main_198 {
    // ref: https://leetcode-cn.com/problems/house-robber/solution/da-jia-jie-she-by-leetcode/217208
    // Reference: LeetCode 70: Climbing Stairs

    public static void main(String[] args) {
        Main_198 main_198 = new Main_198();
        System.out.println(main_198.rob(new int[]{2, 1, 0, 16}));
//        System.out.println(main_198.rob(new int[]{2, 7, 9, 3, 1}));
    }


    // Solution: Dynamic Programming (DP)
    // Time: O(N)  Space: O(1)
    // ref: https://leetcode-cn.com/problems/house-robber/solution/da-jia-jie-she-by-leetcode/217208
    // Reference: LeetCode 70: Climbing Stairs
    public int rob(int[] nums) {
        int size = nums.length;
        int[] dp = new int[size + 1];    // 参考LeetCode 70: Climbing Stairs
                                         // 开辟size+1的空间来便于理解。dp[0]没有用到。
                                         // dp[1]表示：到第1家时之前最多可能偷到多少钱。
                                         // dp[2]表示：到第2家时之前最多可能偷到多少钱。
                                         // dp[3]： 到第3家时，
                                         //    1. 要么偷（dp[1]+第三家）的钱。即dp[1]+nums[2]
                                         //    2. 要么偷（dp[2]）的钱，不偷第三家的钱。即dp[2]
                                         //    找寻以上两种可能的最大值（即：最优解）。即Math.max(dp[1]+nums[2], dp[2])
        // Corner Case
        if (size == 1)  return nums[0];
        if (size == 0)  return 0;

        // Base Case
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0], nums[1]);

        // DP
        for (int i = 3; i < size + 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);   //Note: 动态规划问题：最重要的是：找出状态转移方程！
        }

//        System.out.println(Arrays.toString(dp));
        return dp[size];
    }

}
