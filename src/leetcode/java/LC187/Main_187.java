package leetcode.java.LC187;

import java.util.*;

// Great Tutorial: https://leetcode.wang/leetcode-187-Repeated-DNA-Sequences.html

public class Main_187 {
    public static void main(String[] args) {
        Main_187 main_187 = new Main_187();
        System.out.println(main_187.findRepeatedDnaSequences_solution2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(main_187.findRepeatedDnaSequences_solution2("AAAAAAAAAAAAA"));
    }

    // My Solution: Brute Force (TLE!)
    // Time: O(N^2), where N = len(s)
    // Space: O(M), where M = size(res)
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10)
            return new ArrayList<>();

        Set<String> res = new HashSet<>();          // use Set<String>, instead of List<String>, to avoid duplication. Takes extra space.
        int len = s.length();
        for (int i = 0; i <= len - 10; i++) {
            String candidate = s.substring(i, i + 10);
            for (int j = i + 1; j <= len - 10; j++) {
                if (candidate.equals(s.substring(j, j + 10)))
                    res.add(candidate);
            }
        }
        return new ArrayList<>(res);
    }


    // Solution1: Brute Force (Optimized with cache / Hashset)  (Solution1 on leetcode.com)
    // Ref: https://leetcode.wang/leetcode-187-Repeated-DNA-Sequences.html
    // Time: O(N), where N = len(s)
    // Space: O(M), where M = size(res)
    public List<String> findRepeatedDnaSequences_solution1(String s) {
        if (s.length() < 10)
            return new ArrayList<>();

        Set<String> res = new HashSet<>();
        Set<String> seen = new HashSet<>();       // This Set<String> acts as a cache
        for (int i = 0; i <= s.length() - 10; i++) {
            String candidate = s.substring(i, i + 10);
            if (seen.contains(candidate))
                res.add(candidate);
            else
                seen.add(candidate);
        }
        return new ArrayList<>(res);
    }


    // Solution2: Bit Manipulation
    // Ref: https://leetcode.wang/leetcode-187-Repeated-DNA-Sequences.html
    // Time: O(N), where N = len(s)
    // Space: O(1)
    public List<String> findRepeatedDnaSequences_solution2(String s) {
        if (s.length() < 10)
            return new ArrayList<>();

        Set<String> res = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        char[] map = new char[26];
        map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        int key = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < 10; i++) {
            key <<= 2;
            key |= map[str[i] - 'A'];
            key &= 0xfffff;
        }
        seen.add(key);
        for (int i = 10; i < str.length; i++) {
            key <<= 2;
            key |= map[str[i] - 'A'];
            key &= 0xfffff;
            if (seen.contains(key))
                res.add(s.substring(i - 9, i + 1));
            else
                seen.add(key);
        }

        return new ArrayList<>(res);
    }

}
