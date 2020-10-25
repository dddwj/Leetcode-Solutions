package leetcode.java.LC122;

// See also (Series): LC121, LC123, LC188, LC309, LC714
// Great Tutorial: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti

public class Main_122 {
    public static void main (String[] args) {
        int[] prices = new int[] {7, 1, 5, 3, 6};
        Main_122 main_122 = new Main_122();
        System.out.println(main_122.maxProfit_solution1(prices));
        System.out.println(main_122.maxProfit_solution2(prices));
    }

    // Solution1
    public int maxProfit_solution1(int[] prices) {

        int profit = 0;

        int[] rateOfChange = new int[prices.length - 1];
        for (int i = 0; i < prices.length - 1; i++) {
            rateOfChange[i] = prices[i+1] - prices[i];
        }


        for (int i = 0; i < rateOfChange.length; i++) {
            if (rateOfChange[i] > 0)
                profit += rateOfChange[i];
        }
        // System.out.println(profit);
        return profit;
    }


    // Solution2 (Newer understanding):  Optimization: Dynamic Programming without dp table
    /*
        状态转移方程： 第i天； 第k次交易； 0/1:未持有/持有
        dp[i][k][0] = Max(  dp[i-1][k][0] ,  dp[i-1][k][1] + prices[i]  )
        dp[i][k][1] = Max(  dp[i-1][k][1] ,  dp[i-1][k-1][0] - prices[i])
    */
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
    // Time: O(N)
    // Space: O(1)
    public int maxProfit_solution2(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;

        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }

        return dp_i_0;
    }


}
