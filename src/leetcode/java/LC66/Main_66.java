package leetcode.java.LC66;

import java.util.Arrays;

public class Main_66 {
    public static void main(String[] args) {
        Main_66 main_66 = new Main_66();
        int[] rst = main_66.plusOne(new int[] {8, 9, 9, 9});
        System.out.println(Arrays.toString(rst));
    }

    // Solution: Iterative
    // Time: O(1)  Space: O(1) ?
    public int[] plusOne(int[] digits) {
        return plusOnDigit(digits.length - 1, digits);
    }

    public int[] plusOnDigit(int index, int[] digits) {
        if (digits[index] < 9) {  // Add One
            digits[index]++;
            return digits;
        }
        else if (digits[index] == 9) {  // Need to add another digit?
            digits[index] = 0;
            if (index - 1 < 0) {   // Need to Add leading number?
                int[] temp = new int[digits.length + 1];    // eg：数字999，扩一位，一定是1000（第0位一定为1；后面所有位一定为0）。
                temp[0] = 1;
                return temp;
            } else
                return plusOnDigit(index - 1, digits);
        } else {
            System.out.println("Error");
            return null;
        }
    }
}
