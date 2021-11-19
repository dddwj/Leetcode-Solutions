from typing import *

# Reference: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/solution/810681

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        firstTransactionCost = prices[0]
        firstTransactionProfit = 0

        mostMoneyInPocket = -prices[0]
        profitFromTwoTransactions = 0

        for currentPrice in prices:
            firstTransactionCost = min(firstTransactionCost, currentPrice)
            firstTransactionProfit = max(firstTransactionProfit, currentPrice-firstTransactionCost)

            mostMoneyInPocket = max(mostMoneyInPocket, firstTransactionProfit-currentPrice)
            profitFromTwoTransactions = max(profitFromTwoTransactions, mostMoneyInPocket+currentPrice)

        return profitFromTwoTransactions