package leetcode.java.LC804;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_804 {
    public static void main(String[] args) {
        Main_804 main_804 = new Main_804();
        System.out.println(main_804.uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
    }


    // Solution: Hashset
    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        Set<String> seen = new HashSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                sb.append(morse[c - 'a']);
            }
            seen.add(sb.toString());
        }

        return seen.size();
    }
}
