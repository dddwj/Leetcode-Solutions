package leetcode.java.LC202;

import java.util.*;

public class Main_202 {
    public static void main(String[] args) {
        Main_202 main_202 = new Main_202();
        System.out.println(main_202.isHappy_solution1(19));
        System.out.println(main_202.isHappy_solution1(1));
        System.out.println(main_202.isHappy_solution1(10));
        System.out.println(main_202.isHappy_solution1(Integer.MAX_VALUE));
    }

    // My Solution: HashSet
    private Set<Integer> seen = new HashSet<>();
    public boolean isHappy_mysolution(int n) {
        if (n <= 0)
            return false;
        if (n == 1)
            return true;

        seen.add(n);
        System.out.println(seen);

        int sum = 0;
        while (n > 0) {
            int rm = n % 10;
            sum += rm * rm;
            n = n / 10;
        }

        return (!seen.contains(sum)) && isHappy_mysolution(sum);
    }


    // Solution1: Fast - Slow Pointers
    // NOTE: 判断循环就用【快慢指针】
    public boolean isHappy_solution1(int n) {
        int slow = n, fast = n;
        do{
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
            System.out.println("slow, fast = " + slow + ", " + fast);
        }while(slow != fast);

        return slow == 1;
    }

    private int bitSquareSum(int n) {
        int sum = 0;
        while(n > 0)
        {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }


}
