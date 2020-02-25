package leetcode.java.LC121;


public class Main_121 {
    public static void main(String[] args) {
        Main_121 main_121 = new Main_121();
        System.out.println(main_121.maxProfit_solution2(new int[]{7, 1, 5, 3, 6, 4}));
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
