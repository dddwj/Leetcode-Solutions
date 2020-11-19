package leetcode.java.LC714;

// See also (Series): LC121, LC122, LC123, LC188, LC309
// Great Tutorial: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti


public class Main_714 {

    public static void main(String[] args) {
        Main_714 main_714 = new Main_714();
        System.out.println(main_714.maxProfit_solution(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    /*
        状态转移方程： 第i天； 第k次交易； 0/1:未持有/持有

        dp[i][k][0] = Max(  dp[i-1][k][0] ,  dp[i-1][k][1] + prices[i] - fee)
        dp[i][k][1] = Max(  dp[i-1][k][1] ,  dp[i-1][k-1][0] - prices[i])

        OR

        dp[i][k][0] = Max(  dp[i-1][k][0] ,  dp[i-1][k][1] + prices[i])
        dp[i][k][1] = Max(  dp[i-1][k][1] ,  dp[i-1][k-1][0] - prices[i] - fee)
    */

    // Solution: Dynamic Programming without dp table
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
    // Time: O(N)
    // Space: O(1)
    public int maxProfit_solution(int[] prices, int fee) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);      // Cannot subtract fee here, because (MIN_VALUE - fee) will overflow!
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }
}