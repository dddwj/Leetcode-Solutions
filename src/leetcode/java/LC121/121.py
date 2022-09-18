from typing import *

class Solution1:
    def maxProfit(self, prices: List[int]) -> int:
        max_profit = 0
        buy = 0
        sell = 0
        for i, price in enumerate(prices):
            if price < prices[buy]:
                buy = i
            else:
                if price - prices[buy] > max_profit:
                    max_profit = price - prices[buy]
                    sell = i
        return max_profit

class Solution2:   # Same as Solution1
    def maxProfit(self, prices: List[int]) -> int:
        maxProfit = 0
        min_price = math.inf

        for i, price in enumerate(prices):
            if price < min_price:
                min_price = price
            else:
                maxProfit = max(maxProfit, price - min_price)


        return maxProfit
