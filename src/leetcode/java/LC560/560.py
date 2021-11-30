from typing import *

class Solution:

    def subarraySum(self, nums: List[int], k: int) -> int:
        res = 0
        n = len(nums)
        d = dict()

        d[0] = 1
        prevSum = 0
        for i in range(n):
            prevSum += nums[i]
            if (prevSum-k) in d:
                res += d.get(prevSum-k)
            d[prevSum] = d.get(prevSum, 0) + 1

        return res
