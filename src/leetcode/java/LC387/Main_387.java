package leetcode.java.LC387;

import java.util.Arrays;
import java.util.HashMap;

public class Main_387 {
    public static void main(String[] args) {
        Main_387 main_387 = new Main_387();
        String s = "hello";
        String s2 = "hhello";
        String s3 = "hheelloo";
        String s4 = "";
//        System.out.println(Arrays.toString(s.split("")));
//        System.out.println(Arrays.toString(s.toCharArray()));
//        System.out.println('a' - 'z');
//        System.out.println(s.split("").getClass().getName());
//        System.out.println(s.toCharArray().getClass().getName());
        System.out.println(main_387.firstUniqChar(s));
        System.out.println(main_387.firstUniqChar(s2));
        System.out.println(main_387.firstUniqChar(s3));
        System.out.println(main_387.firstUniqChar(s4));

    }

    // Solution1: HashMap
    // Time: O(N)  Space: O(N)
    public int firstUniqChar_solution1(String s) {
        // Corner Case
        if (s.length() == 0)
            return -1;

        // Init data
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();

        // First pass: add data to map
        for (Character c : arr)
            map.put(c, map.getOrDefault(c, 0) + 1);

        // Second pass: search valid data in map
        int rst = 0;
        for (Character c : arr) {
            if (map.get(c) == 1)
                return rst;
            rst++;
        }

        // No unique character found
        return -1;
    }

    // Solution2:  Alphabet (instead of hashmap)
    // Time: O(N)  Space: O(26) = O(1)
    public int firstUniqChar(String s) {
        // Corner Case
        if (s.length() == 0)
            return -1;

        // Init data
        int[] alphabet = new int[26];
        char[] arr = s.toCharArray();

        // First pass: add data to array
        for (Character c : arr) {
            int index = (int)c % 97;
            alphabet[index]++;
        }

        // Second pass: search valid data in array
        for (int i = 0; i < s.length(); i++) {
            int index = (int)arr[i] % 97;
            if (alphabet[index] == 1)
                return i;
        }

        return -1;
    }

}
