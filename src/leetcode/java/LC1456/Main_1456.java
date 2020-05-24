package leetcode.java.LC1456;

import java.util.Arrays;
import java.util.HashSet;

public class Main_1456 {

    public static void main(String[] args) {
        Main_1456 main_1456 = new Main_1456();
        System.out.println(main_1456.maxVowels_solution("leetcode", 3));
    }



    // My Solution: Sliding Window
    // Time: O(N)
    // Space: O(1)
    public int maxVowels(String s, int k) {
        char[] letters = s.toCharArray();
        int left = 0, right = k - 1;
        int count = 0, maxCount = 0;
        for (int i = 0; i <= right; i++) {
            if (isVowel(letters[i]))
                count++;
        }
        maxCount = count;

        while (right < letters.length - 1) {
            right++;
            if (isVowel(letters[right]))
                count++;


            if (isVowel(letters[left]))
                count--;
            left++;

            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }

    private boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'e')
            return true;
        return false;
    }



    // Solution: Sliding Window (Concise)
    // Time: O(N)
    // Space: O(1)
    // Ref: https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/discuss/648790/C-Concise-code
    public int maxVowels_solution(String s, int k) {
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int maxCount = 0, count = 0, start = 0, end = 0;
        while (end < s.length()) {
            if (vowels.contains(s.charAt(end)))
                count++;
            end++;
            if (end - start > k) {
                if (vowels.contains(s.charAt(start))) {
                    count--;
                }
                start++;
            }

            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }
}
