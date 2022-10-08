# Reference: https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/solutions/623732/java-c-python-dp-prefixsum-in-matrix-clean-code/
class Solution:
    def ways(self, pizza: List[str], k: int) -> int:
        m, n, MOD = len(pizza), len(pizza[0]), 10 ** 9 + 7
        preSum = [[0] * (n + 1) for _ in range(m + 1)]
        for r in range(m-1, -1, -1):
            for c in range(n-1, -1, -1):
                # x1 = preSum[r][c + 1]
                # x2 = preSum[r + 1][c]
                # x99 = preSum[r + 1][c + 1]
                preSum[r][c] = preSum[r][c + 1] + preSum[r + 1][c] - preSum[r + 1][c + 1] + (pizza[r][c] == 'A')
                # print(preSum[r][c])
        # print(preSum)

        @lru_cache(maxsize=None)
        def dp(k, r, c):            # dp(K, r, c) denotes: the number of ways to cut matrix[r..n-1][c..m-1] into K pieces so that each piece has at least one apple on them
            # print(preSum[r][c])
            if preSum[r][c] == 0:
                return 0
            if k == 0:
                return 1
            ans = 0
            # cut horizontally
            for nr in range(r + 1, m):
                if preSum[r][c] - preSum[nr][c] > 0:
                    ans = (ans + dp(k - 1, nr, c)) % MOD
            # cut vertically
            for nc in range(c + 1, n):
                if preSum[r][c] - preSum[r][nc] > 0:
                    ans = (ans + dp(k - 1, r, nc)) % MOD
            return ans

        return dp(k - 1, 0, 0)