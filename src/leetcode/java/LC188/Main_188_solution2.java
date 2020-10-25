package leetcode.java.LC188;

// See also (Series): LC121, LC122, LC123, LC309, LC714
// Great Tutorial: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
//                 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems


import leetcode.java.LC122.Main_122;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


// Solution: Dynamic Programming with [Optimized] dp table
// Time: O(kN)
// Space: O(k)
// Ref: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
public class Main_188_solution2 {

    public static void main(String[] args) throws IOException {
        Main_188_solution2 main_188_solution2 = new Main_188_solution2();

        int arr_size = 10000;
        int[] input = new int[arr_size];
        BufferedReader in = new BufferedReader(new FileReader("/Users/dddwj/IdeaProjects/Leetcode/src/leetcode/java/LC188/large_input.txt"));
        String str;
        while ((str = in.readLine()) != null) {
            input[10000 - arr_size] = Integer.parseInt(str);
            arr_size--;
        }

        System.out.println(main_188_solution2.maxProfit(1000, input));
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int n = prices.length;
        if (k >= (n >>> 1)) {   // k -> inf
            Main_122 main_122 = new Main_122();
            return main_122.maxProfit_solution2(prices);
        }

        int[] dp_ik0 = new int[k + 1];
        int[] dp_ik1 = new int[k + 1];
        Arrays.fill(dp_ik1, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            for (int j = k; j > 0; j--) {
                dp_ik0[j] = Math.max(dp_ik0[j], dp_ik1[j] + prices[i]);
                dp_ik1[j] = Math.max(dp_ik1[j], dp_ik0[j-1] - prices[i]);
            }
        }

        return dp_ik0[k];
    }

}
