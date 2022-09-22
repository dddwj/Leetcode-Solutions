# Tutorial: https://alexgolec.dev/google-interview-questions-deconstructed-the-knights-dialer/
# Reference: https://leetcode.com/problems/knight-dialer/discuss/189287/O(n)-time-O(1)-space-DP-solution-+-Google-interview-question-writeup/957389
# Reference: https://leetcode.com/problems/knight-dialer/discuss/190787/How-to-solve-this-problem-explained-for-noobs!!!
class Solution_memorization:
    def knightDialer(self, n: int) -> int:
        neighbors = {0: [4, 6], 1: [6, 8], 2: [7, 9], 3: [4, 8], 4: [0, 3, 9], 5: [], 6: [0, 1, 7], 7: [2, 6], 8: [1, 3], 9: [2, 4]}
        memo = {}

        def helper(i, n):
            if (i, n) in memo:
                return memo[(i, n)]
            if n == 1:
                return 1

            memo[(i, n)] = 0
            for neighbor in neighbors[i]:
                memo[(i, n)] += helper(neighbor, n - 1)
            memo[(i, n)] %= 10 ** 9 + 7
            return memo[(i, n)]

        ans = 0
        for i in range(10):
            ans += helper(i, n)
            ans %= (10 ** 9 + 7)
        return ans


# Reference: https://leetcode.com/problems/knight-dialer/discuss/189287/O(n)-time-O(1)-space-DP-solution-+-Google-interview-question-writeup
class Solution_dynamic_programming:
    def knightDialer(self, N):
        # Neighbors maps K: starting_key -> V: list of possible destination_keys
        neighbors = {
            0:(4,6),
            1:(6,8),
            2:(7,9),
            3:(4,8),
            4:(0,3,9),
            5:(),
            6:(0,1,7),
            7:(2,6),
            8:(1,3),
            9:(2,4)
        }
        current_counts = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
        for _ in range(N-1):
            next_counts = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            for src_key in range(10):
                for dst_key in neighbors[src_key]:
                    next_counts[dst_key] = (next_counts[dst_key] + current_counts[src_key]) % (10**9 + 7)
            current_counts = next_counts
        return sum(current_counts) % (10**9 + 7)