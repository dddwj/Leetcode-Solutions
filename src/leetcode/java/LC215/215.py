from typing import *
from heapq import *

class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        if not nums or len(nums) < k:
            return 0

        h = []
        for num in nums:
            heappush(h, num)
            if len(h) > k:
                heappop(h)

        return heappop(h)
