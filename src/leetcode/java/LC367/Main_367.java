package leetcode.java.LC367;

import java.util.*;

public class Main_367 {

    public static void main(String[] args) {
        Main_367 main_367 = new Main_367();
//        System.out.println(main_367.isPerfectSquare_solution1(16));
//        System.out.println(main_367.isPerfectSquare_solution1(2147395600 - 1));
//        System.out.println(main_367.isPerfectSquare_solution1(2147395600));
//        System.out.println(main_367.isPerfectSquare_solution1(Integer.MAX_VALUE));
        System.out.println(main_367.isPerfectSquare_solution1(808201));
        System.out.println(main_367.isPerfectSquare_solution1(5));
    }

    // My Solution:
    // Time: O( sqrt(N) )
    // Space: O( sqrt(N) )
    public boolean isPerfectSquare_mysolution(int num) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= num / i; i++) {        // use i <= num / i, instead of i * i <= num, to prevent overflow.
            set.add(i * i);
        }
        return set.contains(num);
    }


    // Solution1: Binary Search
    // Time: O(logN)
    // Space: O(1)
    public boolean isPerfectSquare_solution1(int num) {
        if (num < 2)
            return true;
        long right = num / 2;
        long left = 2;
        while (left <= right) {
            long x = (right + left) / 2;
            if (x * x == num) {          // 注意：不能写成 x * x == num, 会溢出。
                return true;
            } else if (x * x < num) {
                left = x + 1;
            } else if (x * x > num) {
                right = x - 1;
            }
        }
        return false;
    }
}
