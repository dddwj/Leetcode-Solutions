package leetcode.java.LC123;

// See also (Series): LC121, LC122, LC188, LC309, LC714
// Great Tutorial: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
//                 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems

public class Main_123 {

    public static void main(String[] args) {
        Main_123 main_123 = new Main_123();
        System.out.println(main_123.maxProfit_Standard_Version(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(main_123.maxProfit_version2(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }


    // Solution: Dynamic Programming with dp table
    /*
        状态转移方程: K = 2
        dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
        dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     */
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
    // Time: O(KN) = O(2*N)
    // Space: O(KN) = O(2*N)
    public int maxProfit_Standard_Version(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k + 1][2];

        for (int i = 0; i < n; i++) {
            for (int k = max_k; k > 0; k--) {
                if (i - 1 == -1) {       // 处理第一天持有股票的情况，第一天不可能持有股票。
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);     // 不理解这里为什么是k-1, 而不是k+1, 故自己写了下面version2。
            }
        }

        return dp[n-1][max_k][0];
    }


    // Solution (My Version): Dynamic Programming with dp table
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
    //      https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/315111
    // Time: O(KN) = O(2*N)
    // Space: O(KN) = O(2*N)
    /*
        状态转移方程: k = max_k   【k：还剩余k次交易机会，（每次机会下：可选择交易，也可选择不交易）】
        dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
        dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k+1][0] - prices[i])       // 这里使用 k+1 而不是 k-1， 因为k+1表示上一次交易机会。
                                                                            // 当 k = max_k - 1时， dp[i-1][k+1][0] = dp[i-1][max_k][0] = 0（java默认初始化为0),
                                                                            // 从而，dp[i-1][k+1][0] - prices[i] 退化为： -prices[i]。
                                                                            // -prices[i] 表示：在之前没有任何交易当基础上，今天选择交易（买入），得到 -prices[i]的收益。
        return dp[n-1][0][0]:  返回最后一天(n-1) 且剩余0次交易机会(第二维为0) 且手中不持有股票(第三维为0)时的收益
     */
    public int maxProfit_version2(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k + 1][2];

        for (int i = 0; i < n; i++) {
            for (int k = max_k - 1; k >= 0; k--) {
                if (i - 1 == -1) {       // 处理第一天持有股票的情况，第一天不可能持有股票。
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k+1][0] - prices[i]);
            }
        }

        return dp[n-1][0][0];
    }

}
