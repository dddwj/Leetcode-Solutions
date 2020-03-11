package leetcode.java.LC205;

import java.util.*;

public class Main_205 {

    // Reference: https://leetcode-cn.com/problems/isomorphic-strings/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-42/

    public static void main(String[] args) {
        Main_205 main_205 = new Main_205();
        System.out.println(main_205.isIsomorphic_solution21("title", "paper"));
//        System.out.println(main_205.isIsomorphic(null, "paper"));
        System.out.println(main_205.isIsomorphic_solution21("ab", "aa"));
        System.out.println(main_205.isIsomorphic_solution21("aa", "ab"));
        System.out.println(main_205.isIsomorphic_solution21("aa", "aa"));
    }

    // Solution (ONLY Applicable to Alphabets):
    // Space: O(1)
    public boolean isIsomorphic_notApplicableSolution(String s, String t) {
        if (s == null || t == null)
            throw new IllegalArgumentException("Illegal Input");

        int[] mapS = new int[26];
        int[] mapT = new int[26];
        Arrays.fill(mapS, 27);
        Arrays.fill(mapT, 27);
        for (int i = 0; i < s.length(); i++) {
            int chS = s.charAt(i) - 96;
            int chT = t.charAt(i) - 96;
            if (mapS[chS] == 27) {
                mapS[chS] = chT;
                if (mapT[chT] == 27) {
                    mapT[chT] = chS;
                    continue;
                } else {
                    return false;
                }
            } else {   // mapS[chS] != 27
                if (mapT[chT] != chS) {
                    return false;
                }
            }
        }

        return true;
    }


    // My Solution: HashMap + Set
    // Time: O(len(s))
    // Space: O(len(s))
    public boolean isIsomorphic_mysolution(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character chS = s.charAt(i);
            Character chT = t.charAt(i);
            if (map.containsKey(chS)) {
                if (map.get(chS) != chT)
                    return false;
            } else {
                map.put(chS, chT);
            }
        }

        // To check if there is duplicate mapped character.
        int keys = 0;
        Set<Character> set = new HashSet<>();
        for (Character ch : map.values()) {
            keys++;
            set.add(ch);
        }
        return keys == set.size();
    }



    // Solution1: HashMap +  Two Calls
    // Time: O(len(s))
    // Space: O(len(s))
    public boolean isIsomorphic_solution1(String s, String t) {
        return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);

    }
    private boolean isIsomorphicHelper(String s, String t) {
        int n = s.length();
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }
        return true;
    }


    // Solution2: Translator (Opt1)
    // Ref: https://leetcode-cn.com/problems/isomorphic-strings/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-42/
    public boolean isIsomorphic_solution21(String s, String t) {
        return isIsomorphicHelper(s).equals(isIsomorphicHelper(t));
    }
    private String isIsomorphicHelper(String s) {
        int[] map = new int[128];
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            //当前字母第一次出现,赋值
            if (map[c] == 0) {
                map[c] = count;
                count++;
            }
            sb.append(map[c]);
        }
        return sb.toString();
    }


    // Solution2: Translator (Opt2)
    // Ref: https://leetcode-cn.com/problems/isomorphic-strings/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-42/
    public boolean isIsomorphic_solution22(String s, String t) {
        int n = s.length();
        int[] mapS = new int[128];
        int[] mapT = new int[128];
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            //当前的映射值是否相同
            if (mapS[c1] != mapT[c2]) {
                return false;
            } else {
                //是否已经修改过，修改过就不需要再处理
                if (mapS[c1] == 0) {
                    mapS[c1] = i + 1;
                    mapT[c2] = i + 1;
                }
            }
        }
        return true;
    }
}
