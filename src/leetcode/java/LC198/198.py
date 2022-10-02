# Ref:  https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw

# Solution: Top Down
class Solution_Top_Down:
    def rob(self, nums: List[int]) -> int:
        self.memo = [-1] * len(nums)
        if not nums:
            return 0
        return self.dp(nums, 0)

    def dp(self, nums: List[int], start: int) -> int:
        if start >= len(nums):
            return 0

        if self.memo[start] != -1:
            return self.memo[start]

        res = max(self.dp(nums, start + 1), nums[start] + self.dp(nums, start + 2))
        self.memo[start] = res
        return res

# Solution: Bottom Up
class Solution_Bottom_Up:
    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0
        if len(nums) <= 2:
            return max(nums)

        n = len(nums)
        dp = [0] * n
        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])

        for i in range(2, n):
            dp[i] = max(dp[i-1], dp[i-2] + nums[i])

        return dp[n-1]