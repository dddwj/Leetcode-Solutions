# Brute Force
# Time: O(N^2)
class Solution_BruteForce:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        n = len(nums)
        max_s = -1
        for i in range(n):
            for j in range(i+1, n):
                s = nums[i] + nums[j]
                if s < k:
                    max_s = max(max_s, s)
        return max_s

# Two Pointers
# Time: O(NlgN)
# Space: O(1)
class Solution_TwoPointers:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        n = len(nums)
        nums.sort()
        left = 0
        right = len(nums) - 1
        ans = -1

        while left < right:
            s = nums[left] + nums[right]
            if (s < k):
                ans = max(ans, s)
                left += 1
            else:
                right -= 1

        return ans

# Counting Sort
# Time O(N+M) where  M = range of input array
# Space: O(M)
class Solution:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        ans = -1
        count = [0] * 1001
        for num in nums:
            count[num] += 1
        lo = 1
        hi = 1000
        while lo <= hi:
            if lo + hi >= k or count[hi] == 0:
                hi -= 1
            else:
                if count[lo] > (0 if lo < hi else 1):
                    ans = max(ans, lo + hi)
                lo += 1
        return ans