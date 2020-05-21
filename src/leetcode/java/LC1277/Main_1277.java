package leetcode.java.LC1277;

public class Main_1277 {

    // See also: LC221 - Solution2 & 3

    public static void main(String[] args) {
        Main_1277 main_1277 = new Main_1277();
        System.out.println(main_1277.countSquares(new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
        }));
    }



    // Solution: Dynamic Programming
    // Time: O(M*N)
    // Space: O(M*N)
    // Ref: https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/solution/bi-jiao-jian-ji-de-dai-ma-yi-dong-ba-chao-xiang-xi/
    //      LC221 - Solution2 & Solution3
    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int ans = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = matrix[i][j];
                else if (matrix[i][j] == 0)
                    dp[i][j] = 0;
                else
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                ans += dp[i][j];
            }
        }

        return ans;
    }
}
