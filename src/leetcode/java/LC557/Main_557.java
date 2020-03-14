package leetcode.java.LC557;

import java.util.*;

public class Main_557 {

    // Reference: https://leetcode.com/problems/reverse-words-in-a-string-iii/solution/

    public static void main(String[] args) {
        Main_557 main_557 = new Main_557();
        System.out.println(main_557.reverseWords_solution3("Let's take LeetCode contest"));
        System.out.println(main_557.reverseWords_solution3("Let's"));
        System.out.println(main_557.reverseWords_solution3(" "));
    }


    // My Solution: Naive
    // Time: O(N)
    // Space: O(N)
    public String reverseWords_mysolution(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Stack<Character> stack = new Stack<>();
            while (i < n && s.charAt(i) != ' ') {
                stack.push(s.charAt(i));
                i++;
            }
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            if (i != n) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }



    // Solution1:  Built-in Functions: StringBuffer.reverse()
    // Time:  O(N)
    // Space:  O(N)
    public String reverseWords_solution1(String s) {
        String words[] = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word: words) {
            res.append(new StringBuffer(word).reverse().toString()).append(" ");
        }
        return res.toString().trim();
    }


    // Solution2:   Write my Functions  (Split & Reverse)
    // Time:  O(N)
    // Space:   O(N)
    public String reverseWords_solution2(String s) {
        String words[] = split(s);
        StringBuilder res=new StringBuilder();
        for (String word: words)
            res.append(reverse(word) + " ");
        return res.toString().trim();
    }
    public String[] split(String s) {
        ArrayList < String > words = new ArrayList < > ();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                words.add(word.toString());
                word = new StringBuilder();
            } else
                word.append( s.charAt(i));
        }
        words.add(word.toString());
        return words.toArray(new String[words.size()]);
    }

    public String reverse(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            res.insert(0,s.charAt(i));          // NOTE: res.insert(0,char) 用于翻转字符串，值得学习
        return res.toString();
    }



    // Solution3:   StringBuilder and reverse
    // Time:  O(N)
    // Space:  O(N)
    public String reverseWords_solution3(String input) {
        final StringBuilder result = new StringBuilder();
        final StringBuilder word = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                word.append(input.charAt(i));
            } else {
                result.append(word.reverse());
                result.append(" ");
                word.setLength(0);
            }
        }
        result.append(word.reverse());
        return result.toString();
    }


}
