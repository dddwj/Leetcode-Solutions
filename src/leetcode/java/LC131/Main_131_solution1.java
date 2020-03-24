package leetcode.java.LC131;

import java.util.*;

public class Main_131_solution1 {


    public static void main(String[] args) {
        Main_131_solution1 main_131 = new Main_131_solution1();
        System.out.println(main_131.partition("aabb"));
        System.out.println(main_131.partition("a"));
    }


    // Solution1: Divide and Conquer
    // Ref: https://leetcode.wang/leetcode-131-Palindrome-Partitioning.html
    public List<List<String>> partition(String s) {
        return partitionHelper(s, 0);
    }

    private List<List<String>> partitionHelper(String s, int start) {
        // 递归出口，空字符串
        if (start == s.length()) {
            List<String> list = new ArrayList<>();
            List<List<String>> ans = new ArrayList<>();
            ans.add(list);
            return ans;
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = start; i < s.length(); i++) {
            // 当前切割后是回文串才考虑
            if (isPalindrome(s.substring(start, i + 1))) {
                String left = s.substring(start, i + 1);
                //遍历后边字符串的所有结果，将当前的字符串添加到头部
                for (List<String> l : partitionHelper(s, i + 1)) {  // NOTE: 这一步递归是关键
                    l.add(0, left);
                    ans.add(l);
                }
            }
        }

        return ans;
    }

    private boolean isPalindrome(String s) {        // Ref: LC125
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


}
