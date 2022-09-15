# Reference: https://leetcode.com/problems/degree-of-an-array/discuss/124317/JavaC%2B%2BPython-One-Pass-Solution

class Solution:
    def findShortestSubArray(self, nums: List[int]) -> int:
        if not nums:
            return -1

        first, count = {}, {}
        res = len(nums)
        degree = 0
        for i, num in enumerate(nums):
            if num not in first:
                first[num] = i
            count[num] = count.get(num, 0) + 1
            if count[num] > degree:
                degree = count[num]
                res = i - first[num] + 1
            elif count[num] == degree:
                res = min(res, i - first[num] + 1)

        return res
