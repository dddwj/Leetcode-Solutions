package leetcode.java.LC201;

public class Main_201 {

    // Ref: https://leetcode.wang/leetcode-201-Bitwise-AND-of-Numbers-Range.html

    public static void main(String[] args) {
        Main_201 main_201 = new Main_201();
        System.out.println(main_201.rangeBitwiseAnd_solution2(2000, Integer.MAX_VALUE));
        System.out.println(main_201.rangeBitwiseAnd_solution2(Integer.MAX_VALUE - 1, Integer.MAX_VALUE));
    }



    // Solution1: Brute Force (Optimized)
    // Ref: https://leetcode.wang/leetcode-201-Bitwise-AND-of-Numbers-Range.html
    public int rangeBitwiseAnd_solution1(int m, int n) {
        if (m == 0 || m == Integer.MAX_VALUE) {
            return m;
        }

        int res = m;
        for (int i = m + 1; i <= n; i++) {
            res = res & i;
            if (res == 0 || i == Integer.MAX_VALUE)   // Optimization Point
                break;
        }
        return res;
    }



    // Solution2: Bit Manipulation (Bitwise AND between odd and even numbers)
    // Ref: https://leetcode.wang/leetcode-201-Bitwise-AND-of-Numbers-Range.html
    public int rangeBitwiseAnd_solution2(int m, int n) {
        int tailZeroCount = 0;
        while (n != m) {
            m = m >> 1;
            n = n >> 1;
            tailZeroCount++;
        }
        return m << tailZeroCount;
    }




    // Solution3: XOR
    // Ref: https://leetcode.wang/leetcode-201-Bitwise-AND-of-Numbers-Range.html
    // To be understood...
}
