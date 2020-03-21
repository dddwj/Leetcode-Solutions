package leetcode.java.LC509;

public class Main_509 {

    // Reference: 剑指offer P74

    public static void main(String[] args) {
        Main_509 main_509 = new Main_509();
        System.out.println(main_509.fib_solution2(23));
        System.out.println(main_509.fib_solution1(23));
    }

    // Solution1:  Naive   (slow!!)
    // Time: O(2^h), where h = N
    // Space: O(h), where h = N
    public int fib_solution1(int N) {
        if (N <= 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        return fib_solution1(N - 1) + fib_solution1(N - 2);
    }



    // Solution2: Bottom-up Approach using Memorization
    // Time: O(N) Space: O(N)
    public int fib_solution2(int N) {
        if (N <= 1) {
            return N;
        }
        int[] cache = new int[N + 1];
        cache[1] = 1;

        for (int i = 2; i <= N; i++) {
            cache[i] = cache[i-1] + cache[i-2];
        }
        return cache[N];
    }


    // Solution3:  Top-Down Approach using Memorization
    // Time: O(N)
    // Space: O(N)
    public int fib_solution3(int N) {
        if (N <= 1) {
            return N;
        }

        Integer[] cache = new Integer[N+1];
        cache[0] = 0;
        cache[1] = 1;

        return fibCalc(N, cache);
    }
    private int fibCalc(int N, Integer[] cache) {
        if (cache[N] != null) {
            return cache[N];
        }
        cache[N] = fibCalc(N - 1, cache) + fibCalc(N - 2, cache);
        return cache[N];
    }



    // Solution4: Iterative Top-Down Approach
    // Time: O(N)
    // Space: O(1)
    public int fib_solution4(int N) {
        int[] result = new int[]{0, 1};
        if (N <= 1) {
            return result[N];
        }
        int fibN = 0, prevOne = result[1], prevTwo = result[0];

        for (int i = 2; i <= N; i++) {
            fibN = prevOne + prevTwo;

            prevTwo = prevOne;
            prevOne = fibN;
        }
        return fibN;
    }


    // Solution5: Matrix Exponentiation


    // Solution6:  Math: Golden Ratio
    // Time: O(1)  Space: O(1)
    public int fib_solution6(int N) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int)Math.round(Math.pow(goldenRatio, N)/ Math.sqrt(5));
    }
}
