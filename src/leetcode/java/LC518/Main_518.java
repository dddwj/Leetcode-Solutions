// Reference: https://leetcode.com/problems/coin-change-ii/solution/
package leetcode.java.LC518;

import java.util.Arrays;

public class Main_518 {
    public int change(int amount, int[] coins) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, 0);
        memo[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x < amount + 1; x++) {
                memo[x] += memo[x-coin];
                // System.out.println(Arrays.toString(memo));
            }
            // System.out.println("======" + coin);
        }


        return memo[amount];
    }
}
