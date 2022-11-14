class Solution:
    def change(self, amount: int, coins: List[int]) -> int:
        ways = [0] * (amount + 1)
        ways[0] = 1
        for coin in coins:
            for x in range(coin, amount + 1):
                ways[x] += ways[x - coin]
            # print(ways)
        return ways[-1]
