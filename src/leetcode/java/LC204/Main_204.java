package leetcode.java.LC204;

import java.util.Arrays;

public class Main_204 {

    // ref: https://leetcode-cn.com/problems/count-primes/solution/ru-he-gao-xiao-pan-ding-shai-xuan-su-shu-by-labula/
    // ref2: https://www.bilibili.com/video/av46300180/
    // Reference: LC263, LC264

    public static void main(String[] args) {
        Main_204 main_204 = new Main_204();
        main_204.countPrimes_solution3(9);
    }


    // Solution1: Brute Force
    // Time: O( N*sqrt(N) )  Space: O(1)
    public int countPrimes_solution1(int n) {
        if (n <= 2) return 0;

        int count = 0;
        count++;    // 算上质数2

        for (int i = 3; i < n; i += 2)   // 遍历所有奇数
            if (isPrime(i)) {
                count++;
            }

        return count;
    }

    // 判断整数 n 是否是素数
    private boolean isPrime(int i) {
        for (int j = 2; j * j <= i; j++)    // 利用因子的对称性，简化 j<i 为 j<=sqrt(i)。  j*j<=i 比 j<=sqrt(i) 效率更优。
            if (i % j == 0)
                // 有其他整除因子
                return false;
        return true;
    }



    // Solution2:  Sieve of Eratosthenes Algorithm  -- (Not optimized)
    // ref: https://leetcode-cn.com/problems/count-primes/solution/ru-he-gao-xiao-pan-ding-shai-xuan-su-shu-by-labula/
    public int countPrimes_solution2(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);

        // 判断
        for (int i = 2; i < n; i++) {   // 遍历 [2, N)
            if (isPrim[i]) {
                // 如果i是素数， 那么i的倍数不可能是素数了，接下去排除所有i的倍数
                for (int j = 2 * i; j < n; j += i)
                    isPrim[j] = false;
            }
        }

        // 统计素数个数
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) {
                count++;
                System.out.println(i);
            }

        return count;
    }



    // Solution3:  Sieve of Eratosthenes Algorithm  -- (optimized)
    // Time:  O(N * loglogN)  Space: O(1)
    // ref: https://leetcode-cn.com/problems/count-primes/solution/ru-he-gao-xiao-pan-ding-shai-xuan-su-shu-by-labula/
    public int countPrimes_solution3(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);

        // 判断
        for (int i = 2; i * i <= n; i++) {       // 优化1： 因子的对称性，遍历 [ 2, sqrt(N) ]。 简化 i<=N 为： i<=sqrt(N)
            if (isPrim[i]) {
                // 如果i是素数， 那么i的倍数不可能是素数了，接下去排除所有i的倍数
                for (int j = i * i; j < n; j += i)  // 优化2： 将遍历[2, n) 简化为：遍历[i*i, n)。原因： (2..3..4....i-1) * i 已经在之前的循环中被标记过为false了
                    isPrim[j] = false;
            }
        }

        // 统计素数个数
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) {
                count++;
                System.out.println(i);
            }

        return count;
    }
}
