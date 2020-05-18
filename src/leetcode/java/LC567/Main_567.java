package leetcode.java.LC567;

import java.util.*;


// See also: LC438, LC242

public class Main_567 {

    // Reference: https://leetcode.com/problems/permutation-in-string/solution/

    public static void main(String[] args) {
        Main_567 main_567 = new Main_567();
        System.out.println(main_567.checkInclusion_solution("adc", "dcda"));         // 此用例说明：不能用HashSet
        System.out.println(main_567.checkInclusion_solution("hello", "ooolleoooleh"));    // 此用例说明：必须对字母计频率
        System.out.println(main_567.checkInclusion_solution("ab", "a"));            // Corner Case

    }

    // Solution 0: Sliding Window
    // Time: O(N)   Space: O(1)
    // Ref: https://leetcode-cn.com/problems/permutation-in-string/solution/wo-xie-liao-yi-shou-shi-ba-suo-you-hua-dong-chuang/
    //      LC438
    // Note: 记忆模版！
    public boolean checkInclusion_solution(String s1, String s2) {
        // Corner Case
        if (s1.length() > s2.length()) return false;

        int[] need = new int[256];
        int[] window = new int[256];
        Set<Character> count = new HashSet<>(26);
        for (char c : s1.toCharArray()) {
            need[c]++;
            count.add(c);
        }

        int left = 0, right = 0, valid = 0;
        while (right < s2.length()) {
            // move right-ptr
            char c = s2.charAt(right);
            right++;
            if (need[c] > 0) {
                window[c]++;
                if (window[c] == need[c])
                    valid++;
            }

            // move left-ptr, when necessary
            if (right - left >= s1.length()) {
                // check valid window
                if (valid == count.size())
                    return true;
                char d = s2.charAt(left);
                left++;
                if (need[d] > 0) {
                    if (window[d] == need[d])
                        valid--;
                    window[d]--;
                }
            }
        }
        return false;
    }




    // Solution1: Brute Force


    // Solution2: Sort + Compare



    // My Solution: (Solution3 on Leetcode.com)  HashMap  +  Sliding Window
    // Time:  O((len(s2) - len(s1)) * len(s1))
    // Space: O(26 + len(s1))   26: HashMap,  len(s1): 'removed' queue
    public boolean checkInclusion_solution3(String s1, String s2) {
        // Corner Case
        if (s1.length() > s2.length()) return false;

        // Init the sliding window
        int left = 0, right = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (right < s1.length()) {
            Character ch = s2.charAt(right);
            window.put(ch, window.getOrDefault(ch, 0) + 1);
            right++;
        }

        // Iterate through s2.
        while (right <= s2.length()) {
            System.out.println(window);

            int i = 0;
            Queue<Character> removed = new LinkedList<>();
            for (; i < s1.length(); i++) {
                Character ch = s1.charAt(i);
                if (!window.containsKey(ch)) {
                    break;
                } else if (window.get(ch) < 1) {
                    break;
                } else {
                    window.put(ch, window.get(ch) - 1);
                    removed.offer(ch);      // Keep track of removed character, in case of recovery when there is no match.
                }
            }
            if (i == s1.length()) {         // Match. Return true.
                return true;
            } else {                        // No match. Recovery.
                while (!removed.isEmpty()) {
                    Character ch = removed.poll();
                    window.put(ch, window.getOrDefault(ch, 0) + 1);
                }
            }
            if (right == s2.length()) {     // Iterate through the last. Still no match.
                return false;
            }

            // Move window one step forward
            window.put(s2.charAt(left), window.get(s2.charAt(left)) - 1);
            left++;
            window.put(s2.charAt(right), window.getOrDefault(s2.charAt(right), 0) + 1);
            right++;
        }

        return false;
    }




    // Solution5 on Leetcode.com:  Array  +  Sliding Window   (Similar to Solution4)
    // Time:  O((len(s2) - len(s1)) * len(s1))
    // Space: O(26 + len(s1))   26: HashMap,  len(s1): 'removed' queue
    public boolean checkInclusion_solution5(String s1, String s2) {
        // Corner Case
        if (s1.length() > s2.length()) return false;

        // Init the sliding window
        int left = 0, right = 0;
        int[] window = new int[26];
        while (right < s1.length()) {
            Character ch = s2.charAt(right);
            window[ch - 'a']++;
            right++;
        }

        // Iterate through s2.
        while (right <= s2.length()) {
            System.out.println(Arrays.toString(window));

            int i = 0;
            Queue<Character> removed = new LinkedList<>();
            for (; i < s1.length(); i++) {
                Character ch = s1.charAt(i);
                if (window[ch - 'a'] == 0) {
                    break;
                } else {
                    window[ch - 'a']--;
                    removed.offer(ch);      // Keep track of removed character, in case of recovery when there is no match.
                }
            }
            if (i == s1.length()) {         // Match. Return true.
                return true;
            } else {                        // No match. Recovery.
                while (!removed.isEmpty()) {
                    Character ch = removed.poll();
                    window[ch - 'a']++;
                }
            }
            if (right == s2.length()) {     // Iterate through the last. Still no match.
                return false;
            }

            // Move window one step forward
            window[s2.charAt(left) - 'a']--;
            left++;
            window[s2.charAt(right) - 'a']++;
            right++;
        }

        return false;
    }



    // Solution6: Array  +  Optimized Sliding Window
    // Time:  O(l1+(l2−l1))
    // Space: O(1)
    // Ref: https://leetcode.com/problems/permutation-in-string/solution/
    public boolean checkInclusion_solution6(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        int count = 0;
        for (int i = 0; i < 26; i++)
            if (s1map[i] == s2map[i])
                count++;
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
            if (count == 26)
                return true;
            s2map[r]++;
            if (s2map[r] == s1map[r])
                count++;
            else if (s2map[r] == s1map[r] + 1)    // 考虑【s2map[r]++】操作后导致的(原本已经匹配的字母)出现不匹配现象
                count--;
            s2map[l]--;
            if (s2map[l] == s1map[l])
                count++;
            else if (s2map[l] == s1map[l] - 1)  // 考虑【s2map[l]--】操作后导致的(原本已经匹配的字母)出现不匹配现象
                count--;
        }
        return count == 26;
    }
}
