# Reference: https://leetcode.wang/leetcode-283-Move-Zeroes.html
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        if not nums or len(nums) <= 1:
            return nums

        n = len(nums)
        i = j = 0
        while i < n:
            if nums[i] != 0:
                nums[i], nums[j] = nums[j], nums[i]
                j += 1
                i += 1
            else: # nums[i] == 0
                i += 1
