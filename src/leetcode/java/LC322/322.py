# Reference: https://labuladong.github.io/algo/1/7/

# Dynamic Programming: Top Down
# Time: O(S*n) where S = amount, n = len(coins)
# Space: O(S)
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        self.memo = [None] * (amount + 1)
        res = self.dp(coins, amount)
        # print(self.memo)
        return res

    def dp(self, coins: List[int], amount: int) -> int:
        if amount == 0:   # Problem solved
            return 0
        if amount < 0:    # Problem can never be solved
            return -1
        if self.memo[amount]:
            return self.memo[amount]

        min_cost = float('inf')
        for coin in coins:
            sub_problem = self.dp(coins, amount - coin)
            if sub_problem == -1:
                continue
            min_cost = min(min_cost, sub_problem + 1)
        self.memo[amount] = min_cost if (min_cost != float('inf')) else -1
        return self.memo[amount]



# Dynamic Programming: Bottom Up
# Time: O(S*n) where S = amount, n = len(coins)
# Space: O(S)
class Solution_Bottom_Up:
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [amount + 1] * (amount + 1)
        dp[0] = 0

        for i in range(0, len(dp)):
            for coin in coins:
                if i - coin < 0:
                    continue
                dp[i] = min(dp[i], dp[i - coin] + 1)

        # print(dp)
        return dp[amount] if (dp[amount] != amount + 1) else -1

