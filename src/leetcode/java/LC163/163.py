# Reference:https://leetcode.com/problems/missing-ranges/solution/
class Solution:
    def findMissingRanges(self, nums: List[int], lower: int, upper: int) -> List[str]:
        if not nums:
            return [(str(lower) + "->" + str(upper)) if lower != upper else str(lower)]

        ans = []
        prev = lower - 1
        for i in range(len(nums) + 1):
            if i < len(nums):
                curr = nums[i]
            else:
                curr = upper + 1
            if prev + 1 <= curr - 1:
                ans.append((prev + 1, curr - 1))
            prev = curr

        return [(str(start) + "->" + str(end)) if start != end else str(start) for start, end in ans]
