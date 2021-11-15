from typing import *

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        profit = 0
        for i, price in enumerate(prices):
            if i > 0 and price > prices[i-1]:
                profit += (price - prices[i-1])

        return profit

class Solution2:
    def maxProfit(self, prices: List[int]) -> int:
        i = 0
        valley = 0
        peak = 0
        profit = 0
        while i < len(prices) - 1:
            while i < len(prices) - 1 and prices[i] >= prices[i+1]:
                i = i + 1
            valley = i

            while i < len(prices) - 1 and prices[i] <= prices[i+1]:
                i = i + 1
            peak = i

            profit += (prices[peak] - prices[valley])

        return profit