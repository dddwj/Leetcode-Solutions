# Reference: https://leetcode.com/problems/break-a-palindrome/solution/
class Solution:
    def breakPalindrome(self, palindrome: str) -> str:
        if not palindrome:
            return ""

        n = len(palindrome)
        if n == 1:
            return ""

        for i in range(0, n//2):
            if palindrome[i] != 'a':
                return palindrome[:i] + 'a' + palindrome[i+1:]

        # The palindrome string only has 'a'. Change the last character to 'b'
        return palindrome[:-1] + 'b'
