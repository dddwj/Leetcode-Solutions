package leetcode.java.LC131;

import java.util.*;

public class Main_131_solution3 {

    public static void main(String[] args) {
        Main_131_solution3 main_131 = new Main_131_solution3();
        System.out.println(main_131.partition("aabb"));
        System.out.println(main_131.partition("b"));
    }


    // Solution3: 回溯 (dfs)
    // Ref: https://leetcode.wang/leetcode-131-Palindrome-Partitioning.html
    public List<List<String>> partition(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = s.length();
        for (int len = 1; len <= length; len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) &&
                        (len < 3 || dp[i + 1][j - 1]);      // len < 3  <==> i + 1 > j - 1, 意味着长度是1和2时，只需要判断s.charAt(i) == s.charAt(j)
            }
        }

        List<List<String>> ans = new ArrayList<>();
        partitionHelper(s, 0, dp, new ArrayList<>(), ans);
        return ans;
    }

    // TODO: 还不理解'回溯'方法
    private void partitionHelper(String s, int start, boolean[][] dp, List<String> temp, List<List<String>> res) {
        // 到了空串就加到最终结果中
        if (start == s.length()) {
            res.add(new ArrayList<>(temp));
        }

        // 在不同位置切割
        for (int i = start; i < s.length(); i++) {
            // 如果是回文串就加到结果中
            if (dp[start][i]) {
                String left = s.substring(start, i + 1);
                temp.add(left);
                partitionHelper(s, i + 1, dp, temp, res);
                temp.remove(temp.size() - 1);
            }
        }
    }

}
