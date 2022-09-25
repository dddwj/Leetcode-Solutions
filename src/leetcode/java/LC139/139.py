# Great Tutorial: https://leetcode.wang/leetcode-139-Word-Break.html

# Dynamic Programming
# Time: O(N^3)  There are two nested loops, and substring computation at each iteration. Overall that results in O(N^3) time complexity.
# Space: O(N) for dp array
class Solution_dynamic_programming:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        word_set = set(wordDict)
        n = len(s)
        dp = [False] * (n+1)
        dp[0] = True

        for sub_end in range(1, n+1):
            for sub_start in range(0, sub_end):
                if dp[sub_start] and s[sub_start: sub_end] in word_set:
                    dp[sub_end] = True
                    break

        return dp[n]

# Recursion (Brute Force): TLE
# Time: O(2^N)    ref: https://leetcode.com/problems/word-break/discuss/169383/solved-The-Time-Complexity-of-The-Brute-Force-Method-Should-Be-O(2n)-and-Prove-It-Below
class Solution_brute_force:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:

        def wb(s):
            if len(s) == 0:
                return True
            for i in range(1, len(s)+1):
                if s[0:i] in word_set and wb(s[i:]):
                    return True
            return False

        word_set = set(wordDict)
        return wb(s)

# Recursion with memoization
# Time: O(N^3) ?
# Space: O(N) for memo
class Solution_brute_force_optimized:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        memo = [None] * (len(s)+1)

        def recursive(s):
            if len(s) == 0:
                return True
            if memo[len(s)] is not None:
                return memo[len(s)]
            for i in range(1, len(s)+1):
                if s[0:i] in word_set and recursive(s[i:]):
                    memo[i] = True
                    return True
            memo[i] = False
            return False

        word_set = set(wordDict)
        return recursive(s)