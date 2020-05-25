package leetcode.java.LC1035;

import java.util.*;

// See also: LC1143, LC1458, LC72

public class Main_1035 {

    public static void main(String[] args) {
        Main_1035 main_1035 = new Main_1035();
        System.out.println(main_1035.maxUncrossedLines_mysolution2(new int[]{1, 4, 2}, new int[]{1, 1, 4, 2}));
        System.out.println(main_1035.maxUncrossedLines_mysolution2(new int[]{10,5,2,1,5,2}, new int[]{2,5,1,2,5}));
    }



    // My Solution1: Dynamic Programming  (2D-array solution)
    // Time: O(N*M), N = len(A),  M = len(B)
    // Space: O(N*M)
    public int maxUncrossedLines_mysolution1(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0)
            return 0;

        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 0;
        Arrays.fill(dp[0], 0);

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                int prev = Math.max(dp[i - 1][j], dp[i][j - 1]);
                int curr = dp[i - 1][j - 1] + (A[i - 1] == B[j - 1] ? 1 : 0);
                dp[i][j] = Math.max(prev, curr);
            }
        }

        return dp[A.length][B.length];
    }




    // My Solution2: Dynamic Programming  (1D-array solution)
    // Time: O(N*M), N = len(A),  M = len(B)
    // Space: O(M)
    public int maxUncrossedLines_mysolution2(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0)
            return 0;

        int[] dp = new int[B.length + 1];
        Arrays.fill(dp, 0);

        for (int i = 1; i <= A.length; i++) {
            int before = 0;  // i.e.: dp[i - 1][j - 1] in 2D-array solution
            for (int j = 1; j <= B.length; j++) {
                int prev = Math.max(dp[j], dp[j - 1]);  // i.e.: Math.max(dp[i-1][j], dp[i][j-1])   in 2D-array solution
                int curr = before + (A[i - 1] == B[j - 1] ? 1 : 0);
                before = dp[j];     // store the dp[i-1][j] for next iteration. In the next iteration, the `before` would become dp[i - 1][j - 1]
                dp[j] = Math.max(prev, curr);  // i.e.: dp[i][j] in 2D-array solution
            }
        }

        return dp[B.length];
    }
}
