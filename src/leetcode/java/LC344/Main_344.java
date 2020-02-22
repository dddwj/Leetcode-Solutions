package leetcode.java.LC344;

import java.util.Arrays;

public class Main_344 {
    public static void main(String[] args) {
        Main_344 main_344 = new Main_344();
        main_344.reverseString_solution2("hello".toCharArray());
        main_344.reverseString_solution2("a".toCharArray());
        main_344.reverseString_solution2("supper".toCharArray());

    }

    // Solution1: Recursion
    // Time: O(N)  Space: O(N), to keep the recursion stack
    // Note: in-place != constant space, because of recursion stack.
    public void helper(char[] s, int left, int right) {
        if (left > right)
            return;

        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        left++;
        right--;

        helper(s, left, right);
    }
    public void reverseString(char[] s) {
        helper(s, 0, s.length - 1);
    }


    // Solution2: Two Pointers
    // Time: O(N)  Space: O(1)
    public void reverseString_solution2(char[] s) {
        int start = 0;
        int end = s.length - 1;

        while (start < end) {
            // Swap the corresponding elements
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start ++;
            end --;
        }
        System.out.println(Arrays.toString(s));

    }


}
