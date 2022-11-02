# See also: LC209
# Reference:  https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque
#           https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/189039/Detailed-intuition-behind-Deque-solution

# Solution: Deque + prefix sum
# Time: O(N)
# Space: O(N)
class Solution:
    def shortestSubarray(self, A: List[int], k: int) -> int:
        if not A:
            return -1

        n = len(A)
        prefix = [0] * (n + 1)
        for i in range(n):
            prefix[i + 1] = prefix[i] + A[i]

        d = collections.deque()
        res = n + 1
        for i in range(n+1):
            while d and prefix[i] - prefix[d[0]] >= k:
                res = min(res, i - d.popleft())
            while d and prefix[i] <= prefix[d[-1]]:
                d.pop()
            d.append(i)

        return res if res <= n else -1