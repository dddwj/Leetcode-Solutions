# Reference: https://leetcode.com/problems/minimum-cost-to-make-array-equal/discuss/2734162/JavaC%2B%2BPython-Binary-Search
#            https://leetcode.com/problems/minimum-cost-to-make-array-equal/discuss/2734728/Pure-math-based-explanation-of-why-Cost-Function-is-convex
class Solution:
    def get_cost(self, target, nums_cost):
        total = 0
        for num, c in nums_cost:
            total += abs(num - target) * c
        return total

    def minCost(self, nums: List[int], cost: List[int]) -> int:
        nums_cost = [(n, c) for n, c in zip(nums, cost)]
        nums_cost.sort()

        left = nums_cost[0][0]
        right = nums_cost[-1][0]

        min_cost = self.get_cost(left, nums_cost)
        while left < right:
            mid = (left + right) // 2
            y1 = self.get_cost(mid, nums_cost)
            y2 = self.get_cost(mid+1, nums_cost)
            min_cost = min(y1, y2)
            if y1 < y2:
                right = mid
            else:
                left = mid + 1

        return min_cost