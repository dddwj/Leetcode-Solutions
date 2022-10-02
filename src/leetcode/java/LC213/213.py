# Ref:  https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw
class Solution_Bottom_Up:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        if len(nums) <= 3:
            return max(nums)

        return max(
            self.rob_range(nums, 0, n - 2),
            self.rob_range(nums, 1, n - 1)
        )

    def rob_range(self, nums: List[int], start: int, end: int) -> int:
        prev_1 = 0
        prev_2 = 0
        for i in range(start, end+1):
            current = nums[i]
            temp = prev_1
            prev_1 = max(current + prev_2, prev_1)
            prev_2 = temp

        return prev_1