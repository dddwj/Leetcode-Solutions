package leetcode.java.LC309;

// See also (Series): LC121, LC122, LC123, LC188, LC714
// Great Tutorial: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti

public class Main_309 {

    public static void main(String[] args) {
        Main_309 main_309 = new Main_309();
        System.out.println(main_309.maxProfit_solution(new int[]{1,2,3,0,2}));
    }

    /*
    状态转移方程： 第i天； 第k次交易； 0/1:未持有/持有
    dp[i][k][0] = Max(  dp[i-1][k][0] ,  dp[i-1][k][1] + prices[i]  )
    dp[i][k][1] = Max(  dp[i-1][k][1] ,  dp[i-1][k-2][0] - prices[i])
    */

    // Solution: Dynamic Programming without dp table
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
    // Time: O(N)
    // Space: O(1)
    public int maxProfit_solution(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }



}
