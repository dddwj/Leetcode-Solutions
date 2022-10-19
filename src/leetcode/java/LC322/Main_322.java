package leetcode.java.LC322;

import java.util.Arrays;

public class Main_322 {
    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, amount + 1);
        memo[0] = 0;

        for (int i = 0; i < memo.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                memo[i] = Math.min(memo[i], memo[i - coin] + 1);
            }
        }

        if (memo[amount] != amount + 1) {
            return memo[amount];
        }
        return -1;
    }

    public static void main(String[] args) {
        Main_322 main_322 = new Main_322();
        System.out.println(main_322.coinChange(new int[]{2, 3, 5}, 10));
    }

}
