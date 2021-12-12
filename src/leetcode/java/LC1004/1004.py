# Reference: https://leetcode.com/problems/max-consecutive-ones-iii/discuss/1304346/Simple-Solution-w-Explanation-or-Sliding-Window-Approach-with-Comments
from typing import *


class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        result = 0
        left = 0
        n = len(nums)

        for right in range(n):
            if nums[right] == 0:
                if k == 0:
                    while nums[left] != 0:
                        left += 1
                    left += 1
                else:
                    k -= 1
            result = max(result, right - left + 1)

        return result


s = Solution()
print(s.longestOnes([1,1,1,0,0,0,1,1,1,1,0], 2))