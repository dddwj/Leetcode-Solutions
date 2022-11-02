# See also: LC862

# Reference: https://leetcode.com/problems/minimum-size-subarray-sum/solution/

# Solution: Two Pointers
# Time: O(N)
# Space: O(1)
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        if not nums:
            return -1

        window = 0
        left = 0
        right = 0
        n = len(nums)
        min_length = math.inf

        while left < n:
            while window < target and right < n:
                # print(left, right)
                window += nums[right]
                right += 1
            if window >= target:
                length = right - left
                min_length = min(min_length, length)
            window -= nums[left]
            left += 1

        return 0 if min_length == math.inf else min_length