package leetcode.java.LC50;

public class Main_50 {
    public static void main(String[] args) {
        Main_50 main_50 = new Main_50();
        System.out.println(main_50.myPow_solution2(5, 2));
        System.out.println(main_50.myPow_solution2(5, -2));
        System.out.println(main_50.myPow_solution2(1.0, 2147483647));
        System.out.println(main_50.myPow_solution2(1.1, 2147483647));
        System.out.println(main_50.myPow_solution2(1.0, -2147483648));
        System.out.println(main_50.myPow_solution2(1.1, -2147483648));

    }

    // My Solution (Solution1) : Brute Force  (Easy to TLE!)
    // Time: O(N)
    // Space: O(1)
    public double myPow_solution1(double x, int n) {
        if (n == 0)
            return 1;
        if (x == 1)
            return 1;
        if (x == -1) {
            if ((n & 1) != 0) {
                return -1;
            } else {
                return 1;
            }
        }
        if (n == -2147483648) {
            if(x > -1 && x < 1){        // Edge Case! Ref: https://leetcode.wang/leetCode-50-Pow.html
                return Double.POSITIVE_INFINITY;
            } else{
                return 0;
            }
        }

        double res = 1.0;
        boolean isNeg = false;
        if (n < 0) {
            n = -n;
            isNeg = true;
        }
        for (int i = 0; i < n; i++) {
            res *= x;
        }
        return isNeg ? (1 / res) : res;
    }


    // Solution2: Recursion     X^N = X^(N/2) * X^(N/2)
    // Ref: https://leetcode.wang/leetCode-50-Pow.html
    // Time: O(logN)
    // Space: O(N)
    public double myPow_solution2(double x, int n) {
        if (n == 0)
            return 1;
        if (x == -1) {
            if ((n & 1) == 0)
                return 1;
            else
                return -1;
        }
        if (x == 1)
            return 1;
        if (n == -2147483648) {
            if(x > -1 && x < 1){        // Edge Case! Ref: https://leetcode.wang/leetCode-50-Pow.html
                return Double.POSITIVE_INFINITY;
            } else{
                return 0;
            }
        }

        double res = 1.0;
        if (n > 0) {
            res = pow_recursion(x, n);
        } else {
            n = -n;
            res = pow_recursion(x, n);
            res = 1 / res;
        }
        return res;
    }

    private double pow_recursion(double x, int n) {
        if (n == 0)
            return 1;

        double temp = pow_recursion(x, n / 2);
        if ((n & 1) == 0) {
            return temp * temp;
        } else {
            return temp * temp * x;
        }
    }




    // Solution3:  Iteration
    // Ref: https://leetcode.wang/leetCode-50-Pow.html
    // Time: O(N)
    // Space: O(1)
    public double myPow_solution3(double x, int n) {
        if (x == -1) {
            if ((n & 1) != 0) {
                return -1;
            } else {
                return 1;
            }
        }
        if (x == 1.0f)
            return 1;

        if (n == -2147483648) {
            if(x > -1 && x < 1){        // Edge Case! Ref: https://leetcode.wang/leetCode-50-Pow.html
                return Double.POSITIVE_INFINITY;
            } else{
                return 0;
            }
        }
        double mul = 1;
        if (n > 0) {
            mul = powIteration(x, n);
        } else {
            n = -n;
            mul = powIteration(x, n);
            mul = 1 / mul;
        }
        return mul;
    }

    public double powIteration(double x, int n) {
        double ans = 1;
        //遍历每一位
        while (n > 0) {
            //最后一位是 1，加到累乘结果里
            if ((n & 1) == 1) {
                ans = ans * x;
            }
            //更新 x
            x = x * x;
            //n 右移一位
            n = n >> 1;
        }
        return ans;
    }
}
