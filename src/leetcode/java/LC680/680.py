class Solution:

    def validPalindrome(self, s: str) -> bool:
        left = 0
        right = len(s) - 1

        while left < right:
            if s[left] == s[right]:
                left += 1
                right -= 1
                continue
            else:
                # delte left letter
                # isLeftPalindrome = self.checkPalindrome(s[left+1:right+1])

                # delete right letter
                # isRightPalindrome = self.checkPalindrome(s[left:right])

                return self.checkPalindrome(s[left+1:right+1]) or self.checkPalindrome(s[left:right])

        return True

    def checkPalindrome(self, s):
        left = 0
        right = len(s) - 1

        while left < right:
            if s[left] != s[right]:
                return False
            left += 1
            right -= 1

        return True