package leetcode.java.LC242;

import java.util.Arrays;
import java.util.HashMap;


// See also: LC438, LC567

public class Main_242 {
    public static void main(String[] args) {
        Main_242 main_242 = new Main_242();
        String s1 = "anagram";
        String s2 = "nagaram";
        System.out.println(main_242.isAnagram_solution2(s1, s2));
    }

    // Solution1: Sort & Compare
    // Time: O(NlogN + MlogM + N) = O(NlogN)
    // Space: O(1)
    //    Note that in Java, toCharArray() makes a copy of the string so it costs O(n) extra space, but we ignore this for complexity analysis because:
    //      It is a language dependent detail.
    //      It depends on how the function is designed. For example, the function parameter types can be changed to char[].
    public boolean isAnagram_solution1(String s, String t) {
        if (s.length() != t.length())
            return false;

        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();

        Arrays.sort(s_arr);
        Arrays.sort(t_arr);

        return Arrays.equals(s_arr, t_arr);  // Time Complexity: O(N)
    }


    // Solution2: Alphabet
    // Time: O(N+M) = O(N)  Space: O(1)
    public boolean isAnagram_solution2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            alphabet[t.charAt(i) - 'a']--;
            if (alphabet[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    // Solution2.5: Hash Table (Answer to follow up)
    // Time: O(N+M) = O(N)  Space: O(N)
    public boolean isAnagram_solution25(String s, String t) {
        // corner case
        if (s.length() != t.length())
            return false;

        // init data
        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();

        // Create Hash table
        for (Character c : s_arr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Character c : t_arr) {
            map.put(c, map.getOrDefault(c, 0) - 1);
        }

        // Check whether invalid
        for (Character c : map.keySet()) {
            if (map.get(c) != 0)
                return false;
        }

        return true;
    }

    // Follow Up: What if the inputs contain unicode characters? How would you adapt your solution to such case?
    //
    // Answer:
    //
    // Use a hash table instead of a fixed size counter.
    // Imagine allocating a large size array to fit the entire range of unicode characters, which could go up to more than 1 million.
    // A hash table is a more generic solution and could adapt to any range of characters.



}
