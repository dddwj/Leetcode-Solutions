from typing import *

class Solution:
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