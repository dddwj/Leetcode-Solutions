package leetcode.java.LC383;

import java.util.*;

public class Main_383 {

    public static void main(String[] args) {
        Main_383 main_383 = new Main_383();
        System.out.println(main_383.canConstruct("aa", "ab"));
        System.out.println(main_383.canConstruct("aa", "aab"));
        System.out.println(main_383.canConstruct("aaaac", "aabc"));
        System.out.println(main_383.canConstruct("", ""));

    }


    // Great Solution:
    public boolean canConstruct(String ransom, String magazine) {
        if (magazine.length() < ransom.length()) return false;
        int caps[] = new int[26];
        for (char c : ransom.toCharArray()) {
            int index = magazine.indexOf(c, caps[c - 'a']);     // Note: key function here: indexOf().
            if (index == -1)
                return false;
            caps[c - 97] = index + 1;
        }
        return true;
    }



    // My Solution: HashTable / Alphabet Table
    // Time: O(M+N)
    // Space: O(N)
    public boolean canConstruct_mysolution(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null || ransomNote.length() > magazine.length())
            return false;

        if (ransomNote.length() == 0)
            return true;

        Map<Character, Integer> map = new HashMap<>();
        for (Character c : ransomNote.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (Character c : magazine.toCharArray()) {
            if (map.containsKey(c)) {
                int freq = map.get(c);
                freq--;
                if (freq == 0)
                    map.remove(c);
                else
                    map.put(c, freq);
                if (map.keySet().size() == 0)
                    return true;
            }
        }
        return false;
    }
}
