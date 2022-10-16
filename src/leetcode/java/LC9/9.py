# Reference: https://leetcode.com/problems/palindrome-number/solution/
class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0 or (x % 10 == 0 and x != 0):
            return False

        reverted_number = 0
        while x > reverted_number:
            reverted_number = reverted_number * 10 + x % 10
            x = x // 10

        # print("===")
        # print(x)
        # print(reverted_number)

        return x == reverted_number or x == reverted_number // 10