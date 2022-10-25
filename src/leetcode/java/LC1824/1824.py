# Reference: https://leetcode.com/problems/minimum-sideway-jumps/discuss/1152411/PythonC%2B%2B-clean-dp-solution-with-explanation-O(N)
# Time: O(N) Space: O(N)
# Dynamic Programming
class Solution:
    def minSideJumps(self, obstacles: List[int]) -> int:
        n = len(obstacles) - 1
        dp = [[0] * 3 for _ in range(n)]
        dp[0][0] = dp[0][2] = 1

        for i in range(1, n):
            for l in range(3):
                lane = l + 1
                if obstacles[i] == lane or obstacles[i+1] == lane:
                    dp[i][l] = float('inf')
                else:
                    dp[i][l] = min(dp[i-1][l],
                                   dp[i-1][(l+1) % 3] + 1,
                                   dp[i-1][(l+2) % 3] + 1)

        return min(dp[-1])
