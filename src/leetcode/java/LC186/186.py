# Reference: LC151, https://leetcode.com/problems/reverse-words-in-a-string/solution/
class Solution:
    def reverseWords(self, s: List[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        def reverse(s: str, left: int, right: int) -> None:
            while left < right:
                s[left], s[right] = s[right], s[left]
                left += 1
                right -= 1

        reverse(s, 0, len(s)-1)

        left = 0
        for index, char in enumerate(s + [" "]):
            if char == " ":
                reverse(s, left, index-1)
                left = index + 1