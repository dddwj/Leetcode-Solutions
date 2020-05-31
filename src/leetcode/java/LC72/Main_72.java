package leetcode.java.LC72;

// Great Tutorial: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/bian-ji-ju-li

import java.util.Arrays;
import java.util.HashMap;

public class Main_72 {

    public static void main(String[] args) {
        Main_72 main_72 = new Main_72();
        System.out.println(main_72.minDistance_solution2("horse", "ros"));
        System.out.println(main_72.minDistance_solution2("intention", "execution"));
        System.out.println(main_72.minDistance_solution2("dinitrophenylhydrazine", "benzalphenylhydrazone"));   // TLE for solution1
    }




    // Solution1 (TLE!): Recursion  (Brute Force)    [Can be OPTIMIZED with a memo table!]
    // Space: O(N) for recursion stack.
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/bian-ji-ju-li
    public int minDistance_solution1(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = m - 1, j = n - 1;
        return dp(word1, word2, i, j);
    }

    private int dp(String word1, String word2, int i, int j) {
        if (i == -1)
            return j + 1;
        if (j == -1)
            return i + 1;

        if (word1.charAt(i) == word2.charAt(j))
            return dp(word1, word2, i - 1, j - 1);

        return min(dp(word1, word2, i - 1, j),
                        dp(word1, word2, i, j - 1),
                        dp(word1, word2, i - 1, j - 1)
        ) + 1;
    }




    // Solution2: Dynamic Programming
    // Time: O(N*M),  N = len(word1),  M = len(word2)
    // Space: O(N*M)
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/bian-ji-ju-li
    public int minDistance_solution2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i-1][j-1] + 1
                    );
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}
