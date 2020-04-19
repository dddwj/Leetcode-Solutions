package leetcode.java.LC64;

import java.util.Arrays;

public class Main_64 {

    public static void main(String[] args) {
        Main_64 main_64 = new Main_64();
        System.out.println(main_64.minPathSum_solution3(new int[][]{
                {1, 3, 1, 100},
                {1, 5, 1, 1},
                {4, 2, 1, 5}
        }));
        System.out.println(main_64.minPathSum_mysolution(new int[][]{
                {1, 3, 1, 100},
                {1, 5, 1, 1},
                {4, 2, 1, 5}
        }));
    }

    // Solution1: Brute Force: TLE
    // Time: O(2^(M+N))
    // Space: O(M+N) for recursion stack
    public int minPathSum_solution1(int[][] grid) {
        return calculate(grid, 0, 0);
    }

    private int calculate(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length)
            return Integer.MAX_VALUE;
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return grid[i][j];
        return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
    }


    // Solution2: Dynamic Programming (Two-dimension Extra Space)
    // Time: O(M*N)
    // Space: O(M*N)
    // Ref: https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-by-leetcode/
    public int minPathSum_solution2(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1 && j != grid[0].length - 1)
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                else if (j == grid[0].length - 1 && i != grid.length - 1)
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                else if (j != grid[0].length - 1 && i != grid.length - 1)
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j] , dp[i][j + 1]);
                else
                    dp[i][j] = grid[i][j];
            }
        }
        return dp[0][0];
    }



    // Solution3: Dynamic Programming (One-dimension Extra Space)
    // Time: O(M*N)
    // Space: O(N)
    public int minPathSum_solution3(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1 && j != grid[0].length - 1)
                    dp[j] = grid[i][j] + dp[j + 1];
                else if (j == grid[0].length - 1 && i != grid.length - 1)
                    dp[j] = grid[i][j] + dp[j];
                else if (j != grid[0].length - 1 && i != grid.length - 1)
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                else
                    dp[j] = grid[i][j];
            }
        }
        return dp[0];
    }


    // Solution4: Dynamic Programming (No extra space)  (in-place modification)
    // Time: O(M*N)
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-by-leetcode/
    // Omitted



    // My Solution: Dynamic Programming  (Two-dimension Extra Space)
    // (Solution2 on leetcode.com)
    // Time: O(M*N)
    // Space: O(M*N)
    public int minPathSum_mysolution(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m + 1][n + 1];        // expand 1 dimension to avoid border check afterwards
        Arrays.fill(res[m], Integer.MAX_VALUE);
        for (int i = 0; i < m + 1; i++) {
            res[i][n] = Integer.MAX_VALUE;
        }

        int right = n - 1, bottom = m - 1;
        res[bottom][right] = grid[bottom][right];

        while (right >= 0 || bottom >= 0) {
            if (right >= 0) {
                for (int i = bottom; i >= 0; i--) {
                    int sib = res[i][right+1];
                    int down = res[i+1][right];
                    int shorter = Math.min(sib, down);
                    if (sib == down && shorter == Integer.MAX_VALUE)        // start point
                        res[i][right] = grid[i][right];
                    else
                        res[i][right] = grid[i][right] + shorter;
                }
                right--;
            }

            if (bottom >= 0) {
                for (int j = right; j >= 0; j--) {
                    int sib = res[bottom][j+1];
                    int down = res[bottom+1][j];
                    int shorter = Math.min(sib, down);
                    if (sib == down && shorter == Integer.MAX_VALUE)        // start point
                        res[bottom][j] = grid[bottom][j];
                    else
                        res[bottom][j] = grid[bottom][j] + shorter;
                }
                bottom--;
            }
        }

        return res[0][0];
    }

}
