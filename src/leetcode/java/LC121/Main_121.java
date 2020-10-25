package leetcode.java.LC121;

// See also (Series): LC122, LC123, LC188, LC309, LC714
// Great Tutorial: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti

public class Main_121 {
    public static void main(String[] args) {
        Main_121 main_121 = new Main_121();
//        System.out.println(main_121.maxProfit_solution2(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(main_121.maxProfit_solution3(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(main_121.maxProfit_solution4(new int[]{7, 1, 5, 3, 6, 4}));
    }

    // Solution3 (Newer understanding):  Dynamic Programming with dp table
    /*
        状态转移方程： 第i天； 第k次交易； 0/1:未持有/持有
        dp[i][k][0] = Max(  dp[i-1][k][0] ,  dp[i-1][k][1] + prices[i]  )
        dp[i][k][1] = Max(  dp[i-1][k][1] ,  dp[i-1][k-1][0] - prices[i])
    */
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
    // Time: O(N)
    // Space: O(N)
    public int maxProfit_solution3(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[0];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[n-1][0];
    }


    // Solution3 (Newer understanding): Optimization: Dynamic Programming without dp table
    /*
        状态转移方程： 第i天； 第k次交易； 0/1:未持有/持有
        dp[i][k][0] = Max(  dp[i-1][k][0] ,  dp[i-1][k][1] + prices[i]  )
        dp[i][k][1] = Max(  dp[i-1][k][1] ,  dp[i-1][k-1][0] - prices[i])
    */
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
    // Time: O(N)
    // Space: O(1)
    public int maxProfit_solution4(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;

        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }

        return dp_i_0;
    }


    // Solution1: Brute Force
    // Time: O(N*N)
    // Space: O(1)
    public int maxProfit_solution1(int[] prices) {
        int days = prices.length;
        int maxProfit = 0;
        for (int i = 0; i < days; i++) {
            for (int j = i + 1; j < days; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    // Solution2: Dynamic Programming (DP) -- One Pass
    // Time: O(N)
    // Space: O(1)
    public int maxProfit_solution2(int[] prices) {
        int maxprofit = 0;
        int minprice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
    /*

    【Solution2中的 'DP思想'】
        假设当前在第 i 天，令 minPrice 表示前 i-1 天的最低价格；令 maxProfit 表示前 i-1 天的最大收益。
        那么考虑第 i 天的收益时，存在两种情况：
            1. 在第 i 天卖出。很显然，想要获得最大收益，应该在前 i-1 天中价格最低的时候买入，即此时的收益为：prices[i] - minPrice。（可能会出现负数，但是没关系）
            2. 不在第 i 天卖出。那么第 i 天的最大收益就等于前 i -1 天中的最大收益
            故，状态转移方程为：第 i 天最大收益 = max( 在第 i 天卖出的所得收益 , 前 i-1 天的最大收益)
     */
}
