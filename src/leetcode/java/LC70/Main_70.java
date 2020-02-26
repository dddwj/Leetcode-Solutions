package leetcode.java.LC70;

import java.util.Arrays;

public class Main_70 {
    public static void main(String[] args) {
        Main_70 main_70 = new Main_70();
        System.out.println(main_70.climbStairs_solution1(4));
    }

    // Solution1: Brute Force  - Recursion
    // Time: O(2^N) (树形递归的大小为2^N)
    // Space: O(N)  (递归树的深度可以达到N)
    public int climbStairs_solution1(int n) {
        return climb(0, n);
    }

    public int climb(int i, int n) {
        if (i > n)
            return 0;

        if (i == n)     // reach the final line
            return 1;

        return climb(i + 1, n) + climb(i + 2, n);
    }


    // Solution2: Brute Force - Recursion with Memoization
    // Time: O(N)  树形递归的大小可以达到N
    // Space: O(N) 递归树的深度可以达到N
    public int climbStairs_solution2(int n) {
        int memo[] = new int[n + 1];   // 因为要用到n，所以要开辟n+1个大小的空间
        return climb(0, n, memo);
    }

    public int climb(int i, int n, int memo[]) {
        if (i > n)  return 0;
        if (i == n) return 1;   // reach the final line

        if (memo[i] > 0)        // memorized results
            return memo[i];

        memo[i] = climb(i + 1, n, memo) + climb(i + 2, n, memo);
        return memo[i];
    }



    // My Solution1: (Opt1: Classical Dynamic Programming (DP) )
    // Solution3 on LeetCode.com,  aka: Dynamic Programming
    // Time: O(1)  Space: O(N)
    public int climbStairs_mysolution11(int n) {
        if (n == 1) return 1;       // n is a positive integer
        if (n == 2) return 2;

        int[] dp = new int[n + 1];  // we need n+1 space to store n+1 nodes:   0...1...2...3......n
                                    // Note: 动态规划的题，通常申请n+1大小的空间
                                    // 因为要用到n，所以要开辟n+1个大小的空间.
                                    // 数组的下标表示到达n所有的可能性，到达想要的第六层的可能性，数组长度为7才容易理解，其实声明为n也没有啥不行的
                                    // n对应第n阶，数组的首位实际没有用到。当然你也可以初始化大小为n，那样的话dp[n-1]就代表第n阶
        // Base Case
        dp[1] = 1;
        dp[2] = 2;

        // Iterate through
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    // My Solution1:  (Opt2: Without extra space - Classical Dynamic Programming)
    // Solution4 on LeetCode.com,  aka: Fibonacci Number
    // Time: O(N)  Space: O(1)
    public int climbStairs_mysolution12(int n) {
        if (n == 1) return 1;       // n is a positive integer
        if (n == 2) return 2;

        int step1 = 1;
        int step2 = 2;
        int step3 = 3;
        int count = 3;
        while (count <= n) {
            step3 = step1 + step2;
            step1 = step2;
            step2 = step3;
            count++;
        }
        return step3;
    }

}
