# Reference: https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/279221/JavaPython-3-two-easy-DP-codes-w-comment-time-O(n)-NO-change-of-input
# Time: O(N) Space: O(N)
# Solution: Prefix sum
class Solution_Prefix:
    def maxSumTwoNoOverlap(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        n = len(nums)

        def max_sum(prefix_sum, L, R):
            maxL = ans = 0
            for i in range(L+R, n+1):
                maxL = max(maxL, prefix_sum[i-R] - prefix_sum[i-R-L])
                ans = max(ans, maxL + prefix_sum[i] - prefix_sum[i-R])
            return ans

        prefix_sum = [0] * (n+1)
        for i, num in enumerate(nums):
            prefix_sum[i + 1] = prefix_sum[i] + num
        return max(max_sum(prefix_sum, firstLen, secondLen), max_sum(prefix_sum, secondLen, firstLen))

# Reference: https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/279221/JavaPython-3-two-easy-DP-codes-w-comment-time-O(n)-NO-change-of-input
# Time: O(N) Space: O(N)
# Solution: Sliding window
class Solution_Sliding_window:
    def maxSumTwoNoOverlap(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        def maxSum(L: int, R: int) -> int:
            sumL = sumR = 0
            for i in range(0, L + R):
                if i < L:
                    sumL += nums[i]
                else:
                    sumR += nums[i]
            maxL = sumL
            ans = sumL + sumR
            for i in range(L + R, len(nums)):
                sumL += nums[i - R] - nums[i - L - R]
                maxL = max(maxL, sumL)
                sumR += nums[i] - nums[i - R]
                ans = max(ans, maxL + sumR)
            return ans

        return max(maxSum(firstLen, secondLen), maxSum(secondLen, firstLen))