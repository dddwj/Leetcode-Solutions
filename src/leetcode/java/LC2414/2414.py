class Solution:
    def longestContinuousSubstring(self, s: str) -> int:
        slow = 0
        fast = 1
        n = len(s)
        longest = 1

        while fast < n:
            while fast < n and ord(s[fast]) == ord(s[fast-1]) + 1:
                longest = max(longest, fast-slow+1)
                fast += 1
            slow = fast
            fast += 1

        return longest