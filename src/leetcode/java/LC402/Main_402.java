package leetcode.java.LC402;

import java.util.*;

public class Main_402 {
    public static void main(String[] args) {
        Main_402 main_402 = new Main_402();
        System.out.println(main_402.removeKdigits("1432219", 3));
    }



    // Solution: Stack + Greedy
    // Time: O(N)
    // Space: O(N)
    // Ref: https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode/
    public String removeKdigits(String num, int k) {

        LinkedList<Character> stack = new LinkedList<Character>();

        // greedy, looking for the smallest number combination
        for (char digit : num.toCharArray()) {
            while (stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k--;
            }
            stack.addLast(digit);
        }

        // remove the remaining digits from the tail
        for (int i = 0; i < k; i++) {
            stack.removeLast();
        }

        // build the final string, while removing the leading zeros
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        for (char digit : stack) {
            if (leadingZero && digit == '0')
                continue;
            leadingZero = false;
            ret.append(digit);
        }

        // return the final string
        if (ret.length() == 0)
            return "0";
        return ret.toString();
    }
}
