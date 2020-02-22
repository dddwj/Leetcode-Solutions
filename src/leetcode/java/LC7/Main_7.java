package leetcode.java.LC7;

import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;

public class Main_7 {
    public static void main(String[] args) {
        Main_7 main_7 = new Main_7();

        System.out.println(Integer.MAX_VALUE);

        System.out.println(main_7.reverse(1534236462));
        System.out.println(main_7.reverse(1534236460));


    }

    // Solution1: Classical ways!
    // Time: O(N) or O(log(X))  Space: O(1)     X is the input Integer. N is the length of X's char array.
    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int lastDigit = x % 10;
            int newResult = result * 10 + lastDigit;      // Note: 重点！常常用这种循环将字符串数组转为整数！！

            if ((newResult - lastDigit) / 10 != result)   // Overflow occured on last step.
                return 0;                                 // Note: 重点！常常用result配合newResult两个变量，判断是否溢出！

            result = newResult;
            x /= 10;
        }

        return result;
    }

    // Solution2: use long type to avoid overflow.
    // Time: O(N) or O(log(X))  Space: O(1)     X is the input Integer. N is the length of X's char array.
    public int reverse_solution2(int x) {
        long result = 0;
        while (x != 0) {      // Note: 重点！常常用这种循环将字符串数组转为整数！！
            result = result * 10 + x % 10;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
                return 0;
            x /= 10;
        }
        return (int)result;
    }


    // Previous Solution: Brute Force, did not consider the overflow. (Wrong Answer on Leetcode)
    public int reverse_previousSolution(int x) {
        // x = -123;
        x = 1534236469;
        Integer i = x;
        boolean positive = true;

        // Init data
        if (i < 0) {
            positive = false;
            i *= -1;
        }
        char[] arr = i.toString().toCharArray();

        // Reverse the array (not necessary)
        int start = 0, end = arr.length - 1;
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }

        // Transfer to number
        int number = 0;
        for (int j = 0; j < arr.length; j++) {
            int c = Integer.valueOf("" + arr[arr.length - 1 - j]);
            number += Math.pow(10, j) * c;
        }

        // Convert negative back
        if (!positive)
            number *= -1;

        return 0;
    }


}
