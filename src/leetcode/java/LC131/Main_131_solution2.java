package leetcode.java.LC131;

import java.util.*;

public class Main_131_solution2 {

    public static void main(String[] args) {
        Main_131_solution2 main_131 = new Main_131_solution2();
        System.out.println(main_131.partition("aabb"));
        System.out.println(main_131.partition("a"));
    }



    // Solution2: Divide and Conquer (Optimized)  -- Use dynamic programming to store palindromes
    // Ref: https://leetcode.wang/leetcode-131-Palindrome-Partitioning.html
    public List<List<String>> partition(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = s.length();
        for (int len = 1; len <= length; len++) {
            // 从每个下标开始
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                // System.out.println("i, j = " + i + ", " + j);
                dp[i][j] = (s.charAt(i) == s.charAt(j))
                        && (i + 1 > j - 1 || dp[i + 1][j - 1]);     // i + 1 > j - 1  <==>  len < 3, 意味着长度是1和2时，只需要判断s.charAt(i) == s.charAt(j)
            }
        }
        return partitionHelper(s, 0, dp);
    }

    private List<List<String>> partitionHelper(String s, int start, boolean[][] dp) {
        if (start == s.length()) {
            List<String> list = new ArrayList<>();
            List<List<String>> ans = new ArrayList<>();
            ans.add(list);
            return ans;
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = start; i < s.length(); i++) {
            if (dp[start][i]) {
                String left = s.substring(start, i + 1);
                for (List<String> l : partitionHelper(s, i + 1, dp)) {
                    l.add(0, left);
                    ans.add(l);
                }
            }
        }

        return ans;
    }
}
