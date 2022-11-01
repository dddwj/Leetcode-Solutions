# Reference: https://leetcode.com/problems/longest-palindromic-substring/discuss/2954/Python-easy-to-understand-solution-with-comments-(from-middle-to-two-ends).
class Solution:
    def longestPalindrome(self, s: str) -> str:
        if not s or len(s) == 0:
            return ""

        res = ""
        for i in range(len(s)):
            odd_longest = self.expand(s, i, i)
            if len(odd_longest) > len(res):
                res = odd_longest
            even_longest = self.expand(s, i, i + 1)
            if len(even_longest) > len(res):
                res = even_longest
        return res

    def expand(self, s: str, left: int, right: int) -> str:
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return s[left + 1:right]     # Take one step back
