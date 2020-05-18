package leetcode.java.LC438;

import java.util.*;

// See also: LC242, LC567
// Great Tutorial: https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/hua-dong-chuang-kou-tong-yong-si-xiang-jie-jue-zi-/

public class Main_438 {

    public static void main(String[] args) {
        Main_438 main_438 = new Main_438();
        System.out.println(main_438.findAnagrams_solution("cbaebabacd", "abc"));
    }

    // My Solution: Sliding Window + HashMap
    // Time: O(N*N)
    // Space: O(N)
    public List<Integer> findAnagrams_mysolution(String s, String p) {
        if (p == null || s == null || p.length() > s.length())
            return new ArrayList<Integer>();

        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> target = new HashMap<Character, Integer>();
        for (char ch : p.toCharArray()) {
            target.put(ch, target.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<Character, Integer>();
        for (int i = 0; i < p.length() - 1; i++) {
            char ch = s.charAt(i);
            window.put(ch, window.getOrDefault(ch, 0) + 1);
        }
        for (int i = p.length() - 1; i < s.length(); i++) {
            char tail = s.charAt(i);
            window.put(tail, window.getOrDefault(tail, 0) + 1);
            if (i != p.length() - 1) {
                char head = s.charAt(i - p.length());
                window.put(head, window.get(head) - 1);
                if (window.get(head) == 0)
                    window.remove(head);
            }
            if (window.equals(target))
                res.add(i - p.length() + 1);
        }
        return res;
    }


    // Solution: Sliding Window
    // Time: O(N)
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/shi-yong-hua-dong-chuang-kou-jie-ti-hen-rong-yi-by/
    //      https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/hua-dong-chuang-kou-tong-yong-si-xiang-jie-jue-zi-/
    //      LC567 - Solution 0
    // Note: 记忆模版！
    public List<Integer> findAnagrams_solution(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0)
            return list;

        int[] hash = new int[256];
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        int left = 0, right = 0, count = p.length();

        while (right < s.length()) {
            // 右移窗口
            if (hash[s.charAt(right)] > 0) {
                count--;
            }
            hash[s.charAt(right)]--;
            right++;


            // 判断是否匹配
            if (count == 0) {
                list.add(left);
            }


            // 左移窗口
            if (right - left == p.length()) {  // 判断是否要左移
                if (hash[s.charAt(left)] >= 0) {  // 判断最左元素是否在p中出现过
                    count++;
                }
                hash[s.charAt(left)]++;
                left++;
            }
        }
        return list;
    }
}
