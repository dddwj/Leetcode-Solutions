package leetcode.java.LC5;

public class Main_5 {



    // Solution2: Brute Force  (TLE)
    // Time: O(N^3)
    // Space: O(1)
    public String longestPalindrome_solution2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int maxLen = 1;
        String res = s.substring(0, 1);
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String str = s.substring(i, j);
                if (isPalindrome(str) && str.length() > maxLen) {
                    res = str;
                    maxLen = str.length();
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str) {
        if (str.length() == 1) {
            return true;
        }
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++; j--;
        }
        return true;
    }


    // Solution4: Expand Around Center
    // Time: O(N^2)
    // Space: O(1)
    int maxLen = 1;
    int start = 0;
    public String longestPalindrome_solution4(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int len = s.length();
        for (int i = 0; i < len; i++) {
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i + 1);
        }
        return s.substring(start, start + maxLen);
    }

    private void expandAroundCenter(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            int len = j - i + 1;
            if (len > maxLen) {
                maxLen = len;
                start = i;
            }
            i--;  j++;
        }
    }


    // Solution5: Manacher's Algorithm
    // Time: O(N)
    // Omitted
}
