package leetcode.java.LC264;

import java.util.*;

public class Main_264 {

    // Reference: LC263, LC204
    //            剑指Offer P240

    public static void main(String[] args) {
        Main_264 main_264 = new Main_264();
//        System.out.println(main_264.nthUglyNumber(1500));
//        System.out.println(main_264.nthUglyNumber(0));
//        System.out.println(main_264.nthUglyNumber_solution1(6));
        System.out.println(main_264.nthUglyNumber_solution2(10));
        System.out.println(main_264.nthUglyNumber_solution2(1407));

    }


    // Solution1: Heap




    /*
        一个十分巧妙的动态规划问题
         1.我们将前面求得的丑数记录下来，后面的丑数就是前面的丑数*2，*3，*5
         2.但是问题来了，我怎么确定已知前面k-1个丑数，我怎么确定第k个丑数呢
         3.采取用三个指针的方法，p2,p3,p5
         4.p2指向的数字下一次永远*2，p3指向的数字下一次永远*3，p5指向的数字永远*5
         5.我们从2*p2 3*p3 5*p5选取最小的一个数字，作为第k个丑数
         6.如果第K个丑数==2*p2，也就是说前面0-p2个丑数*2不可能产生比第K个丑数更大的丑数了，所以p2++
         7.p3,p5同理
         8.返回第n个丑数
     */
    // Solution2: Dynamic Programming
    // Time: O(N), where N <= 1690
    // Space: O(N), where N <= 1690
    public int nthUglyNumber_solution2(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        int found = 1;

        while (found < n) {
            int temp = Math.min(Math.min(uglyNums[p2]*2, uglyNums[p3]*3), uglyNums[p5]*5);
            uglyNums[found] = temp;
            found++;

            if (temp == uglyNums[p2]*2) {
                p2++;
            }
            if (temp == uglyNums[p3]*3) {
                p3++;
            }
            if (temp == uglyNums[p5]*5) {
                p5++;
            }
        }

        return uglyNums[n-1];
    }

}
