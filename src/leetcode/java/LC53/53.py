# Solution 1: Dynamic Programming
class Solution1:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        currentMax = nums[0]
        maxSum = nums[0]

        for i in range(1, n):
            if currentMax < 0:
                currentMax = nums[i]
            else:
                currentMax = currentMax + nums[i]
            maxSum = max(maxSum, currentMax)

        return maxSum


# Solution 2: Divide and Conquer
class Solution2:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        return self.findBest(nums, 0, n - 1)

    def findBest(self, nums: List[int], left: int, right: int) -> int:
        if left > right:
            return -math.inf

        mid = (left + right) // 2
        curr = best_left_sum = best_right_sum = 0

        for i in range(mid - 1, left - 1, -1):
            curr += nums[i]
            best_left_sum = max(best_left_sum, curr)

        curr = 0
        for i in range(mid + 1, right + 1, 1):
            curr += nums[i]
            best_right_sum = max(best_right_sum, curr)

        best_combined_sum = nums[mid] + best_left_sum + best_right_sum

        best_left_half = self.findBest(nums, left, mid - 1)
        best_right_half = self.findBest(nums, mid + 1, right)

        return max(best_combined_sum, best_left_half, best_right_half)