package leetcode.java.LC3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main_3 {

    // GREAT Tutorial: https://youtu.be/aazeoLBppnc
    //                 https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/


    public static void main(String[] args) {
        Main_3 main_3 = new Main_3();
//        String s = "pwwkew";
        String s = "abbbac";
        // "abcdefg"
        //"bbbbbb"
        //"aabbcc"
        //"pwwkew"
        //"nweasw"
        //"a"
        //""
        System.out.println(main_3.lengthOfLongestSubstring(s));

    }


    // My Solution: Two Pointers   (Similar to Solution3 (Sliding Window Optimized))
    // Time: O(N) ~ O(N*N)
    // Space: O(N)
    public int lengthOfLongestSubstring_mysolution(String s) {
        if (s == null || s.length() == 0)  return 0;

        int length = s.length();
        int max = 1;
        for (int anchor = 0; anchor < length;) {
            HashSet<Character> set = new HashSet();
            for (int j = anchor; j < length; j++) {
                char c = s.charAt(j);
                if (set.contains(c)) {
                    // Skip duplicates
                    while (anchor < length) {
                        if (s.charAt(anchor) == c) {
                            break;
                        }
                        anchor++;
                    }
                    anchor++;
                    break;
                }
                set.add(c);
            }
            max = Math.max(max, set.size());

            // Avoid unneccessary comparison
            if (max >= length - anchor)
                return max;
        }
        return max;
    }


    // Solution1: Brute Force
    // Time: O(N^3)

    // Omitted



    // Solution2: Sliding Window
    // Ref: https://youtu.be/aazeoLBppnc
    // Time: O(N) ~ O(2N)   In the worst case each character will be visited twice by head and end
    // Space: O(min(N, M)) where M is the size of charset/alphabet, N is the size of the input string
    public int lengthOfLongestSubstring_solution2(String s) {
        int left = 0;
        int length = s.length();
        int maxLength = 0;
        Set<Character> window = new HashSet();

        for (int right = 0; right < length; right++) {
            Character ch = s.charAt(right);
            while (window.contains(ch)) {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(ch);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


    // Solution3: (Opt1) Sliding Window (Optimized)  (hashmap)
    // Time: O(N)       In this case, we can skip the characters immediately when we found a repeated character.
    // Space: O(min(N, M)) where M is the size of charset/alphabet, N is the size of the input string
    public int lengthOfLongestSubstring_solution31(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character

        for (int right = 0, left = 0; right < n; right++) {
            if (map.containsKey(s.charAt(right))) {
//             DO NOT USE:   left = map.get(s.charAt(right)) + 1;
                left = Math.max(map.get(s.charAt(right)) + 1, left);       // MUST use Math.max(_, _) to ensure the left side do not go back   <== Test Case: "abba"
            }
            ans = Math.max(ans, right - left + 1);
            map.put(s.charAt(right), right);
        }

        System.out.println(map.keySet());
        System.out.println(map.values());

        return ans;
    }



    // Solution3: (Opt2) Sliding Window (Optimized)  (alphabet table)
    // Time: O(N)   In this case, we can skip the characters immediately when we found a repeated character.
    // Space: O(M), where M is the size of charset/alphabet
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character

        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)] + 1, i);

            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j;
        }
        return ans;
    }

    /*
    *   NOTE:
    *
    *   The previous implements all have no assumption on the charset of the string s.
    *   If we know that the charset is rather small, we can replace the Map with an integer array as direct access table.
    *
    *   int[26] for Letters 'a' - 'z' or 'A' - 'Z'
    *   int[128] for ASCII
    *   int[256] for Extended ASCII
    *
    * */

}
