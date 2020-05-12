package leetcode.java.LC678;

public class Main_678 {
    public static void main(String[] args) {
        Main_678 main_678 = new Main_678();
        // Note: there may read wrong global 'ans' value for solution1, if multiple checkValidString functions are called at the same time.
        System.out.println(main_678.checkValidString_solution2("(*)))"));
        System.out.println(main_678.checkValidString_solution2("(*))"));
        System.out.println(main_678.checkValidString_solution2(")"));
        System.out.println(main_678.checkValidString_solution2("("));
        System.out.println(main_678.checkValidString_solution2("*"));
        System.out.println(main_678.checkValidString_solution2("(*)"));
        System.out.println(main_678.checkValidString_solution2(""));
    }



    // Solution1: Brute Force (TLE)
    // Time: O( N * 3^N )
    // Space: O(
    // Ref: https://leetcode.com/problems/valid-parenthesis-string/solution/
    //      https://github.com/fishercoder1534/Leetcode/blob/master/src/main/java/com/fishercoder/solutions/_678.java
    public boolean checkValidString_solution1(String s) {
        solve(new StringBuilder(s), 0);
        return ans;
    }

    private void solve(StringBuilder sb, int i) {
        if (i == sb.length()) {
            ans |= valid(sb);
        } else if (sb.charAt(i) == '*') {
            for (char c: "() ".toCharArray()) {
                sb.setCharAt(i, c);
                solve(sb, i+1);
                if (ans) return;
            }
            sb.setCharAt(i, '*');
        } else
            solve(sb, i + 1);
    }

    private boolean valid(StringBuilder sb) {
        int bal = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') bal++;
            if (c == ')') bal--;
            if (bal < 0) break;
        }
        return bal == 0;
    }

    private boolean ans = false;



    // Solution2: Dynamic Programming
    // Time: O(N^2 * N),  N^2 for dp array iteration, N for string check in each iteration
    // Space: O(N^2) for dp array
    // Ref: https://leetcode-cn.com/problems/valid-parenthesis-string/solution/you-xiao-de-gua-hao-zi-fu-chuan-by-leetcode/
    public boolean checkValidString_solution2(String s) {
        int n = s.length();
        if (n == 0) return true;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*')
                dp[i][i] = true;
            if (i < n-1 &&
                    (s.charAt(i) == '(' || s.charAt(i) == '*') &&
                    (s.charAt(i+1) == ')' || s.charAt(i+1) == '*')) {
                dp[i][i+1] = true;
            }
        }

        for (int size = 2; size < n; size++) {
            for (int i = 0; i + size < n; i++) {
                if (s.charAt(i) == '*' && dp[i+1][i+size] == true) {
                    dp[i][i+size] = true;
                } else if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                    for (int k = i+1; k <= i+size; k++) {
                        if ((s.charAt(k) == ')' || s.charAt(k) == '*') &&
                                (k == i+1 || dp[i+1][k-1]) &&
                                (k == i+size || dp[k+1][i+size])) {
                            dp[i][i+size] = true;
                        }
                    }
                }
            }
        }
        return dp[0][n-1];
    }




    // Solution3: Greedy
    // Time: O(N)
    // Space: O(1)
    // Tutorial: https://www.bilibili.com/video/BV1ap4y1X7nu/
    public boolean checkValidString_solution3(String s) {
        if (s == null)
            return false;

        int least = 0, most = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                least++;
                most++;
            } else if (c == ')') {
                if (least != 0)
                    least--;
                most--;
            } else if (c == '*') {
                if (least != 0)
                    least--;
                most++;
            } else
                return false;

            if (most < 0)
                return false;
        }

        return least == 0;
    }

}
