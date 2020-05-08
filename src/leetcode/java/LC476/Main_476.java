package leetcode.java.LC476;

public class Main_476 {

    public static void main(String[] args) {
        Main_476 main_476 = new Main_476();
        System.out.println(main_476.findComplement_solution1(5));
        System.out.println(main_476.findComplement_solution1(1));
        System.out.println(main_476.findComplement_solution1(Integer.MAX_VALUE));
        System.out.println(main_476.findComplement_solution1(Integer.MIN_VALUE));
        System.out.println(main_476.findComplement_solution1(Integer.MIN_VALUE + 1));
        System.out.println(main_476.findComplement_solution1(-1));
        System.out.println(main_476.findComplement_solution1(0));
    }


    // My Solution: Bit Manipulation
    public int findComplement_mysolution(int num) {
        if (num == 0)
            return 1;

        int mask = 0, val = num;
        while (val != 0) {
            mask = (mask << 1) + 1;
            val = val >>> 1;
        }
        return num ^ mask;
    }


    // Solution1: Bit Manipulation
    // Ref: https://leetcode-cn.com/problems/number-complement/solution/javaxiao-lu-100wu-xu-qiu-shu-zi-de-er-jin-zhi-jie-/
    public int findComplement_solution1(int num) {
    /*
        找到数字所在的区间[2^(i-1), 2^i]
        找到上区间，然后减一，取得是全一
        这样num与max-1异或后就是所需要的结果
        例子：5的补数为2，5^2=7，找到5的区间在[4,8],然后8-1=7，得到7
        所以5的补数2=5^7,同理任何数均可如此处理。
        注意：数字类型转换，浮点型和整型。
    */
        double max = 0;
        // 循环找到上限
        for(int index = 0; num >= max; index++) {       // Note: 负数不需要考虑mask位数，位数总为32位 (mask === max - 1 = 0 - 1 = -1, 二进制:1111....111 (32个1))。
            max = Math.pow(2, index);
        }
        // 上限减一取的此区间2进制为全一的数
        int mask = (int)(max - 1);
        return num ^ mask;
    }
}
