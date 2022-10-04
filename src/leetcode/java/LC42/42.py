# Dynamic Programming
# Reference: https://labuladong.github.io/algo/4/33/129/
# Time: O(N)
# Space: O(N)
class Solution_Dynamic_Programming:
    def trap(self, height: List[int]) -> int:
        if not height or len(height) == 0:
            return 0

        n = len(height)
        left_max = [0] * n
        right_max = [0] * n
        left_max[0] = height[0]
        right_max[-1] = height[-1]

        for i in range(1, n):
            left_max[i] = max(left_max[i-1], height[i])
        for i in range(n-2, -1, -1):
            right_max[i] = max(right_max[i+1], height[i])

        res = 0
        for i in range(n):
            res += (min(left_max[i], right_max[i]) - height[i])
        return res

# Two Pointers: Dynamic Programming Optimized
# Reference: https://labuladong.github.io/algo/4/33/129/
# Time: O(N)
# Space: (1)
class Solution_Two_Pointers:
    def trap(self, height: List[int]) -> int:
        if not height or len(height) == 0:
            return 0

        n = len(height)
        left_max = right_max = 0
        left, right = 0, n - 1
        res = 0

        while left < right:
            left_max = max(height[left], left_max)
            right_max = max(height[right], right_max)

            if left_max < right_max:
                res += (left_max - height[left])
                left += 1
            else: # left_max >= right_max:
                res += (right_max - height[right])
                right -= 1

        return res


# Other Reference: https://leetcode.wang/leetCode-42-Trapping-Rain-Water.html
