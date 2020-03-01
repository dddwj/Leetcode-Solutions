package leetcode.java.LC371;

public class Main_371 {

    public static void main(String[] args) {
        Main_371 main_371 = new Main_371();
        main_371.getSum(9, 18);
    }

    /*
    *  GREAT Tutorial:
    *   https://leetcode.com/problems/sum-of-two-integers/discuss/84278/A-summary:-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
    *   https://leetcode-cn.com/problems/sum-of-two-integers/solution/wei-yun-suan-xiang-jie-yi-ji-zai-python-zhong-xu-y/
    * */

    public int getSum(int a, int b) {
        while (b != 0) {      // 循环，直到进位为0
            // 与(&)操作：计算进位
            // 异或(^)操作： 计算无进位加法
            int carry = (a&b) << 1;     // 左移一位，模拟进位。
            a = a^b;
            b = carry;
        }
        return a;
    }
}
