package leetcode.java.LC1143;

public class Main_1143 {

    // See also: LC1458

    public static void main(String[] args) {
        Main_1143 main_1143 = new Main_1143();
        System.out.println(main_1143.longestCommonSubsequence_solution1("abee", "babcee"));
        System.out.println(main_1143.longestCommonSubsequence_solution2("abee", "babcee"));
    }


    // Solution: Dynamic Programming
    // Time: O(M*N)
    // Space: O(M*N)
    // Ref: https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-zhi-zui-chang-gong-gong-zi-xu-lie/
    public int longestCommonSubsequence_solution1(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int length1 = t1.length, length2 = t2.length;
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i < length1 + 1; i++) {
            for (int j = 1; j < length2 + 1; j++) {
                if (t1[i - 1] == t2[j - 1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[length1][length2];
    }


    // Solution: Dynamic Programming  (Space Optimized)  2D dp array --> 1D dp array
    // Time: O(M*N)
    // Space: O(N)
    public int longestCommonSubsequence_solution2(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int[] dp = new int[t1.length + 1];
        int ans = 0;
        for(int j = 1; j <= t2.length; j++)
        {
            int last = 0;
            for(int i = 1; i <= t1.length; i++)
            {
                int temp = dp[i];
                dp[i] = t1[i-1] == t2[j-1] ? (last + 1) : Math.max(dp[i-1], dp[i]);
                ans = Math.max(ans, dp[i]);
                last = temp;
            }
        }
        return ans;
    }

}
