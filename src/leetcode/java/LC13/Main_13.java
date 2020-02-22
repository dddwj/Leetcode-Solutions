package leetcode.java.LC13;

import java.util.HashMap;
import java.util.Map;

public class Main_13 {
    public static void main(String[] args) {
        String input = "MCMXCIV";
//        String input = "LVIII";
//        String input = "D";
//        String input = "IV";
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
            put("IV", 4);
            put("IX", 9);
            put("XL", 40);
            put("XC", 90);
            put("CD", 400);
            put("CM", 900);
        }};

        char[] inputArray = input.toCharArray();
        int i = 0;  // index
        int ans = 0;  // result
        // Examine the first N-1 numbers
        while (i < inputArray.length - 1) {
            char curr = inputArray[i];
            char next = inputArray[i+1];
            int currNum = map.get(""+curr);
            int nextNum = map.get(""+next);
            if (currNum < nextNum) {
                // There exists a two-char number
                ans += map.get(""+curr+next);
                i += 2;
            } else {
                // It is a one-char number
                ans += map.get(""+curr);
                i += 1;
            }
        }

        // Examine the Nth number, if applicable
        if (i == inputArray.length - 1)
            ans += map.get(""+inputArray[i]);

        System.out.println(ans);
    }

}
