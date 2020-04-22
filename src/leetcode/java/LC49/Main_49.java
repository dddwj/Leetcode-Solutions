package leetcode.java.LC49;

import java.util.*;

public class Main_49 {

    public static void main(String[] args) {
        Main_49 main_49 = new Main_49();
        System.out.println(main_49.groupAnagrams_solution1(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }


    // Solution1: Categorize by Sorted String
    public List<List<String>> groupAnagrams_solution1(String[] strs) {
        if (strs.length == 0)
            return new ArrayList<>();

        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);      // turns charArray into String
            if (!ans.containsKey(key))
                ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }




    // Solution2: Categorize by Count  (StringBuilder!)
    // Time: O(NK)
    // Space: O(NK)
    public List<List<String>> groupAnagrams_solution2(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List> ans = new HashMap<>();
        int[] freq = new int[26];
        for (String str : strs) {
            Arrays.fill(freq, 0 );
            for (Character c : str.toCharArray()) {
                freq[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append("#");
                sb.append(freq[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<String>());
            }
            ans.get(key).add(str);
        }
        return new ArrayList(ans.values());
    }



    // Solution3: Prime Numbers (Similar to Bit Manipulation?) (Similar to Solution1)
    // Time: O(NK)
    // Space: O(NK)
    // Ref: https://leetcode.wang/leetCode-49-Group-Anagrams.html
    public List<List<String>> groupAnagrams_solution3(String[] strs) {
        HashMap<Integer, List<String>> hash = new HashMap<>();
        //每个字母对应一个质数
        int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103 };
        for (int i = 0; i < strs.length; i++) {
            int key = 1;
            //累乘得到 key
            for (int j = 0; j < strs[i].length(); j++) {
                key *= prime[strs[i].charAt(j) - 'a'];
            }
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }

        }
        return new ArrayList<List<String>>(hash.values());
    }


    // My Solution: use HashMap to judge anagram      (Similar to Solution2 on leetcode.com)
    // Time: O(N*len + N*26)  where N = input string count,  len = avg(length of input strings), N*len: to convert string into frequency table,  N*26: to compare the frequency table with other existing frequency tables
    // Space: O(N*len)     where len = avg(length of input strings)
    public List<List<String>> groupAnagrams_mysolution(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }

        List<List<String>> res = new ArrayList<>();
        HashMap<int[], Integer> table = new HashMap<>();     // <frequency table, line number in the result>

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int[] freq = new int[26];       // frequency table for a string
            for (Character c : str.toCharArray()) {
                freq[c-'a']++;
            }
            int arrIndex = getTableValue(table, freq);
            if (arrIndex >= 0) {
                res.get(arrIndex).add(str);
            } else {
                List<String> line = new ArrayList<>();
                line.add(str);
                res.add(line);
                table.put(freq, res.size() - 1);
            }
        }
        return res;
    }

    private int getTableValue(HashMap<int[], Integer> table, int[] freq) {
        int value = -1;
        for (Map.Entry<int[], Integer> entry : table.entrySet()) {
            boolean match = true;
            int[] line = entry.getKey();
            for (int i = 0; i < 26; i++) {
                if (line[i] != freq[i]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                value = entry.getValue();
                break;
            }
        }
        return value;
    }





}
