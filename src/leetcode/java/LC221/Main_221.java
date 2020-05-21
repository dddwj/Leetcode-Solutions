package leetcode.java.LC221;

public class Main_221 {

    // See also: LC1277

    public static void main(String[] args) {
        Main_221 main_221 = new Main_221();
        System.out.println(main_221.maximalSquare_solution2(new char[][]{
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
        }));
        System.out.println(main_221.maximalSquare_solution2(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }));
    }



    // My Solution (Solution1 on leetcode.com)
    // Time: O(MN*MN)
    // Space: O(1)
    public int maximalSquare_mysolution(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int m = matrix.length, n = matrix[0].length;
        int maxLen = 0;

        for (int i = 0; i + maxLen < m; i++) {
            for (int j = 0; j + maxLen < n; j++) {
                if (matrix[i][j] == '1') {
                    int length = 1;
                    while (i + length < m && j + length < n) {
                        boolean foundZero = false;
                        for (int x = i; !foundZero && x <= i + length; x++) {
                            if (matrix[x][j+length] == '0') {
                                foundZero = true;
                            }
                        }
                        for (int y = j; !foundZero && y <= j + length; y++) {
                            if (matrix[i+length][y] == '0') {
                                foundZero = true;
                            }
                        }
                        if (foundZero)
                            break;
                        else
                            length++;
                    }
                    maxLen = Math.max(maxLen, length);
                }
            }
        }
        return maxLen * maxLen;
    }



    // Solution2: Dynamic Programming
    // Time: O(MN)
    // Space: O(MN)
    // Ref: https://leetcode.com/problems/maximal-square/solution/
    //      LC1277
    public int maximalSquare_solution2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxLen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }


    // Solution3: Dynamic Programming  (Optimization: 2D DP array --> 1D DP array)
    // Time: O(MN)
    // Space: O(N)
    // Ref: https://leetcode.com/problems/maximal-square/solution/
    public int maximalSquare_solution3(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[] dp = new int[cols + 1];
        int maxLen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i-1][j-1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j-1], prev), dp[j]) + 1;
                    maxLen = Math.max(maxLen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxLen * maxLen;
    }
}
