package leetcode.java.LC188;

// See also (Series): LC121, LC122, LC123, LC309, LC714
// Great Tutorial: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
//                 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems

import leetcode.java.LC122.Main_122;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// Solution: Dynamic Programming with dp table   (Not work, Memory Limit Exceeded!!!!)
    /*
        状态转移方程:
        dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
        dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     */
// Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
// Time: O(KN) = O(k*N)
// Space: O(KN) = O(k*N)
public class Main_188_solution1 {
    public static void main(String[] args) throws IOException {
        Main_188_solution1 main_188Solution1 = new Main_188_solution1();
        System.out.println(main_188Solution1.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));


        int arr_size = 10000;
        int[] input = new int[arr_size];
        BufferedReader in = new BufferedReader(new FileReader("/Users/dddwj/IdeaProjects/Leetcode/src/leetcode/java/LC188/large_input.txt"));
        String str;
        while ((str = in.readLine()) != null) {
            input[10000 - arr_size] = Integer.parseInt(str);
            arr_size--;
        }
        System.out.println(main_188Solution1.maxProfit(1000, input));

    }

    public int maxProfit(int k, int[] prices) {
        // return maxProfit_copiedFromLC123(k, prices);
        return maxProfit_optimizedFromLC123(k, prices);
    }

    // Memory Limit Still Exceeded!
    public int maxProfit_optimizedFromLC123(int max_k, int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;
        if (max_k >= n / 2)     // Optimization Point
            return maxProfit_LC122(prices);

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


    public int maxProfit_LC122(int[] prices) {
        Main_122 main_122 = new Main_122();
        return main_122.maxProfit_solution2(prices);
    }


    // Memory Limit Exceeded!
    public int maxProfit_copiedFromLC123(int max_k, int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];        // Use too much memory!!!

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
