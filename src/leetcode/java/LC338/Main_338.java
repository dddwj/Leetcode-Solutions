package leetcode.java.LC338;

import java.util.Arrays;

public class Main_338 {

    // Ref: https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode/

    public static void main(String[] args) {
        Main_338 main_338 = new Main_338();
        System.out.println(Arrays.toString(main_338.countBits_solution4(8)));
    }



    // Solution1: Pop Count
    // Time: O(NK)   Space: O(1)
    // Ref: LC191
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            ans[i] = popcount(i);
        }
        return ans;
    }

    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; count++) {
            x &= (x - 1);
        }
        return count;
    }



    // Solution2: Dynamic Programming  (Highest Bit)
    // Time: O(N),  Space: O(1)
    public int[] countBits_solution2(int num) {
        int[] ans = new int[num + 1];
        int i = 0, b = 1;
        while (b <= num) {
            // generate [b, 2b) or [b, num) from [0, b)
            while (i < b && i + b <= num) {
                ans[i + b] = ans[i] + 1;
                i++;
            }
            i = 0;         // reset i
            b = b << 1;    // b = 2b
        }
        return ans;
    }



    // Solution3: Dynamic Programing (Lowest Bit)
    // Time: O(N)   Space: O(1)
    // Ref: https://leetcode-cn.com/problems/counting-bits/solution/hen-qing-xi-de-si-lu-by-duadua/
    public int[] countBits_solution3(int num) {
        if (num < 0)
            return null;

        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 1) {
                res[i] = res[i - 1] + 1;
            } else {        // i % 2 == 0
                res[i] = res[i >> 1];
            }
        }
        return res;
    }



    // Solution4: Dynamic Programming (Latest Bit)
    // Time: O(N)   Space: O(1)
    // Ref:  Solution1 + Solution3
    public int[] countBits_solution4(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i & (i - 1)] + 1;
        return ans;
    }
}
